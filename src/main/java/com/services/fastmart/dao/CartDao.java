package com.services.fastmart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartProduct;

@Repository
public interface CartDao {
	
	public Cart getCart(String userEmail);
	
	public double updateCartAmount(double amount, String userEmail);
	
	public List<CartProduct> getCartItems(String userEmail, String cartId);
	
	public Cart changeCartItemQuantity(String userEmail,String itemId, int change);
	
	public Cart deleteItemInCart(String userEmail , String itemId, String cartId);
	
	public Cart addItemToCart(String userEmail, CartProduct cartProduct);
	
	public Cart createNewUserCart(String userEmail);

}
