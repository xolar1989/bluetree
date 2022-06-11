package com.example.client.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {

    @GetMapping("/api/v1/fraud-check/{customerId}")
    GetFraudResponse isFraudster(@PathVariable("customerId") int customerId);


}
