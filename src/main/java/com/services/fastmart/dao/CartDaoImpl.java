package com.services.fastmart.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.services.fastmart.entity.DatabaseFields;
import com.services.fastmart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartProduct;

@Component
public class CartDaoImpl implements CartDao {

	private final MongoTemplate mongoTemplate;
	private final CartRepository repository;

	@Autowired
	public CartDaoImpl(MongoTemplate mongoTemplate, CartRepository repository) {
		this.mongoTemplate = mongoTemplate;
		this.repository = repository;
	}

	@Override
	public Cart getCart(String userEmail) {
		Cart cartCheck = repository.findCartByUserEmail(userEmail);
	    if(cartCheck == null) {
			return createNewUserCart(userEmail);
	    }
	  return cartCheck;
	}

	@Override
	public List<CartProduct> getCartItems(String userEmail, String cartId) {
		Cart cart = getCart(userEmail);
		return cart.getCartProducts();
	}

	@Override
	public Cart changeCartItemQuantity(String userEmail,String productId, int change) {
		
		Cart cart = getCart(userEmail);
		List<CartProduct> cartProducts = cart.getCartProducts();
		List<CartProduct> newCartProducts = new ArrayList<>();
		for(CartProduct cartProduct : cartProducts) {
			if(cartProduct.getProductId().equals(productId)) {
				if(change==-1 && cartProduct.getProductQuantity()<=1) {
					deleteItemInCart(userEmail, cartProduct.getProductId(),cart.getCartId());
				}else {
					int prevItemQuantity = cartProduct.getProductQuantity();
					cartProduct.setProductQuantity( prevItemQuantity + change );
					newCartProducts.add(cartProduct);
				}
				
			}else {
				newCartProducts.add(cartProduct);
			}
			
		}
		cart.setCartProducts(newCartProducts);
		mongoTemplate.save(cart, DatabaseFields.CART_COLLECTION);
		return cart;
	}

	@Override
	public Cart deleteItemInCart(String userEmail, String itemId, String cartId) {
		Cart cart = getCart(userEmail);
		List<CartProduct> cartProducts = cart.getCartProducts();
		//System.out.println(cartItems);
		List<CartProduct> newCartProducts = new ArrayList<>();
		for(CartProduct item: cartProducts) {
			if(!item.getProductId().equals(itemId)) {
				newCartProducts.add(item);
			}
		}
		cart.setCartProducts(newCartProducts);
	     mongoTemplate.save(cart, DatabaseFields.CART_COLLECTION);
		return cart;
	}

	@Override
	public Cart addItemToCart(String userEmail, CartProduct cartProduct) {
		Cart cart = getCart(userEmail);
	    List<CartProduct> existed = cart.getCartProducts();
	    List<CartProduct> newCartProducts = new ArrayList<>();
	    boolean flag=false;
	    for(CartProduct cItem: existed) {
	    	if(cItem.getProductId().equals(cartProduct.getProductId())) {
	    		int initialQuantity = cItem.getProductQuantity();
		    	int finalQuantity = initialQuantity + cartProduct.getProductQuantity();
		    	cItem.setProductQuantity(finalQuantity);
		    	newCartProducts.add(cItem);
		    	flag=true;
	    	}else {
	    		newCartProducts.add(cItem);
	    	}
	    }
	    if(!flag) {
	    	newCartProducts.add(cartProduct);
	    }
	    
	    cart.setCartProducts(newCartProducts);
        mongoTemplate.save(cart, DatabaseFields.CART_COLLECTION);
        return cart;
	}

	@Override
	public double updateCartAmount(double totalAmount, String userEmail) {
		Query query = new Query().addCriteria(Criteria.where(DatabaseFields.USER_EMAIL).is(userEmail));
		Update update = new Update();
		update.set(DatabaseFields.CART_AMOUNT, totalAmount);
		mongoTemplate.updateFirst(query, update, Cart.class);
		return totalAmount;
	}

	@Override
	public Cart createNewUserCart(String userEmail) {
		Cart cart = Cart.builder()
				.userEmail(userEmail)
				.cartProducts(Collections.emptyList())
				.cartAmount(0)
				.build();
		return repository.save(cart);
	}

}
