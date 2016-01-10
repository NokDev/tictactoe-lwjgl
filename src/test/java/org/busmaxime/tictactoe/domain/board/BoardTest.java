package org.busmaxime.tictactoe.domain.board;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.busmaxime.BoardDSL.board3By3;

public class BoardTest {

    @Test
    public void it_is_possible_to_add_symbol_on_empty_case() {
        Board board = board3By3();

        board.setStateAt(1, 1, CaseValue.CIRCLE);

        assertThat(board.getStateAt(1, 1)).isEqualTo(CaseValue.CIRCLE);
    }

    @Test(expected = CaseAlreadyFilledException.class)
    public void it_is_not_possible_to_add_symbol_on_filled_case() {
        Board board = board3By3();

        board.setStateAt(1, 1, CaseValue.CIRCLE);
        board.setStateAt(1, 1, CaseValue.CIRCLE);
    }

    @Test(expected = OutOfBoundBoardException.class)
    public void it_should_return_empty_when_checking_case_outside_the_board() {
        Board board = board3By3();

        board.getStateAt(7, 8);
    }

    @Test
    public void the_board_can_compute_the_winner() throws Exception {
        Board board = board3By3();

        board.setStateAt(0, 0, CaseValue.CIRCLE);
        board.setStateAt(0, 1, CaseValue.CIRCLE);
        board.setStateAt(0, 2, CaseValue.CIRCLE);

        assertThat(board.getWinner()).isEqualTo(CaseValue.CIRCLE);
    }
}
