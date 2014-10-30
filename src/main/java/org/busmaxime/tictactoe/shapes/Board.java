package org.busmaxime.tictactoe.shapes;

/**
 * Represents the background.
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
        rowCursor = row;
        columnCursor = column;
    }

    public void moveCursorBy(int deltaRow, int deltaColumn) {
        rowCursor += deltaRow;
        columnCursor += deltaColumn;
    }

    public void setStateAt(int row, int column, int state) {
        this.boardState[row][column] = state;
    }

    public void setStateAtCursorPosition(int state) {
        this.setStateAt(rowCursor, columnCursor, state);
    }

    public int getStateAtCursorPosition() {
        return this.getStateAt(rowCursor, columnCursor);
    }

    public int getStateAt(int row, int column) {
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
}
