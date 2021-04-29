package com.tony.cloudconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider")
public interface ProviderClient {

    @GetMapping("/hello/hello")
    String hello(@RequestParam(value = "name",required = false) String name);

}


