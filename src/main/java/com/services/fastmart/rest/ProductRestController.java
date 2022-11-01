package com.services.fastmart.rest;

import java.util.List;

import com.services.fastmart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.fastmart.entity.ProductReview;
import com.services.fastmart.service.FastmartService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductRestController {
	
	@Autowired
	private FastmartService fastmartService;
	
	@GetMapping("/list")
	public List<Product> getAllProducts(){
		return fastmartService.getAllItems();
	}
	
	@PostMapping("/add")
	public Product addNewProduct(@RequestBody Product product) {
		fastmartService.addNewProduct(product);
		return product;
	}
	
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return fastmartService.updateProduct(product);
	}
	
	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return fastmartService.getProduct(productId);
	}
	
	@GetMapping("/names/categories")
	public List<String> getAllCategoryNames(){
		return fastmartService.getAllCategories();
	}
	
	@GetMapping("/names/subcategory/{categoryName}")
	public List<String> getSubCategoryNames(@PathVariable String categoryName ){
		return fastmartService.getSubCategoryNames(categoryName);
	}
	
	@GetMapping("/{categoryName}/{subCategoryName}")
	public List<Product> getProductsBySubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName){
		return fastmartService.getProductsBySubCategory(categoryName, subCategoryName);
	}
	
	@PostMapping("/review")
	public ProductReview addProductReview(@RequestBody ProductReview productReview) {
		return fastmartService.addProductReview(productReview);
	}
	
	@GetMapping("/reviews/{productId}")
	public List<ProductReview> getProductReviews(@PathVariable String productId){
		return fastmartService.getProductReviews(productId);
	}
	
	@GetMapping("/reviews/top/{productId}")
	public List<ProductReview> getProductTopReviews(@PathVariable String productId){
		return fastmartService.getProductTopReviews(productId);
	}
	
	@GetMapping("/search/{queryString}")
	public List<Product> getProductsBySearchString(@PathVariable String queryString){
		return fastmartService.getProductsBySearch(queryString);
	}
	
	@GetMapping("/top-rated/mobiles")
	public List<Product> getTopRatedMobiles(){
		return fastmartService.getTopRatedMobiles();
	}

}
