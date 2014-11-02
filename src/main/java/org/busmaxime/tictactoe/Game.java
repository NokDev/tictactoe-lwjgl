package org.busmaxime.tictactoe;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.busmaxime.tictactoe.exceptions.CaseAlreadyFilledException;
import org.busmaxime.tictactoe.shapes.Board;
import org.busmaxime.tictactoe.utils.GameConstants;
import org.busmaxime.tictactoe.renderers.BoardRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game extends AbstractGameLoop {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    private BoardRenderer boardRenderer;
    private int win = 0;
    private Board board = null;
    private boolean playerOneTurn = true;

    @Override
    public void init() {
        boardRenderer = new BoardRenderer(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        board = new Board(GameConstants.ROWS, GameConstants.COLUMNS);
    }

    @Override
    public boolean doesTheGameMustStop() {

        if (Display.isCloseRequested()) {
            return true;
        }

        win = checkWinner(board.getBoardState());

        if (win != 0) {
            if (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void update(long delta) {
        if (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                    board.moveCursorBy(0, 1);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                    board.moveCursorBy(0, -1);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                    board.moveCursorBy(-1, 0);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                    board.moveCursorBy(1, 0);
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    try {
                        if (playerOneTurn) {
                            board.setStateAtCursorPosition(1);
                            playerOneTurn = false;
                        } else {
                            board.setStateAtCursorPosition(2);
                            playerOneTurn = true;
                        }
                    } catch (CaseAlreadyFilledException cafe) {
                        LOGGER.log(Level.INFO, cafe.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        boardRenderer.draw(board);
    }

    @Override
    public void shutdown() {
        boardRenderer.shutdown();
    }

    public static void main(String[] argv) {
        Game game = new Game();
        game.run();
    }

    private int checkWinner(int[][] board) {
        // top row
        if ((board[0][0] == board[0][1]) && (board[0][1] == board[0][2])) {
            return board[0][0];
        }

        // middle row
        if ((board[1][0] == board[1][1]) && (board[1][1] == board[1][2])) {
            return board[1][0];
        }

        // bottom row
        if ((board[2][0] == board[2][1]) && (board[2][1] == board[2][2])) {
            return board[2][0];
        }

        // Check verticals
        // left column
        if ((board[0][0] == board[1][0]) && (board[1][0] == board[2][0])) {
            return board[0][0];
        }

        // middle column
        if ((board[0][1] == board[1][1]) && (board[1][1] == board[2][1])) {
            return board[0][1];
        }

        // right column
        if ((board[0][2] == board[1][2]) && (board[1][2] == board[2][2])) {
            return board[0][2];
        }

        // Check diagonals
        // one diagonal
        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            return board[0][0];
        }

        // the other diagonal
        if ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
            return board[0][2];
        }

        // Check if the board is full
        if (board[0][0] == 0
                || board[0][1] == 0
                || board[0][2] == 0
                || board[1][0] == 0
                || board[1][1] == 0
                || board[1][2] == 0
                || board[2][0] == 0
                || board[2][1] == 0
                || board[2][2] == 0) {
            return 0;
        }

        return 3;
    }
}
