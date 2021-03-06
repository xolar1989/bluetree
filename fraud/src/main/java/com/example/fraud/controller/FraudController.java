package com.example.fraud.controller;

import com.example.client.fraud.GetFraudResponse;
import com.example.fraud.service.FraudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
public record FraudController(FraudService fraudService) {

    @GetMapping("/{customerId}")
    public GetFraudResponse isFraudster(@PathVariable int customerId) {

        return new GetFraudResponse(fraudService
                .isFraudulentCustomer(customerId));

    }
}
