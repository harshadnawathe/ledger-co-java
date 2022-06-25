package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Balance;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.BalanceBuilder.newBalance;
import static org.assertj.core.api.Assertions.assertThat;

class LoanAccountResponseTest {

    @Nested
    class LoanAccountResponseMapperTest {

        @Test
        void shouldMapBankFromBalanceToResponse() {
            Balance balance = newBalance().get();

            LoanAccountResponse response = LoanAccountResponse.of(balance);

            assertThat(response.getAccount().getBank()).isEqualTo(balance.getBank());
        }

        @Test
        void shouldMapCustomerFromBalanceToResponse() {
            Balance balance = newBalance().get();

            LoanAccountResponse response = LoanAccountResponse.of(balance);

            assertThat(response.getAccount().getCustomer()).isEqualTo(balance.getCustomer());
        }

        @Test
        void shouldMapAmountPaidFromBalanceToResponse() {
            Balance balance = newBalance().get();

            LoanAccountResponse response = LoanAccountResponse.of(balance);

            assertThat(response.getBalance().getAmountPaid()).isEqualTo(balance.getAmountPaid());
        }

        @Test
        void shouldMapNumEmiRemainingFromBalanceToResponse() {
            Balance balance = newBalance().get();

            LoanAccountResponse response = LoanAccountResponse.of(balance);

            assertThat(response.getBalance().getNumEmiRemaining()).isEqualTo(balance.getNumEmiRemaining());
        }
    }


}