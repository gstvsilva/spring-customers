package com.example.api.service;

import com.example.api.domain.address.Address;
import com.example.api.domain.customer.dto.DataCreateCustomer;
import com.example.api.domain.customer.dto.DataUpdateCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AddressServiceTest {

    @Autowired
    private AddressService service;

    @Test
    @DisplayName("must do nothing when zipcode is empty")
    @WithMockUser
    void create_scenario1() {
        var address = new Address();
        address.setZipcode("");

        service.loadByZipcode(address);
        assertThat(address.getStreet()).isEqualTo(null);
    }

    @Test
    @DisplayName("must set address when zipcode is not empty")
    @WithMockUser
    void create_scenario2() {
        var address = new Address();
        address.setZipcode("80320000");

        service.loadByZipcode(address);
        assertThat(address.getStreet()).isEqualTo("Rua Coronel Ottoni Maciel");
    }

}