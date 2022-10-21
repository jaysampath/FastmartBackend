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

import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartItem;
import com.services.fastmart.service.EcommerceService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartRestController {
	
	@Autowired
	private EcommerceService eCommerceService;
	
	@PostMapping("/add/{userEmail:.+}")
	public Cart addItemToCart(@PathVariable String userEmail, @RequestBody CartItem cartItem) {
		Cart cart = eCommerceService.addItemToCart(userEmail, cartItem);
		double amount = eCommerceService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@PostMapping("/delete/{userEmail:.+}/{itemId}")
	public Cart deleteItemInCart(@PathVariable String userEmail, @PathVariable int itemId) {
		Cart cart = eCommerceService.deleteItemInCart(userEmail, itemId);
		double amount = eCommerceService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@GetMapping("/{userEmail:.+}")
	public Cart getCart(@PathVariable String userEmail) {
		double amount = eCommerceService.cartAmount(userEmail);
		Cart cart = eCommerceService.getCart(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@GetMapping("/items/{userEmail:.+}")
	public List<CartItem> getCartItems(@PathVariable String userEmail){
		List<CartItem> cartItems = eCommerceService.getCartItems(userEmail);
		return cartItems;
		
	}
	
	@PostMapping("/change/add/{userEmail:.+}/{itemId}")
	public Cart addItemQuantityInCart(@PathVariable String userEmail, @PathVariable int itemId) {
		Cart cart = eCommerceService.changeItemQuantityInCart(userEmail, itemId, 1);
		double amount = eCommerceService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@PostMapping("/change/reduce/{userEmail:.+}/{itemId}")
	public Cart reduceItemQuantityInCart(@PathVariable String userEmail, @PathVariable int itemId) {
		Cart cart = eCommerceService.changeItemQuantityInCart(userEmail, itemId, -1);
		double amount = eCommerceService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}

}
