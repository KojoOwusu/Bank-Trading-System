package com.tradingsystem.reportingservice.controllers;

import com.tradingsystem.reportingservice.dao.OrderRepository;
import com.tradingsystem.reportingservice.dao.PortfolioRepository;
import com.tradingsystem.reportingservice.dto.Portfolio;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {
    @Autowired
    PortfolioRepository portfolioRepository;

    @CrossOrigin
    @RequestMapping(value = "/portfolio", method= RequestMethod.GET, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public List<Portfolio> listAllPortfolios(){

        return portfolioRepository.findAll();
    }
}
