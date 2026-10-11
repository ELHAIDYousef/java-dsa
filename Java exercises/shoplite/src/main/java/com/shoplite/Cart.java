package com.shoplite;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Product> items = new ArrayList<>();

    public void add(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        items.add(product);
    }

    public int itemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double total() {
        double sum = 0;
        for (Product p : items) {
            sum += p.price();
        }
        return sum;
    }

    public Product findByName(String name) {
        for (Product p : items) {
            if (p.name().equals(name)) return p;
        }
        return null;
    }
    public List<Product> items() {
        return List.copyOf(items);
    }
}
