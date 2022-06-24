package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("a loan object")
public class LoanTest {

    @Nested
    @DisplayName("when initialized")
    public class LoanConstructorTest {

        @Test
        void shouldContainTheGivenBankName() {
            Loan loan = new Loan("MBI");

            String bank = loan.getBank();

            assertThat(bank).isEqualTo("MBI");
        }
    }
}