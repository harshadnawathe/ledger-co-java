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
        void shouldContainGivenEmi() {
            Payments payments = new Payments(80);

            int emi = payments.getEmi();

            assertThat(emi).isEqualTo(emi);
        }


        @Test
        void shouldNotContainAnyPayment() {
            Payments payments = new Payments(80);

            int size = payments.size();

            assertThat(size).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("when pre-payment is made")
    class PaymentsAddPaymentTest {

        @Test
        void shouldContainTheGivenPayment() {
            Payments payments = new Payments(50);
            Payment payment = newPayment().get();

            payments.add(payment);

            assertThat(payments).containsExactly(payment);
        }
    }

    @Nested
    class PaymentsCheckPaidAmountTest {

        @Test
        void shouldReturnAmountPaidThroughEmi() {
            Payments payments = new Payments(50);

            int amountPaid = payments.amountPaid(5);

            assertThat(amountPaid).isEqualTo(250);
        }

        @Test
        void shouldIncludePrepaymentsWhenApplicable() {
            Payment paymentApplicable = newPayment().withAmount(100).withPaidAfter(2).get();

            Payments payments = new Payments(50);
            payments.add(paymentApplicable);

            int amountPaid = payments.amountPaid(5);

            assertThat(amountPaid).isEqualTo(350);
        }

        @Test
        void shouldNotIncludePrepaymentsWhenNotApplicable() {
            Payment paymentNotApplicable = newPayment().withAmount(100).withPaidAfter(7).get();

            Payments payments = new Payments(50);
            payments.add(paymentNotApplicable);

            int amountPaid = payments.amountPaid(5);

            assertThat(amountPaid).isEqualTo(250);
        }
    }
}