package com.ordervalidation.ordervalidationserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig;
import com.ordervalidation.ordervalidationserver.marketdata.MarketData;
import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MarketData MD;   //injecting market data singleton

    @CrossOrigin
    @RequestMapping(value="/md", method= RequestMethod.POST, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public void getMarketData(@RequestBody List<Trade> md){
            MD.setData(md);
    }

    @RequestMapping(value="/md2", method= RequestMethod.POST, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public void getMarketData2(@RequestBody List<Trade> md){
        MD.setData2(md);
    }


}
