package com.example.api.domain.customer.dto;

import com.example.api.domain.customer.Customer;

public record DataListCustomer(Long id, String nome, String email) {

    public DataListCustomer(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail());
    }

}
