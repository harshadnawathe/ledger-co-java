package com.ledgerco.lending.app;

import lombok.Value;

import java.util.List;

@Value
public class Command {
    String type;
    List<String> arguments;
}
