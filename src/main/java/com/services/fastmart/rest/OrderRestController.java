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
import com.services.fastmart.entity.OrderProduct;
import com.services.fastmart.service.FastmartService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderRestController {
	
	@Autowired
	private FastmartService fastmartService;
	
	
	@PostMapping("/orders")
	public Order saveOrder(@RequestBody Order order) {
		return fastmartService.saveOrder(order);
	}
	
	@GetMapping("/orders/{userEmail:.+}")
	public List<Order> getAllOrders(@PathVariable String userEmail){
		return fastmartService.getAllOrders(userEmail);
	}
	
	@GetMapping("/orders/id/{orderId}")
	public Order getOrderById(@PathVariable String orderId) {
		return fastmartService.getOrder("", orderId);
	}
	
	@GetMapping("/orders/id/items/{orderId}")
	public List<OrderProduct> getOrderItems(@PathVariable String orderId){
		return fastmartService.getOrderProducts("", orderId);
	}
	
	@GetMapping("/latest/{userEmail}")
	public Order getLatestOrder(@PathVariable String userEmail) {
		return fastmartService.getLatestOrder(userEmail);
	}

}
