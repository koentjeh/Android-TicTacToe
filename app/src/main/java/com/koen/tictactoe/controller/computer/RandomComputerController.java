package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.controller.GameController.Figures;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomComputerController implements IComputerController {
    private Board   board;
    private Figures computerFigure;

    public RandomComputerController(Figures figure) {
        this.computerFigure = figure;
    }

    public Move makeMove(Board board) {
        this.board = board;

        return  makeRandomMove();
    }

    private Move makeRandomMove() {
        List<Move> availableMoves = new ArrayList<>();
        Random r = new Random();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board.getBoard()[y][x] == Figures.BLANK){
                    availableMoves.add(new Move(y, x, computerFigure));
                }
            }
        }

        return availableMoves.get(r.nextInt(availableMoves.size()));
    }
}
