package com.example.api.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataCreateCustomer> dataCreateCustomerJson;

    @Autowired
    private JacksonTester<DataUpdateCustomer> dataUpdateCustomerJson;

    @Test
    @DisplayName("must return http code 400 when information is invalid")
    @WithMockUser
    void create_scenario1() throws Exception {
        var response = mvc.perform(post("/customers"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("must return http code 201 when information is valid")
    @WithMockUser
    void create_scenario2() throws Exception {
        var addresses = mountAddresses();
        var response = mvc
                .perform(
                    post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dataCreateCustomerJson.write(
                            new DataCreateCustomer("gustavo", "test@gmail.com", addresses.stream().toList())
                    ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("must return http code 400 when information is invalid")
    @WithMockUser
    void update_scenario1() throws Exception {
        var response = mvc.perform(put("/customers"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("must return http code 404 when information is not found")
    @WithMockUser
    void update_scenario2() throws Exception {
        var addresses = mountAddresses();
        var response = mvc
                .perform(
                    put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataUpdateCustomerJson.write(
                            new DataUpdateCustomer(1l, "gustavo", "test@gmail.com", addresses.stream().toList())
                        ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("must return http code 204")
    @WithMockUser
    void delete_scenario1() throws Exception {
        var response = mvc.perform(delete("/customers/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private ArrayList<Address> mountAddresses() {
        var address = new Address();
        address.setStreet("");
        address.setNumeral("1");
        address.setZipcode("80320000");

        var addresses = new ArrayList<Address>();
        addresses.add(address);

        return addresses;
    }
}