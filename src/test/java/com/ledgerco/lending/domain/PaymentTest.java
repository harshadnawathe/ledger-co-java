package com.ledgerco.lending.domain;

import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.PaymentBuilder.newPayment;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void shouldContainTheGivenAmount() {
        Payment payment = newPayment().withAmount(5000).get();

        int amount = payment.getAmount();

        assertThat(amount).isEqualTo(5000);
    }

    @Test
    void shouldContainTheGivenEmiNumber() {
        Payment payment = newPayment().withPaidAfter(10).get();

        int paidAfter = payment.getPaidAfter();

        assertThat(paidAfter).isEqualTo(10);
    }
}