package org.busmaxime.tictactoe.renderers;

import java.io.IOException;
import org.busmaxime.tictactoe.shapes.Board;
import org.busmaxime.tictactoe.utils.GameConstants;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author maxime
 */
public class BoardRenderer {

    private Texture crossTexture;
    private Texture circleTexture;
    private Texture caseTexture;
    private Texture cursorTexture;

    /**
     * Create the main window in which all the elements will be drawn. It also activate openGL options like enabling alpha channel for PNG.
     *
     * @param width
     * @param height
     */
    public BoardRenderer(int width, int height) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException ex) {
            System.exit(0);
        }

        try {
            crossTexture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream("/cross.png"));
            circleTexture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream("/circle.png"));
            caseTexture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream("/case.png"));
            cursorTexture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream("/cursor.png"));
        } catch (IOException iOException) {
            System.out.println("Error while loading texture.");
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, GameConstants.WINDOW_WIDTH, 0, GameConstants.WINDOW_HEIGHT, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void draw(Board board) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        int rows = board.getRows();
        int columns = board.getColumns();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Renderer.draw(caseTexture, row, column);

                if (board.getStateAt(row, column) == 1) {
                    Renderer.draw(circleTexture, row, column);
                } else if (board.getStateAt(row, column) == 2) {
                    Renderer.draw(crossTexture, row, column);
                }
            }
        }

        Renderer.draw(cursorTexture, board.getRowCursor(), board.getColumnCursor());

        Display.update();
    }

    public void shutdown() {
        Display.destroy();
    }
}
