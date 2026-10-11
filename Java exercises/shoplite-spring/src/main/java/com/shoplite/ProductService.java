package com.shoplite;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(String name, double price) {
        return repository.save(new Product(name, price));
    }

    public List<Product> findCheaperThan(double maxPrice) {
        return repository.findByPriceLessThanEqual(maxPrice);
    }
}