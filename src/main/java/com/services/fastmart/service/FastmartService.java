package com.services.fastmart.service;

import java.util.List;
import com.services.fastmart.entity.Cart;
import com.services.fastmart.entity.CartProduct;
import com.services.fastmart.entity.Product;
import com.services.fastmart.entity.ProductReview;
import com.services.fastmart.entity.Order;
import com.services.fastmart.entity.OrderProduct;
import com.services.fastmart.entity.User;
import com.services.fastmart.rest.request.LoginRequest;

public interface FastmartService {
	
	public User saveNewUser(User user);
	
	public User getUserByEmail(String userEmail);
	
	public boolean checkUserIsAuth(LoginRequest loginRequest);
	
	public String checkExistingUser(String email);
	
	public User updateUserPassword(String email,String password);

	public List<Product> getAllItems();

	public Product addNewProduct(Product product);

	public Product updateProduct(Product product);

	public Product getProduct(String id);
	
	public List<Product> getTopRatedMobiles();

	public List<String> getAllCategories();

	public List<String> getSubCategoryNames(String itemCategory);

	public List<Product> getProductsBySubCategory(String itemCategory, String itemSubCategory);

	public ProductReview addProductReview(ProductReview productReview);

	public List<ProductReview> getProductReviews(String itemId);

	public List<ProductReview> getProductTopReviews(String itemId);
	
	public List<Product> getProductsBySearch(String queryString);

	public Order saveOrder(Order order);

	public List<Order> getAllOrders(String userEmail);

	public List<OrderProduct> getOrderProducts(String userEmail, String orderId);

	public Order getOrder(String userEmail, String orderId);
	
	public Order getLatestOrder(String userEmail);

	public Cart addProductToCart(String userEmail, CartProduct cartProduct);

	public Cart deleteProductInCart(String userEmail, String itemId);

	public Cart getCart(String userEmail);

	public List<CartProduct> getCartProducts(String userEmail);

	public Cart changeProductQuantityInCart(String userEmail, String itemId, int change);

	double cartAmount(String userEmail);

}
