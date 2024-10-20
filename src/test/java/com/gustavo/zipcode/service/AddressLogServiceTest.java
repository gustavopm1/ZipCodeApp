package com.gustavo.zipcode.service;

import com.gustavo.zipcode.model.Address;
import com.gustavo.zipcode.model.AddressLog;
import com.gustavo.zipcode.repository.AddressLogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddressLogServiceTest {

    @Mock
    private AddressLogRepository addressLogRepository;

    @InjectMocks
    private AddressLogService addressLogService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetAllLogs() {
        AddressLog log1 = new AddressLog();
        log1.setCep("12345678");
        log1.setLogradouro("Test 1");
        log1.setTimestamp(LocalDateTime.now());

        AddressLog log2 = new AddressLog();
        log2.setCep("87654321");
        log2.setLogradouro("Test 2");
        log2.setTimestamp(LocalDateTime.now());

        when(addressLogRepository.findAll()).thenReturn(List.of(log1, log2));

        Flux<AddressLog> logsFlux = addressLogService.getAllLogs();

        StepVerifier.create(logsFlux)
                .expectNextMatches(addressLog -> {
                    assertEquals("12345678", addressLog.getCep());
                    assertEquals("Test 1", addressLog.getLogradouro());
                    return true;
                })
                .expectNextMatches(addressLog2 -> {
                    assertEquals("87654321", addressLog2.getCep());
                    assertEquals("Test 2", addressLog2.getLogradouro());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void testGetLogsByCep() {

        AddressLog log1 = new AddressLog();
        log1.setCep("12345678");
        log1.setLogradouro("Test 1");
        log1.setTimestamp(LocalDateTime.now());

        AddressLog log2 = new AddressLog();
        log2.setCep("87654321");
        log2.setLogradouro("Test 2");
        log2.setTimestamp(LocalDateTime.now());

        AddressLog log3 = new AddressLog();
        log3.setCep("12345678");
        log3.setLogradouro("Test 3");
        log3.setTimestamp(LocalDateTime.now());

        when(addressLogRepository.findByCep("12345678")).thenReturn(List.of(log1, log3));

        // When
        Flux<AddressLog> logsFlux = addressLogService.getLogsByCep("12345678");

        // Then
        StepVerifier.create(logsFlux)
                .expectNextMatches(addressLog -> {
                    assertEquals("Test 1", addressLog.getLogradouro());
                    return true;
                })
                .expectNextMatches(addressLog -> {
                    assertEquals("Test 3", addressLog.getLogradouro());
                    return true;
                })
                .verifyComplete();
    }

    @Test
    public void testLogAddress() {

        AddressLog log = new AddressLog();
        log.setCep("12345678");
        log.setLogradouro("Test Street");

        addressLogService.logAddress("12345678", new Address());

        verify(addressLogRepository, times(1)).save(any(AddressLog.class));
    }
}