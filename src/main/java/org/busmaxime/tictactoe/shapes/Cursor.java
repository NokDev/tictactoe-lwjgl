package org.busmaxime.tictactoe.shapes;

/**
 * Represents the cursor that indicates which case on the board is selected.
 *
 * @author maxime
 */
public class Cursor extends AbstractEntity {

    public Cursor(int x, int y) {
        super("/cursor.png", x, y, 70, 70);
    }
}
