package com.ledgerco.lending.domain;

public interface Ledger {
    LoanAccount save(LoanAccount account);
    LoanAccount findByBankAndCustomer(String bank, String customer);
}
