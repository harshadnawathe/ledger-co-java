package com.ledgerco.lending.domain;

import java.util.ArrayList;

public class Payments extends ArrayList<Payment> {
    public int amountPaid(int monthNo, int emi) {
        int paymentsThroughEmi = monthNo * emi;
        int paymentsMade = this.stream()
                .filter( (it) -> it.isApplicable(monthNo) )
                .map(Payment::getAmount)
                .reduce(0, Integer::sum);

        return paymentsThroughEmi + paymentsMade;
    }
}
