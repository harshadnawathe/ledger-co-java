package com.ledgerco.lending.service;

import com.ledgerco.lending.service.model.AccountSpec;

public class LoanAccountNotFoundException extends Exception {
    private final AccountSpec account;

    public LoanAccountNotFoundException(AccountSpec account) {
        super(String.format("Account with bank: %s and customer: %s, not found", account.getBank(), account.getCustomer()));
        this.account = account;
    }

    public AccountSpec getAccount() {
        return account;
    }
}
