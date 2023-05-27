package com.services.fastmart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.PRODUCT_COLLECTION)
@Getter
@Setter
@ToString
@Builder
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

}
