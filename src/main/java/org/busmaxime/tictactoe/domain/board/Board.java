package org.busmaxime.tictactoe.domain.board;

/**
 * Represents the board.
 *
 * @author maxime
 */
public class Board {

    private final int rows;
    private final int columns;
    private final CaseValue[][] boardState;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.boardState = new CaseValue[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                boardState[row][column] = CaseValue.EMPTY;
            }
        }
    }

    public void setStateAt(int row, int column, CaseValue value) {
        if (this.boardState[row][column] == CaseValue.EMPTY) {
            this.boardState[row][column] = value;
        } else {
            throw new CaseAlreadyFilledException();
        }
    }

    public CaseValue getStateAt(int row, int column) {
        if (row > rows || row < 0) {
            throw new OutOfBoundBoardException();
        }

        if (column > columns || column < 0) {
            throw new OutOfBoundBoardException();
        }

        return this.boardState[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public CaseValue[][] getBoardState() {
        return boardState.clone();
    }

    public CaseValue getWinner() {
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

        return CaseValue.EMPTY;
    }
}
