package com.ledgerco.lending.domain;

import java.util.ArrayList;

public class Payments extends ArrayList<Payment> {
    private final int emi;

    public Payments(int emi) {
        this.emi = emi;
    }

    public int getEmi() {
        return emi;
    }
}
