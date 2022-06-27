package com.ledgerco.lending.app.cmd.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;

import java.util.List;

public class TestHandler2 extends CommandHandler {

    private boolean commandHandled = false;

    @Override
    protected boolean canHandle(String type) {
        return type.equals("Command2");
    }

    @Override
    protected void doHandle(List<String> arguments) {
        commandHandled = true;
    }

    public boolean isCommandHandled() {
        return commandHandled;
    }
}
