package com.services.fastmart.dao;

import java.util.List;

import com.services.fastmart.entity.Product;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.ProductReview;

@Repository
public interface ProductsDao {

	List<Product> getAllProducts();

	Product updateProduct(Product product);

	Product getProductById(String id);

	Product addNewProduct(Product product);

	List<String> getProductCategoryNames();

	Product findProductByNameAndCompany(String itemName, String itemManufacturer);
	
	List<String> getProductSubCategoryNames(String category);
	
	List<Product> getProductsBySubCategory(String category, String subCategory);
	
	ProductReview addProductReview(ProductReview productReview);
	
	List<ProductReview> getProductReviews(String productId);
	
	List<ProductReview> getProductTopReviews(String productId);
	
	List<Product> getProductsBySearch(String queryString);
	
	List<Product> getTopRatedMobiles();

}
