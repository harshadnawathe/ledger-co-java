package com.ledgerco.lending.domain;

public class Balance {
    private final String bank;

    public Balance(String bank) {

        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }
}
