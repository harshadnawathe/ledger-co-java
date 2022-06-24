package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.LoanBuilder.newLoan;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("a loan object")
public class LoanTest {

    @Nested
    @DisplayName("when initialized")
    public class LoanConstructorTest {

        @Test
        void shouldContainTheGivenBankName() {
            Loan loan = newLoan().withBank("MBI").get();

            String bank = loan.getBank();

            assertThat(bank).isEqualTo("MBI");
        }

        @Test
        void shouldContainTheGivenCustomerName() {
            Loan loan = newLoan().withCustomer("Harry").get();

            String customer = loan.getCustomer();

            assertThat(customer).isEqualTo("Harry");
        }

        @Test
        void shouldContainTheGivenPrincipleAmount() {
            Loan loan = newLoan().withPrincipal(2000).get();

            int principal = loan.getPrincipal();

            assertThat(principal).isEqualTo(2000);
        }

        @Test
        void shouldContainTheGivenLoanPeriodInYears() {
            Loan loan = newLoan().withPeriodInYears(2).get();

            int periodInYears = loan.getPeriodInYears();

            assertThat(periodInYears).isEqualTo(2);
        }

        @Test
        void shouldContainTheGivenLoanRate() {
            Loan loan = newLoan().withLoanRate(2).get();

            int loanRate = loan.getLoanRate();

            assertThat(loanRate).isEqualTo(2);
        }
    }
}