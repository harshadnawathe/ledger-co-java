package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Payment;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentSpecTest {

    @Nested
    class PaymentSpecMapperTest {

        @Test
        void shouldMapAmountFromSpecToPayment() {
            PaymentSpec spec = new PaymentSpec(1000, 5);

            Payment payment = spec.toPayment();

            assertThat(payment.getAmount()).isEqualTo(1000);
        }

        @Test
        void shouldMapPaidAfterFromSpecToPayment() {
            PaymentSpec spec = new PaymentSpec(1000, 5);

            Payment payment = spec.toPayment();

            assertThat(payment.getPaidAfter()).isEqualTo(5);
        }
    }
}