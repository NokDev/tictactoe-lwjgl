package org.busmaxime.tictactoe.shapes;

import org.busmaxime.tictactoe.exceptions.CaseAlreadyFilledException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author mbus
 */
public class BoardIT {

    @Test
    public void itIsPossibleToAddSymbolOnEmptyCase() {
        //Given
        Board board = new Board(3, 3);

        //When
        board.moveCursorAt(1, 1);
        board.setStateAtCursorPosition(1);

        //Then
        Assertions.assertThat(board.getStateAtCursorPosition()).isEqualTo(1);
    }

    @Test(expected = CaseAlreadyFilledException.class)
    public void itIsNotPossibleToAddSymbolOnFilledCase() {
        //Given
        Board board = new Board(3, 3);

        //When
        board.moveCursorAt(1, 1);
        board.setStateAtCursorPosition(1);
        board.setStateAtCursorPosition(1);

        //Then
        // It should raise an exception
    }

    @Test
    public void itIsPossibleToMoveCursorByDelta() {
        //Given
        Board board = new Board(3, 3);

        //When
        board.moveCursorAt(0, 0);
        board.moveCursorBy(1, 1);

        //Then
        Assertions.assertThat(board.getColumnCursor()).isEqualTo(1);
        Assertions.assertThat(board.getRowCursor()).isEqualTo(1);
    }

    @Test
    public void itIsNotPossibleToMoveCursorOutsideTheBoard() {
        //Given
        Board board = new Board(3, 3);

        //When
        board.moveCursorAt(4, 4);

        //Then
        Assertions.assertThat(board.getColumnCursor()).isEqualTo(0);
        Assertions.assertThat(board.getRowCursor()).isEqualTo(0);
    }

    @Test
    public void itShouldReturnZeroWhenCheckingCaseOutsideTheBoard() {
        //Given
        Board board = new Board(5, 5);

        //Then
        Assertions.assertThat(board.getStateAt(7, 8)).isEqualTo(0);
    }
}
