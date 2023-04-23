package com.neuymin.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    // 10 sec -> 10000 calls to the same api
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi() {
        logger.info("Sample API call received");
//        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/wrong-url", String.class);
//        return responseEntity.getBody();
        return "sample-api";
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }

}
