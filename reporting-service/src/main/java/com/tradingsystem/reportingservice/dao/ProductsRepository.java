package com.tradingsystem.reportingservice.dao;

import com.tradingsystem.reportingservice.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findByTicker(String ticker);
}
