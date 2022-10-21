package com.services.fastmart.dao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.services.fastmart.entity.Item;
import com.services.fastmart.entity.ItemReview;
import com.services.fastmart.service.EcommerceService;
import com.services.fastmart.service.SequenceGeneratorService;

@Repository
public class ItemsRepositoryImpl implements ItemsRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	SimpleDateFormat sdf = new SimpleDateFormat();

	Logger logger = LoggerFactory.getLogger(EcommerceService.class);

	@Override
	public Item findItemByNameAndCompany(String itemName, String itemManufacturer) {
		
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("itemName").is(itemName),
				Criteria.where("itemManufacturer").is(itemManufacturer)));
		List<Item> item = mongoTemplate.find(query, Item.class);
		if (item.size() == 0)
			return null;
		else
			return item.get(0);
	}

	@Override
	public List<Item> getAllItems() {
		
		Query query = new Query();
		query.limit(60);
		List<Item> items = mongoTemplate.find(query,Item.class);
		return items;
	}

	@Override
	public Item updateItem(Item item) {
		
		Item updatedItem = mongoTemplate.save(item, "item");
		return updatedItem;
	}

	@Override
	public Item getItemById(long id) {
		
		Item item = mongoTemplate.findById(id, Item.class);
		return item;
	}

	@Override
	public Item addNewItem(Item item) {
		
		Item checkItem = findItemByNameAndCompany(item.getItemName(), item.getItemManufacturer());
		if (checkItem == null) {
			item.setItemId(SequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
			mongoTemplate.insert(item, "item");
			return item;
		}
		int prevStock = checkItem.getItemStock();
		double prevRating = checkItem.getItemRating();
		int prevNumRatings = checkItem.getNumRatings();
		item.setItemId(checkItem.getItemId());
		item.setItemRating(prevRating);
		item.setItemStock((item.getItemStock() + prevStock));
		item.setNumRatings(prevNumRatings);
		mongoTemplate.save(item, "item");
		return item;
	}

	@Override
	public List<String> getItemCategoryNames() {
		
		List<String> names = mongoTemplate.findDistinct("itemCategory", Item.class, String.class);
		return names;
	}

	@Override
	public List<String> getItemSubCategoryNames(String categoryName) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("itemCategory").is(categoryName));
		List<String> subCategoryNames = mongoTemplate.findDistinct(query, "itemSubCategory", Item.class, String.class);
		return subCategoryNames;
	}

	@Override
	public List<Item> getItemsBySubCategory(String category, String subCategory) {
		
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("itemCategory").is(category),
				Criteria.where("itemSubCategory").is(subCategory)));
		List<Item> items = mongoTemplate.find(query, Item.class);
		return items;
	}

	@Override
	public ItemReview addItemReview(ItemReview itemReview) {
		
		itemReview.setId(SequenceGeneratorService.generateSequence(ItemReview.SEQUENCE_NAME));
		itemReview.setPostedTime(sdf.format(System.currentTimeMillis()));
		mongoTemplate.insert(itemReview, "item_review");
		updateItemRatings(itemReview.getItemId(), itemReview.getRating());
		return itemReview;
	}

	public void updateItemRatings(int itemId, float rating) {

		Item item = mongoTemplate.findById(itemId, Item.class);
		double prevRating = item.getItemRating();
		int prevNumRatings = item.getNumRatings();
		double newRating = 0;
		newRating = (((prevRating * prevNumRatings) + rating) / (prevNumRatings + 1));
		DecimalFormat df = new DecimalFormat("#.##");
		newRating = Double.parseDouble(df.format(newRating));
		item.setItemRating(newRating);
		item.setNumRatings(prevNumRatings + 1);
		mongoTemplate.save(item, "item");
	}

	@Override
	public List<ItemReview> getItemReviews(long id) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("itemId").is(id));
		query.limit(50);
		List<ItemReview> itemReviews = mongoTemplate.find(query, ItemReview.class);
		return itemReviews;
	}

	@Override
	public List<ItemReview> getItemTopReviews(long id) {
		
		Query queryForBottomTwoRating = new Query();
		queryForBottomTwoRating.addCriteria(Criteria.where("itemId").is(id));
		if (mongoTemplate.count(queryForBottomTwoRating, ItemReview.class) <= 4) {
			return mongoTemplate.find(queryForBottomTwoRating, ItemReview.class, "item_review");
		}

		queryForBottomTwoRating.with(Sort.by(Direction.ASC, "rating"));
		queryForBottomTwoRating.limit(2);
		List<ItemReview> itemAscTopTwoReviews = mongoTemplate.find(queryForBottomTwoRating, ItemReview.class,
				"item_review");
		Query queryForTopTwoRating = new Query();
		queryForTopTwoRating.with(Sort.by(Direction.DESC, "rating"));
		queryForTopTwoRating.limit(2);
		List<ItemReview> itemDescTopTwoRating = mongoTemplate.find(queryForTopTwoRating, ItemReview.class,
				"item_review");
		List<ItemReview> itemTopReviews = new ArrayList<>(itemDescTopTwoRating);
		itemTopReviews.addAll(itemAscTopTwoReviews);
		return itemTopReviews;
	}

	@Override
	public List<Item> getItemsBySearch(String queryString) {
		
		Query query = new Query();
		String[] arr = queryString.split(" ");
		if (arr.length == 2) {
			String temp = String.valueOf(arr[0].charAt(0));
			temp = temp.toUpperCase();
			arr[0] = temp + arr[0].substring(1);
			query.addCriteria(new Criteria().orOperator(Criteria.where("itemName").regex(".*"+queryString+".*", "i"),
					Criteria.where("itemCategory").regex(".*"+queryString+".*", "i"), Criteria.where("itemManufacturer").regex(".*"+queryString+".*", "i"),
					Criteria.where("itemSubCategory").regex(".*"+queryString+".*", "i"),new Criteria().andOperator(Criteria.where("itemManufacturer").regex(".*"+arr[0]+".*", "i"),
							Criteria.where("itemSubCategory").regex(".*"+arr[1]+".*", "i"))));
			if(mongoTemplate.count(query, Item.class,"item")==0) {
				query = new Query();
				query.addCriteria(new Criteria().orOperator(Criteria.where("itemName").regex(".*"+queryString+".*", "i"),
						Criteria.where("itemCategory").regex(".*"+queryString+".*", "i"), Criteria.where("itemManufacturer").regex(".*"+queryString+".*", "i"),
						Criteria.where("itemSubCategory").regex(".*"+queryString+".*", "i")));
			}
		}else {
			query.addCriteria(new Criteria().orOperator(Criteria.where("itemName").regex(queryString,"i"),
					Criteria.where("itemCategory").regex(".*"+queryString+".*", "i"), Criteria.where("itemManufacturer").regex(".*"+queryString+".*", "i"),
					Criteria.where("itemSubCategory").regex(".*"+queryString+".*", "i")));
		}
		
		List<Item> itemsBySearch = mongoTemplate.find(query, Item.class, "item");
		query.limit(15);
		return itemsBySearch;
	}

	@Override
	public List<Item> getTopRatedMobiles() {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("itemCategory").is("Mobiles"));
		query.with(Sort.by(Direction.DESC, "itemPrice"));
		query.limit(6);
		List<Item> topRatedMobiles = mongoTemplate.find(query, Item.class, "item");
		return topRatedMobiles;
	}

}
