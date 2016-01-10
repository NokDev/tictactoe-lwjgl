package org.busmaxime.tictactoe.infrastructure.lwjgl.model;

import org.busmaxime.tictactoe.domain.board.Board;

public class Cursor {

    private final Board board;
    private int row;
    private int column;

    public Cursor(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void increaseRowToOneUnit() {
        if (this.row < board.getRows() - 1) {
            this.row++;
        }
    }

    public void decreaseRowToOneUnit() {
        if (this.row > 0) {
            this.row--;
        }
    }

    public void increaseColumnToOneUnit() {
        if (this.column < board.getColumns() - 1) {
            this.column++;
        }
    }

    public void decreaseColumnToOneUnit() {
        if (this.column > 0) {
            this.column--;
        }
    }
}
