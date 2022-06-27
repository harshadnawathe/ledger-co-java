package com.ledgerco.lending.app.cmd;

import java.util.List;

public class CommandSwitch {

    List<CommandHandler> getHandlers() {
        return handlers;
    }

    private final List<CommandHandler> handlers;

    public CommandSwitch(List<CommandHandler> handlers) {
        this.handlers = handlers;
    }

     public void handle(Command command) {
         handlers.stream()
                 .filter(it -> it.canHandle(command.getType()))
                 .findFirst()
                 .ifPresent(it -> it.doHandle(command.getArguments()));
     }
}
