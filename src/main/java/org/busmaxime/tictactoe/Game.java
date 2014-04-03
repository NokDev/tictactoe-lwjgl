package org.busmaxime.tictactoe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.busmaxime.tictactoe.shapes.Board;
import org.busmaxime.tictactoe.utils.GameConstants;
import org.busmaxime.tictactoe.renderers.LwjglRenderer;
import org.busmaxime.tictactoe.shapes.Circle;
import org.busmaxime.tictactoe.shapes.Cross;
import org.busmaxime.tictactoe.shapes.Cursor;
import org.busmaxime.tictactoe.shapes.AbstractEntity;
import org.busmaxime.tictactoe.shapes.PlayerOneScreen;
import org.busmaxime.tictactoe.shapes.PlayerTwoScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game extends AbstractGameLoop {

    private LwjglRenderer renderer = new LwjglRenderer();
    private int win = 0;
    private Cursor cursor = null;
    private Board board = null;
    private List<AbstractEntity> entities = new ArrayList<AbstractEntity>();
    private boolean curseurCanMove = true;
    private boolean playerOneTurn = true;
    private int indexCursorX = 0;
    private int indexCursorY = 0;
    private boolean gameFinished = false;
    private byte[][] boardState = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };

    @Override
    public void init() {
        renderer.init(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        cursor = new Cursor(0, 0);
        board = new Board();
    }

    @Override
    public boolean isGameShouldStop() {

        if (Display.isCloseRequested()) {
            return true;
        }

        win = checkWinner(boardState);

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
                    if (indexCursorY == 2) {
                        curseurCanMove = false;
                    }
                    if (curseurCanMove) {
                        cursor.move(0, 70);
                        curseurCanMove = false;
                        indexCursorY++;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                    if (indexCursorY == 0) {
                        curseurCanMove = false;
                    }
                    if (curseurCanMove) {
                        cursor.move(0, -70);
                        curseurCanMove = false;
                        indexCursorY--;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                    if (indexCursorX == 0) {
                        curseurCanMove = false;
                    }
                    if (curseurCanMove) {
                        cursor.move(-70, 0);
                        curseurCanMove = false;
                        indexCursorX--;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                    if (indexCursorX == 2) {
                        curseurCanMove = false;
                    }
                    if (curseurCanMove) {
                        cursor.move(70, 0);
                        curseurCanMove = false;
                        indexCursorX++;
                    }
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    int xCursor = cursor.getPosX();
                    int yCursor = cursor.getPosY();
                    if (playerOneTurn) {
                        if (boardState[indexCursorX][indexCursorY] != 0) {
                            System.out.println("There's already something here.");
                        } else {
                            entities.add(new Cross(xCursor, yCursor));
                            playerOneTurn = false;
                            boardState[indexCursorX][indexCursorY] = 1;
                        }
                    } else {
                        if (boardState[indexCursorX][indexCursorY] != 0) {
                            System.out.println("There's already something here.");
                        } else {
                            entities.add(new Circle(xCursor, yCursor));
                            playerOneTurn = true;
                            boardState[indexCursorX][indexCursorY] = 2;
                        }
                    }
                }
            }

        } else {
            if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
                curseurCanMove = true;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                curseurCanMove = true;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                curseurCanMove = true;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                curseurCanMove = true;
            }
        }
    }

    @Override
    public void draw() {
        LinkedList<AbstractEntity> entitiesToDraw = new LinkedList<AbstractEntity>(entities);

        if (win == 1) {
            entitiesToDraw.add(new PlayerOneScreen());
        } else if (win == 2) {
            entitiesToDraw.add(new PlayerTwoScreen());
        } else {
            entitiesToDraw.addFirst(board);
            entitiesToDraw.addLast(cursor);

        }

        renderer.draw(entitiesToDraw);
    }

    @Override
    public void shutdown() {
        renderer.shutdown();
    }

    public static void main(String[] argv) {
        Game game = new Game();
        game.run();
    }

    private int checkWinner(byte[][] board) {
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
