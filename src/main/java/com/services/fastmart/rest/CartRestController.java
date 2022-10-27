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
import com.services.fastmart.entity.CartProduct;
import com.services.fastmart.service.FastmartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartRestController {
	
	@Autowired
	private FastmartService fastmartService;
	
	@PostMapping("/add/{userEmail:.+}")
	public Cart addItemToCart(@PathVariable String userEmail, @RequestBody CartProduct cartProduct) {
		Cart cart = fastmartService.addProductToCart(userEmail, cartProduct);
		double amount = fastmartService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@PostMapping("/delete/{userEmail:.+}/{itemId}")
	public Cart deleteItemInCart(@PathVariable String userEmail, @PathVariable String itemId) {
		Cart cart = fastmartService.deleteProductInCart(userEmail, itemId);
		double amount = fastmartService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@GetMapping("/{userEmail:.+}")
	public Cart getCart(@PathVariable String userEmail) {
		double amount = fastmartService.cartAmount(userEmail);
		Cart cart = fastmartService.getCart(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@GetMapping("/items/{userEmail:.+}")
	public List<CartProduct> getCartItems(@PathVariable String userEmail){
		return fastmartService.getCartProducts(userEmail);
		
	}
	
	@PostMapping("/change/add/{userEmail:.+}/{itemId}")
	public Cart addItemQuantityInCart(@PathVariable String userEmail, @PathVariable String itemId) {
		Cart cart = fastmartService.changeProductQuantityInCart(userEmail, itemId, 1);
		double amount = fastmartService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}
	
	@PostMapping("/change/reduce/{userEmail:.+}/{itemId}")
	public Cart reduceItemQuantityInCart(@PathVariable String userEmail, @PathVariable String itemId) {
		Cart cart = fastmartService.changeProductQuantityInCart(userEmail, itemId, -1);
		double amount = fastmartService.cartAmount(userEmail);
		cart.setCartAmount(amount);
		return cart;
	}

}
