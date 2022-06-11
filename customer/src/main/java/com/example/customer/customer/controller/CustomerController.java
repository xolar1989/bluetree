package com.example.customer.customer.controller;


import com.example.customer.customer.dto.CreateCustomerRequest;
import com.example.customer.customer.dto.GetCustomerResponse;
import com.example.customer.customer.dto.GetCustomersResponse;
import com.example.customer.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
record CustomerController(CustomerService customerService) {

    @PostMapping
    public ResponseEntity<Void> registerCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        log.info("new customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        return ResponseEntity.ok(
                GetCustomersResponse.entityToDtoMapper().apply(customerService
                        .getAllCustomers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponse> getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(GetCustomerResponse
                        .entityToDtoMapper().apply(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
