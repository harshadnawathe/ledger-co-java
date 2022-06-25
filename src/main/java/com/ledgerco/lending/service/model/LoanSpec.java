package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Loan;
import lombok.Value;

@Value
public class LoanSpec {
    int principal;
    int periodInYears;
    int loanRate;

    public Loan toLoan() {
        return new Loan(principal, periodInYears, loanRate);
    }
}
