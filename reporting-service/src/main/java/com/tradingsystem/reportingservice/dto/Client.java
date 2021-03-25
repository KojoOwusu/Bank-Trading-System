package com.tradingsystem.reportingservice.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long clientid;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private double funds = 150.0;

    @OneToMany(mappedBy="client", cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    public Long getClientid() {
        return clientid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getFunds() {
        return funds;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
