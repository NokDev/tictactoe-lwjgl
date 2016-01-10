package org.busmaxime;

import org.busmaxime.tictactoe.domain.board.Board;

public class BoardDSL {

    public static Board board3By3() {
        return new Board(3, 3);
    }
}
