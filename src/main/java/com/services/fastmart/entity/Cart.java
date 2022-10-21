package com.services.fastmart.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customer_cart")
public class Cart {
	
	@Transient
    public static final String SEQUENCE_NAME = "customer_cart_sequence";
	
	
	@Id
	private long cartId;
	
	
	private String userEmail;
	
	
	private double cartAmount;
	
	
	private List<CartItem> cartItems;
	
	public Cart() {
		
	}

	public Cart(String userEmail, List<CartItem> cartItems, double cartAmount) {
		this.userEmail = userEmail;
		this.cartAmount = cartAmount;
		this.cartItems = cartItems;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(double cartAmount) {
		this.cartAmount = cartAmount;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userEmail=" + userEmail + ", cartAmount=" + cartAmount + ", cartItems="
				+ cartItems + "]";
	}

	
	
	

}
