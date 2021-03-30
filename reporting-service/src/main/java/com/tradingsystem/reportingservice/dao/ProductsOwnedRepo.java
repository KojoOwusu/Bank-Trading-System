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

    List<ProductsOwned> findAll();

    @Query(value = "select p.quantityOwned from ProductsOwned p where p.client=:client AND p.product=:product")
    int findQuantityOwned(@Param("client") Client client, @Param("product") Product product);

    @Modifying
    @Query("update ProductsOwned p set p.quantityOwned = :quantity where p.client=:client AND p.product=:product")
    int updateQuantity(@Param("quantity") int quantity, @Param("client") Client client, @Param("product") Product product);

    @Query(value="select p.product from ProductsOwned p where p.product=:product AND p.client=:client")
    Product getProductFromProductsOwned(@Param("product") Product product, @Param("client") Client client);
}
