package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceTest {

    @Nested
    @DisplayName("when initialized")
    class BalanceConstructorTest {

        @Test
        void shouldContainTheGivenBankName() {
            Balance balance = new Balance("IDIDI");

            String bank = balance.getBank();

            assertThat(bank).isEqualTo("IDIDI");
        }
    }

}