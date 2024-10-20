package com.gustavo.zipcode.service;

import com.gustavo.zipcode.exception.AddressNotFoundException;
import com.gustavo.zipcode.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AddressService {


    private final WebClient webClient;
    private final AddressLogService addressLogService;

    // Constructor injection for WebClient and AddressLogService
    @Autowired
    public AddressService(WebClient.Builder webClientBuilder, AddressLogService addressLogService, @Value("${cep.baseUrl}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build(); // Default base URL
        this.addressLogService = addressLogService;
    }

    public Mono<Address> getAddressByCep(String cep) {

        return this.webClient.get()
                .uri("/{cep}", cep)// Append the CEP to the URI
                .retrieve() // Retrieve the response
                .onStatus(status -> status.value() == 404, response ->
                        Mono.error(new AddressNotFoundException("CEP not found: " + cep)))
                .bodyToMono(Address.class)  // Map the response body to the Address model class
                .doOnNext(address -> addressLogService.logAddress(cep, address));

    }

}