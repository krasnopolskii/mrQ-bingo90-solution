package bingo.model;

import bingo.configuration.BingoConfiguration;

public class RowTemplate implements Comparable<RowTemplate> {
    public static int cursor = 0;

    private boolean[] template;
    private int size;
    private int lastIndex = -1;

    public RowTemplate() {
        this.size = 0;
        template = new boolean[BingoConfiguration.TICKET_COLUMNS];
    }

    public void setAt(int i) {
        template[i] = true;
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean[] getTemplate() {
        return template;
    }

    @Override
    public int compareTo(RowTemplate o) {
        if  (size == o.size && size == BingoConfiguration.TICKET_ROWS_MAX_NUMBERS) {
            return 0;
        }
        if (size == BingoConfiguration.TICKET_ROWS_MAX_NUMBERS) {
            return 1;
        }
        if (o.size == BingoConfiguration.TICKET_ROWS_MAX_NUMBERS) {
            return -1;
        }
//        return Integer.compare(this.lastIndex, o.lastIndex);
        if (cursor == 0) {
            return 0;
        }
        return Boolean.compare(this.getTemplate()[cursor - 1], o.getTemplate()[cursor - 1]);
    }
}
