package com.ledgerco.lending.domain;

public class Loan {
    private final String bank;

    public Loan(String bank) {
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }
}
