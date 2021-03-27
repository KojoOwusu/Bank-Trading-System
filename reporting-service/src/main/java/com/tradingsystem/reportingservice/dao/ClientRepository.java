package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Query("update Client c set c.funds = :amount  where clientid = :clientid")
    int updateClientFunds(@Param("clientid") Long clientid, @Param("amount") Double amount);

}
