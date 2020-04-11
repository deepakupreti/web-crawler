package com.crawler.webcrawler.library.impl;

import com.crawler.webcrawler.library.DataHolder;
import com.crawler.webcrawler.model.InputObject;
import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.model.response.CrawlReponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryDataHolder implements DataHolder {

    private ConcurrentHashMap<Integer, InputObject> requestMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, RequestStatus> requestStatusMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, CrawlReponse> responseMap = new ConcurrentHashMap<>();


    @Override
    public void addCrawlRequest(Integer token, InputObject inputObject) {
        requestMap.putIfAbsent(token, inputObject);
    }

    @Override
    public InputObject getCrawlRequest(Integer token) {
        return requestMap.get(token);
    }

    public void updateRequestStatus(Integer token, RequestStatus requestStatus) {
        requestStatusMap.put(token, requestStatus);
    }

    public RequestStatus getRequestStatus(Integer token) {
        return requestStatusMap.getOrDefault(token, RequestStatus.NO_SUCH_REQUEST);
    }

    public void addCrawlResponse(Integer token, CrawlReponse crawlReponse) {
        responseMap.put(token, crawlReponse);
    }

    public CrawlReponse getcrawlResponse(Integer token) {
        return responseMap.getOrDefault(token, new CrawlReponse());
    }

}
