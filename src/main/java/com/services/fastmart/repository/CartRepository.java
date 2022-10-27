package com.services.fastmart.repository;

import com.services.fastmart.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Cart findCartByUserEmail(String userEmail);
}
