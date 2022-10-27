package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.PRODUCT_COLLECTION)
public class Product {
	@Id
	private String productId;

	@Field(DatabaseFields.PRODUCT_NAME)
	private String productName;

	@Field(DatabaseFields.PRODUCT_PRICE)
	private int price;

	@Field(DatabaseFields.PRODUCT_CATEGORY)
	private String category;

	@Field(DatabaseFields.PRODUCT_SUB_CATEGORY)
	private String subCategory;

	@Field(DatabaseFields.PRODUCT_MANUFACTURER)
	private String manufacturer;

	@Field(DatabaseFields.PRODUCT_STOCK)
	private int stock;

	@Field(DatabaseFields.PRODUCT_RATING)
	private double rating;

	@Field(DatabaseFields.PRODUCT_NUM_RATINGS)
	private int numRatings;

	@Field(DatabaseFields.PRODUCT_IMAGE_URL)
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				", subCategory='" + subCategory + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", stock=" + stock +
				", rating=" + rating +
				", numRatings=" + numRatings +
				", imageUrl='" + imageUrl + '\'' +
				'}';
	}
}
