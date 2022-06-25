package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.util.BalanceBuilder.newBalance;
import static org.assertj.core.api.Assertions.assertThat;

class BalanceTest {

    @Nested
    @DisplayName("when initialized")
    class BalanceConstructorTest {

        @Test
        void shouldContainTheGivenBankName() {
            Balance balance = newBalance().withBank("IDIDI").get();

            String bank = balance.getBank();

            assertThat(bank).isEqualTo("IDIDI");
        }

        @Test
        void shouldContainTheGivenCustomer() {
            Balance balance = newBalance().withCustomer("Dale").get();

            String customer = balance.getCustomer();

            assertThat(customer).isEqualTo("Dale");
        }

        @Test
        void shouldContainTheAmountPaid() {
            Balance balance = newBalance().withAmountPaid(8000).get();

            int amountPaid = balance.getAmountPaid();

            assertThat(amountPaid).isEqualTo(8000);
        }

        @Test
        void shouldContainTheGivenNumEmiRemaining() {
            Balance balance = newBalance().withNumEmiRemaining(20).get();

            int numEmiRemaining = balance.getNumEmiRemaining();

            assertThat(numEmiRemaining).isEqualTo(20);
        }
    }

}