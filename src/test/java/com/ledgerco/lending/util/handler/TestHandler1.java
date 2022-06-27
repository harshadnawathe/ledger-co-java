package com.ledgerco.lending.util.handler;

import com.ledgerco.lending.app.cmd.CommandHandler;

import java.util.List;

public class TestHandler1 implements CommandHandler {

    private boolean commandHandled = false;

    @Override
    public boolean canHandle(String type) {
        return type.equals("Command1");
    }

    @Override
    public void doHandle(List<String> arguments) {
        commandHandled = true;
    }

    public boolean isCommandHandled() {
        return commandHandled;
    }
}
