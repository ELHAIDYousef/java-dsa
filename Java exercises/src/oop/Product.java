package oop;

import java.math.BigDecimal;

public class Product {
    private BigDecimal price;
    private int quantity;


    public Product(BigDecimal price, int quantity) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getInventoryValue(){
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }
}
