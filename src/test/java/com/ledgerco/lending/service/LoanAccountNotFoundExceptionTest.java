package com.ledgerco.lending.service;

import com.ledgerco.lending.service.model.AccountSpec;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanAccountNotFoundExceptionTest {

    @Nested
    class LoanAccountNotFoundExceptionConstructorTest {

        @Test
        void shouldContainTheGivenAccountSpec() {
            AccountSpec account = new AccountSpec("MBI", "Harry");
            LoanAccountNotFoundException exception = new LoanAccountNotFoundException(account);

            AccountSpec actual = exception.getAccount();

            assertThat(actual).isEqualTo(account);
        }
    }

    @Test
    void shouldContainExceptionMessageWithBankAndCustomer() {
        AccountSpec account = new AccountSpec("MBI", "Harry");

        LoanAccountNotFoundException exception = new LoanAccountNotFoundException(account);

        assertThat(exception).hasMessage("Account with bank: MBI and customer: Harry, not found");
    }
}