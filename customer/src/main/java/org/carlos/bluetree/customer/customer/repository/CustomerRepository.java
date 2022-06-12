package org.carlos.bluetree.customer.customer.repository;

import org.carlos.bluetree.customer.customer.persistence.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
