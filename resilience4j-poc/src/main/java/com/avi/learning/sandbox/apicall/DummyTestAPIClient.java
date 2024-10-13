package com.avi.learning.sandbox.apicall;

import com.avi.learning.sandbox.model.DummyResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DummyTestAPIClient {

    @Autowired
    private RestTemplate restTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @CircuitBreaker(name = "CircuitBreakerService")
    @Retry(name = "CircuitBreakerService", fallbackMethod = "callAPIFallback")
    public String callAPI() throws InterruptedException {
        int n = atomicInteger.incrementAndGet();
        if (n % 3 == 0) {
            throw new RuntimeException("Dummy");
        }
        if (n % 2 == 0) {
            Thread.sleep(1100);
        }

        DummyResponse response = restTemplate.getForObject("/asd/test/12", DummyResponse.class);
        return Objects.nonNull(response) ? response.getStatus() : "Error";
    }

    public String callAPIFallback(Exception ex) {
        return "Retried: " + ex.getMessage();
    }

    @TimeLimiter(name = "userConfigs")
    @CircuitBreaker(name = "userConfigs")
    @Retry(name = "userConfigs", fallbackMethod = "callAPIFallback")
    public CompletionStage<String> generateConfiguration() throws InterruptedException {
        int n = atomicInteger.incrementAndGet();
        if (n % 3 == 0) {
            throw new RuntimeException("Dummy");
        }
        if (n % 2 == 0) {
            Thread.sleep(1100);
        }
        return CompletableFuture.supplyAsync(() -> "1").thenApply(userConfigurations -> "2");
    }
}
