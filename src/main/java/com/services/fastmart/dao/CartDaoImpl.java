package com.services.fastmart.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartItem;
import com.services.fastmart.service.SequenceGeneratorService;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	
	@Override
	public Cart getCart(String userEmail) {
		
		Query query = new Query();
	    query.addCriteria(Criteria.where("userEmail").is(userEmail));
	    List<Cart> cartCheck = mongoTemplate.find(query, Cart.class);
	    if(cartCheck.size()==0) {
	    	Cart newCart = createNewUserCart(userEmail);
	    	return newCart;
	    }
	    
	  return cartCheck.get(0);
	}

	

	@Override
	public List<CartItem> getCartItems(String userEmail, int cartId) {
		
		Cart cart = getCart(userEmail);
		List<CartItem> cartItems = cart.getCartItems();
		return cartItems;
	}

	@Override
	public Cart changeCartItemQuantity(String userEmail,int itemId, int change) {
		
		Cart cart = getCart(userEmail);
		List<CartItem> cartItems = cart.getCartItems();
		List<CartItem> newCartItems = new ArrayList<>();
		for(CartItem cartItem: cartItems) {
			if(cartItem.getItemId()==itemId) {
				if(change==-1 && cartItem.getItemQuantity()<=1) {
					deleteItemInCart(userEmail,cartItem.getItemId(),cart.getCartId());
				}else {
					int prevItemQuantity = cartItem.getItemQuantity();
					cartItem.setItemQuantity( prevItemQuantity + change );
					newCartItems.add(cartItem);
				}
				
			}else {
				newCartItems.add(cartItem);
			}
			
		}
		cart.setCartItems(newCartItems);
		mongoTemplate.save(cart, "customer_cart");
		return cart;
	}

	@Override
	public Cart deleteItemInCart(String userEmail, long itemId, long cartId) {
		
		Cart cart = getCart(userEmail);
		List<CartItem> cartItems = cart.getCartItems();
		//System.out.println(cartItems);
		List<CartItem> newCartItems = new ArrayList<>();
		for(CartItem item: cartItems) {
			if(item.getItemId()!=itemId) {
				newCartItems.add(item);
			}
		}
		cart.setCartItems(newCartItems);
	     mongoTemplate.save(cart,"customer_cart");
		return cart;
	}

	@Override
	public Cart addItemToCart(String userEmail,CartItem cartItem) {
		
		Cart cart = getCart(userEmail);
	    List<CartItem> existed = cart.getCartItems();
	    List<CartItem> newCartItems = new ArrayList<>();
	    boolean flag=false;
	    for(CartItem cItem: existed) {
	    	if(cItem.getItemId()==cartItem.getItemId()) {
	    		int initialQuantity = cItem.getItemQuantity();
		    	int finalQuantity = initialQuantity + cartItem.getItemQuantity();
		    	cItem.setItemQuantity(finalQuantity);
		    	newCartItems.add(cItem);
		    	flag=true;
	    	}else {
	    		newCartItems.add(cItem);
	    	}
	    }
	    if(flag==false) {
	    	newCartItems.add(cartItem);
	    }
	    
	    cart.setCartItems(newCartItems);
        mongoTemplate.save(cart,"customer_cart");
        return cart;
	}

	@Override
	public double cartAmount(double totalAmount, String userEmail) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userEmail").is(userEmail));
		Update update = new Update();
		update.set("cartAmount", totalAmount);
		mongoTemplate.updateFirst(query, update, Cart.class);
		return totalAmount;
	}


	@Override
	public Cart createNewUserCart(String userEmail) {
		
		List<CartItem> tempCartItems = new ArrayList<>();
		Cart cart = new Cart(userEmail,tempCartItems,0);
		cart.setCartId(SequenceGeneratorService.generateSequence(Cart.SEQUENCE_NAME));
		mongoTemplate.insert(cart, "customer_cart");	   
		return cart;
	}

	



}
