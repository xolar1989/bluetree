package com.example.fraud.service;

import com.example.fraud.persistence.FraudCheckHistory;
import com.example.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public record FraudService(FraudRepository fraudRepository) {


    public boolean isFraudulentCustomer(Integer customerId){
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
