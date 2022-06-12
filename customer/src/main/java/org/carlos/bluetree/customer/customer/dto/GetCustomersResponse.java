package org.carlos.bluetree.customer.customer.dto;

import org.carlos.bluetree.customer.customer.persistence.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Builder
public class GetCustomersResponse {

    @Singular
    List<GetCustomerResponse> customers;

    public static Function<Collection<Customer>, GetCustomersResponse> entityToDtoMapper() {
        return customersVar -> GetCustomersResponse.builder()
                .customers(customersVar.stream()
                        .map(customer -> GetCustomerResponse
                                .entityToDtoMapper().apply(customer))
                        .collect(Collectors.toList()))
                .build();
    }
}
