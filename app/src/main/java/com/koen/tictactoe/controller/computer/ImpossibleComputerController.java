package com.koen.tictactoe.controller.computer;

import android.annotation.SuppressLint;
import android.util.Log;

import com.koen.tictactoe.controller.GameController.Figures;
import com.koen.tictactoe.model.Move;

import java.util.ArrayList;
import java.util.Random;

public class ImpossibleComputerController implements IComputerController {
    private static final String TAG = "ImpossibleComputerController";

    private Figures     figure;
    private Figures     enemyFigure;
    private Object[][]  board;

    public ImpossibleComputerController(Figures figure) {
        this.figure = figure;
        this.enemyFigure = (figure == Figures.X) ? Figures.O : Figures.X;
    }

    @Override
    public Move makeMove(Object[][] board) {
        this.board = board;

        Move move = canWin();
        if (move != null) return move;  // Rule 1: If I have a winning move, take it.
        move = shouldBlock();
        if (move != null) return move;  // Rule 2: If the opponent has a winning move, block it.
        move = createFork();
        if (move != null) return move;  // Rule 3: If I can create a fork (two winning ways) after this move, do it.
        move = blockFork();
        if (move != null) return move;  // Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
        move = bestPossibleMove();
        if (move != null) return move;  // Rule 5: Place in the position such as I may win in the most number of possible ways.

        return randomMove();
    }

    // Rule 1
    private Move canWin() { return playThirdInSequenceOfTwo(this.figure); }

    // Rule 2
    private Move shouldBlock() { return playThirdInSequenceOfTwo(this.enemyFigure); }

    // Implementation Of Rule 1 & 2
    private Move playThirdInSequenceOfTwo(Figures f) {
        // Search Row For 2 Same Figures Plus Empty
        for (int y = 0; y < 3; y++) {
            int count = 0, emptyCount = 0;
            for (int x = 0; x < 3; x++) {
                if (this.board[y][x] == f) count++;
                if (this.board[y][x] == Figures.BLANK) emptyCount++;
            }
            if (count == 2 && emptyCount == 1) {
                for (int x = 0; x < 3; x++) {
                    if (this.board[y][x] == Figures.BLANK) return new Move(y, x, this.figure);
                }
            }
        } // End Row Search

        // Search Column For 2 Same Figures Plus Empty
        for (int x = 0; x < 3; x++) {
            int count = 0, emptyCount = 0;
            for (int y = 0; y < 3; y++) {
                if (this.board[y][x] == f) count++;
                if (this.board[y][x] == Figures.BLANK) emptyCount++;
            }
            if (count == 2 && emptyCount == 1) {
                for (int y = 0; y < 3; y++) {
                    if (this.board[y][x] == Figures.BLANK) return new Move(y, x, this.figure);
                }
            }
        } // End Column Search

        // Search Diagonal For 2 Same Figures Plus Empty
        int count = 0, emptyCount = 0;
        for (int y = 0, x = 0; y < 3; y++, x++) {
            if (this.board[y][x] == f) count++;
            if (this.board[y][x] == Figures.BLANK) emptyCount++;
            if (count == 2 && emptyCount == 1) {
                if (this.board[0][0] == Figures.BLANK) return new Move(0, 0, this.figure);
                if (this.board[1][1] == Figures.BLANK) return new Move(1, 1, this.figure);
                if (this.board[2][2] == Figures.BLANK) return new Move(2, 2, this.figure);
            }
        } // Emd Diagonal Search

        // Search Anti Diagonal For 2 Same Figures Plus Empty
        count = 0; emptyCount = 0;
        for (int y = 2, x = 0; x < 3; y--, x++) {
            if (this.board[y][x] == f) count++;
            if (this.board[y][x] == Figures.BLANK) emptyCount++;
            if (count == 2 && emptyCount == 1) {
                if (this.board[2][0] == Figures.BLANK) return new Move(2, 0, this.figure);
                if (this.board[1][1] == Figures.BLANK) return new Move(1, 1, this.figure);
                if (this.board[0][2] == Figures.BLANK) return new Move(0, 2, this.figure);
            }
        } // End Anti Diagonal Search
        return null;
    }

    @SuppressLint("LongLogTag")
    private Move createFork() {
        Log.i(TAG, "createFork(" + this.figure + ")");

        return null;
    }

    @SuppressLint("LongLogTag")
    private Move blockFork() {
        Log.i(TAG, "blockFork(" + this.figure + ")");

        return null;
    }

    @SuppressLint("LongLogTag")
    private Move bestPossibleMove() {
        Log.i(TAG, "bestPossibleMove(" + this.figure + ")");

        return null;
    }

    private Move randomMove() {
        ArrayList<Move> availableMoves = null;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (this.board[y][x] == Figures.BLANK) {
                    availableMoves.add(new Move(y, x, this.figure));
                }
            }
        }
        Random randomMove = new Random();
        int index = randomMove.nextInt(availableMoves.size());
        return availableMoves.get(index);
    }
}
