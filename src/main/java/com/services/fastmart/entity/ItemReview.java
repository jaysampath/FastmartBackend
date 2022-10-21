package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="item_review")
public class ItemReview {
	
	@Transient
    public static final String SEQUENCE_NAME = "item_review_sequence";
	
    @Id
    @Indexed
	private long id;
	

	private String userEmail;
	

	private String review;
	

	private String postedTime;
	
	private float rating;
	

	private int itemId;
	
	public ItemReview() {
		
	}

	public ItemReview(String userEmail, String review, String postedTime, float rating, int itemId) {
		this.userEmail = userEmail;
		this.review = review;
		this.postedTime = postedTime;
		this.rating = rating;
		this.itemId = itemId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "ItemReview [id=" + id + ", userEmail=" + userEmail + ", review=" + review + ", postedTime=" + postedTime
				+ ", rating=" + rating + ", itemId=" + itemId + "]";
	}

	

	
	
	

}
