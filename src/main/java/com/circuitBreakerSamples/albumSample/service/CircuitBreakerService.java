package com.circuitBreakerSamples.albumSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBreakerService {
    private RestTemplate restTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public String getAlbumList() {
        System.out.println("CircuitBreakerService => getAlbumList");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String baseUrl = "https://jsonplaceholder.typicode.com/albums";
        return circuitBreaker.run(() -> restTemplate.getForObject(baseUrl, String.class),
                throwable -> getDefaultAlbumList());
    }

    private String getDefaultAlbumList() {
        System.out.println("CircuitBreakerService => getDefaultAlbumList");
        return "getDefaultAlbumList";
    }

}
