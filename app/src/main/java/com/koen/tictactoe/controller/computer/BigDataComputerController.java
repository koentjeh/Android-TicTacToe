package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.controller.GameController.Figures;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class BigDataComputerController implements IComputerController {
    private Board   board;
    private Figures computerFigure;

    public BigDataComputerController(Figures figure) {
        this.computerFigure = figure;
    }

    public Move makeMove(Board board) {
        this.board = board;

        return null;
    }
}
