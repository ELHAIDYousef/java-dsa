package com.shoplite;

public interface PaymentGateway {
    boolean charge(double amount); // true = payment accepted
}
