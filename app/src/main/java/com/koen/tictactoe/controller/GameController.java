package com.koen.tictactoe.controller;

import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

import java.util.Random;

public class GameController {
    public enum Computer    { NONE, RANDOM, LOGIC, MINMAX, BIGDATA }
    public enum Player      { BLANK, X, O }

    private Board       board = new Board();
    private ComputerController computer;

    private Player  player;
    private Player  turn;
    private int     turnCount = 0;

    public GameController(Computer computer, Player selectedPlayer) {
        this.player = selectedPlayer;
        this.computer = new ComputerController(computer, getOppositePlayer(selectedPlayer));
        this.turn = selectedPlayer;
    }

    public Player getTurn() { return this.turn; }

    public Move makeMove(int y, int x){
        if(turnCount >= 9) return new Move(Player.BLANK);

        Move move = (this.turn == player) ? new Move(y, x, this.turn) : computer.makeMove(board);

        board.updateBoard(move);

        if(board.isWinningMove(move)) return new Move(this.turn);

        nextTurn();

        return move;
    }

    public boolean playingAgainstComputer(){
        return (computer.getType() != Computer.NONE);
    }

    private void nextTurn(){
        turnCount++;
        this.turn = getOppositePlayer(this.turn);
    }

    public static Player getRandomPlayer(){
        Random random = new Random();
        return Player.values()[random.nextInt(Player.values().length)];
    }

    public static Player getOppositePlayer(Player player){
        return (player == Player.X) ? Player.O : Player.X;
    }
}
