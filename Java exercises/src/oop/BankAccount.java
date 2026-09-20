package oop;

import java.math.BigDecimal;

public class BankAccount {
    private String owner;
    private BigDecimal balance;

    public BankAccount(String owner, BigDecimal balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount() {
        this.balance = BigDecimal.ZERO;
    }

    public String getOwner() {
        return this.owner;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount should be positive.");
            return;
        }

        this.balance = this.balance.add(amount);

        System.out.println("The current amount is: " + this.balance);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount should be positive.");
            return;
        }

        if (amount.compareTo(this.balance) > 0) {
            System.out.println("Insufficient balance.");
            return;
        }

        this.balance = this.balance.subtract(amount);
    }
}
