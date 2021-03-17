package com.example.demo.order;


import java.util.Arrays;
import java.util.List;

import com.example.consumingorderservice.wsdl.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

	@Autowired
	OrderSOAPClient soapClient;


/*
	@RequestMapping("/api/orders")
	public List <Order> getAllOrders() {


		return orderservice.getAllOrder();

	}
*/
//	@RequestMapping("/api/orders/{id}")
//	public Order getOrderbyid(@PathVariable String id) {
//		return orderservice.getorderbyid(id);
	//}

	
	@RequestMapping(method=RequestMethod.POST, value="/api/order", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ValidationResponse sendOrder(@RequestBody Order order) {
		String productName = order.getProduct();
		int quantity = order.getQuantity();
		double price = order.getPrice();
		String side = order.getSide();


		var response = soapClient.setOrder(productName, quantity, price, side);
		return response;
	}
}
