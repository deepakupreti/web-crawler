package com.crawler.webcrawler.service.impl;

import com.crawler.webcrawler.library.DataHolder;
import com.crawler.webcrawler.model.InputObject;
import com.crawler.webcrawler.model.PageInfo;
import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.model.response.CrawlReponse;
import com.crawler.webcrawler.service.CrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    DataHolder dataHolder;

    @Autowired
    @Qualifier("threadPoolTaskExcecutor")
    private TaskExecutor taskExecutor;

    @Autowired
    @Qualifier("internalThreadPoolTaskExcecutor")
    private TaskExecutor taskExecutorInternal;

    AtomicInteger atomicInteger;

    public CrawlerServiceImpl() {
        atomicInteger = new AtomicInteger();
    }

    @Override
    public int crawlPages(String url, int maxDepth) {
        int key = atomicInteger.incrementAndGet();
        dataHolder.addCrawlRequest(key, new InputObject(url, maxDepth));
        dataHolder.updateRequestStatus(key, RequestStatus.SUBMITTED);

        CrawlReponse crawlReponse = new CrawlReponse();

        taskExecutor.execute(() -> {
            long startTime = System.currentTimeMillis();
            log.info("main_name " + java.lang.Thread.currentThread().getName());
            dataHolder.updateRequestStatus(key, RequestStatus.IN_PROCESS);
            getPageLinks(url, 0, maxDepth, new ConcurrentHashMap<String, Boolean>(), crawlReponse);
            dataHolder.addCrawlResponse(key, crawlReponse);
            dataHolder.updateRequestStatus(key, RequestStatus.PROCESSED);
            long endTime = System.currentTimeMillis();
            log.info("crawling took {} ms ", endTime - startTime);
        });
        return key;
    }

    @Override
    public void setCountDetails(String pageTitle, String pageLink, Integer imageCount, CrawlReponse crawlReponse) {
        crawlReponse.getDetails().add(new PageInfo(pageTitle, pageLink, imageCount));
        crawlReponse.setTotal_images(crawlReponse.getTotal_images() + imageCount);
        crawlReponse.setTotal_links(crawlReponse.getTotal_links() + 1);
    }

    @Override
    public void getPageLinks(String URL, int curDepth, int MAX_DEPTH, ConcurrentHashMap<String, Boolean> links, CrawlReponse crawlReponse) {
        if ((!links.contains(URL) && (curDepth < MAX_DEPTH))) {
            try {
                links.put(URL, true);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                Elements img = document.getElementsByTag("img");
                String pageTitle = document.title();
                String pageLink = URL;
                Integer imageCount = img.size();

                setCountDetails(pageTitle, pageLink, imageCount, crawlReponse);

                int upatedDepth = curDepth + 1;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), upatedDepth, MAX_DEPTH, links, crawlReponse);
                }

            } catch (IOException e) {
                log.error( "error while crawling {} url , error : {}",URL, e.getMessage());
            }
        }

    }
}
