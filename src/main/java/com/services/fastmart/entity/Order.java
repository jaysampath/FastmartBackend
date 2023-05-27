package com.services.fastmart.entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.ORDER_COLLECTION)
@Getter
@Setter
@ToString
@Builder
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


}
