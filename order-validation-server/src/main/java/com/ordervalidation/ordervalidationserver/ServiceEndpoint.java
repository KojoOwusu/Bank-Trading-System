package com.ordervalidation.ordervalidationserver;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import turntabl.trading.ordervalidservice.ValidateOrder;
import turntabl.trading.ordervalidservice.ValidationResponse;

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://turntabl/trading/ordervalidservice";

    public ServiceEndpoint(){

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateOrder")
    @ResponsePayload
    public ValidationResponse validateOrder(@RequestPayload ValidateOrder request){
        ValidationResponse response = new ValidationResponse();
        response.setOrderID("2fb-00-56-ui-URI2");
        response.setOrderstatus("Order was validated successfully");
        return response;
    }
}
