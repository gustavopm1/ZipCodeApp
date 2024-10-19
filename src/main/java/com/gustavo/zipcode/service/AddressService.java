package com.gustavo.zipcode.service;

import com.gustavo.zipcode.exception.AddressNotFoundException;
import com.gustavo.zipcode.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class AddressService {

    private final WebClient webClient;

    // Constructor injection for WebClient with the base URL of the external API
    public AddressService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://opencep.com/v1").build();
    }

    public Mono<Address> getAddressByCep(String cep) {

        return this.webClient.get()
                .uri("/{cep}", cep)// Append the CEP to the URI
                .retrieve() // Retrieve the response
                .onStatus(status -> status.value() == 404, response ->
                        Mono.error(new AddressNotFoundException("CEP not found: " + cep)))
                .bodyToMono(Address.class);  // Map the response body to the Address model class

    }

}