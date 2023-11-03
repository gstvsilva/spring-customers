package com.example.api.service.dto.customer;

import com.example.api.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DataCreateCustomer(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotEmpty
        List<Address> addresses) {
}
