package com.ledgerco.lending.domain;

public class Balance {
    private final String bank;
    private final String customer;
    private final int amountPaid;
    private final int numEmiRemaining;

    public Balance(String bank, String customer, int amountPaid, int numEmiRemaining) {
        this.bank = bank;
        this.customer = customer;
        this.amountPaid = amountPaid;
        this.numEmiRemaining = numEmiRemaining;
    }

    public String getBank() {
        return bank;
    }

    public String getCustomer() {
        return customer;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getNumEmiRemaining() {
        return numEmiRemaining;
    }
}
