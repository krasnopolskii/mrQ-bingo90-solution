package bingo.service;

import bingo.configuration.BingoConfiguration;
import bingo.model.RowTemplate;
import bingo.model.Strip;
import bingo.model.Ticket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class StripGenerator {

    private final RowTemplatesGenerator rowTemplatesGenerator;

    public StripGenerator(RowTemplatesGenerator rowTemplatesGenerator) {
        this.rowTemplatesGenerator = rowTemplatesGenerator;
    }

    public Strip generateStrip() {
        var rowTemplates = rowTemplatesGenerator.generateTemplates();
        var tickets = createTickets(rowTemplates);
        var visited = new ArrayList<Ticket>(1);
        for (int i = 1; i <= BingoConfiguration.BINGO_RANGE; i++) {
            var ticket = tickets.poll();
            visited.add(ticket);
            if (!ticket.add(i)) {
                throw new RuntimeException("wrong ticket");
            }
            shuffleQueue(tickets, visited);
            visited = new ArrayList<>();
        }
        Ticket rest;
        while ((rest = tickets.poll()) != null) {
            visited.add(rest);
        }
        return new Strip(visited);
    }

    private <V> void shuffleQueue(Queue<V> queue, List<V> visited) {
        V rest;
        while ((rest = queue.poll()) != null) {
            visited.add(rest);
        }
        Collections.shuffle(visited);
        queue.addAll(visited);
    }

    public List<Strip> generateStrips(int count) {
        var result = new ArrayList<Strip>(count);
        for (int i = 0; i < count; i++) {
            result.add(generateStrip());
        }
        return result;
    }


    private PriorityQueue<Ticket> createTickets(List<RowTemplate> rowTemplates) {
        var tickets = new ArrayList<Ticket>(BingoConfiguration.STRIP_SIZE);
        var rows = new ArrayList<RowTemplate>();
        for (int r = 0; r < rowTemplates.size(); r++) {
            rows.add(rowTemplates.get(r));
            if  ((r+1) % 3 == 0) {
                tickets.add(new Ticket(rows));
                rows = new ArrayList<>();
            }
        }
        Collections.shuffle(tickets);
        return new PriorityQueue<>(tickets);
    }

}
