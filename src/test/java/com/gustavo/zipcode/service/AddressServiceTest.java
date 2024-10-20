package com.gustavo.zipcode.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.gustavo.zipcode.exception.AddressNotFoundException;
import com.gustavo.zipcode.model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;


@ActiveProfiles("test")
@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressLogService addressLogService;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8081);
        wireMockServer.start();

        addressService = new AddressService(WebClient.builder(), addressLogService, "http://localhost:8081");

        configureFor("localhost", 8081);
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetAddressByCep_Success() {
        stubFor(get(urlEqualTo("/12345678"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"cep\":\"12345-678\",\"logradouro\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\"}")
                        .withStatus(200)));


        Mono<Address> addressMono = addressService.getAddressByCep("12345678");

        StepVerifier.create(addressMono)
                .expectNextMatches(address -> {
                    assertNotNull(address);
                    assertEquals("12345-678", address.getCep());
                    assertEquals("Praça da Sé", address.getLogradouro());
                    assertEquals("lado ímpar", address.getComplemento());
                    assertEquals("Sé", address.getBairro());
                    assertEquals("São Paulo", address.getLocalidade());
                    assertEquals("SP", address.getUf());
                    assertEquals("3550308", address.getIbge());
                    return true;
                })
                .verifyComplete();


        Mockito.verify(addressLogService).logAddress(eq("12345678"), Mockito.any(Address.class));
    }

    @Test
    public void testGetAddressByCep_NotFound() {
        stubFor(get(urlEqualTo("/v1/00000000"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("CEP not found")));

        Mono<Address> addressMono = addressService.getAddressByCep("00000000");

        StepVerifier.create(addressMono)
                .expectError(AddressNotFoundException.class)
                .verify();
    }
}
