package org.busmaxime.tictactoe.domain.board;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void it_is_possible_to_add_symbol_on_empty_case() {
        Board board = new Board(3, 3);

        board.moveCursorAt(1, 1);
        board.setStateAtCursorPosition(1);

        assertThat(board.getStateAtCursorPosition()).isEqualTo(1);
    }

    @Test(expected = CaseAlreadyFilledException.class)
    public void it_is_not_possible_to_add_symbol_on_filled_case() {
        Board board = new Board(3, 3);

        board.moveCursorAt(1, 1);
        board.setStateAtCursorPosition(1);
        board.setStateAtCursorPosition(1);
    }

    @Test
    public void it_is_possible_to_move_cursor_by_delta() {
        Board board = new Board(3, 3);

        board.moveCursorAt(0, 0);
        board.moveCursorBy(1, 1);

        assertThat(board.getColumnCursor()).isEqualTo(1);
        assertThat(board.getRowCursor()).isEqualTo(1);
    }

    @Test
    public void it_is_not_possible_to_move_cursor_outside_the_board() {
        Board board = new Board(3, 3);

        board.moveCursorAt(4, 4);

        assertThat(board.getColumnCursor()).isEqualTo(0);
        assertThat(board.getRowCursor()).isEqualTo(0);
    }

    @Test
    public void it_should_return_zero_when_checking_case_outside_the_board() {
        //Given
        Board board = new Board(5, 5);

        //Then
        assertThat(board.getStateAt(7, 8)).isEqualTo(0);
    }
}
