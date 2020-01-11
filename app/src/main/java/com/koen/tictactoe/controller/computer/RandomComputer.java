package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomComputer implements IComputer {
    private Board  board;
    private Player computer;

    public RandomComputer(Player computer) {
        this.computer = computer;
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
                if (board.getBoard()[y][x] == Player.BLANK){
                    availableMoves.add(new Move(y, x, computer));
                }
            }
        }

        if (availableMoves.size() != 0) return availableMoves.get(r.nextInt(availableMoves.size()));

        return null;
    }
}
