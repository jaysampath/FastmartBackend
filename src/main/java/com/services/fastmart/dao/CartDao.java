package com.services.fastmart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartItem;

@Repository
public interface CartDao {
	
	public Cart getCart(String userEmail);
	
	public double cartAmount(double amount, String userEmail);
	
	public List<CartItem> getCartItems(String userEmail, int cartId);
	
	public Cart changeCartItemQuantity(String userEmail,int itemId, int change);
	
	public Cart deleteItemInCart(String userEmail , long itemId, long cartId);
	
	public Cart addItemToCart(String userEmail,CartItem cartItem);
	
	public Cart createNewUserCart(String userEmail);

}
