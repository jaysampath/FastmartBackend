package com.services.fastmart.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderItem;
import com.services.fastmart.service.EcommerceService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderRestController {
	
	@Autowired
	private EcommerceService eCommerceService;
	
	
	@PostMapping("/orders")
	public Order saveOrder(@RequestBody Order order) {
		Order insertedOrder = eCommerceService.saveOrder(order);
		return insertedOrder;
	}
	
	@GetMapping("/orders/{userEmail:.+}")
	public List<Order> getAllOrders(@PathVariable String userEmail){
		List<Order> orders = eCommerceService.getAllOrders(userEmail);
		return orders;
	}
	
	@GetMapping("/orders/id/{orderId}")
	public Order getOrderById(@PathVariable int orderId) {
		Order order = eCommerceService.getOrder("", orderId);
		return order;
	}
	
	@GetMapping("/orders/id/items/{orderId}")
	public List<OrderItem> getOrderItems(@PathVariable int orderId){
		List<OrderItem> orderItems = eCommerceService.getOrderItems("", orderId);
		
		return orderItems;
	}
	
	@GetMapping("/latest/{userEmail}")
	public Order getLatestOrder(@PathVariable String userEmail) {
		Order latestOrder = eCommerceService.getLatestOrder(userEmail);
		return latestOrder;
	}

}
