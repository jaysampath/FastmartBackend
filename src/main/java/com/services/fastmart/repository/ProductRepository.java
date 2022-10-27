package com.services.fastmart.repository;

import com.services.fastmart.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findProductByProductNameAndManufacturer(String productName, String manufacturer);

    Product findByProductId(String productId);
}
