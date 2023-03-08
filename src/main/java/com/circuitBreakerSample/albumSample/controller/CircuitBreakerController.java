package com.circuitBreakerSample.albumSample.controller;

import com.circuitBreakerSample.albumSample.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    @Autowired
    private CircuitBreakerService service;

    @GetMapping("/albums")
    public String albums() {
        return service.getAlbumList();
    }
}
