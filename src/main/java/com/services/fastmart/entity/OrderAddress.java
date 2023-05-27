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
public class OrderAddress {

    @Field(DatabaseFields.ORDER_CUSTOMER_NAME)
    private String customerName;

    @Field(DatabaseFields.ORDER_CUSTOMER_MOBILE)
    private String customerMobile;

    @Field(DatabaseFields.ADDRESS)
    private String address;

    @Field(DatabaseFields.ADDRESS_CITY)
    private String city;

    @Field(DatabaseFields.ADDRESS_STATE)
    private String state;

    @Field(DatabaseFields.ADDRESS_PINCODE)
    private String pincode;
}
