package com.lab4.demo.item;

import com.lab4.demo.review.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void beforeEach() {
        reviewRepository.deleteAll();
        repository.deleteAll();
    }


}
