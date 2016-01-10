package org.busmaxime.tictactoe.domain.board;

/**
 * @author mbus
 */
public class CaseAlreadyFilledException extends RuntimeException {

    public CaseAlreadyFilledException() {
        super("There is already a symbol on the selected case.");
    }
}
