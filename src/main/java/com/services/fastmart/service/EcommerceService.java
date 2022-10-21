package com.services.fastmart.service;

import java.util.List;
import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartItem;
import com.services.fastmart.entity.Item;
import com.services.fastmart.entity.ItemReview;
import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderItem;
import com.services.fastmart.entity.User;
import com.services.fastmart.helpers.LoginInput;

public interface EcommerceService {
	
	public User saveNewUser(User user);
	
	public User getUserByEmail(String userEmail);
	
	public String checkUserIsAuth(LoginInput loginInput);
	
	public String checkExistingUser(String email);
	
	public User updateUserPassword(String email,String password);

	public List<Item> getAllItems();

	public Item addNewItem(Item item);

	public Item updateItem(Item item);

	public Item getItem(int id);
	
	public List<Item> getTopRatedMobiles();

	public List<String> getAllCategories();

	public List<String> getSubCategoryNames(String itemCategory);

	public List<Item> getItemsBySubCategory(String itemCategory, String itemSubCategory);

	public ItemReview addItemReview(ItemReview itemReview);

	public List<ItemReview> getItemReviews(long itemId);

	public List<ItemReview> getItemTopReviews(long itemId);
	
	public List<Item> getItemsBySearch(String queryString);

	public Order saveOrder(Order order);

	public List<Order> getAllOrders(String userEmail);

	public List<OrderItem> getOrderItems(String userEmail, int orderId);

	public Order getOrder(String userEmail, int orderId);
	
	public Order getLatestOrder(String userEmail);

	public Cart addItemToCart(String userEmail, CartItem cartItem);

	public Cart deleteItemInCart(String userEmail, int itemId);

	public Cart getCart(String userEmail);

	public List<CartItem> getCartItems(String userEmail);

	public Cart changeItemQuantityInCart(String userEmail, int itemId, int change);

	double cartAmount(String userEmail);

	public Cart createNewUserCart(String userEmail);

}
