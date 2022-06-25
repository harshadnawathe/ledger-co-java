package com.ledgerco.lending.util;

import com.ledgerco.lending.domain.Loan;
import org.apache.commons.lang3.RandomUtils;

public class LoanBuilder {

    private int principal = RandomUtils.nextInt(1, 100000000);
    private int periodInYears = RandomUtils.nextInt(1, 25);
    private int loanRate = RandomUtils.nextInt(1,15);


    public static LoanBuilder newLoan() {
        return new LoanBuilder();
    }

    public Loan get() {
        return new Loan(principal, periodInYears, loanRate);
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
