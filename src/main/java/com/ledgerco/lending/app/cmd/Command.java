package com.ledgerco.lending.app.cmd;

import lombok.Value;

import java.util.List;

@Value
public class Command {
    String type;
    List<String> arguments;
}
