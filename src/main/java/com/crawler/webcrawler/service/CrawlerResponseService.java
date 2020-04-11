package com.crawler.webcrawler.service;

import com.crawler.webcrawler.model.response.CrawlReponse;

public interface CrawlerResponseService {

    public CrawlReponse getCrawlResponse(int token);
}
