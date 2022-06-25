package com.ledgerco.lending.domain;

import org.apache.commons.lang3.RandomUtils;
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

    @Test
    void shouldCalculateTheInterest() {
        Loan loan = newLoan().withPrincipal(2000)
                .withPeriodInYears(2)
                .withLoanRate(2)
                .get();

        double interest = loan.interest();

        assertThat(interest).isEqualTo(80.0);
    }

    @Test
    void shouldCalculateTheTotalAmount() {
        Loan loan = newLoan().withPrincipal(2000)
                .withPeriodInYears(2)
                .withLoanRate(2)
                .get();

        double totalAmount = loan.totalAmount();

        assertThat(totalAmount).isEqualTo(2080.0);
    }

    @Nested
    class EmiCalculationTest {
        @Test
        void shouldCalculateTheEmi() {
            Loan loan = newLoan().withPrincipal(2400)
                    .withPeriodInYears(2)
                    .withLoanRate(2)
                    .get();

            int emi = loan.emi();

            assertThat(emi).isEqualTo(104);
        }

        @Test
        void shouldRoundUpTheEmiToTheNearestInteger() {
            Loan loan = newLoan().withPrincipal(2000)
                    .withPeriodInYears(2)
                    .withLoanRate(2)
                    .get();

            int emi = loan.emi();

            assertThat(emi).isEqualTo(87);
        }
    }

    @Nested
    class EmiCountCalculationTest {

        @Test
        void shouldReturnNumberOfEmiRemaining() {
            Loan loan = newLoan().withPrincipal(2400)
                    .withPeriodInYears(2)
                    .withLoanRate(2)
                    .get();

            int numOfEmi = loan.numberOfEmi(2496);

            assertThat(numOfEmi).isEqualTo(24);
        }

        @Test
        void shouldRoundUpTheNumberOfEmiRemainingToNearestInteger() {
            Loan loan = newLoan().withPrincipal(2000)
                    .withPeriodInYears(2)
                    .withLoanRate(2)
                    .get();

            int numberOfEmi = loan.numberOfEmi(1036);

            assertThat(numberOfEmi).isEqualTo(12);
        }

        @Test
        void shouldReturnZeroWhenAmountRemainingIsNotPositive() {
           Loan loan = newLoan().withPrincipal(2000)
                    .withPeriodInYears(2)
                    .withLoanRate(2)
                    .get();
            double amountRemaining = -RandomUtils.nextDouble(0, 1036);

            int numberOfEmi = loan.numberOfEmi(amountRemaining);

            assertThat(numberOfEmi).isEqualTo(0);
        }
    }
}