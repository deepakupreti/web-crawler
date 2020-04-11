//package com.crawler.webcrawler.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.RequestHandlerKey;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//@Configuration
//@EnableSwagger2
//public class swaggerConfig {
//
//    // return instance of Docket
//    @Bean
//    @Profile("dev")
//    public Docket config(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
////                .paths(PathSelectors.regex("/order.*"))
//                .apis(RequestHandlerSelectors.basePackage("com.crawler.webcrawler.controller"))
//                .build()
//                .apiInfo(getMetaData());
//    }
//
//
//    @Bean
//    @Profile("default")
//    public Docket config_default(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(false)
//                .select()
//                .build();
//
//    }
//    ApiInfo getMetaData(){
//        return new ApiInfo(
//                "Restaurant-Service",
//                "Rest Api Documentation",
//                "1.0",
//                null,
//                null,null,
//                null,
//                Collections.emptyList());
//    }
//
//}
