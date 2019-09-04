package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.model.Move;

public interface IComputerController {
    Move makeMove(Object[][] board);
}
