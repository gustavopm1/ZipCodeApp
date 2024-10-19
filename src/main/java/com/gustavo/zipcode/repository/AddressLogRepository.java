package com.gustavo.zipcode.repository;

import com.gustavo.zipcode.model.AddressLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressLogRepository extends JpaRepository<AddressLog, Long> {

    List<AddressLog> findByCep(String cep);

}
