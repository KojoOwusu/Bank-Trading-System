package com.tradingsystem.reportingservice;

import com.tradingsystem.reportingservice.dao.*;
import com.tradingsystem.reportingservice.dto.Client;
import com.tradingsystem.reportingservice.dto.Product;
import com.tradingsystem.reportingservice.dto.TradeOrder;
import com.tradingsystem.reportingservice.webinterfaces.OrderStatusInterface;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

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
    ProductsRepository productsRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductsOwnedRepo productsOwnedRepo;

    @Autowired
    PortfolioRepository portfolioRepository;

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange-con.herokuapp.com").build();

    public void run(){
        List<TradeOrder> orders = getOrderIDs();
                for(TradeOrder order: orders){
                    if(order.getOrderid()!=null){
                        OrderStatus orderStatus = fetchOrderStatus(order.getOrderid(), order.getExchange());
                        System.out.println(orderStatus.toString());
                        if(orderStatus.getCumulativeQuantity() > 0 && orderStatus.getCumulativeQuantity()!=orderStatus.getQuantity()){

                            var client = portfolioRepository.findClientID(orderRepository.findPortfolioByOrderID(order.getOrderid()).getPortfolioid());
                            Product product = productsRepository.findByTicker(order.getProductname()).get();
                            updateUserData(orderStatus,order,client, product);
                        }
                    }
                }
    }

    private OrderStatus fetchOrderStatus(String orderId, String exchange){
        Call<OrderStatus> req;
        OrderStatusInterface FetchOrderStatusService;
        FetchOrderStatusService =  retrofit.create(OrderStatusInterface.class);
        req = FetchOrderStatusService.getOrderStatus(orderId, exchange);
        try{
            Response<OrderStatus> res = req.execute();
            return res.body();
        }catch(java.io.IOException e){e.printStackTrace();}
        return new OrderStatus();
    }

   private List<TradeOrder> getOrderIDs(){
        return orderRepository.findAll();
    }


   private void updateOrderStatus(String status,String id){
        orderRepository.updateOrderStatus(status, id);
   }


   private void updateUserData(OrderStatus status, TradeOrder order,Client client, Product product){
        Double funds = client.getFunds();
        int currentQuantity = productsOwnedRepo.findQuantityOwned(client,product);
            var executionsList = status.getExecutions();
            for(int i= order.getExecutions();i<executionsList.size();i++){
                Execution e = executionsList.get(i);
                if(order.getSide().equals("BUY")) {
                    funds -= e.getPrice() * e.getQuantity();
                    currentQuantity += e.getQuantity();
                }
                else {
                    funds += e.getPrice() * e.getQuantity();
                    currentQuantity -= e.getQuantity();
                }
                order.setExecutions(order.getExecutions()+1);
            }
            if(status.getCumulativeQuantity() == status.getQuantity()){
                updateOrderStatus("completed", order.getOrderid());
            }else{
                updateOrderStatus("partially-complete", order.getOrderid());
            }
       clientRepository.updateClientFunds(client.getClientid(), funds);
       orderRepository.updateExecutions(order.getExecutions(), order.getOrderid());
       productsOwnedRepo.updateQuantity(currentQuantity, client,product);
   }
}
