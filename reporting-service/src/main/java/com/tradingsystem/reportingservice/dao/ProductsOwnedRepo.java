package com.tradingsystem.reportingservice.dao;


import com.tradingsystem.reportingservice.dto.Client;
import com.tradingsystem.reportingservice.dto.Portfolio;
import com.tradingsystem.reportingservice.dto.Product;
import com.tradingsystem.reportingservice.dto.ProductsOwned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsOwnedRepo extends JpaRepository<ProductsOwned, Long> {

    List<ProductsOwned> findAllByClient(Client client);

    @Modifying
    @Query("update ProductsOwned p set p.quantityOwned = :quantity where p.client=:client AND p.product=:product")
    int updateOrderStatus(@Param("quantity") int quantity, @Param("client") Client client, @Param("product") Product product);
}
