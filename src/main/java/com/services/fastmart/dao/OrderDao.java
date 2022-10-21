package com.services.fastmart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderItem;

@Repository
public interface OrderDao {
	
	public Order saveOrder(Order order);
	
	public List<OrderItem> getOrderItems(String userEmail,int orderId);
	
	public Order getOrder(String userEmail, int orderId);
	
	public List<Order> getAllOrders(String userEmail);
	
	public Order getLatestOrder(String userEmail);

}
 