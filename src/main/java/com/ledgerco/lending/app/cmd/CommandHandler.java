package com.ledgerco.lending.app.cmd;

import java.util.List;

public interface CommandHandler {

    boolean canHandle(String type);

    void doHandle(List<String> arguments);
}
