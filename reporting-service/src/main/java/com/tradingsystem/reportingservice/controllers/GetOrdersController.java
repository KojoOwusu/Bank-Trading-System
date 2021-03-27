package com.tradingsystem.reportingservice.controllers;


import com.tradingsystem.reportingservice.dao.OrderRepository;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GetOrdersController {
    @Autowired
    OrderRepository orderRepository;

    @CrossOrigin
    @RequestMapping(value = "/orders", method= RequestMethod.GET, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public List<String> listAllOrders(){
        return orderRepository.findallIDs();
    }
}
