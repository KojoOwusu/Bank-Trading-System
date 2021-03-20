package com.exchangeconnectivity.exchangeserver.resourceclasses;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus extends Order{
    private final List<Execution> executions;
    private final int cumulativeQuantity;

    public OrderStatus(String product, int quantity, double price, String side, List<Execution> executions, int cumulativeQuantity) {
        super(product,quantity,price,side);
        this.executions=executions;
        this.cumulativeQuantity=cumulativeQuantity;
    }
    public OrderStatus(){
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
