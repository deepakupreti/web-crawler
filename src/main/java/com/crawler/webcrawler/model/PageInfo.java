package com.crawler.webcrawler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class PageInfo {
    String page_title;
    String page_link;
    Integer image_count;

//    @Override
//    public String toString(){
//        return "page_title "+page_title
//                +"page_link "+ page_link
//                + "image_count "+image_count;
//    }
}
