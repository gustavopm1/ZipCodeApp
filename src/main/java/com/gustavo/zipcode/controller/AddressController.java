package com.gustavo.zipcode.controller;

import com.gustavo.zipcode.model.Address;
import com.gustavo.zipcode.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AddressController {

    @Autowired
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{cep}")
    public Mono<Address> getZipCodeDetails(@PathVariable String cep) {
        return addressService.getAddressByCep(cep);
    }
}
