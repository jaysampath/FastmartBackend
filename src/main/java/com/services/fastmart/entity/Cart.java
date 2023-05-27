package com.services.fastmart.entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = DatabaseFields.CART_COLLECTION)
@Getter
@Setter
@ToString
@Builder
public class Cart {

    @Id
    private String cartId;

    @Field(DatabaseFields.USER_EMAIL)
    private String userEmail;

    @Field(DatabaseFields.CART_AMOUNT)
    private double cartAmount;

    @Field(DatabaseFields.CART_PRODUCTS)
    private List<CartProduct> cartProducts;

}
