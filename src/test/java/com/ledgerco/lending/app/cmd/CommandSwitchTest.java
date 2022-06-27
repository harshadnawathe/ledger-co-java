package com.ledgerco.lending.app.cmd;

import com.ledgerco.lending.app.cmd.handler.TestHandler1;
import com.ledgerco.lending.app.cmd.handler.TestHandler2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class CommandSwitchTest {


    @Nested
    class CommandSwitchConstructorTest {

        @Test
        void shouldContainGivenHandlers() {
            List<CommandHandler> handlers = emptyList();
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            final List<CommandHandler> actual = commandSwitch.getHandlers();

            assertThat(actual).isEqualTo(handlers);
        }

    }

    @Nested
    class CommandSwitchHandleTest {

        @Test
        void shouldDelegateIfCurrentHandlerCanHandle() {
            TestHandler1 testHandler1 = new TestHandler1();
            TestHandler2 testHandler2 = new TestHandler2();
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(testHandler1);
            handlers.add(testHandler2);
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            commandSwitch.handle(new Command("Command1", emptyList()));

            assertThat(testHandler1.isCommandHandled()).isTrue();
        }

        @Test
        void shouldNotDelegateToNextIfCurrentHandlerCanHandle() {
            TestHandler1 testHandler1 = new TestHandler1();
            TestHandler2 testHandler2 = new TestHandler2();
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(testHandler1);
            handlers.add(testHandler2);
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            commandSwitch.handle(new Command("Command1", emptyList()));

            assertThat(testHandler2.isCommandHandled()).isFalse();
        }

        @Test
        void shouldNotDelegateIfCurrentHandlerCantHandle() {
            TestHandler1 testHandler1 = new TestHandler1();
            TestHandler2 testHandler2 = new TestHandler2();
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(testHandler1);
            handlers.add(testHandler2);
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            commandSwitch.handle(new Command("Command2", emptyList()));

            assertThat(testHandler1.isCommandHandled()).isFalse();
        }

        @Test
        void shouldDelegateToNextIfCurrentHandlerCantHandle() {
            TestHandler1 testHandler1 = new TestHandler1();
            TestHandler2 testHandler2 = new TestHandler2();
            ArrayList<CommandHandler> handlers = new ArrayList<>();
            handlers.add(testHandler1);
            handlers.add(testHandler2);
            CommandSwitch commandSwitch = new CommandSwitch(handlers);

            commandSwitch.handle(new Command("Command2", emptyList()));

            assertThat(testHandler2.isCommandHandled()).isTrue();
        }
    }
}