package com.ledgerco.lending;

import org.junit.jupiter.api.Test;

import static com.ledgerco.lending.util.SystemOutTap.tapSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalTest {

    @Test
    void shouldProduceTheOutputByReadingTheInputFile() {
        String expected = "IDIDI Dale 1326 9\n" +
                "IDIDI Dale 3652 4\n" +
                "UON Shelly 15856 3\n" +
                "MBI Harry 9044 10\n";

        String output = tapSystemOut(() -> Main.main(new String[]{"./sample_input/input2.txt"}));

        assertThat(output).isEqualTo(expected);
    }
}
