package com.exchangeconnectivity.exchangeserver;

import com.thoughtworks.qdox.model.expression.Or;
import org.springframework.web.bind.annotation.*;
import com.exchangeconnectivity.exchangeserver.resourceclasses.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
public class OrderController {
    //create Retrofit service for making requests
    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();
    private Retrofit retrofit2 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange2.matraining.com").build();

    //CREATE ORDER  /api/createorder

    @RequestMapping(value = "/api/createorder", method=RequestMethod.POST,produces = "application/json",headers = "Accept=*/*", consumes="application/json")
    @ResponseBody
    public  OrderResponse createOrder(@RequestBody TradeOrder newOrder) {
        Call<String> req = null;
        APIInterfaces.CreateOrderService service;
        service = newOrder.getExchange().equals("exchange1")?(retrofit.create(APIInterfaces.CreateOrderService.class)):(retrofit2.create(APIInterfaces.CreateOrderService.class));
        req = service.createOrder(new Order(newOrder.getProduct(),newOrder.getQuantity(), newOrder.getPrice(), newOrder.getSide()));
        try {
            Response<String> res = req.execute();
            OrderResponse o = new OrderResponse(res.body(), newOrder.getExchange());
            return o;


        }catch(java.io.IOException e){}
        catch(java.lang.IllegalArgumentException e){e.getMessage();}
        return new OrderResponse("",newOrder.getExchange());
    }

    //GET ORDER STATUS /api/getorder

    @RequestMapping(value="/api/getorder", method=RequestMethod.GET, produces = "application/json",headers="Accept=*/*")
    @ResponseBody
    public OrderStatus getOrderStatus(@RequestParam String ID,@RequestParam String exchange ){
        Call<OrderStatus> req = null;
        APIInterfaces.GetOrder service;
        service=  exchange.equals("exchange1")?(retrofit.create(APIInterfaces.GetOrder.class)):(retrofit2.create(APIInterfaces.GetOrder.class));
        req = service.getOrderStatus(ID);
        try {
            Response<OrderStatus> res = req.execute();
            if(res.body() == null)
                return new OrderStatus();
            else
                return res.body();
        }catch(java.io.IOException e){}
        catch(java.lang.IllegalArgumentException e){e.getMessage();}
        return new OrderStatus();
    }


    //CANCEL ORDER /api/cancelorder
    @RequestMapping(value = "/api/cancelorder",produces = "application/json", method=RequestMethod.DELETE, headers = "Accept=*/*")
    @ResponseBody
    public String cancelOrder(@RequestParam String ID, @RequestParam String exchange) {
        Call<String> req = null;
        APIInterfaces.CancelOrder service;

       service = exchange.equals("exchange1")?(retrofit.create(APIInterfaces.CancelOrder.class)):(retrofit2.create(APIInterfaces.CancelOrder.class));
         req = service.cancelOrder(ID);
        try {
            Response<String> res = req.execute();
            if(res.body() == null)
                return "false";
            else
                return res.body();
        }catch(java.io.IOException e){}
        catch(java.lang.IllegalArgumentException e){e.getMessage();}
        return "failed to cancel orderID: "+ID;
    }

    //EDIT ORDER /api/editorder

    @RequestMapping(value = "/api/editorder", method=RequestMethod.PUT, produces = "application/json", consumes = "application/json", headers = "Accept=*/*")
    @ResponseBody
    public String modifyOrder(@RequestParam String ID, @RequestBody Order o) {
       APIInterfaces.EditOrderService service = retrofit.create(APIInterfaces.EditOrderService.class);
       Call<String> req = service.editOrder(o, ID);
        try {
            Response<String> res = req.execute();
            if(res.body() == null)
                return "false";
            else
                return res.body();
        }catch(java.io.IOException e){}
        catch(java.lang.IllegalArgumentException e){e.getMessage();}
        return "failed to edit orderID: "+ID;

        // return "true";
    }
}
