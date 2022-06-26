package com.ledgerco.lending.service;

import com.ledgerco.lending.db.inmem.InMemoryLedger;
import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.service.model.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.util.LoanAccountBuilder.newLoanAccount;
import static com.ledgerco.lending.util.LoanBuilder.newLoan;
import static org.assertj.core.api.Assertions.assertThat;

class LoanAccountServiceTest {

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
            LoanAccountResponse expectedResponse = createLoanAccountResponse();

            LoanAccountResponse response = service.createLoanAccount(request);

            assertThat(response).isEqualTo(expectedResponse);
        }
    }

    @Nested
    class LoanAccountServiceMakePaymentTest {

        @Test
        void shouldAddThePaymentToLoanAccountAndStoreItInLedger() {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            LoanAccountService service = new LoanAccountService(ledger);
            MakePaymentRequest request = makePaymentRequest();

            service.makePayment(request);

            LoanAccount loanAccount = ledger.findByBankAndCustomer(
                    request.getAccount().getBank(),
                    request.getAccount().getCustomer());

            assertThat(loanAccount.getPayments())
                    .allMatch(
                            payment -> payment.getAmount() == request.getPayment().getAmount() &&
                                    payment.getPaidAfter() == request.getPayment().getPaidAfter()
                    );
        }

        @Test
        void shouldReturnTheLoanAccountResponse() {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            LoanAccountService service = new LoanAccountService(ledger);
            MakePaymentRequest request = makePaymentRequest();
            LoanAccountResponse expectedResponse = makePaymentResponse();

            LoanAccountResponse response = service.makePayment(request);

            assertThat(response).isEqualTo(expectedResponse);
        }
    }

    private CreateLoanAccountRequest createLoanAccountRequest() {
        return new CreateLoanAccountRequest(
                new AccountSpec("MBI", "Harry"),
                new LoanSpec(10000, 3, 7)
        );
    }

    private LoanAccountResponse createLoanAccountResponse() {
        return new LoanAccountResponse(
                new AccountSpec("MBI", "Harry"),
                new BalanceSpec(0, 36)
        );
    }

    private LoanAccount loanAccount() {
        return newLoanAccount()
                .withBank("MBI")
                .withCustomer("Harry")
                .withLoan(
                        newLoan()
                                .withPrincipal(10000)
                                .withPeriodInYears(3)
                                .withLoanRate(7)
                                .get()
                )
                .get();
    }

    private MakePaymentRequest makePaymentRequest() {
        return new MakePaymentRequest(
                new AccountSpec("MBI", "Harry"),
                new PaymentSpec(5000, 10)
        );
    }

    private LoanAccountResponse makePaymentResponse() {
        return new LoanAccountResponse(
                new AccountSpec("MBI", "Harry"),
                new BalanceSpec(8370, 12)
        );
    }
}