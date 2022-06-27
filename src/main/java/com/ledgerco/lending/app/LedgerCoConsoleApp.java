package com.ledgerco.lending.app;

import com.ledgerco.lending.app.cmd.Command;
import com.ledgerco.lending.app.cmd.CommandSwitch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LedgerCoConsoleApp {

    private final Path inputFilePath;
    private final CommandSwitch commandSwitch;

    public LedgerCoConsoleApp(Path inputFilePath, CommandSwitch commandSwitch) {
        this.inputFilePath = inputFilePath;
        this.commandSwitch = commandSwitch;
    }

     public void run() {
         try (Stream<String> lines = Files.lines(inputFilePath)) {
             lines.map(Command::parse).forEach(commandSwitch::handle);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

    Path getInputFilePath() {
        return inputFilePath;
    }

    CommandSwitch getCommandSwitch() {
        return commandSwitch;
    }
}
