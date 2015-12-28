package org.busmaxime.tictactoe;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.busmaxime.tictactoe.domain.board.CaseAlreadyFilledException;
import org.busmaxime.tictactoe.domain.board.Board;
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

        win = board.getWinner();

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

}
