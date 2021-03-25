package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getOne(Long aLong);

    List<Client> findAll();
}
