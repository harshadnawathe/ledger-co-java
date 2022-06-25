package com.ledgerco.lending.db.inmem;

import com.ledgerco.lending.domain.LoanAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.domain.util.LoanAccountBuilder.newLoanAccount;
import static org.assertj.core.api.Assertions.assertThat;

class KeyTest {

    @Nested
    @DisplayName("when initialized")
    class KeyConstructorTest {

        @Test
        void shouldContainTheGivenBank() {
            Key key = new Key("IDIDI", "Harry");

            String bank = key.getBank();

            assertThat(bank).isEqualTo("IDIDI");
        }

        @Test
        void shouldContainTheGivenCustomer() {
            Key key = new Key("IDIDI", "Harry");

            String customer = key.getCustomer();

            assertThat(customer).isEqualTo("Harry");
        }
    }

    @Nested
    class KeyEqualityTest {

        @Test
        void shouldBeEqual() {
            Key key1 = new Key("IDIDI", "Harry");
            Key key2 = new Key("IDIDI", "Harry");

            boolean isEqual = key1.equals(key2);

            assertThat(isEqual).isTrue();
        }

        @Test
        void shouldHaveSameHashCode() {
            Key key1 = new Key("IDIDI", "Harry");
            Key key2 = new Key("IDIDI", "Harry");

            boolean isEqual = key1.hashCode() == key2.hashCode();

            assertThat(isEqual).isTrue();
        }
    }

    @Nested
    class KeyOfLoanAccountTest {

        @Test
        void shouldCreateKeyFromLoanAccount() {
            LoanAccount loanAccount = newLoanAccount().withBank("MBI").withCustomer("Dale").get();
            Key expected = new Key("MBI", "Dale");

            Key key = Key.of(loanAccount);

            assertThat(key).isEqualTo(expected);
        }
    }
}