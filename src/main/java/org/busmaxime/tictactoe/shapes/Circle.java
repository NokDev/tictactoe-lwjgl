package org.busmaxime.tictactoe.shapes;

/**
 * Represents the element of the player 2.
 *
 * @author maxime
 */
public class Circle extends AbstractEntity {

    public Circle(int x, int y) {
        super("/circle.png", x, y, 70, 70);
    }
}
