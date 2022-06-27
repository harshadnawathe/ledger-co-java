package com.ledgerco.lending.app.cmd.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;

import java.util.List;

public class TestHandler2 implements CommandHandler {

    private boolean commandHandled = false;

    @Override
    public boolean canHandle(String type) {
        return type.equals("Command2");
    }

    @Override
    public void doHandle(List<String> arguments) {
        commandHandled = true;
    }

    public boolean isCommandHandled() {
        return commandHandled;
    }
}
