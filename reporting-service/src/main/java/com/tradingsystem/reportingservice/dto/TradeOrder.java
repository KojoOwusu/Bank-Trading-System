package com.tradingsystem.reportingservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class TradeOrder implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String orderid;

    @Column(nullable = false)
    private String productname;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String side;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private String orderstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="portfolioid", nullable = false)
    @JsonIgnore
    private Portfolio portfolio;

    @Column(nullable = false)
    private String exchange;

    @Column(nullable = false)
    private int executions = 0;
    //store cumulative quantity which will be zero from start



    public String getOrderid() {
        return orderid;
    }

    public String getProductname() {
        return productname;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public String getExchange() {
        return exchange;
    }

    public int getExecutions() {
        return executions;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setExecutions(int executions) {
        this.executions = executions;
    }
}


