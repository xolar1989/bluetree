package com.example.fraud.repository;

import com.example.fraud.persistence.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<FraudCheckHistory,Integer> {
}
