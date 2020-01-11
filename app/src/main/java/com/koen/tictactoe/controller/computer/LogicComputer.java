package com.koen.tictactoe.controller.computer;

import android.util.Log;

import com.koen.tictactoe.controller.GameController;
import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class LogicComputer implements IComputer {
    private final String TAG = "LogicComputer";

    private Board  board;
    private Player computer;
    private Player player;

    public LogicComputer(Player computer) {
        this.computer = computer;
        this.player = GameController.getOppositePlayer(computer);
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
        return twoThirdSequence(computer);
    }

    private Move block() {
        Log.i(TAG, "block()");
        return twoThirdSequence(player);
    }

    private Move createFork() {
        Log.i(TAG, "createFork()");
        return null;
    }

    private Move blockFork() {
        Log.i(TAG, "blockFork()");

        if (board.getBoard()[0][0] == player && board.getBoard()[1][1] == computer && board.getBoard()[2][2] == player) return emptySide();
        if (board.getBoard()[2][0] == player && board.getBoard()[1][1] == computer && board.getBoard()[0][2] == player) return emptySide();
        if (board.getBoard()[2][1] == player && board.getBoard()[1][2] == player) return new Move(2, 2, computer);

        return null;
    }

    private Move center() {
        Log.i(TAG, "center()");
        if (board.getBoard()[1][1] == Player.BLANK) return new Move(1, 1, computer);

        return null;
    }

    private Move oppositeCorner() {
        Log.i(TAG, "oppositeCorner()");
        if (board.getBoard()[0][0] == player && board.getBoard()[2][2] == Player.BLANK) return new Move(2, 2, computer);
        if (board.getBoard()[2][0] == player && board.getBoard()[0][2] == Player.BLANK) return new Move(0, 2, computer);
        if (board.getBoard()[0][2] == player && board.getBoard()[2][0] == Player.BLANK) return new Move(2, 0, computer);
        if (board.getBoard()[2][2] == player && board.getBoard()[0][0] == Player.BLANK) return new Move(0, 0, computer);

        return null;
    }

    private Move emptyCorner() {
        Log.i(TAG, "emptyCorner()");
        if (board.getBoard()[0][0] == Player.BLANK) return new Move(0, 0, computer);
        if (board.getBoard()[2][0] == Player.BLANK) return new Move(2, 0, computer);
        if (board.getBoard()[0][2] == Player.BLANK) return new Move(0, 2, computer);
        if (board.getBoard()[2][2] == Player.BLANK) return new Move(2, 2, computer);

        return null;
    }

    private Move emptySide() {
        Log.i(TAG, "emptySide()");
        if (board.getBoard()[0][1] == Player.BLANK) return new Move(0, 1, computer);
        if (board.getBoard()[1][2] == Player.BLANK) return new Move(1, 2, computer);
        if (board.getBoard()[2][1] == Player.BLANK) return new Move(2, 1, computer);
        if (board.getBoard()[1][0] == Player.BLANK) return new Move(1, 0, computer);

        return null;
    }

    private Move twoThirdSequence(Player player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            int count = 0, emptyCount = 0;

            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == player) ++count;
                if (board.getBoard()[i][j] == Player.BLANK) ++emptyCount;
            }

            if (count == 2 && emptyCount == 1) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == Player.BLANK) return new Move(i, j, computer);
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            int count = 0, emptyCount = 0;

            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[j][i] == player) ++count;
                if (board.getBoard()[j][i] == Player.BLANK) ++emptyCount;
            }

            if (count == 2 && emptyCount == 1) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[j][i] == Player.BLANK) return new Move(j, i, computer);
                }
            }
        }

        // Check downward diagonal
        int count = 0, emptyCount = 0;
        for (int y = 0, x = 0; y < 3; y++, x++) {
            if (board.getBoard()[y][x] == player) ++count;
            if (board.getBoard()[y][x] == Player.BLANK) ++emptyCount;

            if (count == 2 && emptyCount == 1) {
                if (board.getBoard()[0][0] == Player.BLANK) return new Move(0, 0, computer);
                if (board.getBoard()[1][1] == Player.BLANK) return new Move(1, 1, computer);
                if (board.getBoard()[2][2] == Player.BLANK) return new Move(2, 2, computer);
            }
        }

        // Check upward diagonal
        count = 0; emptyCount = 0;
        for (int y = 0, x = 2; y < 3; y++, x--) {
            if (board.getBoard()[y][x] == player) ++count;
            if (board.getBoard()[y][x] == Player.BLANK) ++emptyCount;

            if (count == 2 && emptyCount == 1) {
                if (board.getBoard()[2][0] == Player.BLANK) return new Move(2, 0, computer);
                if (board.getBoard()[1][1] == Player.BLANK) return new Move(1, 1, computer);
                if (board.getBoard()[0][2] == Player.BLANK) return new Move(0, 2, computer);
            }
        }

        return null;
    }
}
