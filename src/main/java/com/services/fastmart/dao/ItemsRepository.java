package com.services.fastmart.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Item;
import com.services.fastmart.entity.ItemReview;

@Repository
public interface ItemsRepository {

	List<Item> getAllItems();

	Item updateItem(Item item);

	Item getItemById(long id);

	Item addNewItem(Item item);

	List<String> getItemCategoryNames();

	Item findItemByNameAndCompany(String itemName, String itemManufacturer);
	
	List<String> getItemSubCategoryNames(String category);
	
	List<Item> getItemsBySubCategory(String category, String subCategory);
	
	ItemReview addItemReview(ItemReview itemReview);
	
	List<ItemReview> getItemReviews(long id);
	
	List<ItemReview> getItemTopReviews(long id);
	
	List<Item> getItemsBySearch(String queryString);
	
	List<Item> getTopRatedMobiles();

}
