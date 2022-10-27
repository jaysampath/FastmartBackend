package com.services.fastmart.entity;

import org.springframework.data.mongodb.core.mapping.Field;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "customerName='" + customerName + '\'' +
                ", customerMobile='" + customerMobile + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
