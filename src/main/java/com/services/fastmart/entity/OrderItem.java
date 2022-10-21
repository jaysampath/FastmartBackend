package com.services.fastmart.entity;

public class OrderItem {

	private int orderItemId;

	private String orderItemName;

	private int orderItemQuantity;

	private String orderItemImageUrl;

	private int orderItemPrice;

	public OrderItem() {

	}

	public OrderItem(int orderItemId, String orderItemName, int orderItemQuantity, String itemImageUrl,
			int itemImagePrice) {
		this.orderItemId = orderItemId;
		this.orderItemName = orderItemName;
		this.orderItemQuantity = orderItemQuantity;
		this.orderItemImageUrl = itemImageUrl;
		this.orderItemPrice = itemImagePrice;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getOrderItemImageUrl() {
		return orderItemImageUrl;
	}

	public void setOrderItemImageUrl(String orderItemImageUrl) {
		this.orderItemImageUrl = orderItemImageUrl;
	}

	public int getOrderItemPrice() {
		return orderItemPrice;
	}

	public void setOrderItemPrice(int orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}

	public String getOrderItemName() {
		return orderItemName;
	}

	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}

	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

}
