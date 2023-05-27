package com.services.fastmart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Builder
public class OrderProduct {

    @Field(DatabaseFields.PRODUCT_ID)
    private String productId;

    @Field(DatabaseFields.PRODUCT_NAME)
    private String productName;

    @Field(DatabaseFields.ORDER_PRODUCT_QUANTITY)
    private int productQuantity;

    @Field(DatabaseFields.PRODUCT_IMAGE_URL)
    private String productImageUrl;

    @Field(DatabaseFields.PRODUCT_PRICE)
    private int productPrice;
}
