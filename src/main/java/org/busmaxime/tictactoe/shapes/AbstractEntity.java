package org.busmaxime.tictactoe.shapes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author maxime
 */
public abstract class AbstractEntity {

    private Texture texture;
    private int posX;
    private int posY;
    private int squareWidth = 0;
    private int squareHeight = 0;

    /**
     * texture width computed by slick utils
     */
    protected float textureWidth;

    /**
     * texture height computed by slick utils
     */
    protected float textureHeight;

    public AbstractEntity(String imageName, int x, int y, int width, int height) {
        this.posX = x;
        this.posY = y;
        this.squareWidth = width;
        this.squareHeight = height;

        try {
            texture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream(imageName));
            this.textureWidth = texture.getWidth();
            this.textureHeight = texture.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(Cross.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
    public void draw() {
        texture.bind();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, this.textureHeight);
        GL11.glVertex2d(this.posX, this.posY);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2d(this.posX, this.posY + this.squareWidth);
        GL11.glTexCoord2f(this.textureWidth, 0);
        GL11.glVertex2d(this.posX + this.squareWidth, this.posY + this.squareHeight);
        GL11.glTexCoord2f(this.textureWidth, this.textureHeight);
        GL11.glVertex2d(this.posX + this.squareWidth, posY);
        GL11.glEnd();
    }

    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
