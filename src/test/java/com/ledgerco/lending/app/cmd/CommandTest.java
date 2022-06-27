package com.ledgerco.lending.app.cmd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    private final String TEXT = "MyCmd Arg1 Arg2";

    @Test
    void shouldCreateCommandWithGivenTypeFromText() {
        Command command = Command.parse(TEXT);

        String type = command.getType();

        assertThat(type).isEqualTo("MyCmd");
    }

    @Test
    void shouldCreateCommandWithGivenArguments() {
        Command command = Command.parse(TEXT);

        List<String> arguments = command.getArguments();

        assertThat(arguments).containsExactly("Arg1", "Arg2");
    }
}