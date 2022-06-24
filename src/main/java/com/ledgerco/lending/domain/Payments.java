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

    public int amountPaid(int monthNo) {
        int paymentsThroughEmi = monthNo * emi;
        int paymentsMade = this.stream()
                .filter( (it) -> it.isApplicable(monthNo) )
                .map(Payment::getAmount)
                .reduce(0, Integer::sum);

        return paymentsThroughEmi + paymentsMade;
    }
}
