package com.services.fastmart.entity;

import org.springframework.data.mongodb.core.mapping.Field;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
