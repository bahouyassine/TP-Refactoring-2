package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import theatricalplays.Formats.PrintFormat;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void TxtTest() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", TheaterPlayType.TRAGEDY),
                "as-like", new Play("As You Like It", TheaterPlayType.COMEDY),
                "othello", new Play("Othello", TheaterPlayType.TRAGEDY));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,PrintFormat.TXT);
        verify(result);
    }

    @Test
    void TxtTestWithDiscount() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", TheaterPlayType.TRAGEDY),
                "as-like", new Play("As You Like It", TheaterPlayType.COMEDY),
                "othello", new Play("Othello", TheaterPlayType.TRAGEDY));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 100),
                new Performance("as-like", 120),
                new Performance("othello", 90)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,PrintFormat.TXT);
        verify(result);
    }

    @Test
    void htmlTest() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", TheaterPlayType.TRAGEDY),
                "as-like", new Play("As You Like It", TheaterPlayType.COMEDY),
                "othello", new Play("Othello", TheaterPlayType.TRAGEDY));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,PrintFormat.HTML);
        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Map<String, Play> plays = Map.of(
                "henry-v",  new Play("Henry V", TheaterPlayType.HISTORY),
                "as-like", new Play("As You Like It", TheaterPlayType.PASTORAL));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));
        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice, plays,PrintFormat.TXT);

        });
    }
}
