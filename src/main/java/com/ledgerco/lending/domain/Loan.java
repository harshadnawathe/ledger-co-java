package com.ledgerco.lending.domain;

public class Loan {
    private final String bank;
    private final String customer;
    private final int principal;

    public Loan(String bank, String customer, int principal) {
        this.bank = bank;
        this.customer = customer;
        this.principal = principal;
    }

    public String getBank() {
        return bank;
    }

    public String getCustomer() {
        return customer;
    }

    public int getPrincipal() {
        return principal;
    }
}
