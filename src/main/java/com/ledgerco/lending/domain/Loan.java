package com.ledgerco.lending.domain;

public class Loan {
    private final String bank;
    private final String customer;
    private final int principal;
    private final int periodInYears;

    public Loan(String bank, String customer, int principal, int periodInYears) {
        this.bank = bank;
        this.customer = customer;
        this.principal = principal;
        this.periodInYears = periodInYears;
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

    public int getPeriodInYears() {
        return periodInYears;
    }
}
