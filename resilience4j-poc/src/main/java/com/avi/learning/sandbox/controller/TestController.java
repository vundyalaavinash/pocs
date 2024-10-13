package com.avi.learning.sandbox.controller;


import com.avi.learning.sandbox.model.Response;
import com.avi.learning.sandbox.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public Response<String> getString() throws InterruptedException {
        return new Response<>(testService.getStatus());
    }

    @GetMapping(value = "/t2")
    public Response<String> getString2() throws InterruptedException, ExecutionException {
        return new Response<>(testService.generateConfiguration());
    }
}
