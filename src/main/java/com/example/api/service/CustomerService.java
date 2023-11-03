package com.example.api.service;

import com.example.api.domain.customer.Customer;
import com.example.api.service.dto.customer.DataCreateCustomer;
import com.example.api.service.dto.customer.DataListCustomer;
import com.example.api.service.dto.customer.DataUpdateCustomer;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    public Page<DataListCustomer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(DataListCustomer::new);
    }

    public Customer findOne(Long id) {
        return customerRepository.getReferenceById(id);
    }

    public Customer create(DataCreateCustomer data) {
        var customer = new Customer(data);

        customer.getAddresses().forEach(a -> {
            a.setCustomer(customer);
            if (a.getStreet().isBlank()
                || a.getNeighborhood().isBlank()
                || a.getCity().isBlank()
                || a.getState().isBlank()
            ) {
                addressService.loadByZipcode(a);
            }
        });

        customerRepository.save(customer);
        return customer;
    }

    public Customer update(DataUpdateCustomer data) {
        var customer = customerRepository.getReferenceById(data.id());

        addressRepository.deleteByCustomer(customer);

        customer.getAddresses().clear();
        data.addresses().forEach(a -> {
            if (a.getStreet().isBlank()
                || a.getNeighborhood().isBlank()
                || a.getCity().isBlank()
                || a.getState().isBlank()
            ) {
                addressService.loadByZipcode(a);
            }
            customer.addAddress(a);
        });

        customer.update(data);
        return customer;
    }

    public void delete(Long id) {
        var customer = customerRepository.getReferenceById(id);
        customerRepository.delete(customer);
    }
}
