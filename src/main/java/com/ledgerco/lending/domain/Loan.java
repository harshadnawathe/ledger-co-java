package com.ledgerco.lending.domain;

public class Loan {
    private final int principal;
    private final int periodInYears;
    private final int loanRate;

    public Loan(int principal, int periodInYears, int loanRate) {
        this.principal = principal;
        this.periodInYears = periodInYears;
        this.loanRate = loanRate;
    }

    public int getPrincipal() {
        return principal;
    }

    public int getPeriodInYears() {
        return periodInYears;
    }

    public int getLoanRate() {
        return loanRate;
    }
}
