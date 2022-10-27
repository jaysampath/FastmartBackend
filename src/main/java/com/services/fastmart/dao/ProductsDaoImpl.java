package com.services.fastmart.dao;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.services.fastmart.entity.DatabaseFields;
import com.services.fastmart.entity.Product;
import com.services.fastmart.repository.ProductRepository;
import com.services.fastmart.repository.ProductReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.services.fastmart.entity.ProductReview;

@Component
public class ProductsDaoImpl implements ProductsDao {

    Logger logger = LoggerFactory.getLogger(ProductsDaoImpl.class);

    private final MongoTemplate mongoTemplate;

    private final ProductRepository productRepository;

    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductsDaoImpl(MongoTemplate mongoTemplate, ProductRepository productRepository, ProductReviewRepository productReviewRepository) {
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
    }

    @Override
    public Product findProductByNameAndCompany(String productName, String manufacturer) {
        return productRepository.findProductByProductNameAndManufacturer(productName, manufacturer);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Update update = new Update();
        update.set(DatabaseFields.PRODUCT_STOCK, product.getStock());
        update.set(DatabaseFields.PRODUCT_PRICE, product.getPrice());
        update.set(DatabaseFields.PRODUCT_CATEGORY, product.getCategory());
        update.set(DatabaseFields.PRODUCT_SUB_CATEGORY, product.getSubCategory());
        update.set(DatabaseFields.PRODUCT_NAME, product.getProductName());
        update.set(DatabaseFields.PRODUCT_NUM_RATINGS, product.getNumRatings());
        update.set(DatabaseFields.PRODUCT_IMAGE_URL, product.getImageUrl());
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true);

        Product updatedProduct = mongoTemplate.findAndModify(
                new Query().addCriteria(Criteria.where(DatabaseFields.PRODUCT_ID).is(product.getProductId())),
                update, findAndModifyOptions, Product.class, DatabaseFields.PRODUCT_COLLECTION);

        logger.info("Product updated - {}", product);
        return updatedProduct;
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        Product checkProduct = productRepository.findProductByProductNameAndManufacturer(product.getProductName(), product.getManufacturer());
        if (checkProduct == null) {
            Product persistedProduct = productRepository.save(product);
            logger.info("Persisted new product - {}", persistedProduct);
            return persistedProduct;
        }
        return updateProduct(product);
    }

    @Override
    public List<String> getProductCategoryNames() {
        return mongoTemplate.findDistinct(DatabaseFields.PRODUCT_CATEGORY, Product.class, String.class);
    }

    @Override
    public List<String> getProductSubCategoryNames(String categoryName) {
        Query query = new Query().addCriteria(Criteria.where(DatabaseFields.PRODUCT_CATEGORY).is(categoryName));
        return mongoTemplate.findDistinct(query, DatabaseFields.PRODUCT_SUB_CATEGORY, Product.class, String.class);
    }

    @Override
    public List<Product> getProductsBySubCategory(String category, String subCategory) {
        Query query = new Query().addCriteria(new Criteria().andOperator(Criteria.where(DatabaseFields.PRODUCT_CATEGORY).is(category),
                Criteria.where(DatabaseFields.PRODUCT_SUB_CATEGORY).is(subCategory)));
        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public ProductReview addProductReview(ProductReview productReview) {
        productReview.setPostedTime(System.currentTimeMillis());
        productReviewRepository.save(productReview);
        updateItemRatings(productReview.getProductId(), productReview.getRating());
        return productReview;
    }

    public void updateItemRatings(String productId, float rating) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return;
        Product product = optionalProduct.get();
        double prevRating = product.getRating();
        int prevNumRatings = product.getNumRatings();
        double newRating = (((prevRating * prevNumRatings) + rating) / (prevNumRatings + 1));
        DecimalFormat df = new DecimalFormat("#.##");
        newRating = Double.parseDouble(df.format(newRating));
        Update update = new Update();
        update.set(DatabaseFields.PRODUCT_NUM_RATINGS, prevNumRatings + 1);
        update.set(DatabaseFields.PRODUCT_RATING, newRating);
        mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("_id").is(product.getProductId())),
                update,
                DatabaseFields.PRODUCT_COLLECTION);
    }

    @Override
    public List<ProductReview> getProductReviews(String productId) {
        return productReviewRepository.findAllByProductId(productId);
    }

    @Override
    public List<ProductReview> getProductTopReviews(String productId) {
        Sort sort = Sort.by(Direction.DESC, DatabaseFields.PRODUCT_REVIEW_POSTED_TIME);
        return productReviewRepository.findAllByProductId(productId, sort)
                .limit(4)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsBySearch(String queryString) {
        String regexString = ".*" + queryString + "*.";
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where(DatabaseFields.PRODUCT_NAME).regex(regexString, "i"),
                Criteria.where(DatabaseFields.PRODUCT_CATEGORY).regex(regexString, "i"),
                Criteria.where(DatabaseFields.PRODUCT_SUB_CATEGORY).regex(regexString, "i"),
                Criteria.where(DatabaseFields.PRODUCT_MANUFACTURER).regex(regexString, "i")
        ));
        query.limit(15);
        return mongoTemplate.find(query, Product.class, DatabaseFields.PRODUCT_COLLECTION);
    }

    @Override
    public List<Product> getTopRatedMobiles() {
        Query query = new Query().addCriteria(Criteria.where(DatabaseFields.PRODUCT_CATEGORY).is("Mobiles"));
        query.with(Sort.by(Direction.DESC, DatabaseFields.PRODUCT_PRICE));
        query.limit(6);
        return mongoTemplate.find(query, Product.class, DatabaseFields.PRODUCT_COLLECTION);
    }

}
