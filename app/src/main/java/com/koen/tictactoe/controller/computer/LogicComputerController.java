package com.koen.tictactoe.controller.computer;

import android.util.Log;

import com.koen.tictactoe.controller.GameController.Figures;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class LogicComputerController implements IComputerController {
    private final String TAG = "LogicComputerController";

    private Board       board;
    private Figures     computerFigure;
    private Figures     playerFigure;

    public LogicComputerController(Figures figure) {
        this.computerFigure = figure;
        this.playerFigure = ((figure == Figures.X) ? Figures.O : Figures.X);
    }

    public Move makeMove(Board board) {
        this.board = board;

        Move move = win();
        if (move != null) return move;

        move = block();
        if (move != null) return move;

        move = createFork();
        if (move != null) return move;

        move = blockFork();
        if (move != null) return move;

        move = center();
        if (move != null) return move;

        move = oppositeCorner();
        if (move != null) return move;

        move = emptyCorner();
        if (move != null) return move;

        return emptySide();
    }

    private Move win() {
        Log.i(TAG, "win()");
        return twoThirdSequence(computerFigure);
    }

    private Move block() {
        Log.i(TAG, "block()");
        return twoThirdSequence(playerFigure);
    }

    private Move createFork() {
        Log.i(TAG, "createFork()");
        return null;
    }

    private Move blockFork() {
        Log.i(TAG, "blockFork()");

        if (board.getBoard()[0][0] == playerFigure && board.getBoard()[1][1] == computerFigure && board.getBoard()[2][2] == playerFigure) return emptySide();
        if (board.getBoard()[2][0] == playerFigure && board.getBoard()[1][1] == computerFigure && board.getBoard()[0][2] == playerFigure) return emptySide();
        if (board.getBoard()[2][1] == playerFigure && board.getBoard()[1][2] == playerFigure) return new Move(2, 2, computerFigure);

        return null;
    }

    private Move center() {
        Log.i(TAG, "center()");
        if (board.getBoard()[1][1] == Figures.BLANK) return new Move(1, 1, computerFigure);

        return null;
    }

    private Move oppositeCorner() {
        Log.i(TAG, "oppositeCorner()");
        if (board.getBoard()[0][0] == playerFigure && board.getBoard()[2][2] == Figures.BLANK) return new Move(2, 2, computerFigure);
        if (board.getBoard()[2][0] == playerFigure && board.getBoard()[0][2] == Figures.BLANK) return new Move(0, 2,computerFigure);
        if (board.getBoard()[0][2] == playerFigure && board.getBoard()[2][0] == Figures.BLANK) return new Move(2, 0, computerFigure);
        if (board.getBoard()[2][2] == playerFigure && board.getBoard()[0][0] == Figures.BLANK) return new Move(0, 0, computerFigure);

        return null;
    }

    private Move emptyCorner() {
        Log.i(TAG, "emptyCorner()");
        if (board.getBoard()[0][0] == Figures.BLANK) return new Move(0, 0, computerFigure);
        if (board.getBoard()[2][0] == Figures.BLANK) return new Move(2, 0, computerFigure);
        if (board.getBoard()[0][2] == Figures.BLANK) return new Move(0, 2, computerFigure);
        if (board.getBoard()[2][2] == Figures.BLANK) return new Move(2, 2, computerFigure);

        return null;
    }

    private Move emptySide() {
        Log.i(TAG, "emptySide()");
        if (board.getBoard()[0][1] == Figures.BLANK) return new Move(0, 1, computerFigure);
        if (board.getBoard()[1][2] == Figures.BLANK) return new Move(1, 2, computerFigure);
        if (board.getBoard()[2][1] == Figures.BLANK) return new Move(2, 1, computerFigure);
        if (board.getBoard()[1][0] == Figures.BLANK) return new Move(1, 0, computerFigure);

        throw new UnsupportedOperationException("Computer couldn't figure out a move");
    }

    private Move twoThirdSequence(Figures figure) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            int count = 0, emptyCount = 0;

            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == figure) ++count;
                if (board.getBoard()[i][j] == Figures.BLANK) ++emptyCount;
            }

            if (count == 2 && emptyCount == 1) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == Figures.BLANK) return new Move(i, j, computerFigure);
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            int count = 0, emptyCount = 0;

            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[j][i] == figure) ++count;
                if (board.getBoard()[j][i] == Figures.BLANK) ++emptyCount;
            }

            if (count == 2 && emptyCount == 1) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[j][i] == Figures.BLANK) return new Move(j, i, computerFigure);
                }
            }
        }

        // Check downward diagonal
        int count = 0, emptyCount = 0;
        for (int y = 0, x = 0; y < 3; y++, x++) {
            if (board.getBoard()[y][x] == figure) ++count;
            if (board.getBoard()[y][x] == Figures.BLANK) ++emptyCount;

            if (count == 2 && emptyCount == 1) {
                if (board.getBoard()[0][0] == Figures.BLANK) return new Move(0, 0, computerFigure);
                if (board.getBoard()[1][1] == Figures.BLANK) return new Move(1, 1, computerFigure);
                if (board.getBoard()[2][2] == Figures.BLANK) return new Move(2, 2, computerFigure);
            }
        }

        // Check upward diagonal
        count = 0; emptyCount = 0;
        for (int y = 0, x = 2; y < 3; y++, x--) {
            if (board.getBoard()[y][x] == figure) ++count;
            if (board.getBoard()[y][x] == Figures.BLANK) ++emptyCount;

            if (count == 2 && emptyCount == 1) {
                if (board.getBoard()[2][0] == Figures.BLANK) return new Move(2, 0, computerFigure);
                if (board.getBoard()[1][1] == Figures.BLANK) return new Move(1, 1, computerFigure);
                if (board.getBoard()[0][2] == Figures.BLANK) return new Move(0, 2, computerFigure);
            }
        }

        return null;
    }
}
