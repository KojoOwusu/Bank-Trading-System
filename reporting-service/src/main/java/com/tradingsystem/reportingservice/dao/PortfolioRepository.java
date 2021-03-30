package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Client;
import com.tradingsystem.reportingservice.dto.Portfolio;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio getOne(Long aLong);

    List<Portfolio> findAllById(Iterable<Long> iterable);

    List<Portfolio> findAll();

    @Query(value="select client from Portfolio where portfolioid=:id")
    Client findClientID(@Param("id") long id);
}
