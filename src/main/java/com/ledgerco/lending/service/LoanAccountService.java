package com.ledgerco.lending.service;

import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.service.model.CheckBalanceRequest;
import com.ledgerco.lending.service.model.CreateLoanAccountRequest;
import com.ledgerco.lending.service.model.LoanAccountResponse;
import com.ledgerco.lending.service.model.MakePaymentRequest;

public class LoanAccountService {
    private final Ledger ledger;

    public LoanAccountService(Ledger ledger) {
        this.ledger = ledger;
    }

    Ledger getLedger() {
        return ledger;
    }

    public LoanAccountResponse createLoanAccount(CreateLoanAccountRequest request) {
        LoanAccount loanAccount = ledger.save(request.toLoanAccount());
        return LoanAccountResponse.of(loanAccount.balance(0));
    }

    public LoanAccountResponse makePayment(MakePaymentRequest request) {
        LoanAccount loanAccount = ledger.findByBankAndCustomer(
                request.getAccount().getBank(),
                request.getAccount().getCustomer());

        loanAccount.addLumpSum(request.getPayment().toPayment());

        loanAccount = ledger.save(loanAccount);

        return LoanAccountResponse.of(
                loanAccount.balance(request.getPayment().getPaidAfter())
        );
    }

    public LoanAccountResponse checkBalance(CheckBalanceRequest request) {
        LoanAccount loanAccount = ledger.findByBankAndCustomer(
                request.getAccount().getBank(),
                request.getAccount().getCustomer());

        return LoanAccountResponse.of(
                loanAccount.balance(request.getMonthNo())
        );
    }
}
