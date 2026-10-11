package com.shoplite;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Product> cheaperThan(@RequestParam double maxPrice) {
        return service.findCheaperThan(maxPrice);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
// 201 instead of 200
    public Product create(@Valid @RequestBody CreateProductRequest request) {
        return service.create(request.name(), request.price());
    }
}
