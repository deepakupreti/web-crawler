package com.crawler.webcrawler;

import com.crawler.webcrawler.library.DataHolder;
import com.crawler.webcrawler.model.PageInfo;
import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.model.response.CrawlReponse;
import com.crawler.webcrawler.service.CrawlerResponseService;
import com.crawler.webcrawler.service.impl.CrawlerResponseServiceImpl;
import com.crawler.webcrawler.service.impl.CrawlerStatusServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
@ActiveProfiles("test")
public class WebCrawlerApplicationTests {

    @Mock
    DataHolder dataHolder;

    @InjectMocks
    private CrawlerStatusServiceImpl crawlerStatusServiceImpl;

    @InjectMocks
    CrawlerResponseServiceImpl crawlerResponseServiceImpl;


    @Test
    public void getStatus() throws Exception {
//        Mockito.when(dataHolder.getRequestStatus(1)).thenReturn(RequestStatus.SUBMITTED);

        Mockito.doReturn(RequestStatus.SUBMITTED).when(dataHolder).getRequestStatus(Mockito.anyInt());
        Assert.assertEquals(crawlerStatusServiceImpl.getStatus(1), RequestStatus.SUBMITTED);
    }


    @Test
    public void getResponse() throws Exception {
//        Mockito.when(dataHolder.getRequestStatus(1)).thenReturn(RequestStatus.SUBMITTED);
         List<PageInfo> pageInfos = Arrays.asList(new PageInfo("google homepage ", "http://www.google.com", 4)
                , new PageInfo("gmail homepage ", "http://www.gmail.com", 3)
                , new PageInfo("google drive homepage ", "http://www.googleDrive.com", 1));


        CrawlReponse crawlReponse = new CrawlReponse(3, 8,pageInfos);
        Mockito.doReturn(crawlReponse).when(dataHolder).getcrawlResponse(Mockito.anyInt());

        crawlerResponseServiceImpl.getCrawlResponse(1);

        Assert.assertEquals(crawlerResponseServiceImpl.getCrawlResponse(1), crawlReponse);
    }


}
