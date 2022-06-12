package org.carlos.bluetree.fraud.service;

import org.carlos.bluetree.fraud.persistence.FraudCheckHistory;
import org.carlos.bluetree.fraud.repository.FraudRepository;
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
