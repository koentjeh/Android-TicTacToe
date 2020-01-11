package com.koen.tictactoe.model;

import com.koen.tictactoe.controller.GameController.Player;

public class Board {
    private Object[][] board;

    public Board() {
        this.board = new Object[3][3];
        for (int y = 0; y < 3; y++) for (int x = 0; x < 3; x++) this.board[y][x] = Player.BLANK;
    }

    public Object[][] getBoard() {
        return this.board;
    }

    public void updateBoard(Move move) {
        this.board[move.getY()][move.getX()] = move.getPlayer();
    }

    public boolean isWinningMove(Move move) {
        int column = 0;
        int row = 0;

        for (int i = 0; i < 3; i++) {
            // check column
            if (this.board[i][move.getX()] == move.getPlayer()) column++;
            // check row
            if (this.board[move.getY()][i] == move.getPlayer()) row++;
        }

        // validate row & column
        if (column == 3 || row == 3) {
            return true;
        }

        // check diagonal
        if (this.board[0][0] == this.board[1][1] &&
            this.board[0][0] == this.board[2][2] &&
            this.board[1][1] != Player.BLANK) {
                return true;
        }

        // check anti diagonal
        if (this.board[2][0] == this.board[1][1] &&
            this.board[2][0] == this.board[0][2] &&
            this.board[1][1] != Player.BLANK) {
                return true;
        }

        return false;
    }
}
