package com.crawler.webcrawler.model.response;

import com.crawler.webcrawler.model.PageInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrawlReponse {
    Integer total_links = 0;
    Integer total_images = 0;

    List<PageInfo> details = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "total_links " + total_links +
//                "total_images " + total_images +
//                Arrays.toString(details.toArray());
//    }
}
