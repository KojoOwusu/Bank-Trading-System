package tradeengine.TradeServices.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import tradeengine.TradeServices.*;
import tradeengine.TradeServices.marketdata.MarketData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//this class will expose and implement Trade engine business logic

@Service
public class TradeengineService {

    @Autowired
    MarketData MD;
  //  private List<TradeOrder> ordersToDispatch = new ArrayList<>();
    private Order orderRequest;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Jedis jedis;

    public TradeengineService(){};

    public void init(Order orderRequest, Jedis jedis){
        this.orderRequest=orderRequest;
        this.jedis = jedis;
    }

    private void nonsplitOrder(String side, int quantity){
        if(side.equals("buy")) {
            if (getBidPriceForMarket1() > getBidPriceForMarket2())
                dispatchOrder(createTrade(orderRequest.getPrice(), "exchange1", quantity));
            else
                dispatchOrder(createTrade(orderRequest.getPrice(), "exchange2", quantity));
        }else{
            if(getAskPriceForMarket1()>getAskPriceForMarket2())
                dispatchOrder(createTrade(orderRequest.getPrice(), "exchange2", quantity));
            else
                dispatchOrder(createTrade(orderRequest.getPrice(), "exchange1", quantity));
        }
        System.out.println(orderRequest.getPrice()+" "+orderRequest.getQuantity());
    }

    private void multilegSplit(String side){

        List<Matchable> ordersList = fetchOrderbookdata(orderRequest.getProduct(), side).orElse(new ArrayList<>());
        if(side.equals("buy")){
            Collections.reverse(ordersList);
        }
        int difference;
        int index=0;
        int remainder = orderRequest.getQuantity();
        do {
            if(index == ordersList.size()-1 || ordersList.isEmpty()){
                nonsplitOrder(side,remainder);
                break;
            }
            TradeOrder finalorder;
            Matchable o = ordersList.get(index);

            if(orderRequest.getSide().equals("BUY") && orderRequest.getPrice() < o.getPrice() || orderRequest.getSide().equals("SELL") && orderRequest.getPrice() > o.getPrice()){
                index+=1;
                difference = -1;
                continue;
            }
            int actualQuantity = o.getQuantity()-o.getCumulativeQuantity();
            difference = actualQuantity-orderRequest.getQuantity();
            if(difference<0) {
                remainder = Math.abs(difference);
                finalorder = createTrade(o.getPrice(), o.getExchange(), actualQuantity);
            }
            else if(difference>0) {
                remainder = orderRequest.getQuantity();
                finalorder = createTrade(o.getPrice(), o.getExchange(), remainder);
            }
            else {
                remainder = 0;
                finalorder = createTrade(o.getPrice(), o.getExchange(), remainder);
            }
            orderRequest.setQuantity(remainder);
            System.out.println(finalorder.getPrice()+" "+finalorder.getQuantity()+" "+finalorder.getExchange());
            index+=1;
            dispatchOrder(finalorder);
        }while(difference < 0);
    }

    private void dispatchOrder(TradeOrder order){
        try {
            jedis.lpush("Queue#pending",objectMapper.writeValueAsString(order));
        }catch (JsonProcessingException e){e.printStackTrace();}
    }


    public void match() throws JsonProcessingException {
        if(orderRequest.getSide().equals("BUY")) {
            multilegSplit("sell");
        }
        else
            multilegSplit("buy");
       }

    private TradeOrder createTrade(double price,String exchange, int quantity){
       TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setProduct(orderRequest.getProduct());
        tradeOrder.setFunds(orderRequest.getFunds());
        tradeOrder.setSide(orderRequest.getSide());
        tradeOrder.setQuantity(quantity);
        tradeOrder.setPrice(price);
        tradeOrder.setExchange(exchange);
        tradeOrder.setPortfolioID(orderRequest.getPortfolioID());
        tradeOrder.setQuantityOwned(orderRequest.getQuantityOwned());
        return tradeOrder;
    }

    private Double getBidPriceForMarket1(){
        return MD.getData1().stream().filter(x->x.getTICKER().equals(orderRequest.getProduct())).findFirst().get().getBID_PRICE();
    }

    private Double getBidPriceForMarket2(){
        return MD.getData2().stream().filter(x->x.getTICKER().equals(orderRequest.getProduct())).findFirst().get().getBID_PRICE();
    }

    private Double getAskPriceForMarket1(){
        return MD.getData1().stream().filter(x->x.getTICKER().equals(orderRequest.getProduct())).findFirst().get().getASK_PRICE();
    }

    private Double getAskPriceForMarket2(){
        return MD.getData2().stream().filter(x->x.getTICKER().equals(orderRequest.getProduct())).findFirst().get().getASK_PRICE();
    }


    public Optional<List<Matchable>> fetchOrderbookdata(String product, String side){
        APIinterfaces.GetOrderBookData service = ExchangeServices.getOrderBookDataInterface("http://exchange-con.herokuapp.com");
        try {
            List<OrderStatus> ordersmarket1 = service.getOrderBookData(product, side, "exchange1").execute().body().stream().
                    filter(x->x.getCumulativeQuantity() != x.getQuantity()).collect(Collectors.toList());

            List<OrderStatus> ordersmarket2 = service.getOrderBookData(product, side, "exchange2").execute().body().stream().
                    filter(x->x.getCumulativeQuantity() != x.getQuantity()).collect(Collectors.toList());
            List<Matchable> newList = new ArrayList<>();

           ordersmarket1.stream().forEach(x-> newList.add(new Matchable(x.getProduct(), x.getQuantity(), x.getPrice(), x.getSide(),x.getExecutions(),x.getCumulativeQuantity(),"exchange1")));
           ordersmarket2.stream().forEach(x-> newList.add(new Matchable(x.getProduct(), x.getQuantity(), x.getPrice(), x.getSide(),x.getExecutions(),x.getCumulativeQuantity(),"exchange2")));
           Collections.sort(newList);
           return Optional.of(newList);

        }catch(java.io.IOException e){e.getMessage();}
        return Optional.empty();
    }
}
