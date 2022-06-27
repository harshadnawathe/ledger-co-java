package com.ledgerco.lending.app.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;
import com.ledgerco.lending.service.LoanAccountNotFoundException;
import com.ledgerco.lending.service.LoanAccountService;
import com.ledgerco.lending.service.model.AccountSpec;
import com.ledgerco.lending.service.model.CheckBalanceRequest;
import com.ledgerco.lending.service.model.LoanAccountResponse;

import java.util.List;

import static java.lang.Integer.parseInt;

public class BalanceCommandHandler implements CommandHandler {
    private final LoanAccountService loanAccountService;
    private static final String COMMAND_TYPE = "BALANCE";

    public BalanceCommandHandler(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }

    @Override
    public boolean canHandle(String type) {
        return type.equalsIgnoreCase(COMMAND_TYPE);
    }

    @Override
    public void doHandle(List<String> arguments) {
        try {
            LoanAccountResponse response = loanAccountService.checkBalance(checkBalanceRequest(arguments));

            String balance = String.format("%s %s %d %d",
                    response.getAccount().getBank(), response.getAccount().getCustomer(),
                    response.getBalance().getAmountPaid(), response.getBalance().getNumEmiRemaining()
            );

            System.out.println(balance);

        } catch (LoanAccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CheckBalanceRequest checkBalanceRequest(List<String> arguments) {
        return new CheckBalanceRequest(
                new AccountSpec(
                        arguments.get(0),
                        arguments.get(1)
                ),
                parseInt(arguments.get(2))
        );
    }

    LoanAccountService getLoanAccountService() {
        return loanAccountService;
    }
}
