package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Item {
	
	@Transient
    public static final String SEQUENCE_NAME = "item_sequence";

	@Id
	@Indexed
	private long itemId;

	private String itemName;

	private int itemPrice;

	private String itemCategory;

	private String itemSubCategory;

	private String itemManufacturer;

	private int itemStock;

	private double itemRating;

	private int numRatings;

	private String itemImageUrl;

	public Item() {

	}

	public Item(String itemName, int itemPrice, String itemCategory, String itemSubCategory, String itemManufacturer,
			int itemStock, double itemRating, int numRatings, String itemImageUrl) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemSubCategory = itemSubCategory;
		this.itemManufacturer = itemManufacturer;
		this.itemStock = itemStock;
		this.itemRating = itemRating;
		this.numRatings = numRatings;
		this.itemImageUrl = itemImageUrl;
	}

	

	public String getItemImageUrl() {
		return itemImageUrl;
	}

	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}

	public double getItemRating() {
		return itemRating;
	}

	public void setItemRating(double itemRating) {
		this.itemRating = itemRating;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSubCategory() {
		return itemSubCategory;
	}

	public void setItemSubCategory(String itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}

	public String getItemManufacturer() {
		return itemManufacturer;
	}

	public void setItemManufacturer(String itemManufacturer) {
		this.itemManufacturer = itemManufacturer;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCategory="
				+ itemCategory + ", itemSubCategory=" + itemSubCategory + ", itemManufacturer=" + itemManufacturer
				+ ", itemStock=" + itemStock + ", itemRating=" + itemRating + ", numRatings=" + numRatings + "]";
	}

}
