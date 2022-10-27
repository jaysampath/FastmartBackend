package com.services.fastmart.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.ORDER_COLLECTION)
public class Order {

    @Id
    private ObjectId orderId;

    @Field(DatabaseFields.USER_EMAIL)
    private String userEmail;

    @Field(DatabaseFields.ORDER_TIME)
    private String orderTime;

    @Field(DatabaseFields.ORDER_AMOUNT)
    private double orderAmount;

    @Field(DatabaseFields.ORDER_PRODUCTS)
    private List<OrderProduct> orderProducts;

    @Field(DatabaseFields.ORDER_ADDRESS)
    private OrderAddress orderAddress;

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderId() {
        return orderId != null ? orderId.toString() : null;
    }

    public void setOrderId(String orderId) {
        this.orderId = new ObjectId(orderId);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", userEmail=" + userEmail + ", orderTime=" + orderTime + ", orderAmount="
                + orderAmount + ", orderAddress=" + orderAddress + ", orderProducts=" + orderProducts + "]";
    }


}
