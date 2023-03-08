package com.circuitBreakerSample.bookStoreSample.service;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090").build();
    private final ReactiveCircuitBreaker readingListCircuitBreaker;

    public BookService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.readingListCircuitBreaker = circuitBreakerFactory.create("recommended");
    }

    public Mono<String> readingList() {
        return readingListCircuitBreaker.run(webClient.get().uri("/recommended").retrieve().bodyToMono(String.class), throwable -> {
            LOG.warn("Error making request to book service", throwable);
            return Mono.just("Cloud Native Java (O'Reilly)");
        });
    }

}
