package com.neuymin.microservices.limitsservice.controller;

import com.neuymin.microservices.limitsservice.beans.Limits;
import com.neuymin.microservices.limitsservice.configuration.Configuration;
import org.apache.hc.client5.http.config.Configurable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMin(), configuration.getMax());
    }

}
