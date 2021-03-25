package tradeengine.TradeServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tradeengine.TradeServices.Order;
import tradeengine.TradeServices.TradeData;
import tradeengine.TradeServices.TradeOrder;
import tradeengine.TradeServices.marketdata.MarketData;


//this class will expose and implement Trade engine business logic
@Service
public class TradeengineService {
    @Autowired
    MarketData MD;

    private Order orderRequest;

    TradeengineService(){};

    public TradeOrder trade(){
        if(orderRequest.getSide().equals("BUY")){
            if(getAskPriceForMarket1()<getAskPriceForMarket2())
                return createTrade("exchange1");
            else
                return createTrade("exchange2");
        }
        else{
            if(getBidPriceForMarket1()>getBidPriceForMarket2())
                return createTrade("exchange1");
            else
                return createTrade("exchange2");
        }
    }

    private TradeOrder createTrade(String exchange){
       TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setProduct(orderRequest.getProduct());
        tradeOrder.setFunds(orderRequest.getFunds());
        tradeOrder.setSide(orderRequest.getSide());
        tradeOrder.setQuantity(orderRequest.getQuantity());
        tradeOrder.setPrice(orderRequest.getPrice());
        tradeOrder.setExchange(exchange);
        tradeOrder.setPortfolioID(orderRequest.getPortfolioID());
        tradeOrder.setQuantityOwned(orderRequest.getQuantityOwned());
        return tradeOrder;
    }

    public void setOrderRequest(Order orderRequest){
        this.orderRequest=orderRequest;
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
}
