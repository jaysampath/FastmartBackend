package com.services.fastmart.repository;

import com.services.fastmart.entity.ProductReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReview, String> {

    List<ProductReview> findAllByProductId(String productId);

    Stream<ProductReview> findAllByProductId(String productId, Sort sot);
}
