package com.example.api.service;

import com.example.api.domain.address.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AddressServiceTest {

    @Autowired
    private AddressService service;

    @Test
    @DisplayName("must do nothing when zipcode is empty")
    @WithMockUser
    void loadByZipcode_scenario1() {
        var address = new Address();
        address.setZipcode("");

        service.loadByZipcode(address);
        assertThat(address.getStreet()).isEqualTo(null);
    }

    @Test
    @DisplayName("must set address when zipcode is not empty")
    @WithMockUser
    void loadByZipcode_scenario2() {
        var address = new Address();
        address.setZipcode("80320000");

        service.loadByZipcode(address);
        assertThat(address.getStreet()).isEqualTo("Rua Coronel Ottoni Maciel");
    }

}