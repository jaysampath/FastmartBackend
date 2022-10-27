package com.services.fastmart.entity;


import org.springframework.data.mongodb.core.mapping.Field;

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


    public String getProductImageUrl() {
        return productImageUrl;
    }


    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }


    public int getProductStock() {
        return productStock;
    }


    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }


    public String getProductManufacturer() {
        return productManufacturer;
    }


    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", productStock=" + productStock +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
