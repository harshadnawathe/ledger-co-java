package com.ledgerco.lending.app.cmd;

import java.util.List;
import java.util.stream.IntStream;

public class CommandSwitch {

    private final CommandHandler head;

    public CommandSwitch(List<CommandHandler> handlers) {
        head = handlers.stream().findFirst().orElse(null);
        createLinks(handlers);
    }

    private void createLinks(List<CommandHandler> handlers) {
        IntStream.range(0, handlers.size() - 1)
                .forEach(i -> {
                    CommandHandler current = handlers.get(i);
                    CommandHandler next = handlers.get(i + 1);
                    current.setNext(next);
                });
    }

    CommandHandler getHead() {
        return head;
    }

    void handle(Command command) {
        if (this.head != null) {
            this.head.handle(command);
        }
    }
}
