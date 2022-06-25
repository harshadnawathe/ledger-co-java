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
            LoanAccountService service = new LoanAccountService(new InMemoryLedger());
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