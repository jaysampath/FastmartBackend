package com.services.fastmart.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customer_order")
public class Order {
	
	@Transient
    public static final String SEQUENCE_NAME = "order_sequence";
	
    @Id
	private String orderId;
	

	private String userEmail; 
	
	private String orderTime;
	
	

	private double orderAmount;
	
	private List<OrderItem> orderItems;
	
	private OrderAddress orderAddress;
	
	public Order() {
		
	}

	public Order(String userEmail, String orderTime, double orderAmount, OrderAddress orderAddress,
			List<OrderItem> orderItems) {
		this.userEmail = userEmail;
		this.orderTime = orderTime;
		this.orderAmount = orderAmount;
		this.orderAddress = orderAddress;
		this.orderItems = orderItems;
	}



	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userEmail=" + userEmail + ", orderTime=" + orderTime + ", orderAmount="
				+ orderAmount + ", orderAddress=" + orderAddress + ", orderItems=" + orderItems + "]";
	}

	
	
	
	

}
