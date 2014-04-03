package org.busmaxime.tictactoe.shapes;

/**
 * Represents the element of the player 1.
 *
 * @author maxime
 */
public class Cross extends AbstractEntity {

    public Cross(int x, int y) {
        super("/cross.png", x, y, 70, 70);
    }
}
