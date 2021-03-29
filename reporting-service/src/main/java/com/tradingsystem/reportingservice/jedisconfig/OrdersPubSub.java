package com.tradingsystem.reportingservice.jedisconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradingsystem.reportingservice.OpenOrder;
import com.tradingsystem.reportingservice.dao.OrderRepository;
import com.tradingsystem.reportingservice.dao.PortfolioRepository;
import com.tradingsystem.reportingservice.dao.ProductsOwnedRepo;
import com.tradingsystem.reportingservice.dao.ProductsRepository;
import com.tradingsystem.reportingservice.dto.Portfolio;
import com.tradingsystem.reportingservice.dto.ProductsOwned;
import com.tradingsystem.reportingservice.dto.TradeOrder;
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
        System.out.printf("Order has been placed to exchange");
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

        orderRepository.save(tradeOrder);
        System.out.println("Saved order: "+ tradeOrder.getOrderid() +" to the database");
        var ProductsOwned = new ProductsOwned();
        ProductsOwned.setClient(portfolio.getClient());
        ProductsOwned.setQuantityOwned(0);
        ProductsOwned.setProduct(productsRepository.findByTicker(tradeOrder.getProductname()).get());
        productsOwnedRepo.save(ProductsOwned);


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
}
