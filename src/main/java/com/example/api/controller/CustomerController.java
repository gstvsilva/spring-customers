package com.example.api.controller;

import com.example.api.domain.customer.dto.DataCreateCustomer;
import com.example.api.domain.customer.dto.DataListCustomer;
import com.example.api.domain.customer.dto.DataUpdateCustomer;
import com.example.api.domain.customer.dto.DataViewCustomer;
import com.example.api.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("customers")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<Page<DataListCustomer>> list(@PageableDefault(size = 20, sort = {"name"}) Pageable pageable) {
        var page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity view(@PathVariable Long id) {
        var customer = service.findOne(id);
        return ResponseEntity.ok(new DataViewCustomer(customer));
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DataCreateCustomer data, UriComponentsBuilder uriBuilder) {
        var customer = service.create(data);
        var uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataViewCustomer(customer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateCustomer data) {
        var customer = service.update(data);
        return ResponseEntity.ok(new DataViewCustomer(customer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
