package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.LoanAccount;
import lombok.Value;

@Value
public class CreateLoanAccountRequest {
    AccountSpec account;
    LoanSpec loan;

    public LoanAccount toLoanAccount() {
        return new LoanAccount(
                this.account.getBank(),
                this.account.getCustomer(),
                this.loan.toLoan()
        );
    }
}
