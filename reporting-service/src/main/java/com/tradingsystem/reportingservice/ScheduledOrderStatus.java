package com.tradingsystem.reportingservice;

import com.tradingsystem.reportingservice.dao.ClientRepository;
import com.tradingsystem.reportingservice.dao.OrderRepository;
import com.tradingsystem.reportingservice.dto.Client;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import com.tradingsystem.reportingservice.webinterfaces.OrderStatusInterface;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Service
public class ScheduledOrderStatus {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();
    private Retrofit retrofit2 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange2.matraining.com").build();

    public void run(){
        List<TradeOrder> orders = getOrderIDs();
     //   orders.stream().filter(x->x.getOrderid() != null).map(x->fetchOrderStatus(x.getOrderid(),x.getExchange())).filter(x->x.getCumulativeQuantity()==x.getQuantity()).
                for(var order: orders){
                    if(order.getOrderid()!=null){
                        OrderStatus orderStatus = fetchOrderStatus(order.getOrderid(), order.getExchange());
                        if(orderStatus.getCumulativeQuantity() > 0 ){
                            updateOrder(order.getOrderid());
                            var client = order.getPortfolio().getClient();
                            if(order.getSide().equals("BUY")){
                                client.setFunds(client.getFunds()-calculateAmount(orderStatus, order));
                            }
                            else{
                                client.setFunds(client.getFunds()+calculateAmount(orderStatus,order));
                            }
                             clientRepository.updateClientFunds(client.getClientid(), client.getFunds());
                        }
                    }
                }
    }

    private OrderStatus fetchOrderStatus(String orderId, String exchange){
        Call<OrderStatus> req;
        OrderStatusInterface service;
        service=  exchange.equals("exchange1")?(retrofit.create(OrderStatusInterface.class)):(retrofit2.create(OrderStatusInterface.class));
        req = service.getOrderStatus(orderId, exchange);
        try{
            Response<OrderStatus> res = req.execute();
            return res.body();
        }catch(java.io.IOException e){}
        return new OrderStatus();
    }

   private List<TradeOrder> getOrderIDs(){
        return orderRepository.findAll();
    }

   private void updateOrder(String id){
        orderRepository.updateOrderStatus("completed", id);
   }


   private Double calculateAmount(OrderStatus status, TradeOrder order){
        int difference = status.getCumulativeQuantity()-order.getExecutions();
        Double sum=0.0;

        if(difference>0 && order.getExecutions()==0){
            for(var ex: status.getExecutions()){
                sum+=ex.getQuantity()*ex.getPrice();
                order.setExecutions(order.getExecutions()+1);
            }
            orderRepository.updateExecutions(order.getExecutions(), order.getOrderid());
            return sum;
        }
        else if(difference>0 && order.getExecutions()>0){
            var executionsList = status.getExecutions();
            for(int i= order.getExecutions();i<status.getExecutions().size();i++){
                Execution e = executionsList.get(0);
                sum+=e.getPrice()*e.getQuantity();
                order.setExecutions(order.getExecutions()+1);
            }
            orderRepository.updateExecutions(order.getExecutions(), order.getOrderid());
            return sum;
        }
        return order.getPortfolio().getClient().getFunds();
   }
}
