package com.services.fastmart.repository;

import com.services.fastmart.entity.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

    List<Order> findAllByUserEmail(String userEmail);
}
