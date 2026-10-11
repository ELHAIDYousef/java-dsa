package com.shoplite;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class PromoCodeService {

    private static final Map<String, DoubleUnaryOperator> PROMOS = Map.of(
            "SUMMER10", amount -> amount * 0.9,
            "WELCOME50", amount -> Math.max(0, amount - 50)
    );

    public double apply(double amount, String code) {
        if (code == null || code.isBlank()) {
            return amount;
        }
        DoubleUnaryOperator promo = PROMOS.get(code.trim().toUpperCase());
        if (promo == null) {
            throw new IllegalArgumentException("Unknown promo code: " + code);
        }
        return promo.applyAsDouble(amount);
    }
}