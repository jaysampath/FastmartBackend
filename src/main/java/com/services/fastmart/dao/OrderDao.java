package com.services.fastmart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderProduct;

@Repository
public interface OrderDao {
	
	public Order saveOrder(Order order);
	
	public List<OrderProduct> getOrderItems(String userEmail, String orderId);
	
	public Order getOrder(String userEmail, String orderId);
	
	public List<Order> getAllOrders(String userEmail);
	
	public Order getLatestOrder(String userEmail);

}
 