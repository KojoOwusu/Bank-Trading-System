package com.tradingsystem.reportingservice.dto;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
public class ProductsOwned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="productid", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="clientid", nullable = false)
    private Client client;

    @Column(nullable = false)
    private int quantityOwned=0;

    public ProductsOwned(){}

    public Product getProduct() {
        return product;
    }

    public Client getClient() {
        return client;
    }

    public int getQuantityOwned() {
        return quantityOwned;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setQuantityOwned(int quantityOwned) {
        this.quantityOwned = quantityOwned;
    }
}
