package com.crawler.webcrawler.controller;

import com.crawler.webcrawler.model.enums.RequestStatus;
import com.crawler.webcrawler.model.response.CrawlReponse;
import com.crawler.webcrawler.service.impl.CrawlerResponseServiceImpl;
import com.crawler.webcrawler.service.impl.CrawlerStatusServiceImpl;
import com.crawler.webcrawler.service.impl.CrawlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;


@RestController
@Validated
@RequestMapping("/")
public class CrawlerController {

    private final CrawlerServiceImpl crawlerServiceImpl;
    private final CrawlerResponseServiceImpl crawlerResponseServiceImpl;
    private final CrawlerStatusServiceImpl crawlerStatusServiceImpl;

    @Autowired
    public CrawlerController(CrawlerServiceImpl crawlerServiceImpl,
                             CrawlerResponseServiceImpl crawlerResponseServiceImpl,
                             CrawlerStatusServiceImpl crawlerStatusServiceImpl) {
        this.crawlerServiceImpl = crawlerServiceImpl;
        this.crawlerResponseServiceImpl = crawlerResponseServiceImpl;
        this.crawlerStatusServiceImpl = crawlerStatusServiceImpl;

    }



    @GetMapping(value = "/crawl", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> crawl(
            @RequestParam(required = true) String url,
            @RequestParam
            @Max(value=4, message="Depth cannot exceed 4 ") Integer depth) {
        return new ResponseEntity<>(
                crawlerServiceImpl.crawlPages(url, depth),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/getResult", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CrawlReponse> getCrawlResponse(
            @RequestParam(required = true) Integer token) {
        return new ResponseEntity<>(
                crawlerResponseServiceImpl.getCrawlResponse(token),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/getStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestStatus> getRequestStatus(
            @RequestParam(required = true) Integer token) {
        return new ResponseEntity<>(
                crawlerStatusServiceImpl.getStatus(token),
                HttpStatus.OK
        );
    }
}
