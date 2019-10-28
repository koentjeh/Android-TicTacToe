package com.koen.tictactoe.controller;

import android.util.Log;

import com.koen.tictactoe.controller.computer.BigDataComputerController;
import com.koen.tictactoe.controller.computer.IComputerController;
import com.koen.tictactoe.controller.computer.LogicComputerController;
import com.koen.tictactoe.controller.computer.MinMaxComputerController;
import com.koen.tictactoe.controller.computer.RandomComputerController;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class GameController {
    private static final String TAG = "GameController";

    public enum Difficulties        { NONE, RANDOM, LOGIC, MINMAX, BIGDATA }
    public enum Figures             { BLANK, X, O }

    private Board                   board = new Board();
    private IComputerController     computer;
    private Figures                 turn;
    private int                     turnCount = 1;

    public GameController(Difficulties difficulty, Figures figure) {
        this.configureComputer(difficulty, figure);
        this.turn = figure;
    }

    public Figures getTurn() { return (this.turn != Figures.X) ? Figures.X : Figures.O; }

    public Move makeMove(int y, int x) {
        Move move = new Move(y, x, this.turn);
        board.updateBoard(move);
        move = isEndingMove(move);
        return move;
    }

    public Move makeMoveComputer() {
        Move move = computer.makeMove(board);
        board.updateBoard(move);
        move = isEndingMove(move);
        return move;
    }

    private Move isEndingMove(Move move) {
        Log.i(TAG, "Current turn = " + turnCount);

        if (board.isWinningMove(move)) {
            move.setEndingMove(this.turn.toString());
        } else if (turnCount >= 9) {
            move.setEndingMove("The Game Ended In A Tie");
        } else nextTurn();
        return move;
    }

    private void nextTurn() {
        turnCount++;
        this.turn = (this.turn != Figures.X) ? Figures.X : Figures.O;
    }

    //--------------------------//
    //          COMPUTER        //
    //--------------------------//

    // Return IF Method Below Has Been Initialized
    public boolean isComputerConfigured() { return this.computer != null; }

    // Set Computer Difficulty And Figure
    private void configureComputer(Difficulties difficulty, Figures figure) {
        // Select Other Figure As Player Figure
        Figures computerFigure = (figure == Figures.X) ? Figures.O : Figures.X;

        switch (difficulty) {
            case NONE:
                this.computer = null;
                break;
            case RANDOM:
                this.computer = new RandomComputerController(computerFigure);
                break;
            case LOGIC:
                this.computer = new LogicComputerController(computerFigure);
                break;
            case MINMAX:
                this.computer = new MinMaxComputerController(computerFigure);
                break;
            case BIGDATA:
                this.computer = new BigDataComputerController(computerFigure);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + difficulty);
        }
    }

    private void logMove(Move move) {
        Log.i(TAG, "Move " + move.getFigure() + " = " + move.getY() + ":" + move.getX() + " | IsEndingMove?:" + move.isEndingMove() + " - " + move.getEndingMessage());
    }
}
