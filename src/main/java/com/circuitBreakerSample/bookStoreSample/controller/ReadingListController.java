package com.circuitBreakerSample.bookStoreSample.controller;

import com.circuitBreakerSample.bookStoreSample.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReadingListController {
    private BookService service;

    @RequestMapping(value = "/to-read")
    public Mono<String> toRead() {
        return service.readingList();
    }
}
