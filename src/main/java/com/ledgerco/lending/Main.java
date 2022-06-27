package com.ledgerco.lending;

import com.ledgerco.lending.app.LedgerCoConsoleApp;
import com.ledgerco.lending.app.cmd.CommandHandler;
import com.ledgerco.lending.app.cmd.CommandSwitch;
import com.ledgerco.lending.app.handler.BalanceCommandHandler;
import com.ledgerco.lending.app.handler.LoanCommandHandler;
import com.ledgerco.lending.app.handler.PaymentCommandHandler;
import com.ledgerco.lending.db.inmem.InMemoryLedger;
import com.ledgerco.lending.service.LoanAccountService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {

        InMemoryLedger ledger = new InMemoryLedger();

        LoanAccountService loanAccountService = new LoanAccountService(ledger);

        List<CommandHandler> commandHandlers = asList(
                new LoanCommandHandler(loanAccountService),
                new PaymentCommandHandler(loanAccountService),
                new BalanceCommandHandler(loanAccountService)
        );

        CommandSwitch commandSwitch = new CommandSwitch(commandHandlers);

        Path inputFilePath = Paths.get(args[0]);

        LedgerCoConsoleApp app = new LedgerCoConsoleApp(inputFilePath, commandSwitch);

        app.run();
    }
}
