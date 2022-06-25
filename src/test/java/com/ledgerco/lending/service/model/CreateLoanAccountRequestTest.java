package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.LoanAccount;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateLoanAccountRequestTest {
    
    @Nested
    class CreateLoanAccountRequestMappingTest {

        @Test
        void shouldMapBankFromSpecToLoanAccount() {
            CreateLoanAccountRequest request = creatLoanAccountRequest();

            LoanAccount loanAccount = request.toLoanAccount();

            assertThat(loanAccount.getBank()).isEqualTo(request.getAccount().getBank());
        }

        @Test
        void shouldMapCustomerFromSpecToLoanAccount() {
            CreateLoanAccountRequest request = creatLoanAccountRequest();

            LoanAccount loanAccount = request.toLoanAccount();

            assertThat(loanAccount.getCustomer()).isEqualTo(request.getAccount().getCustomer());
        }


        private CreateLoanAccountRequest creatLoanAccountRequest() {
            return new CreateLoanAccountRequest(
                    new AccountSpec("MBI", "Harry"),
                    new LoanSpec(2000, 2, 2)
            );
        }
    }
}