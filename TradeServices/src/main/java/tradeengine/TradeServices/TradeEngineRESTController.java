package tradeengine.TradeServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tradeengine.TradeServices.marketdata.MarketData;


import java.util.List;


@RestController
public class TradeEngineRESTController {
    @Autowired
    MarketData MD;

    // receive data from exchange one
@RequestMapping(method = RequestMethod.POST, value="/md", consumes = "application/json")
@ResponseBody
public void receiveMarketData(@RequestBody List<TradeData> md){
    MD.setData(md);
}


// receive market data from exhange two. handle that
@RequestMapping(method = RequestMethod.POST, value="/md2", consumes = "application/json")
@ResponseBody
public void receiveMarketData2(@RequestBody List<TradeData> md){
        //MD.setData(md);
    }
}



