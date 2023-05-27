package com.services.fastmart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.PRODUCT_REVIEW_COLLECTION)
@Getter
@Setter
@ToString
@Builder
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

}
