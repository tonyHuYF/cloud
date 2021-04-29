package com.tony.cloudconsumer.controller;

import com.tony.cloudconsumer.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/feign-hello")
    public String hello(String name) {
        return providerClient.hello(name);
    }

}
