package bingo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bingo.Solution;
import bingo.configuration.BingoConfiguration;
import bingo.model.Strip;
import bingo.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SolutionTest {

    private static final int ATTEMPTS = 100;
    private static final int STRIPS_COUNT = 10000;

    Solution solution;


    @BeforeEach
    void init() {
        solution = new Solution();
    }

    @Test
    void performanceTest() {
        var generatedStrips = solution.solve();
        assertNumbersAndSize(generatedStrips);
        assertRows(generatedStrips);
        assertColumns(generatedStrips);
    }

    private void assertRows(List<Strip> strips) {
        for (Strip strip : strips) {
            for (Ticket ticket : strip.getTickets()) {
                var rows = ticket.getRows();
                for (Integer[] row : rows) {
                    var blanks = 0;
                    var numbers = 0;
                    for (Integer number : row) {
                        if  (number == null) {
                            blanks++;
                        }
                        else {
                            numbers++;
                        }
                    }
                    assertThat(blanks, is(BingoConfiguration.TICKET_COLUMNS - BingoConfiguration.TICKET_ROWS_MAX_NUMBERS));
                    assertThat(numbers, is(BingoConfiguration.TICKET_ROWS_MAX_NUMBERS));
                }
            }
        }
    }

    private void assertColumns(List<Strip> strips) {
        for (Strip strip : strips) {
            for (Ticket ticket : strip.getTickets()) {
                var rows = ticket.getRows();
                for (int column = 0; column < BingoConfiguration.TICKET_COLUMNS; column++) {
                    Integer previous = null;
                    for (int i = 0; i < BingoConfiguration.TICKET_ROWS; i++) {
                        var cur = rows[i][column];
                        if (cur == null) {
                            continue;
                        }
                        if (previous != null) {
                            assertTrue(cur > previous);
                        }
                        assertThat(cur / 10, is(cur == BingoConfiguration.BINGO_RANGE ? column + 1 : column));
                    }
                }
            }
        }
    }

    private void assertNumbersAndSize(List<Strip> strips) {
        for (Strip strip : strips) {
            var numbers = new int[BingoConfiguration.BINGO_RANGE];
            for (Ticket ticket : strip.getTickets()) {
                var numbersCount = 0;
                var rows = ticket.getRows();
                assertThat(rows.length, is(BingoConfiguration.TICKET_ROWS));
                for (Integer[] row : rows) {
                    assertThat(row.length, is(BingoConfiguration.TICKET_COLUMNS));
                    for (Integer number : row) {
                        if  (number != null) {
                            numbers[number - 1]++;
                            numbersCount++;
                        }
                    }
                }
                assertThat(numbersCount, is(BingoConfiguration.BINGO_RANGE / BingoConfiguration.STRIP_SIZE));
            }
            for (int numberSaw : numbers) {
                assertThat(numberSaw, is(1));
            }
        }
    }
}
