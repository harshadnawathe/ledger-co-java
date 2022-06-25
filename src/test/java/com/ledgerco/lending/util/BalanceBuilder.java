package com.ledgerco.lending.util;

import com.ledgerco.lending.domain.Balance;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class BalanceBuilder {
    private String bank = RandomStringUtils.randomAlphanumeric(3, 16);
    private String customer = RandomStringUtils.randomAlphanumeric(1, 16);
    private int amountPaid = RandomUtils.nextInt(1, 10000);
    private int numEmiRemaining = RandomUtils.nextInt(0, 240);

    public static BalanceBuilder newBalance() {
        return new BalanceBuilder();
    }

    public Balance get() {
        return new Balance(bank, customer, amountPaid, numEmiRemaining);
    }

    public BalanceBuilder withBank(String bank) {
        this.bank = bank;
        return this;
    }

    public BalanceBuilder withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public BalanceBuilder withAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public BalanceBuilder withNumEmiRemaining(int numEmiRemaining) {
        this.numEmiRemaining = numEmiRemaining;
        return this;
    }
}
