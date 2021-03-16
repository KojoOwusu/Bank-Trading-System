package tradeengine.TradeServices;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@RestController
public class TradeEngineRESTController {

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).baseUrl("https://exchange-con.herokuapp.com").build();

@RequestMapping(method=RequestMethod.POST, value="/api/trade", produces = "application/json", consumes= "application/json")
@ResponseBody
public String receiveOrder(@RequestBody Order order) {

    var service = retrofit.create(PostOrderService.class);
    var req = service.createOrder(order);
    try {
//        return req.execute().body();

        Response<String> res = req.execute();
        System.out.println(res);
        return res.body();
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
return "NO";
};
}
