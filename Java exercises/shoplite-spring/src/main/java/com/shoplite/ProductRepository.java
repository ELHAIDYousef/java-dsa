package com.shoplite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceLessThanEqual(double maxPrice);

    Optional<Product> findByNameIgnoreCase(String name);
}