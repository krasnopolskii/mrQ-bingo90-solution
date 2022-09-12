package bingo.model;

import java.util.List;
import java.util.Random;

public class Ticket implements Comparable<Ticket> {
    private static Random r = new Random();

    private List<RowTemplate> rowTemplates;
    private Integer[][] rows;
    private int[] columnSizes;
    private int lastFilledColumn;

    public Ticket(List<RowTemplate> rowTemplates) {
        this.rowTemplates = rowTemplates;
        int width = rowTemplates.get(0).getTemplate().length;
        this.columnSizes = new int[width];
        this.rows = new Integer[rowTemplates.size()][width];
        for(int colNum = 0; colNum < width; colNum++) {
            var size = 0;
            for (RowTemplate rowTemplate : rowTemplates) {
                if (rowTemplate.getTemplate()[colNum]) {
                    size++;
                }
            }
            columnSizes[colNum] = size;
        }
        this.lastFilledColumn = -1;
    }

    public Integer[][] getRows() {
        return this.rows;
    }

    public boolean add(int number) {
        var colNum = number / 10;
        colNum = colNum == columnSizes.length ? colNum - 1 : colNum;
        if (columnSizes[colNum] == 0) {
            return false;
        }
        var inserted = false;
        for (int rowNum = 0; rowNum < rowTemplates.size(); rowNum++) {
            var rowTemplate = rowTemplates.get(rowNum);
            if (rowTemplate.getTemplate()[colNum]) {
                rows[rowNum][colNum] = number;
                columnSizes[colNum]--;
                inserted = true;
                rowTemplate.getTemplate()[colNum] = false;
                break;
            }
        }
        if (columnSizes[colNum] == 0) {
            lastFilledColumn = -1;
        }
        return inserted;
    }

    @Override
    public int compareTo(Ticket o) {
        return Integer.compare(this.lastFilledColumn, o.lastFilledColumn);
    }
}
