package com.ledgerco.lending.domain.util;

import com.ledgerco.lending.domain.Loan;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class LoanBuilder {

    private String bank = RandomStringUtils.randomAlphanumeric(3, 16);
    private String customer = RandomStringUtils.randomAlphanumeric(1, 16);
    private int principal = RandomUtils.nextInt(1, 100000000);
    private int periodInYears = RandomUtils.nextInt(1, 25);
    private int loanRate = RandomUtils.nextInt(1,15);


    public static LoanBuilder newLoan() {
        return new LoanBuilder();
    }

    public Loan get() {
        return new Loan(bank, customer, principal, periodInYears, loanRate);
    }

    public LoanBuilder withBank(String bank) {
        this.bank = bank;
        return this;
    }

    public LoanBuilder withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public LoanBuilder withPrincipal(int principal) {
        this.principal = principal;
        return this;
    }

    public LoanBuilder withPeriodInYears(int periodInYears) {
        this.periodInYears = periodInYears;
        return this;
    }

    public LoanBuilder withLoanRate(int loanRate) {
        this.loanRate = loanRate;
        return this;
    }
}
