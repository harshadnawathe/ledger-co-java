package com.ledgerco.lending.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.LoanAccountBuilder.newLoanAccount;
import static com.ledgerco.lending.domain.util.LoanBuilder.newLoan;
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
    }
}