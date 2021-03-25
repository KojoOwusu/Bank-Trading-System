package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Portfolio;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio getOne(Long aLong);

    List<Portfolio> findAllById(Iterable<Long> iterable);

    List<Portfolio> findAll();
}
