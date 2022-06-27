package com.ledgerco.lending.app;

import com.ledgerco.lending.app.cmd.CommandSwitch;
import com.ledgerco.lending.util.handler.TestHandler1;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;


class LedgerCoConsoleAppTest {

    Path inputFilePath = generateTestInput("test-input.txt", singletonList("Command1 Arg1"));

    @Nested
    class LedgerCoConsoleAppConstructorTest {

        CommandSwitch commandSwitch = new CommandSwitch(emptyList());

        @Test
        void shouldContainGivenInputFilePath() {

            LedgerCoConsoleApp app = new LedgerCoConsoleApp(inputFilePath, commandSwitch);

            Path actual = app.getInputFilePath();

            assertThat(actual).isEqualTo(inputFilePath);
        }

        @Test
        void shouldContainGivenCommandSwitch() {
            LedgerCoConsoleApp app = new LedgerCoConsoleApp(inputFilePath, commandSwitch);

            CommandSwitch actual = app.getCommandSwitch();

            assertThat(actual).isEqualTo(commandSwitch);
        }
    }

    @Nested
    class LedgerCoConsoleAppRunTest {


        private final TestHandler1 handler = new TestHandler1();
        CommandSwitch commandSwitch = new CommandSwitch(singletonList(handler));

        @Test
        void shouldExecuteCommandFromTheInputFile() {
            LedgerCoConsoleApp app = new LedgerCoConsoleApp(inputFilePath, commandSwitch);

            app.run();

            assertThat(handler.isCommandHandled()).isTrue();
        }
    }

    @SneakyThrows
    private Path generateTestInput(String fileName, List<String> content) {
        Path tempFile = Files.createTempFile(fileName, null);
        Files.write(tempFile, content);
        return tempFile;
    }
}