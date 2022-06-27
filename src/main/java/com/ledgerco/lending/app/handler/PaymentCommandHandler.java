package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;
import com.ledgerco.lending.service.LoanAccountNotFoundException;
import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.MakePaymentRequest;
import com.ledgerco.lending.service.model.PaymentSpec;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PaymentCommandHandler implements CommandHandler {
    private final LoanAccountService loanAccountService;
    private static final String COMMAND_TYPE = "PAYMENT";

    public PaymentCommandHandler(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }


    @Override
    public boolean canHandle(String type) {
        return type.equalsIgnoreCase(COMMAND_TYPE);
    }

    @Override
    public void doHandle(List<String> arguments) {
        try {
            loanAccountService.makePayment(makePaymentRequest(arguments));
        } catch (LoanAccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private MakePaymentRequest makePaymentRequest(List<String> arguments) {
        return new MakePaymentRequest(
                new AccountSpec(
                        arguments.get(0),
                        arguments.get(1)
                ),
                new PaymentSpec(
                        parseInt(arguments.get(2)),
                        parseInt(arguments.get(3))
                )
        );
    }

    LoanAccountService getLoanAccountService() {
        return loanAccountService;
    }
}
