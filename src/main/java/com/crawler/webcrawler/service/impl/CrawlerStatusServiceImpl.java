package com.crawler.webcrawler.service.impl;

import com.crawler.webcrawler.library.DataHolder;
import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.service.CrawlerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerStatusServiceImpl implements CrawlerStatusService {

    @Autowired
    DataHolder dataHolder;

    public RequestStatus getStatus(int token){
           return dataHolder.getRequestStatus(token);
    }
}
