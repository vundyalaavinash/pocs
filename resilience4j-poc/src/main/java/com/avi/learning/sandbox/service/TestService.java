package com.avi.learning.sandbox.service;

import com.avi.learning.sandbox.apicall.DummyTestAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class TestService {

    @Autowired
    private DummyTestAPIClient client;

    public String getStatus() throws InterruptedException {
        return client.callAPI();
    }

    public String generateConfiguration() throws ExecutionException, InterruptedException {
        return client.generateConfiguration().toCompletableFuture().get();
    }
}
