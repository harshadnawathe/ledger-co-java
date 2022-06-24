package com.ledgerco.lending.domain;

public class Loan {
    private final String bank;
    private final String customer;

    public Loan(String bank, String customer) {
        this.bank = bank;
        this.customer = customer;
    }

    public String getBank() {
        return bank;
    }

    public String getCustomer() {
        return customer;
    }
}
