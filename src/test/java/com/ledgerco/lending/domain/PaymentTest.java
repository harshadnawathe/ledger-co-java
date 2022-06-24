package com.ledgerco.lending.domain;

import org.junit.jupiter.api.Nested;
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

    @Nested
    class PaymentIsApplicableForMonthTest{

        @Test
        void shouldBeApplicableWhenPaymentIsDoneBeforeGivenMonth() {
            Payment payment = newPayment().withPaidAfter(5).get();

            boolean applicable = payment.isApplicable(6);

            assertThat(applicable).isTrue();
        }

        @Test
        void shouldBeApplicableWhenPaymentIsDoneInTheGivenMonth() {
            Payment payment = newPayment().withPaidAfter(5).get();

            boolean applicable = payment.isApplicable(5);

            assertThat(applicable).isTrue();
        }

        @Test
        void shouldNotBeApplicableWhenPaymentIsDoneInTheGivenMonth() {
            Payment payment = newPayment().withPaidAfter(5).get();

            boolean applicable = payment.isApplicable(4);

            assertThat(applicable).isFalse();
        }
    }
}