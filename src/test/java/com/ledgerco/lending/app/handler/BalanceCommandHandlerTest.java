package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.service.LoanAccountNotFoundException;
import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.BalanceSpec;
import com.ledgerco.lending.service.model.CheckBalanceRequest;
import com.ledgerco.lending.service.model.LoanAccountResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static com.ledgerco.lending.util.SystemOutTap.tapSystemOut;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BalanceCommandHandlerTest {

    @Nested
    class BalanceCommandHandlerConstructorTest {

        @Test
        void shouldContainGivenLoanAccountService() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            BalanceCommandHandler handler = new BalanceCommandHandler(service);

            LoanAccountService actual = handler.getLoanAccountService();

            assertThat(actual).isEqualTo(service);
        }
    }

    @Nested
    class BalanceCommandHandlerCanHandleTest {

        @Test
        void shouldReturnTrueForLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            BalanceCommandHandler handler = new BalanceCommandHandler(service);

            boolean canHandle = handler.canHandle("BALANCE");

            assertThat(canHandle).isTrue();
        }

        @Test
        void shouldReturnFalseForNonLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            BalanceCommandHandler handler = new BalanceCommandHandler(service);

            boolean canHandle = handler.canHandle("LOAN");

            assertThat(canHandle).isFalse();
        }
    }

    @Nested
    class BalanceCommandHandlerDoHandleTest {

        @SneakyThrows
        @Test
        void shouldCallServiceWithCreateLoanAccountRequest() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            CheckBalanceRequest request = new CheckBalanceRequest(
                    new AccountSpec("IDIDI", "Dale"),
                    3
            );
            Mockito.when(service.checkBalance(Mockito.eq(request))).thenReturn(
                    new LoanAccountResponse(
                            new AccountSpec("IDIDI", "Dale"),
                            new BalanceSpec(1000, 20)
                    )
            );
            BalanceCommandHandler handler = new BalanceCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 3".split("\\s+"));

            handler.doHandle(arguments);

            Mockito.verify(service).checkBalance(Mockito.eq(request));
        }

        @SneakyThrows
        @Test
        void shouldPrintBalanceOnTheConsole() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            CheckBalanceRequest request = new CheckBalanceRequest(
                    new AccountSpec("IDIDI", "Dale"),
                    3
            );
            Mockito.when(service.checkBalance(Mockito.eq(request))).thenReturn(
                    new LoanAccountResponse(
                            new AccountSpec("IDIDI", "Dale"),
                            new BalanceSpec(1000, 20)
                    )
            );
            BalanceCommandHandler handler = new BalanceCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 3".split("\\s+"));

            String balance = tapSystemOut(() -> handler.doHandle(arguments));

            assertThat(balance).isEqualTo("IDIDI Dale 1000 20\n");
        }

        @SneakyThrows
        @Test
        void shouldThrowWhenLoanAccountNotFoundExceptionIsThrownByService() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            BalanceCommandHandler handler = new BalanceCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 3".split("\\s+"));
            Mockito.when(service.checkBalance(Mockito.any())).thenThrow(
                    new LoanAccountNotFoundException(new AccountSpec("IDIDI", "Dale"))
            );

            assertThatThrownBy(() -> handler.doHandle(arguments))
                    .isInstanceOf(RuntimeException.class)
                    .hasCauseInstanceOf(LoanAccountNotFoundException.class)
                    .hasRootCauseMessage("Account with bank: IDIDI and customer: Dale, not found");
        }
    }
}