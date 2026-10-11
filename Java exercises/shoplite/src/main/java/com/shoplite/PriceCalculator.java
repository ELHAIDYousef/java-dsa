package com.shoplite;

public class PriceCalculator {

    public double finalPrice(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        if (amount > 100) {
            return amount * 0.9;
        }
        return amount;
    }
}
