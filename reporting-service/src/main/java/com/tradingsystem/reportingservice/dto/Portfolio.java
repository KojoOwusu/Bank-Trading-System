package com.tradingsystem.reportingservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Portfolio implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long portfolioid;

    @Column(nullable = false)
    private String portfolioname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientid", nullable = false)
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    List<TradeOrder> tradeOrders;

    public void setPortfolioid(Long portfolioid) {
        this.portfolioid = portfolioid;
    }

    public void setPortfolioname(String portfolioname) {
        this.portfolioname = portfolioname;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getPortfolioid() {
        return portfolioid;
    }

    public String getPortfolioname() {
        return portfolioname;
    }

    public Client getClient() {
        return client;
    }

    public List<TradeOrder> getTradeOrders() {
        return tradeOrders;
    }
}
