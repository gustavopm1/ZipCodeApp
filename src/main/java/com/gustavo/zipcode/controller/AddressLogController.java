package com.gustavo.zipcode.controller;

import com.gustavo.zipcode.model.AddressLog;
import com.gustavo.zipcode.service.AddressLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/logs")
public class AddressLogController {

    private final AddressLogService addressLogService;

    @Autowired
    public AddressLogController(AddressLogService addressLogService) {
        this.addressLogService = addressLogService;
    }

    @GetMapping
    public Flux<AddressLog> getAllLogs() {
        return addressLogService.getAllLogs();
    }

    @GetMapping("/{cep}")
    public Flux<AddressLog> getLogsByCep(@PathVariable String cep) {
        return addressLogService.getLogsByCep(cep);
    }
}
