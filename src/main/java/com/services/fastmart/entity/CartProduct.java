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
public class CartProduct {

    @Field(DatabaseFields.PRODUCT_ID)
    private String productId;

    @Field(DatabaseFields.PRODUCT_NAME)
    private String productName;

    @Field(DatabaseFields.PRODUCT_IMAGE_URL)
    private String productImageUrl;

    @Field(DatabaseFields.CART_PRODUCT_STOCK)
    private int productStock;

    @Field(DatabaseFields.CART_PRODUCT_MANUFACTURER)
    private String productManufacturer;

    @Field(DatabaseFields.CART_PRODUCT_QUANTITY)
    private int productQuantity;

    @Field(DatabaseFields.CART_PRODUCT_PRICE)
    private int productPrice;
}
