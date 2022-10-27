package com.services.fastmart.entity;

public class DatabaseFields {
    //Item
    public static final String PRODUCT_COLLECTION = "products";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_SUB_CATEGORY = "subCategory";
    public static final String PRODUCT_MANUFACTURER = "manufacturer";
    public static final String PRODUCT_STOCK = "stock";
    public static final String PRODUCT_RATING = "rating";
    public static final String PRODUCT_NUM_RATINGS = "numRatings";
    public static final String PRODUCT_IMAGE_URL = "imageUrl";

    // product review
    public static final String PRODUCT_REVIEW_COLLECTION = "product-reviews";
    public static final String PRODUCT_REVIEW_ID = "reviewId";
    public static final String PRODUCT_REVIEW = "review";
    public static final String PRODUCT_REVIEW_POSTED_TIME = "postedTime";
    public static final String PRODUCT_REVIEW_RATING = "rating";

    //Cart
    public static final String CART_COLLECTION = "cart";
    public static final String CART_ID = "cartId";
    public static final String CART_AMOUNT = "cartAmount";
    public static final String CART_PRODUCTS = "cartProducts";

    //Cart Item
    public static final String CART_PRODUCT_QUANTITY = "productQuantity";
    public static final String CART_PRODUCT_PRICE = "productPrice";
    public static final String CART_PRODUCT_MANUFACTURER = "productManufacturer";
    public static final String CART_PRODUCT_STOCK = "productStock" ;


    //User
    public static final String USER_COLLECTION = "users";
    public static final String USER_ID = "userId";
    public static final String USER_EMAIL = "userEmail";
    public static final String USERNAME = "username";
    public static final String USER_PRETTY_NAME = "prettyName";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";


    //Order
    public static final String ORDER_COLLECTION = "orders";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_TIME = "orderTime";
    public static final String ORDER_AMOUNT = "orderAmount";
    public static final String ORDER_PRODUCTS = "orderProducts";
    public static final String ORDER_ADDRESS = "orderAddress";
    public static final String ORDER_PRODUCT_QUANTITY = "productQuantity" ;


    //Order Address
    public static final String ORDER_CUSTOMER_NAME = "customerName";
    public static final String ORDER_CUSTOMER_MOBILE = "customerMobile";
    public static final String ADDRESS = "address";
    public static final String ADDRESS_CITY = "city";
    public static final String ADDRESS_STATE = "state";
    public static final String ADDRESS_PINCODE = "pincode";
}
