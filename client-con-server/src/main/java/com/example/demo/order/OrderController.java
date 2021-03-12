package com.example.demo.order;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class OrderController {
	@Autowired
	private OrderService orderservice;
	
	
	@RequestMapping("/")
	public String helloworld(){

		
		return "HelloWorld";
		
	}
	
	@RequestMapping("/orders")
	public List <Order> getAllOrders() {

		
		return orderservice.getAllOrder();
		
	}
	
	@RequestMapping("/orders/{id}")
	public Order getOrderbyid(@PathVariable String id) {
		return orderservice.getorderbyid(id);
	}

	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void addTopic(@RequestBody Order order) {
		
		System.out.print("I received the request" + order);
		
	//orderservice.addOrder(order);
	}
}
