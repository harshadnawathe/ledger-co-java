package com.ledgerco.lending.domain.util;

import com.ledgerco.lending.domain.Payment;
import org.apache.commons.lang3.RandomUtils;

public class PaymentBuilder {

    private int amount = RandomUtils.nextInt(1, 10000);
    private int paidAfter = RandomUtils.nextInt(0, 240);

    public static PaymentBuilder newPayment() {
        return new PaymentBuilder();
    }

    public Payment get() {
        return new Payment(amount, paidAfter);
    }

    public PaymentBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder withPaidAfter(int paidAfter) {
        this.paidAfter = paidAfter;
        return this;
    }
}
