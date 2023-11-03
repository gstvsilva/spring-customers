package com.example.api.service;

import com.example.api.domain.address.Address;
import com.example.api.service.dto.viacep.ViaCep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepService viaCepService;

    public void loadByZipcode(Address address) {
        if (address.getZipcode() == null) {
            return;
        }

        ViaCep viaCep = viaCepService.get(address.getZipcode());
        if (viaCep != null && viaCep.cep() != null) {
            address.setStreet(viaCep.logradouro());
            address.setState(viaCep.uf());
            address.setCity(viaCep.localidade());
            address.setNeighborhood(viaCep.bairro());
            address.setCountry("Brasil");
        }
    }


}
