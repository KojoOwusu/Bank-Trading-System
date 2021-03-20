package tradeengine.TradeServices;

import com.google.gson.GsonBuilder;

import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@RestController
public class TradeEngineRESTController {

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).baseUrl("https://exchange-con.herokuapp.com").build();

@RequestMapping(method=RequestMethod.POST, value="/api/trade", produces = "application/json", consumes= "application/json")
@ResponseBody
public tradeengine.TradeServices.Response receiveOrder(@RequestBody Order order) {
    TradeOrder orderwithexchange = new TradeOrder();
    orderwithexchange.setPrice(order.getPrice());
    orderwithexchange.setProduct(order.getProduct());
    orderwithexchange.setQuantity(order.getQuantity());
    orderwithexchange.setSide(order.getSide());
    orderwithexchange.setExchange("exchange1");    //this is where trade engine specifies exchange to trade on.

    var service = retrofit.create(OrderService.class);
    var req = service.createOrder(orderwithexchange);
    try {
//        return req.execute().body();

        Response<tradeengine.TradeServices.Response> res = req.execute();
        System.out.println(res);
        tradeengine.TradeServices.Response result = res.body();
        return result;
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
return new tradeengine.TradeServices.Response();
};
}
