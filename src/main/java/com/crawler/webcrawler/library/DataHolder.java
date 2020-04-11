package com.crawler.webcrawler.library;

import com.crawler.webcrawler.model.InputObject;
import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.model.response.CrawlReponse;
import org.springframework.stereotype.Component;


@Component
public interface DataHolder {
    public void addCrawlRequest(Integer token, InputObject inputObject);

    public InputObject getCrawlRequest(Integer token);

    public void updateRequestStatus(Integer token, RequestStatus requestStatus);

    public RequestStatus getRequestStatus(Integer token);

    public void addCrawlResponse(Integer token, CrawlReponse crawlReponse);

    public CrawlReponse getcrawlResponse(Integer token);

}
