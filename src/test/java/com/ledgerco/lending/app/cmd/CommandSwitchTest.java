package com.ledgerco.lending.app.cmd;

import com.ledgerco.lending.app.cmd.handler.TestHandler1;
import com.ledgerco.lending.app.cmd.handler.TestHandler2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class CommandSwitchTest {

    @Nested
    class CommandSwitchConstructorTest {

        @Test
        void shouldSetFirstCommandHandlerAsHead() {

            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(new TestHandler1());

            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            assertThat(commandSwitch.getHead()).isEqualTo(handlers.get(0));
        }

        @Test
        void shouldCreateLinks() {
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(new TestHandler1());
            handlers.add(new TestHandler2());

            new CommandSwitch(handlers);

            assertThat(handlers.get(0).getNext()).isEqualTo(handlers.get(1));
        }
    }

    @Nested
    class CommandSwitchHandleTest {

        @Test
        void shouldDelegateTheCommandToHead() {
            TestHandler1 testHandler1 = new TestHandler1();
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(testHandler1);
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            commandSwitch.handle(new Command("Command1", emptyList()));

            assertThat(testHandler1.isCommandHandled()).isTrue();
        }
    }
}