package com.ledgerco.lending.service;

import com.ledgerco.lending.db.inmem.InMemoryLedger;
import com.ledgerco.lending.domain.Balance;
import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.CreateLoanAccountRequest;
import com.ledgerco.lending.service.model.LoanAccountResponse;
import com.ledgerco.lending.service.model.LoanSpec;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.ledgerco.lending.util.BalanceBuilder.newBalance;
import static org.assertj.core.api.Assertions.assertThat;

class LoanAccountServiceTest {
    private static final int MONTH = 0;
    private final Balance balance = newBalance()
            .withBank("MBI")
            .withCustomer("Harry")
            .withAmountPaid(0)
            .withNumEmiRemaining(24)
            .get();

    private final LoanAccountResponse expectedResponse = LoanAccountResponse.of(balance);
    private final Ledger ledger = new Ledger() {
        @Override
        public LoanAccount save(LoanAccount account) {
            return fakeAccount();
        }

        @Override
        public LoanAccount findByBankAndCustomer(String bank, String customer) {
            return fakeAccount();
        }

        private LoanAccount fakeAccount() {
            LoanAccount account = Mockito.mock(LoanAccount.class);
            Mockito.when(account.balance(MONTH)).thenReturn(balance);
            return account;
        }
    };

    @Nested
    class LoanAccountServiceConstructorTest {

        @Test
        void shouldContainTheGivenLedgerObject() {
            InMemoryLedger expected = new InMemoryLedger();
            LoanAccountService service = new LoanAccountService(expected);

            Ledger ledger = service.getLedger();

            assertThat(ledger).isEqualTo(expected);
        }
    }

    @Nested
    class LoanAccountServiceLoanAccountCreationTest {

        @Test
        void shouldCreateTheLoanAccountInTheLedger() {
            InMemoryLedger ledger = new InMemoryLedger();
            LoanAccountService service = new LoanAccountService(ledger);
            CreateLoanAccountRequest request = createLoanAccountRequest();

            service.createLoanAccount(request);

            LoanAccount loanAccount = ledger.findByBankAndCustomer(
                    request.getAccount().getBank(),
                    request.getAccount().getCustomer());
            assertThat(loanAccount).isNotNull();
        }

        @Test
        void shouldReturnTheLoanAccountResponse() {
            LoanAccountService service = new LoanAccountService(ledger);
            CreateLoanAccountRequest request = createLoanAccountRequest();

            LoanAccountResponse response = service.createLoanAccount(request);

            assertThat(response).isEqualTo(expectedResponse);
        }
    }

    private CreateLoanAccountRequest createLoanAccountRequest() {
        return new CreateLoanAccountRequest(
                new AccountSpec("MBI", "Harry"),
                new LoanSpec(2000, 2, 2)
        );
    }


}