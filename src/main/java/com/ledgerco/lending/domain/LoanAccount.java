package com.ledgerco.lending.domain;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class LoanAccount {
    private final String bank;
    private final String customer;
    private final Loan loan;
    private final Payments payments;

    LoanAccount(String bank, String customer, Loan loan, Payments payments) {
        this.bank = bank;
        this.customer = customer;
        this.loan = loan;
        this.payments = payments;
    }

    public LoanAccount(String bank, String customer, Loan loan) {
        this(bank, customer, loan, new Payments());
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

    public List<Payment> getPayments() {
        return unmodifiableList(payments);
    }

    public void addLumpSum(Payment payment) {
        payments.add(payment);
    }

    public Balance balance(int monthNo) {
        final int amountPaid = amountPaid(monthNo);
        final int numEmiRemaining = loan.numberOfEmi(loan.totalAmount() - amountPaid);
        return new Balance(bank, customer, amountPaid, numEmiRemaining);
    }

    private int amountPaid(int monthNo) {
        int amountPaid = payments.amountPaid(monthNo, loan.emi());
        if (amountPaid > loan.totalAmount()) {
            amountPaid = (int) loan.totalAmount();
        }
        return amountPaid;
    }
}
