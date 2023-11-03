package com.example.api.service;

import com.example.api.service.dto.viacep.ViaCep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ViaCepService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String URL_TEMPLATE = "https://viacep.com.br/ws/%s/json/";

    public ViaCep get(String cep) {
        log.info("Buscando informações do CEP: {}", cep);

        try {
            return restTemplate.getForObject(String.format(URL_TEMPLATE, cep), ViaCep.class);
        } catch (RestClientException e) {
            log.error("Erro ao buscar informações do CEP: {}", cep, e);
            return null;
        }
    }

}


