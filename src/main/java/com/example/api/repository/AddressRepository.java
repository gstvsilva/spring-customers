package com.example.api.repository;

import com.example.api.domain.address.Address;
import com.example.api.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    void deleteByCustomer(Customer customer);
}
