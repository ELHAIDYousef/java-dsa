package oop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products = new ArrayList<>();

    public boolean add(Product product) {
        return products.add(product);
    }

    public boolean remove(Product product) {
        return products.remove(product);
    }

    public BigDecimal total() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Product product : products) {
            sum = sum.add(product.getInventoryValue());
        }

        return sum;
    }
}