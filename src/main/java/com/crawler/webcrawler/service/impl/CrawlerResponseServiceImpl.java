package com.crawler.webcrawler.service.impl;

import com.crawler.webcrawler.library.DataHolder;
import com.crawler.webcrawler.model.response.CrawlReponse;
import com.crawler.webcrawler.service.CrawlerResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlerResponseServiceImpl implements CrawlerResponseService {
    @Autowired
    DataHolder dataHolder;

    @Override
    public CrawlReponse getCrawlResponse(int token){
        /***
         *  currently not persisting the response and keeping it in memory
         *  but as the response is quite big so will have to keep it in db
         */
        return dataHolder.getcrawlResponse(token);
    }

}
