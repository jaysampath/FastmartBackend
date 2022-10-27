package com.services.fastmart.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.fastmart.entity.Product;
import com.services.fastmart.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class SaveStarterProducts implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(SaveStarterProducts.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Bootstrapping Fastmart..");

        if (!productRepository.findAll().isEmpty()) {
            logger.info("Products already present. Bootstrap skipped");
            return;
        }

        List<Product> products = getStarterProducts();
        products.forEach(product -> productRepository.save(product));

        if (productRepository.findAll().isEmpty()) {
            logger.error("Something went wrong. Bootstrap failed");
        } else {
            logger.info("Bootstrap finished successfully");
        }
    }

    public List<Product> getStarterProducts() {
        List<Product> productList;
        try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("starter-products.json")){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            productList = mapper.readValue(jsonString, new TypeReference<List<Product>>() {});
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        return productList;
    }
}
