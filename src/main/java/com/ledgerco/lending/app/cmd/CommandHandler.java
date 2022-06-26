package com.ledgerco.lending.app.cmd;

import java.util.List;

public abstract class CommandHandler {

    private CommandHandler next;

    public void setNext(CommandHandler next) {
        this.next = next;
    }

    CommandHandler getNext() {
        return next;
    }

    public final void handle(Command command) {
        if (this.canHandle(command.getType())) {
            this.doHandle(command.getArguments());
            return;
        }

        if (next != null) {
            next.handle(command);
        }
    }

    protected abstract boolean canHandle(String type);

    protected abstract void doHandle(List<String> arguments);
}
