package com.ledgerco.lending.service.model;

import com.ledgerco.lending.domain.Loan;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanSpecTest {

    @Nested
    class LoanSpecToLoanMappingTest {

        @Test
        void shouldMapPrincipalFromSpecToLoan() {
            LoanSpec spec = new LoanSpec(2000, 2, 2);

            Loan loan = spec.toLoan();

            assertThat(loan.getPrincipal()).isEqualTo(spec.getPrincipal());
        }

        @Test
        void shouldMapPeriodInYearsFromSpecToLoan() {
            LoanSpec spec = new LoanSpec(2000, 2, 2);

            Loan loan = spec.toLoan();

            assertThat(loan.getPeriodInYears()).isEqualTo(spec.getPeriodInYears());
        }

        @Test
        void shouldMapLoanRateFromSpecToLoan() {
            LoanSpec spec = new LoanSpec(2000, 2, 2);

            Loan loan = spec.toLoan();

            assertThat(loan.getLoanRate()).isEqualTo(spec.getLoanRate());
        }

    }
}