package ru.j4jdraft.ood.tictac.cli;

import ru.j4jdraft.ood.tictac.model.Grid;
import ru.j4jdraft.ood.tictac.model.Position;

public class PseudoTextGridFormatter implements GridFormatter {
    private final static String NL = System.lineSeparator();
    private final static String H_LINE = "\u2500\u2500\u2500";
    private final static char V_LINE = '\u2502';
    private final static char TL_CORNER = '\u250C';
    private final static char TR_CORNER = '\u2510';
    private final static char BL_CORNER = '\u2514';
    private final static char BR_CORNER = '\u2518';
    private final static char L_TEE = '\u251C';
    private final static char R_TEE = '\u2524';
    private final static char T_TEE = '\u252C';
    private final static char B_TEE = '\u2534';
    private final static char CROSS = '\u253C';

    @Override
    public String format(Grid grid) {
        StringBuilder buffer = new StringBuilder();
        int size = grid.size();
        makeTop(buffer, size);
        for (int r = 0; r < size; r++) {
            makeMarkRow(buffer, r, grid);
            if (r < size - 1) {
                makeHorizontal(buffer, size);
            }
        }
        makeBottom(buffer, size);
        return buffer.toString();
    }

    private void makeMarkRow(StringBuilder buffer, int row, Grid grid) {
        int size = grid.size();
        buffer.append(V_LINE);
        for (int c = 0; c < size; c++) {
            buffer.append(" ");
            buffer.append(grid.getMark(new Position(row, c)));
            buffer.append(" ");
            buffer.append(V_LINE);
        }
        buffer.append(NL);
    }

    private void makeHorizontal(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, L_TEE, CROSS, R_TEE);
    }

    private void makeBottom(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, BL_CORNER, B_TEE, BR_CORNER);
    }

    private void makeTop(StringBuilder buffer, int size) {
        makeGraphRow(buffer, size, TL_CORNER, T_TEE, TR_CORNER);
    }

    private void makeGraphRow(StringBuilder buffer, int size, char start, char middle, char end) {
        buffer.append(start);
        for (int i = 0; i < size; i++) {
            buffer.append(H_LINE);
            if (i < size - 1) {
                buffer.append(middle);
            }
        }
        buffer.append(end);
        buffer.append(NL);
    }
}
