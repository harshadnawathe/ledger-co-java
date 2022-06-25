package com.ledgerco.lending.db.inmem;

import com.ledgerco.lending.domain.LoanAccount;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.ledgerco.lending.util.LoanAccountBuilder.newLoanAccount;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryLedgerTest {

    @Nested
    class InMemoryLedgerConstructorTest {

        @Test
        void shouldInitializeTheStoreWithAMapObject() {
            InMemoryLedger ledger = new InMemoryLedger();

            Map<Key, LoanAccount> store = ledger.getStore();

            assertThat(store).isNotNull();
        }

        @Test
        void shouldContainTheGivenStore() {
            HashMap<Key, LoanAccount> store = new HashMap<>();
            InMemoryLedger ledger = new InMemoryLedger(store);

            Map<Key, LoanAccount> ledgerStore = ledger.getStore();

            assertThat(ledgerStore).isEqualTo(store);
        }
    }

    @Nested
    class InMemoryLedgerSaveTest {

        @Test
        void shouldSaveTheGivenLoanAccountInTheLedger() {
            HashMap<Key, LoanAccount> store = new HashMap<>();
            InMemoryLedger ledger = new InMemoryLedger(store);
            LoanAccount loanAccount = newLoanAccount().get();

            ledger.save(loanAccount);

            assertThat(store).containsValue(loanAccount);
        }
    }

    @Nested
    class InMemoryLedgerFindTest {

        @Test
        void shouldReturnANonNullObjectWhenPresentInTheLedger() {
            InMemoryLedger ledger = new InMemoryLedger();
            LoanAccount account = newLoanAccount().get();
            ledger.save(account);

            LoanAccount findResult = ledger.findByBankAndCustomer(account.getBank(), account.getCustomer());

            assertThat(findResult).isNotNull();
        }

        @Test
        void shouldReturnResultWithTheSameKey() {
            InMemoryLedger ledger = new InMemoryLedger();
            LoanAccount account = newLoanAccount().get();
            ledger.save(account);

            LoanAccount findResult = ledger.findByBankAndCustomer(account.getBank(), account.getCustomer());

            assertThat(Key.of(findResult)).isEqualTo(new Key(account.getBank(), account.getCustomer()));
        }

        @Test
        void shouldReturnANullObjectWhenNotPresentInTheLedger() {
            InMemoryLedger ledger = new InMemoryLedger();

            LoanAccount findResult = ledger.findByBankAndCustomer("Unknown", "Unknown");

            assertThat(findResult).isNull();
        }
    }

}