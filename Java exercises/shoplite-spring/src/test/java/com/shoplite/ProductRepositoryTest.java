package com.shoplite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository repository;

    @BeforeEach
    void insertProducts() {
        repository.save(new Product("Mouse", 120));
        repository.save(new Product("Pad", 30));
        repository.save(new Product("Laptop", 9000));
    }

    @Test
    void findByPriceLessThanEqual_includesTheBoundary() {
        List<Product> result = repository.findByPriceLessThanEqual(120);
        assertThat(result)
                .extracting(Product::getName)
                .containsExactlyInAnyOrder("Mouse", "Pad");
    }

    @Test
    void findByPriceLessThanEqual_nothingCheapEnough_returnsEmpty() {
        assertThat(repository.findByPriceLessThanEqual(10)).isEmpty();
    }

    @Test
    void findByNameIgnoreCase_differentCase_findsIt() {
        assertThat(repository.findByNameIgnoreCase("mOuSe"))
                .isPresent()
                .get()
                .extracting(Product::getPrice)
                .isEqualTo(120.0);
    }

    @Test
    void save_generatesId() {
        Product saved = repository.save(new Product("Cable", 15));
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void eachTestStartsWithOnlyTheThreeInsertedProducts() {
        assertThat(repository.count()).isEqualTo(3);
    }
}