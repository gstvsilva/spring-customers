package com.example.api.domain.customer.dto;

import com.example.api.domain.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataUpdateCustomer(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotEmpty
        List<Address> addresses) {
}
