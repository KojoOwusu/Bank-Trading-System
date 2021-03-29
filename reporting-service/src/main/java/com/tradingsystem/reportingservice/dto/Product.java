package com.tradingsystem.reportingservice.dto;


import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    @Column(nullable = false)
    private String ticker;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductsOwned> productsowned;

    public Product(){}

    public Product(String ticker, List<ProductsOwned> productsowned) {
        this.ticker = ticker;
        this.productsowned = productsowned;
    }

    public Long getId() {
        return productid;
    }

    public String getTicker() {
        return ticker;
    }

    public List<ProductsOwned> getProductsowned() {
        return productsowned;
    }

    public void setId(Long id) {
        this.productid = id;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setProductsowned(List<ProductsOwned> productsowned) {
        this.productsowned = productsowned;
    }
}
