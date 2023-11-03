package com.example.api.domain.customer;

import com.example.api.domain.address.Address;
import com.example.api.domain.customer.dto.DataCreateCustomer;
import com.example.api.domain.customer.dto.DataUpdateCustomer;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "customers")
@Entity(name = "Customer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @Valid
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;

    public Customer(DataCreateCustomer data) {
        this.name = data.name();
        this.email = data.email();
        this.addresses = data.addresses();
    }

    public void update(DataUpdateCustomer data) {
        this.name = data.name();
        this.email = data.email();
        this.addresses = data.addresses();
    }

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        address.setCustomer(this);
        addresses.add(address);
    }
}
