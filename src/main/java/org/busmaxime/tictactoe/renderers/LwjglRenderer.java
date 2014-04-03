package org.busmaxime.tictactoe.renderers;

import java.util.List;
import org.busmaxime.tictactoe.shapes.AbstractEntity;
import org.busmaxime.tictactoe.utils.GameConstants;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maxime
 */
public class LwjglRenderer {

    /**
     * Create the main window in which all the elements will be drawn. It also activate openGL options like enabling alpha channel for PNG.
     *
     * @param width
     * @param height
     */
    public void init(int width, int height) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException ex) {
            System.exit(0);
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

    public void draw(List<AbstractEntity> entities) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        for (AbstractEntity entity : entities) {
            entity.draw();
        }
        Display.update();
    }

    public void shutdown() {
        Display.destroy();
    }
}
