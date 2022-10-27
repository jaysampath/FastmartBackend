package com.services.fastmart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.PRODUCT_REVIEW_COLLECTION)
public class ProductReview {

    @Id
    private String reviewId;

    @Field(DatabaseFields.USER_EMAIL)
    private String userEmail;

    @Field(DatabaseFields.PRODUCT_REVIEW)
    private String review;

    @Field(DatabaseFields.PRODUCT_REVIEW_POSTED_TIME)
    private long postedTime;

    @Field(DatabaseFields.PRODUCT_REVIEW_RATING)
    private float rating;

    @Field(DatabaseFields.PRODUCT_ID)
    private String productId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(long postedTime) {
        this.postedTime = postedTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "reviewId='" + reviewId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", review='" + review + '\'' +
                ", postedTime='" + postedTime + '\'' +
                ", rating=" + rating +
                ", productId=" + productId +
                '}';
    }
}
