package org.busmaxime.tictactoe.infrastructure.lwjgl;

import org.busmaxime.tictactoe.GameConstants;
import org.busmaxime.tictactoe.domain.board.Board;
import org.busmaxime.tictactoe.domain.board.CaseAlreadyFilledException;
import org.busmaxime.tictactoe.domain.board.CaseValue;
import org.busmaxime.tictactoe.infrastructure.lwjgl.model.Cursor;
import org.busmaxime.tictactoe.infrastructure.lwjgl.renderers.BoardRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LwjglGame extends AbstractGameLoop {
    private static final Logger LOGGER = Logger.getLogger(LwjglGame.class.getName());

    private BoardRenderer boardRenderer;
    private CaseValue win = CaseValue.EMPTY;
    private Board board = null;
    private boolean playerOneTurn = true;
    private Cursor cursor = null;

    public static void main(String[] argv) {
        LwjglGame lwjglGame = new LwjglGame();
        lwjglGame.run();
    }

    @Override
    public void init() {
        boardRenderer = new BoardRenderer(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        board = new Board(GameConstants.ROWS, GameConstants.COLUMNS);
        cursor = new Cursor(0, 0, board);
    }

    @Override
    public boolean doesTheGameMustStop() {

        if (Display.isCloseRequested()) {
            return true;
        }

        win = board.getWinner();

        if (win != CaseValue.EMPTY) {
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
                    cursor.increaseRowToOneUnit();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                    cursor.decreaseRowToOneUnit();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                    cursor.decreaseColumnToOneUnit();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                    cursor.increaseColumnToOneUnit();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    try {
                        if (playerOneTurn) {
                            board.setStateAt(cursor.getRow(), cursor.getColumn(), CaseValue.CIRCLE);
                            playerOneTurn = false;
                        } else {
                            board.setStateAt(cursor.getRow(), cursor.getColumn(), CaseValue.CROSS);
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
        boardRenderer.draw(board, cursor);
    }

    @Override
    public void shutdown() {
        boardRenderer.shutdown();
    }
}
