package com.koen.tictactoe.controller.computer;

import com.koen.tictactoe.controller.GameController;
import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class MinMaxComputer implements IComputer {
    private Board  board;
    private Player computer;
    private Player player;

    public MinMaxComputer(Player computer) {
        this.computer = computer;
        this.player = GameController.getOppositePlayer(computer);
    }

    public Move makeMove(Board board) {
        this.board = board;

//        int turn = gc.getTurnCount();
//
//        minimax(turn, computerFigure);

        return null;
    }

//    private int minimax(int dept, Figures figure) {
//        List availableMoves = new ArrayList<>();
//
//        for (int y = 0; y < 3; y++) {
//            for (int x = 0; x < 3; x++) {
//                if (board.getBoard()[y][x] == Figures.BLANK) availableMoves.add(new Move(y, x, Figures.BLANK));
//            }
//        }
//
//        int bestScore = (figure == computerFigure) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
//        int currentScore;
//        Move bestMove = new Move(-1, -1, Figures.BLANK);
//
//        if (availableMoves.isEmpty() || dept == 0) {
//            bestScore = evaluate();
//        } else {
//            for (int i = 0; i < availableMoves.size(); i++) {
//                Move move = availableMoves.get(i);
//                board.getBoard()[move.getY()][move.getX()] = figure;
//
//                if (figure == computerFigure) {
//                    currentScore = minimax(dept - 1, );
//                    if (currentScore > bestScore) {
//                        bestScore = currentScore;
//                        bestMove = new Move(move.getY(), move.getX(), computerFigure);
//                    }
//                } else {
//                    currentScore = minimax(dept - 1, computerFigure);
//                    if (currentScore < bestScore) {
//                        bestScore = currentScore;
//                        bestMove = new Move(move.getY(), move.getX(), computerFigure);
//                    }
//                }
//
//                board.getBoard()[move.getY()][move.getX()] = Figures.BLANK;
//            }
//        }
//
//    }
//
//    private int evaluate() {
//
//    }
}
