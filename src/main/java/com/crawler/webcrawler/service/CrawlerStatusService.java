package com.crawler.webcrawler.service;

import com.crawler.webcrawler.model.enums.RequestStatus;

public interface CrawlerStatusService {

    public RequestStatus getStatus(int token);
}
