package com.crawler.webcrawler.service;

import com.crawler.webcrawler.model.response.CrawlReponse;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public interface CrawlerService {

    public int crawlPages(String url, int maxDepth);
    void setCountDetails(String pageTitle, String pageLink, Integer imageCount, CrawlReponse crawlReponse);
    public void getPageLinks(String URL, int curDepth, int MAX_DEPTH, ConcurrentHashMap<String,Boolean> concurrentHashMap, CrawlReponse crawlReponse);
}
