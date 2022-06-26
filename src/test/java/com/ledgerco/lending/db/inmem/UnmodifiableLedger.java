package com.ledgerco.lending.db.inmem;

import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;

public class UnmodifiableLedger implements Ledger {

    private final InMemoryLedger ledger;

    public UnmodifiableLedger(InMemoryLedger ledger) {
        this.ledger = ledger;
    }

    @Override
    public LoanAccount save(LoanAccount account) {
        throw new ModificationNotAllowedException();
    }

    @Override
    public LoanAccount findByBankAndCustomer(String bank, String customer) {
        return ledger.findByBankAndCustomer(bank, customer);
    }

    public static class ModificationNotAllowedException extends RuntimeException {
    }
}
