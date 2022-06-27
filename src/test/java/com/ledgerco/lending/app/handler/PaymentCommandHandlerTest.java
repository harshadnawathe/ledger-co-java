package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.service.LoanAccountNotFoundException;
import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.MakePaymentRequest;
import com.ledgerco.lending.service.model.PaymentSpec;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentCommandHandlerTest {

    @Nested
    class PaymentCommandHandlerConstructorTest {

        @Test
        void shouldContainGivenLoanAccountService() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            PaymentCommandHandler handler = new PaymentCommandHandler(service);

            LoanAccountService actual = handler.getLoanAccountService();

            assertThat(actual).isEqualTo(service);
        }
    }

    @Nested
    class PaymentCommandHandlerCanHandleTest {

        @Test
        void shouldReturnTrueForLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            PaymentCommandHandler handler = new PaymentCommandHandler(service);

            boolean canHandle = handler.canHandle("PAYMENT");

            assertThat(canHandle).isTrue();
        }

        @Test
        void shouldReturnFalseForNonLoanType() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            PaymentCommandHandler handler = new PaymentCommandHandler(service);

            boolean canHandle = handler.canHandle("BALANCE");

            assertThat(canHandle).isFalse();
        }
    }

    @Nested
    class PaymentCommandHandlerDoHandleTest {

        @SneakyThrows
        @Test
        void shouldCallServiceWithCreateLoanAccountRequest() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            PaymentCommandHandler handler = new PaymentCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 1000 5".split("\\s+"));
            MakePaymentRequest request = new MakePaymentRequest(
                    new AccountSpec("IDIDI", "Dale"),
                    new PaymentSpec(1000, 5)
            );

            handler.doHandle(arguments);

            Mockito.verify(service).makePayment(Mockito.eq(request));
        }

        @SneakyThrows
        @Test
        void shouldThrowWhenLoanAccountNotFoundExceptionIsThrownByService() {
            LoanAccountService service = Mockito.mock(LoanAccountService.class);
            PaymentCommandHandler handler = new PaymentCommandHandler(service);
            List<String> arguments = Arrays.asList("IDIDI Dale 1000 5".split("\\s+"));
            Mockito.when(service.makePayment(Mockito.any())).thenThrow(
                    new LoanAccountNotFoundException(new AccountSpec("IDIDI", "Dale"))
            );

            assertThatThrownBy(() -> {
                handler.doHandle(arguments);
            }).isInstanceOf(RuntimeException.class)
                    .hasCauseInstanceOf(LoanAccountNotFoundException.class)
                    .hasRootCauseMessage("Account with bank: IDIDI and customer: Dale, not found");
        }
    }
}