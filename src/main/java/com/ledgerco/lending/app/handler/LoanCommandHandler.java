package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;
import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.CreateLoanAccountRequest;
import com.ledgerco.lending.service.model.LoanSpec;

import java.util.List;

import static java.lang.Integer.parseInt;

public class LoanCommandHandler implements CommandHandler {

    private final LoanAccountService loanAccountService;
    private static final String COMMAND_TYPE = "LOAN";

    public LoanCommandHandler(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }


    @Override
    public boolean canHandle(String type) {
        return type.equalsIgnoreCase(COMMAND_TYPE);
    }

    @Override
    public void doHandle(List<String> arguments) {
        loanAccountService.createLoanAccount(createLoanAccountRequest(arguments));
    }

    private CreateLoanAccountRequest createLoanAccountRequest(List<String> arguments) {
        return new CreateLoanAccountRequest(
                new AccountSpec(
                        arguments.get(0),
                        arguments.get(1)
                ),
                new LoanSpec(
                        parseInt(arguments.get(2)),
                        parseInt(arguments.get(3)),
                        parseInt(arguments.get(4))
                )

        );
    }

    LoanAccountService getLoanAccountService() {
        return loanAccountService;
    }
}
