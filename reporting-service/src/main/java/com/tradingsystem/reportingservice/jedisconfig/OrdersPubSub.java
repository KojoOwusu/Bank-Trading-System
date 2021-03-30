package com.tradingsystem.reportingservice.jedisconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradingsystem.reportingservice.OpenOrder;
import com.tradingsystem.reportingservice.dao.OrderRepository;
import com.tradingsystem.reportingservice.dao.PortfolioRepository;
import com.tradingsystem.reportingservice.dao.ProductsOwnedRepo;
import com.tradingsystem.reportingservice.dao.ProductsRepository;
import com.tradingsystem.reportingservice.dto.*;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPubSub;



public class OrdersPubSub extends JedisPubSub {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ProductsOwnedRepo productsOwnedRepo;
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel: "+channel+" "+message);

        try{
        OpenOrder order =objectMapper.readValue(message, OpenOrder.class);
        TradeOrder tradeOrder = new TradeOrder();
        Portfolio portfolio  = portfolioRepository.getOne((long)order.getPortfolioID());

        tradeOrder.setProductname(order.getProduct());
        tradeOrder.setQuantity(order.getQuantity());
        tradeOrder.setPrice(order.getPrice());
        tradeOrder.setSide(order.getSide());
        tradeOrder.setOrderstatus(order.getOrderStatus());
        tradeOrder.setOrderid(order.getOrderID());
        tradeOrder.setExchange(order.getExchange());
        tradeOrder.setPortfolio(portfolio);
        tradeOrder.setExecutions(order.getExecutions());

        orderRepository.save(tradeOrder);

            Client client = portfolioRepository.findClientID(portfolio.getPortfolioid());
            Product product = productsRepository.findByTicker(tradeOrder.getProductname()).get();
        if(!checkIfEntryExists(product,tradeOrder, client)){
            var ProductsOwned = new ProductsOwned();
            ProductsOwned.setClient(client);
            ProductsOwned.setQuantityOwned(0);
            ProductsOwned.setProduct(product);
           productsOwnedRepo.save(ProductsOwned);
        }
            System.out.println("Saved order: "+ tradeOrder.getOrderid() +" to the database");
        }catch(JsonProcessingException e){}
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Subscribed to channel : "+ channel);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Unsubscribed from channel : "+ channel);
    }
    private Boolean checkIfEntryExists(Product p, TradeOrder order, Client client){
        return productsOwnedRepo.findAll().stream().anyMatch(x->productsOwnedRepo.getProductFromProductsOwned(p,client).getTicker().equals(order.getProductname()));
    }
}
