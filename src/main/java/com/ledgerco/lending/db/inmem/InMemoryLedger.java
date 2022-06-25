package com.ledgerco.lending.db.inmem;

import com.ledgerco.lending.domain.Ledger;
import com.ledgerco.lending.domain.LoanAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLedger implements Ledger {

    private final Map<Key, LoanAccount> store;

    InMemoryLedger(Map<Key, LoanAccount> store) {
        this.store = store;
    }

    public InMemoryLedger() {
        this(new ConcurrentHashMap<>());
    }

    @Override
    public LoanAccount save(LoanAccount account) {
        this.store.put(Key.of(account), account);
        return account;
    }

    @Override
    public LoanAccount findByBankAndCustomer(String bank, String customer) {
        return this.store.get(new Key(bank, customer));
    }

    Map<Key, LoanAccount> getStore() {
        return store;
    }
}
