package com.ledgerco.lending.app.cmd;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class CommandHandlerTest {

    @Nested
    class CommandHandlerSetterTest {

        @Test
        void shouldSetNextCommandHandler() {
            CommandHandler commandHandler = new TestHandler1();
            CommandHandler next = new TestHandler2();

            commandHandler.setNext(next);

            assertThat(commandHandler.getNext()).isEqualTo(next);
        }
    }

    @Nested
    class CommandHandlerHandleTest {

        @Test
        void shouldDelegateIfCurrentHandlerCanHandle() {
            TestHandler1 commandHandler = new TestHandler1();

            commandHandler.handle(new Command("Command1", emptyList()));

            assertThat(commandHandler.isCommandHandled()).isTrue();
        }

        @Test
        void shouldNotDelegateToNextIfCurrentHandlerCanHandle() {
            TestHandler1 commandHandler = new TestHandler1();
            TestHandler2 next = new TestHandler2();
            commandHandler.setNext(next);

            commandHandler.handle(new Command("Command1", emptyList()));

            assertThat(next.isCommandHandled()).isFalse();
        }

        @Test
        void shouldDelegateToNextIfCurrentHandlerCantHandle() {
            TestHandler1 commandHandler = new TestHandler1();
            TestHandler2 next = new TestHandler2();
            commandHandler.setNext(next);

            commandHandler.handle(new Command("Command2", emptyList()));

            assertThat(next.isCommandHandled()).isTrue();
        }

        @Test
        void shouldNotDelegateIfCurrentHandlerCantHandle() {
            TestHandler1 commandHandler = new TestHandler1();
            TestHandler2 next = new TestHandler2();
            commandHandler.setNext(next);

            commandHandler.handle(new Command("Command2", emptyList()));

            assertThat(commandHandler.isCommandHandled()).isFalse();
        }
    }

    static class TestHandler1 extends CommandHandler {

        private boolean commandHandled = false;

        @Override
        protected boolean canHandle(String type) {
            return type.equals("Command1");
        }

        @Override
        protected void doHandle(List<String> arguments) {
            commandHandled = true;
        }

        public boolean isCommandHandled() {
            return commandHandled;
        }
    }

    static class TestHandler2 extends CommandHandler {

        private boolean commandHandled = false;

        @Override
        protected boolean canHandle(String type) {
            return type.equals("Command2");
        }

        @Override
        protected void doHandle(List<String> arguments) {
            commandHandled = true;
        }

        public boolean isCommandHandled() {
            return commandHandled;
        }
    }
}