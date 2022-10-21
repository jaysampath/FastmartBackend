package com.services.fastmart.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.DistinctIterable;
import com.services.fastmart.entity.Item;
import com.services.fastmart.entity.ItemReview;
import com.services.fastmart.service.EcommerceService;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemRestController {
	
	@Autowired
	private EcommerceService eCommerceService;
	
	@GetMapping("/items")
	public List<Item> getItems(){
		List<Item> items = eCommerceService.getAllItems();
		return items;
	}
	
	@PostMapping("/items")
	public Item AddNewItem(@RequestBody Item item) {
		//item.setItemId(0);
		eCommerceService.addNewItem(item);
		return item;
	}
	
	@PutMapping("/items")
	public Item updateItem(@RequestBody Item item) {
	 Item updatedItem =	eCommerceService.updateItem(item);
		return updatedItem;
	}
	
	@GetMapping("/items/{itemId}")
	public Item GetItem(@PathVariable int itemId) {
		Item item = eCommerceService.getItem(itemId);
		return item;
	}
	
	@GetMapping("/names/categories")
	public List<String> getAllCategoryNames(){
		List<String> categoryNames = eCommerceService.getAllCategories();
		return categoryNames;
	}
	
	@GetMapping("/names/subcategory/{categoryName}")
	public List<String> getSubCategoryNames(@PathVariable String categoryName ){
		List<String> subCategoryNames = eCommerceService.getSubCategoryNames(categoryName);
		return subCategoryNames;
	}
	
	@GetMapping("/items/{categoryName}/{subCategoryName}")
	public List<Item> getItemsBySubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName){
		List<Item> items = eCommerceService.getItemsBySubCategory(categoryName, subCategoryName);
		return items;
	}
	
	@PostMapping("/review")
	public ItemReview addItemReview(@RequestBody ItemReview itemReview) {
		ItemReview review = eCommerceService.addItemReview(itemReview);
		return review;
	}
	
	@GetMapping("/reviews/{itemId}")
	public List<ItemReview> getItemReviews(@PathVariable long itemId){
		List<ItemReview> itemReviews = eCommerceService.getItemReviews(itemId);
		return itemReviews;
	}
	
	@GetMapping("/reviews/top/{itemId}")
	public List<ItemReview> getItemTopReviews(@PathVariable long itemId){
		List<ItemReview> topReviews = eCommerceService.getItemTopReviews(itemId);
		return topReviews;
	}
	
	@GetMapping("/search/{queryString}")
	public List<Item> getItemsBySearchString(@PathVariable String queryString){
		List<Item> getItemsBySearch = eCommerceService.getItemsBySearch(queryString);
		return getItemsBySearch;
	}
	
	@GetMapping("/top/mobiles")
	public List<Item> getTopRatedMobiles(){
		List<Item> topMobiles = eCommerceService.getTopRatedMobiles();
		return topMobiles;
	}

}
