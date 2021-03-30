package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getOne(Long aLong);

    List<Client> findAll();

    @Modifying
    @Query("update Client c set c.funds = :amount  where c.clientid = :clientid")
    int updateClientFunds(@Param("clientid") long clientid, @Param("amount") Double amount);

}
