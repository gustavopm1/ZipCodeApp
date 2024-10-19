package com.gustavo.zipcode.service;

import com.gustavo.zipcode.model.Address;
import com.gustavo.zipcode.model.AddressLog;
import com.gustavo.zipcode.repository.AddressLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class AddressLogService {

    private final AddressLogRepository addressLogRepository;

    @Autowired
    public AddressLogService(AddressLogRepository addressLogRepository) {
        this.addressLogRepository = addressLogRepository;
    }

    public Flux<AddressLog> getAllLogs() {
        return Flux.fromIterable(addressLogRepository.findAll());
    }

    public Flux<AddressLog> getLogsByCep(String cep) {
        return Flux.fromIterable(addressLogRepository.findByCep(cep));
    }

    public void logAddress(String cep, Address address) {
        AddressLog addressLog = new AddressLog();
        addressLog.setCep(cep);
        addressLog.setLogradouro(address.getLogradouro());
        addressLog.setComplemento(address.getComplemento());
        addressLog.setUnidade(address.getUnidade());
        addressLog.setBairro(address.getBairro());
        addressLog.setLocalidade(address.getLocalidade());
        addressLog.setUf(address.getUf());
        addressLog.setIbge(address.getIbge());
        addressLog.setTimestamp(LocalDateTime.now());

        addressLogRepository.save(addressLog);
    }
}
