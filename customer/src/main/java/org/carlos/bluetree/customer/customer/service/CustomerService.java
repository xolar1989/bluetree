package org.carlos.bluetree.customer.customer.service;

import org.carlos.bluetree.amqp.RabbitMQMessageProducer;
import org.carlos.bluetree.client.fraud.FraudClient;
import org.carlos.bluetree.client.fraud.GetFraudResponse;
import org.carlos.bluetree.client.notification.NotificationRequest;
import org.carlos.bluetree.customer.configuration.rabbitmq.NotificationMq;
import org.carlos.bluetree.customer.customer.dto.CreateCustomerRequest;
import org.carlos.bluetree.customer.customer.persistence.Customer;
import org.carlos.bluetree.customer.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService{

    private final CustomerRepository customerRepository;
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
