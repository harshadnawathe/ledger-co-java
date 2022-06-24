package com.ledgerco.lending.domain.util;

import com.ledgerco.lending.domain.Loan;
import com.ledgerco.lending.domain.LoanAccount;
import org.apache.commons.lang3.RandomStringUtils;

public class LoanAccountBuilder {

    private String bank = RandomStringUtils.randomAlphanumeric(3, 16);
    private String customer = RandomStringUtils.randomAlphanumeric(1, 16);
    private Loan loan = LoanBuilder.newLoan().get();

    public static LoanAccountBuilder newLoanAccount() {
        return new LoanAccountBuilder();
    }

    public LoanAccount get() {
        return new LoanAccount(bank, customer, loan);
    }

    public LoanAccountBuilder withBank(String bank) {
        this.bank = bank;
        return this;
    }

    public LoanAccountBuilder withCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public LoanAccountBuilder withLoan(Loan loan) {
       this.loan = loan;
        return this;
    }
}
