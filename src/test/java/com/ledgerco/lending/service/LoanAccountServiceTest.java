package com.ledgerco.lending.service;

import com.ledgerco.lending.db.inmem.InMemoryLedger;
import com.ledgerco.lending.db.inmem.UnmodifiableLedger;
import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.service.model.*;
import com.ledgerco.lending.util.LoanAccountAssertion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.util.LoanAccountBuilder.newLoanAccount;
import static com.ledgerco.lending.util.LoanBuilder.newLoan;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

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

            LoanAccountAssertion.assertThat(loanAccount)
                    .hasBank(request.getAccount().getBank())
                    .hasCustomer(request.getAccount().getCustomer())
                    .hasLoanWithPrincipal(request.getLoan().getPrincipal())
                    .hasLoanWithPeriodInYears(request.getLoan().getPeriodInYears())
                    .hasLoanWithLoanRate(request.getLoan().getLoanRate());
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
        void shouldAddThePaymentToLoanAccountAndStoreItInLedger() throws Exception {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            LoanAccountService service = new LoanAccountService(ledger);
            MakePaymentRequest request = makePaymentRequest();

            service.makePayment(request);

            LoanAccount loanAccount = ledger.findByBankAndCustomer(
                    request.getAccount().getBank(),
                    request.getAccount().getCustomer());

            LoanAccountAssertion.assertThat(loanAccount).containsPaymentMatching(
                    payment -> payment.getAmount() == request.getPayment().getAmount() &&
                            payment.getPaidAfter() == request.getPayment().getPaidAfter()
            );
        }

        @Test
        void shouldReturnTheLoanAccountResponse() throws Exception {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            LoanAccountService service = new LoanAccountService(ledger);
            MakePaymentRequest request = makePaymentRequest();
            LoanAccountResponse expectedResponse = makePaymentResponse();

            LoanAccountResponse response = service.makePayment(request);

            assertThat(response).isEqualTo(expectedResponse);
        }

        @Test
        void shouldThrowExceptionWhenLoanAccountIsNotFound() {
            LoanAccountService service = new LoanAccountService(new InMemoryLedger());
            MakePaymentRequest request = new MakePaymentRequest(new AccountSpec("Unknown", "Unknown"), null);

            assertThatThrownBy(
                    () -> service.makePayment(request)
            ).isInstanceOf(LoanAccountNotFoundException.class)
                    .hasMessage("Account with bank: Unknown and customer: Unknown, not found");
        }
    }

    @Nested
    class LoanAccountServiceCheckBalanceTest {

        @Test
        void shouldReturnTheLoanAccountResponse() throws Exception {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            LoanAccountService service = new LoanAccountService(ledger);
            CheckBalanceRequest request = checkBalanceRequest();
            LoanAccountResponse expectedResponse = checkBalanceResponse();

            LoanAccountResponse response = service.checkBalance(request);

            assertThat(response).isEqualTo(expectedResponse);
        }

        @Test
        void shouldNotModifyLedger() throws Exception {
            InMemoryLedger ledger = new InMemoryLedger();
            ledger.save(loanAccount());
            UnmodifiableLedger unmodifiableLedger = new UnmodifiableLedger(ledger);
            LoanAccountService service = new LoanAccountService(unmodifiableLedger);
            CheckBalanceRequest request = checkBalanceRequest();

            try {
                service.checkBalance(request);
            } catch (UnmodifiableLedger.ModificationNotAllowedException e) {
                fail();
            }
        }

        @Test
        void shouldThrowExceptionWhenLoanAccountIsNotFound() {
            LoanAccountService service = new LoanAccountService(new InMemoryLedger());
            CheckBalanceRequest request = new CheckBalanceRequest(new AccountSpec("Unknown", "Unknown"), 0);

            assertThatThrownBy(
                    () -> service.checkBalance(request)
            ).isInstanceOf(LoanAccountNotFoundException.class)
                    .hasMessage("Account with bank: Unknown and customer: Unknown, not found");
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

    private CheckBalanceRequest checkBalanceRequest() {
        return new CheckBalanceRequest(
                new AccountSpec("MBI", "Harry"),
                12
        );
    }

    private LoanAccountResponse checkBalanceResponse() {
        return new LoanAccountResponse(
                new AccountSpec("MBI", "Harry"),
                new BalanceSpec(4044, 24)
        );
    }
}