package com.tradingsystem.reportingservice.controllers;

import com.tradingsystem.reportingservice.dao.ClientRepository;
import com.tradingsystem.reportingservice.dto.Client;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetAllClientsController {
    @Autowired
    ClientRepository clientRepository;

    @CrossOrigin
    @RequestMapping(value = "/clients", method= RequestMethod.GET, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public List<Client> listAllClients(){

        return clientRepository.findAll();
    }
}
