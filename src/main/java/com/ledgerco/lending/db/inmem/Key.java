package com.ledgerco.lending.db.inmem;

import com.ledgerco.lending.domain.LoanAccount;

class Key {
    private final String bank;
    private final String customer;

    Key(String bank, String customer) {
        this.bank = bank;
        this.customer = customer;
    }

    public static Key of(LoanAccount loanAccount) {
        return new Key(loanAccount.getBank(), loanAccount.getCustomer());
    }

    public String getBank() {
        return bank;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (!bank.equals(key.bank)) return false;
        return customer.equals(key.customer);
    }

    @Override
    public int hashCode() {
        int result = bank.hashCode();
        result = 31 * result + customer.hashCode();
        return result;
    }
}
