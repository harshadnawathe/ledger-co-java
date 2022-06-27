package com.ledgerco.lending.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutTap {

    public static String tapSystemOut(PrintCallable callable) {
        final PrintStream standardOut = System.out;
        try {
            final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            callable.call();

            return outputStreamCaptor.toString();
        } finally {
            System.setOut(standardOut);
        }
    }

    @FunctionalInterface
    public interface PrintCallable {
        void call();
    }
}
