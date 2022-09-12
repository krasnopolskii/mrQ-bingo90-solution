package bingo.service;

import bingo.configuration.BingoConfiguration;
import bingo.model.RowTemplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class RowTemplatesGenerator {

    public List<RowTemplate> generateTemplates() {
        var templates = createTemplates();
        var visited = new ArrayList<RowTemplate>(templates.size());
        RowTemplate.cursor = 0;
        for (int i = 1; i <= BingoConfiguration.BINGO_RANGE; i++) {
            if (i % 10 == 0 && i != BingoConfiguration.BINGO_RANGE) {
                RowTemplate.cursor++;
                Collections.shuffle(visited);
                templates.addAll(visited);
                visited = new ArrayList<>(templates.size());
            }
            var template = templates.poll();
            visited.add(template);
            template.setAt(RowTemplate.cursor);
        }
        RowTemplate rest;
        while ((rest = templates.poll()) != null) {
            visited.add(rest);
        }
        Collections.shuffle(visited);
        return visited;
    }

    private PriorityQueue<RowTemplate> createTemplates() {
        var rowCount = BingoConfiguration.TICKET_ROWS * BingoConfiguration.STRIP_SIZE;
        var templates = new ArrayList<RowTemplate>(rowCount);
        for (int i = 0; i < rowCount; i++) {
            templates.add(new RowTemplate());
        }
        return new PriorityQueue<>(templates);
    }

}
