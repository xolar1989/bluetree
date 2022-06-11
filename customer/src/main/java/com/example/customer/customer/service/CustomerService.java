package com.example.customer.customer.service;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.client.fraud.FraudClient;
import com.example.client.fraud.GetFraudResponse;
import com.example.client.notification.NotificationClient;
import com.example.client.notification.NotificationRequest;
import com.example.customer.configuration.rabbitmq.NotificationMq;
import com.example.customer.customer.dto.CreateCustomerRequest;
import com.example.customer.customer.persistence.Customer;
import com.example.customer.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService{

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final NotificationMq notificationMq;

    public void registerCustomer(CreateCustomerRequest customerRequest) {
        Customer customer = CreateCustomerRequest
                .dtoToEntityMapper()
                .apply(customerRequest);
        if (customerRepository.findAll().stream()
                .anyMatch(customerEntity -> customerEntity.equals(customer))) {
            throw new IllegalArgumentException("sss");
        }

        customerRepository.saveAndFlush(customer);


        GetFraudResponse fraudResponse = fraudClient
                .isFraudster(customer.getId());

        log.info("ddd {}", fraudResponse);

        if (fraudResponse.isFraudster()) {
            throw new IllegalArgumentException("fraudster");
        }
        var notification = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notification,
                "internal.exchange",
                "internal.notification.routing-key"
        );


    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
}
