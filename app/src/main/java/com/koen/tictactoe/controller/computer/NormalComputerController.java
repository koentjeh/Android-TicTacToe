package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.controller.GameController;
import com.koen.tictactoe.model.Move;

public class NormalComputerController implements IComputerController {
    private GameController.Figures figure;

    public NormalComputerController(GameController.Figures figure) {
        this.figure = figure;
    }

    public GameController.Figures getFigure() {
        return figure;
    }

    @Override
    public Move makeMove(Object[][] board) {
        return new Move(0, 0, figure);
    }
}
