package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.CreateLoanAccountRequest;
import com.ledgerco.lending.service.model.LoanSpec;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoanCommandHandlerTest {

    @Nested
    class LoanCommandHandlerConstructorTest {

        @Test
        void shouldContainTheGivenLoanAccountService() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            LoanCommandHandler handler = new LoanCommandHandler(service);

            LoanAccountService actual = handler.getLoanAccountService();

            assertThat(actual).isEqualTo(service);
        }
    }

    @Nested
    class LoanCommandHandlerCanHandleTest {

        @Test
        void shouldReturnTrueForLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            LoanCommandHandler handler = new LoanCommandHandler(service);

            boolean canHandle = handler.canHandle("LOAN");

            assertThat(canHandle).isTrue();
        }

        @Test
        void shouldReturnFalseForNonLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            LoanCommandHandler handler = new LoanCommandHandler(service);

            boolean canHandle = handler.canHandle("BALANCE");

            assertThat(canHandle).isFalse();
        }
    }

    @Nested
    class LoanCommandHandlerDoHandleTest {

        @Test
        void shouldCallServiceWithCreateLoanAccountRequest() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            LoanCommandHandler handler = new LoanCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 5000 1 6".split("\\s+"));
            CreateLoanAccountRequest request = new CreateLoanAccountRequest(
                    new AccountSpec("IDIDI", "Dale"),
                    new LoanSpec(5000, 1, 6)
            );

            handler.doHandle(arguments);

            Mockito.verify(service).createLoanAccount(Mockito.eq(request));
        }
    }
}