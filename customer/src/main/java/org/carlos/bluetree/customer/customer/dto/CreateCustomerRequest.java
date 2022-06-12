package org.carlos.bluetree.customer.customer.dto;

import org.carlos.bluetree.customer.customer.persistence.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
public class CreateCustomerRequest{

    private String firstName;
    private String lastName;
    private String email;

    public static Function<CreateCustomerRequest, Customer> dtoToEntityMapper() {
        return request -> Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

}
