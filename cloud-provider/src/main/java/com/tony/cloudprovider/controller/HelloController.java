package com.tony.cloudprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        return "i am provider, " + name + " say hello!!!  prot:" + port;
    }
}
