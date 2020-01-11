package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public interface IComputer {
    Move makeMove(Board board);
}
