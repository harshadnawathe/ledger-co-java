package com.ledgerco.lending.domain;

public class Payment {
    private final int amount;
    private final int paidAfter;

    public Payment(int amount, int paidAfter) {
        this.amount = amount;
        this.paidAfter = paidAfter;
    }

    public int getAmount() {
        return amount;
    }

    public int getPaidAfter() {
        return paidAfter;
    }
    public boolean isApplicable(int month) {
        return month >= paidAfter;
    }
}
