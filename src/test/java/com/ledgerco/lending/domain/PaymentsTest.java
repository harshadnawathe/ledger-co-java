package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.PaymentBuilder.newPayment;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentsTest {

    @Nested
    @DisplayName("when initialized")
    class PaymentsConstructorTest{

        @Test
        void shouldNotContainAnyPayment() {
            Payments payments = new Payments();

            int size = payments.size();

            assertThat(size).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("when pre-payment is made")
    class PaymentsAddPaymentTest {

        @Test
        void shouldContainTheGivenPayment() {
            Payments payments = new Payments();
            Payment payment = newPayment().get();

            payments.add(payment);

            assertThat(payments).containsExactly(payment);
        }
    }

    @Nested
    class PaymentsCheckPaidAmountTest {

        @Test
        void shouldReturnAmountPaidThroughEmi() {
            Payments payments = new Payments();

            int amountPaid = payments.amountPaid(5, 50);

            assertThat(amountPaid).isEqualTo(250);
        }

        @Test
        void shouldIncludePrepaymentsWhenApplicable() {
            Payment paymentApplicable = newPayment().withAmount(100).withPaidAfter(2).get();

            Payments payments = new Payments();
            payments.add(paymentApplicable);

            int amountPaid = payments.amountPaid(5, 50);

            assertThat(amountPaid).isEqualTo(350);
        }

        @Test
        void shouldNotIncludePrepaymentsWhenNotApplicable() {
            Payment paymentNotApplicable = newPayment().withAmount(100).withPaidAfter(7).get();

            Payments payments = new Payments();
            payments.add(paymentNotApplicable);

            int amountPaid = payments.amountPaid(5, 50);

            assertThat(amountPaid).isEqualTo(250);
        }
    }
}