package com.crawler.webcrawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class InputObject {
    String url;
    Integer depth;


//    public InputObject(String url, Integer depth){
//        this.url = url;
//        this.depth = depth;
//    }
}
