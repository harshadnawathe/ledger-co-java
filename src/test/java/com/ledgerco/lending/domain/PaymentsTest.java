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

}