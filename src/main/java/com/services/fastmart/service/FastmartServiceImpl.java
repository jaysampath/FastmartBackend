package com.services.fastmart.service;

import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;

import com.services.fastmart.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.services.fastmart.dao.CartDao;
import com.services.fastmart.dao.ProductsDao;
import com.services.fastmart.dao.OrderDao;
import com.services.fastmart.dao.UserDao;
import com.services.fastmart.entity.Product;
import com.services.fastmart.exception.EmailActionException;
import com.services.fastmart.exception.ItemActionException;
import com.services.fastmart.rest.request.LoginRequest;
import com.services.fastmart.exception.OrderActionException;
import com.services.fastmart.exception.UserActionException;

@Service
public class FastmartServiceImpl implements FastmartService {

    @Autowired
    private ProductsDao productsDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private EmailService emailService;

    private final static Logger logger = LoggerFactory.getLogger(FastmartServiceImpl.class);

    @Override
    public List<Product> getAllItems() {
        return productsDao.getAllProducts();
    }

    @Override
    public List<Product> getTopRatedMobiles() {
        return productsDao.getTopRatedMobiles();
    }

    @Override
    @Async
    public Product addNewProduct(Product product) {
        return productsDao.addNewProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productsDao.updateProduct(product);
    }

    @Override
    public Product getProduct(String id) {
        Product product = productsDao.getProductById(id);
        if (product == null) {
            throw new ItemActionException("Item with id-" + id + " not found");
        }
        return product;
    }

    @Override
    public List<String> getAllCategories() {
        return productsDao.getProductCategoryNames();
    }

    @Override
    public List<String> getSubCategoryNames(String itemCategory) {
        return productsDao.getProductSubCategoryNames(itemCategory);
    }

    @Override
    @Async
    public List<Product> getProductsBySubCategory(String itemCategory, String itemSubCategory) {
        return productsDao.getProductsBySubCategory(itemCategory, itemSubCategory);
    }

    @Override
    @Async
    public ProductReview addProductReview(ProductReview productReview) {
        return productsDao.addProductReview(productReview);
    }

    @Override
    @Async
    public List<ProductReview> getProductReviews(String itemId) {
        return productsDao.getProductReviews(itemId);
    }

    @Override
    public List<ProductReview> getProductTopReviews(String itemId) {
        return productsDao.getProductTopReviews(itemId);
    }

    @Override
    public List<Product> getProductsBySearch(String queryString) {
        return productsDao.getProductsBySearch(queryString);
    }


    @Override
    public Order saveOrder(Order order) {
        double orderAmount = 0;
        List<OrderProduct> orderProducts = order.getOrderProducts();
        for (OrderProduct oItem : orderProducts) {
            Product product = productsDao.getProductById(oItem.getProductId());
            if (product == null) {
                throw new ItemActionException("invalid item id. No item present in the database with that id");
            }
            int prevStock = product.getStock();
            int finalStock = prevStock - oItem.getProductQuantity();
            if (finalStock < 0) {
                throw new OrderActionException(product.getProductName()
                        + " is Out of Stock or invalid item quantity. Available: " + product.getStock());
            }
            product.setStock(prevStock - oItem.getProductQuantity());
            productsDao.updateProduct(product);
            orderAmount = orderAmount + (product.getPrice() * oItem.getProductQuantity());
            oItem.setProductName(product.getProductName());
            oItem.setProductImageUrl(product.getImageUrl());
            oItem.setProductPrice(product.getPrice());

        }
        order.setOrderAmount(orderAmount);
        order.setOrderProducts(orderProducts);
        orderDao.saveOrder(order);
        for (OrderProduct oItem : orderProducts) {
            cartDao.deleteItemInCart(order.getUserEmail(), oItem.getProductId(), "");
        }
        try {
            emailService.sendOrderPlacedMail(order);
        } catch (MessagingException e) {
            throw new EmailActionException(e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders(String userEmail) {
        List<Order> orders = orderDao.getAllOrders(userEmail);
        Collections.reverse(orders);
        return orders;
    }

    @Override
    public List<OrderProduct> getOrderProducts(String userEmail, String orderId) {
        return orderDao.getOrderItems(userEmail, orderId);
    }

    @Override
    public Order getOrder(String userEmail, String orderId) {
        return orderDao.getOrder(userEmail, orderId);
    }

    @Override
    public Order getLatestOrder(String userEmail) {
        Order order = orderDao.getLatestOrder(userEmail);
        if (order == null) {
            throw new OrderActionException("No orders yet");
        }
        return order;
    }

    @Override
    public Cart addProductToCart(String userEmail, CartProduct cartProduct) {
        Product product = productsDao.getProductById(cartProduct.getProductId());
        if (product != null) {
            cartProduct.setProductImageUrl(product.getImageUrl());
            cartProduct.setProductManufacturer(product.getManufacturer());
            cartProduct.setProductStock(product.getStock());
            cartProduct.setProductName(product.getProductName());
            cartProduct.setProductPrice(product.getPrice());
        }
        return cartDao.addItemToCart(userEmail, cartProduct);
    }

    @Override
    @Async
    public Cart deleteProductInCart(String userEmail, String itemId) {
        return cartDao.deleteItemInCart(userEmail, itemId, "");
    }

    @Override
    public Cart getCart(String userEmail) {
        return cartDao.getCart(userEmail);
    }

    @Override
    public List<CartProduct> getCartProducts(String userEmail) {
        return cartDao.getCartItems(userEmail, "");
    }

    @Override
    public Cart changeProductQuantityInCart(String userEmail, String itemId, int change) {
        return cartDao.changeCartItemQuantity(userEmail, itemId, change);
    }

    @Override
    public double cartAmount(String userEmail) {
        Cart cart = getCart(userEmail);
        List<CartProduct> cartProducts = cart.getCartProducts();
        double totalAmount = 0;
        if (cartProducts.size() == 0) {
            cartDao.updateCartAmount(totalAmount, userEmail);
            return 0;
        }
        for (CartProduct cartProduct : cartProducts) {
            Product product = productsDao.getProductById(cartProduct.getProductId());
            if (product == null) {
                throw new ItemActionException("invalid item id - "+ cartProduct.getProductId() +". No item present in the database with that id");
            }
            totalAmount = totalAmount + (product.getPrice() * cartProduct.getProductQuantity());
        }
        cartDao.updateCartAmount(totalAmount, userEmail);
        return totalAmount;
    }

}
