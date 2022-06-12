package org.carlos.bluetree.customer.customer.dto;


import org.carlos.bluetree.customer.customer.persistence.Customer;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;


@Builder
@Getter
public class GetCustomerResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public static Function<Customer, GetCustomerResponse> entityToDtoMapper() {
        return customer -> GetCustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();

    }
}
