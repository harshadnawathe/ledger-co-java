package com.ledgerco.lending.domain.util;

import com.ledgerco.lending.domain.Loan;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class LoanBuilder {

    private String bank = RandomStringUtils.randomAlphanumeric(3, 16);
    private String customer = RandomStringUtils.randomAlphanumeric(1, 16);
    private int principal = RandomUtils.nextInt(1, 100000000);


    public static LoanBuilder newLoan() {
        return new LoanBuilder();
    }

    public LoanBuilder withBank(String bank) {
        this.bank = bank;
        return this;
    }

    public LoanBuilder withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public Loan get() {
        return new Loan(bank, customer, principal);
    }

    public LoanBuilder withPrincipal(int principal) {
        this.principal = principal;
        return this;
    }
}
