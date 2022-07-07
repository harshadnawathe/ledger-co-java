package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.ledgerco.lending.util.LoanAccountBuilder.newLoanAccount;
import static com.ledgerco.lending.util.LoanBuilder.newLoan;
import static com.ledgerco.lending.util.PaymentBuilder.newPayment;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("a loanAccount object")
class LoanAccountTest {

    @Nested
    @DisplayName("when initialized")
    class LoanAccountConstructorTest {

        @Test
        void shouldContainTheGivenBankName() {
            LoanAccount loanAccount = newLoanAccount().withBank("MBI").get();

            String bank = loanAccount.getBank();

            assertThat(bank).isEqualTo("MBI");
        }

        @Test
        void shouldContainTheGivenCustomerName() {
            LoanAccount loanAccount = newLoanAccount().withCustomer("Harry").get();

            String customer = loanAccount.getCustomer();

            assertThat(customer).isEqualTo("Harry");
        }

        @Test
        void shouldContainTheGivenLoan() {
            Loan loan = newLoan().get();
            LoanAccount loanAccount = newLoanAccount().withLoan(loan).get();

            Loan actual = loanAccount.getLoan();

            assertThat(actual).isEqualTo(loan);
        }

        @Test
        void shouldHaveNoLumpSumPayments() {
            Loan loan = newLoan().get();
            LoanAccount loanAccount = newLoanAccount().withLoan(loan).get();

            List<Payment> payments = loanAccount.getPayments();

            assertThat(payments).isEmpty();
        }
    }

    @Test
    void shouldAddLumpSumPaymentToPayments() {
        LoanAccount loanAccount = newLoanAccount().get();
        Payment payment = newPayment().get();

        loanAccount.addLumpSum(payment);

        assertThat(loanAccount.getPayments()).containsExactly(payment);
    }

    @Nested
    class LoanAccountBalanceTest {

        @Test
        void shouldIncludeBankName() {
            LoanAccount loanAccount = newLoanAccount().withBank("IDIDI").get();

            Balance balance = loanAccount.balance(3);

            assertThat(balance.getBank()).isEqualTo("IDIDI");
        }

        @Test
        void shouldIncludeCustomerName() {
            LoanAccount loanAccount = newLoanAccount().withCustomer("Dale").get();

            Balance balance = loanAccount.balance(3);

            assertThat(balance.getCustomer()).isEqualTo("Dale");
        }

        @Test
        void shouldIncludeAmountPaid() {
            LoanAccount loanAccount = newLoanAccount().withLoan(
                    newLoan()
                            .withPrincipal(5000)
                            .withPeriodInYears(1)
                            .withLoanRate(6)
                            .get()
            ).get();

            Balance balance = loanAccount.balance(3);

            assertThat(balance.getAmountPaid()).isEqualTo(1326);
        }

        @Test
        void shouldClampAmountPaidToTotalAmount() {
            LoanAccount loanAccount = newLoanAccount().withLoan(
                    newLoan()
                            .withPrincipal(5000)
                            .withPeriodInYears(1)
                            .withLoanRate(6)
                            .get()
            ).get();

            Balance balance = loanAccount.balance(12);

            assertThat(balance.getAmountPaid()).isEqualTo(5300);
        }

        @Test
        void shouldIncludeNumEmiRemaining() {
            LoanAccount loanAccount = newLoanAccount().withLoan(
                    newLoan()
                            .withPrincipal(5000)
                            .withPeriodInYears(1)
                            .withLoanRate(6)
                            .get()
            ).get();

            Balance balance = loanAccount.balance(3);

            assertThat(balance.getNumEmiRemaining()).isEqualTo(9);
        }

    }
}