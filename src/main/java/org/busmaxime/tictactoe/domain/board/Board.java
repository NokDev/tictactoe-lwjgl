package org.busmaxime.tictactoe.domain.board;

/**
 * Represents the board.
 *
 * @author maxime
 */
public class Board {

    private int rows;
    private int columns;
    private int[][] boardState;

    private int rowCursor = 0;
    private int columnCursor = 0;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.boardState = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                boardState[row][column] = 0;
            }
        }
    }

    public void moveCursorAt(int row, int column) {
        if (row < rows && row >= 0) {
            rowCursor = row;
        }

        if (column < columns && column >= 0) {
            columnCursor = column;
        }
    }

    public void moveCursorBy(int deltaRow, int deltaColumn) {
        this.moveCursorAt(rowCursor + deltaRow, columnCursor + deltaColumn);
    }

    /**
     *
     * @param row
     * @param column
     * @param state
     * @throws CaseAlreadyFilledException
     */
    public void setStateAt(int row, int column, int state) {
        if (this.boardState[row][column] == 0) {
            this.boardState[row][column] = state;
        } else {
            throw new CaseAlreadyFilledException();
        }
    }

    public void setStateAtCursorPosition(int state) {
        this.setStateAt(rowCursor, columnCursor, state);
    }

    public int getStateAtCursorPosition() {
        return this.getStateAt(rowCursor, columnCursor);
    }

    public int getStateAt(int row, int column) {
        if (row > rows || row < 0) {
            return 0;
        }

        if (column > columns || column < 0) {
            return 0;
        }

        return this.boardState[row][column];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public void setBoardState(int[][] boardState) {
        this.boardState = boardState;
    }

    public int getRowCursor() {
        return rowCursor;
    }

    public void setRowCursor(int rowCursor) {
        this.rowCursor = rowCursor;
    }

    public int getColumnCursor() {
        return columnCursor;
    }

    public void setColumnCursor(int columnCursor) {
        this.columnCursor = columnCursor;
    }

    public int getWinner() {
        // top row
        if ((boardState[0][0] == boardState[0][1]) && (boardState[0][1] == boardState[0][2])) {
            return boardState[0][0];
        }

        // middle row
        if ((boardState[1][0] == boardState[1][1]) && (boardState[1][1] == boardState[1][2])) {
            return boardState[1][0];
        }

        // bottom row
        if ((boardState[2][0] == boardState[2][1]) && (boardState[2][1] == boardState[2][2])) {
            return boardState[2][0];
        }

        // Check verticals
        // left column
        if ((boardState[0][0] == boardState[1][0]) && (boardState[1][0] == boardState[2][0])) {
            return boardState[0][0];
        }

        // middle column
        if ((boardState[0][1] == boardState[1][1]) && (boardState[1][1] == boardState[2][1])) {
            return boardState[0][1];
        }

        // right column
        if ((boardState[0][2] == boardState[1][2]) && (boardState[1][2] == boardState[2][2])) {
            return boardState[0][2];
        }

        // Check diagonals
        // one diagonal
        if ((boardState[0][0] == boardState[1][1]) && (boardState[1][1] == boardState[2][2])) {
            return boardState[0][0];
        }

        // the other diagonal
        if ((boardState[0][2] == boardState[1][1]) && (boardState[1][1] == boardState[2][0])) {
            return boardState[0][2];
        }

        // Check if the board is full
        if (boardState[0][0] == 0
                || boardState[0][1] == 0
                || boardState[0][2] == 0
                || boardState[1][0] == 0
                || boardState[1][1] == 0
                || boardState[1][2] == 0
                || boardState[2][0] == 0
                || boardState[2][1] == 0
                || boardState[2][2] == 0) {
            return 0;
        }

        return 3;
    }
}
