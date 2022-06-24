package com.ledgerco.lending.domain;

public class LoanAccount {
    private final String bank;
    private final String customer;
    private final Loan loan;

    public LoanAccount(String bank, String customer, Loan loan) {

        this.bank = bank;
        this.customer = customer;
        this.loan = loan;
    }

    public String getBank() {
        return bank;
    }

    public String getCustomer() {
        return customer;
    }

    public Loan getLoan() {
        return loan;
    }
}
