package com.circuitBreakerSamples.bookStoreSample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BookStoreController {

    @RequestMapping(value = "/recommended")
    public Mono<String> readingList() {
        return Mono.just("Spring in Action, Cloud Native Java, Learning Spring Boot");
    }
}
