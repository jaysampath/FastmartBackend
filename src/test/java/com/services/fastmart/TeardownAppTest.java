package com.services.fastmart;

import com.services.fastmart.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeardownAppTest {

    // DO NOT RUN THIS TEST. ONLY FOR DEVELOPMENT PURPOSES
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void teardownTest() {
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        productReviewRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();

        assertTrue(orderRepository.findAll().isEmpty());
        assertTrue(cartRepository.findAll().isEmpty());
        assertTrue(productReviewRepository.findAll().isEmpty());
        assertTrue(productRepository.findAll().isEmpty());
        assertTrue(userRepository.findAll().isEmpty());
    }
}
