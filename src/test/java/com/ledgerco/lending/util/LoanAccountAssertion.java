package com.ledgerco.lending.util;

import com.ledgerco.lending.domain.LoanAccount;
import com.ledgerco.lending.domain.Payment;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.function.Predicate;

public class LoanAccountAssertion extends AbstractAssert<LoanAccountAssertion, LoanAccount> {
    protected LoanAccountAssertion(LoanAccount account) {
        super(account, LoanAccountAssertion.class);
    }

    public LoanAccountAssertion hasBank(String bank) {
        Assertions.assertThat(actual.getBank()).isEqualTo(bank);
        return this;
    }

    public LoanAccountAssertion hasCustomer(String customer) {
        Assertions.assertThat(actual.getCustomer()).isEqualTo(customer);
        return this;
    }

    public LoanAccountAssertion hasLoanWithPrincipal(int principal) {
        Assertions.assertThat(actual.getLoan().getPrincipal()).isEqualTo(principal);
        return this;
    }

    public LoanAccountAssertion hasLoanWithPeriodInYears(int periodInYears) {
        Assertions.assertThat(actual.getLoan().getPeriodInYears()).isEqualTo(periodInYears);
        return this;
    }

    public LoanAccountAssertion hasLoanWithLoanRate(int loanRate) {
        Assertions.assertThat(actual.getLoan().getLoanRate()).isEqualTo(loanRate);
        return this;
    }

    public LoanAccountAssertion containsPaymentMatching(Predicate<Payment> predicate) {
        Assertions.assertThat(actual.getPayments()).anyMatch(predicate);
        return this;
    }

    public static LoanAccountAssertion assertThat(LoanAccount account) {
        return new LoanAccountAssertion(account);
    }
}
