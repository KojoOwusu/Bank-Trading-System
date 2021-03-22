package com.ordervalidation.ordervalidationserver.validation;

import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import turntabl.trading.ordervalidservice.ValidateOrder;
import java.lang.Math.*;

import java.util.List;

public class Validator {
    private Trade tradedata;
    private ValidateOrder request;

    public Validator(Trade tradedata, ValidateOrder request) {
        this.tradedata = tradedata;
        this.request = request;
    }

    public Boolean validate(){
        return quantityCheck() && isFundsSufficient() && isPriceReasonable() && exceedLimit();
    }

    private Boolean quantityCheck(){
        if(request.getSide().equals("SELL")){
            return request.getQuantityOwned()>=request.getQuantity();
        }
        return true;
    }

    private Boolean isFundsSufficient(){
        if(request.getSide().equals("BUY")){
            return request.getFunds().doubleValue()>=tradedata.getASK_PRICE()*request.getQuantity();
        }
        return true;
    }

    private Boolean isPriceReasonable(){
        return (Math.abs(request.getPrice().doubleValue()-tradedata.getASK_PRICE())<=tradedata.getMAX_PRICE_SHIFT()) || (Math.abs(request.getPrice().doubleValue()-tradedata.getBID_PRICE())<=tradedata.getMAX_PRICE_SHIFT());
    }

    private Boolean exceedLimit(){
        if(request.getSide().equals("BUY")){
            return request.getQuantity() <= tradedata.getBUY_LIMIT();
        }
        return request.getQuantity() <= tradedata.getSELL_LIMIT();

    }
}
