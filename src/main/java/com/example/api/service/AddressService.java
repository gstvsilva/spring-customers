package com.example.api.service;

import com.example.api.domain.address.Address;
import com.example.api.service.dto.ViaCep;
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
        if (viaCep != null && viaCep.getCep() != null) {
            address.setStreet(viaCep.getLogradouro());
            address.setState(viaCep.getUf());
            address.setCity(viaCep.getLocalidade());
            address.setNeighborhood(viaCep.getBairro());
            address.setCountry("Brasil");
        }
    }


}
