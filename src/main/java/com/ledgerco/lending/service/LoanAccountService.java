package com.ledgerco.lending.service;

import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.service.model.CreateLoanAccountRequest;
import com.ledgerco.lending.service.model.LoanAccountResponse;

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
}
