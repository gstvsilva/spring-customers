package com.example.api.service.dto.customer;

import com.example.api.domain.address.Address;
import com.example.api.domain.customer.Customer;

import java.util.List;

public record DataViewCustomer(Long id, String name, String email, List<Address> addresses) {

    public DataViewCustomer(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getAddresses());
    }
}
