package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Payment;
import lombok.Value;

@Value
public class PaymentSpec {
    int amount;
    int paidAfter;

    public Payment toPayment() {
        return new Payment(amount, paidAfter);
    }
}
