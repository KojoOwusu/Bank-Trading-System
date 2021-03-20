package com.exchangeconnectivity.exchangeserver.resourceclasses;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse extends Order{
    private final List<Execution> executions;
    private final int cumulativeQuantity;

    public OrderResponse(String product, int quantity, double price, String side, List<Execution> executions, int cumulativeQuantity) {
        super(product,quantity,price,side);
        this.executions=executions;
        this.cumulativeQuantity=cumulativeQuantity;
    }
    public OrderResponse(){
       super("",0,0.0,"");
        this.executions= new ArrayList<Execution>();
        this.cumulativeQuantity=0;
    }

    public List<Execution> getExecutions() {
        return executions;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }


}
