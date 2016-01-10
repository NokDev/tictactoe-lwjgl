package org.busmaxime.tictactoe.infrastructure.lwjgl.renderers;

import org.busmaxime.tictactoe.GameConstants;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Renderer {

    /**
     * OpenGL Texture's coordinates are like (1) and entities coordinates are like (2):<br/><br/>
     * <pre>
     * (0,0)   (1,0)     (0,1)   (1,1)
     *    -------           -------
     *   |       |         |       |
     *   |  (1)  |         |  (2)  |
     *   |       |         |       |
     *    -------           -------
     * (0,1)   (1,1)     (0,0)   (0,1)
     * </pre> So, in order to apply texture in the good way, top-left coordinate of (1) should be associated to top-left coordinate of (2)
     */
    public static void draw(Texture texture, int row, int column) {
        float textureWidth = texture.getWidth();
        float textureHeight = texture.getHeight();

        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, textureHeight);
        GL11.glVertex2f(column * GameConstants.SPRITE_PIXEL_WIDTH, row * GameConstants.SPRITE_PIXEL_HEIGHT);

        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(column * GameConstants.SPRITE_PIXEL_WIDTH, row * GameConstants.SPRITE_PIXEL_HEIGHT + GameConstants.SPRITE_PIXEL_HEIGHT);

        GL11.glTexCoord2f(textureWidth, 0);
        GL11.glVertex2f(column * GameConstants.SPRITE_PIXEL_WIDTH + GameConstants.SPRITE_PIXEL_WIDTH, row * GameConstants.SPRITE_PIXEL_HEIGHT + GameConstants.SPRITE_PIXEL_HEIGHT);

        GL11.glTexCoord2f(textureWidth, textureHeight);
        GL11.glVertex2f(column * GameConstants.SPRITE_PIXEL_WIDTH + GameConstants.SPRITE_PIXEL_WIDTH, row * GameConstants.SPRITE_PIXEL_HEIGHT);

        GL11.glEnd();
    }
}
