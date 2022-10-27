package com.services.fastmart.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.CART_COLLECTION)
public class Cart {

    @Id
    private String cartId;

    @Field(DatabaseFields.USER_EMAIL)
    private String userEmail;

    @Field(DatabaseFields.CART_AMOUNT)
    private double cartAmount;

    @Field(DatabaseFields.CART_PRODUCTS)
    private List<CartProduct> cartProducts;

    public Cart() {}

    public Cart(String userEmail, List<CartProduct> cartProducts, double cartAmount) {
        this.userEmail = userEmail;
        this.cartAmount = cartAmount;
        this.cartProducts = cartProducts;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public double getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(double cartAmount) {
        this.cartAmount = cartAmount;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", userEmail=" + userEmail + ", cartAmount=" + cartAmount + ", cartProducts="
                + cartProducts + "]";
    }

}
