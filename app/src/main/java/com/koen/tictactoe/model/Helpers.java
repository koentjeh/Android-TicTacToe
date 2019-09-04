package com.koen.tictactoe.model;

import android.util.Log;

public class Helpers {

    // Log Board
    public Helpers(Object[][] board) {
        final String TAG = "Logging Board";
        for (int i = 0; i < 3; i++) Log.i(TAG, board[i][0] + " " + board[i][1] + " " + board[i][2]);
    }

    // Log Move
    public Helpers(Move move) {
        final String TAG = "Logging Move: ";
        if (move != null) Log.i(TAG, "Move " + move.getFigure() + " = " + move.getY() + ":" + move.getX() + " | IsEndingMove?:" + move.isEndingMove() + " - " + move.getEndingMessage());
        else Log.i(TAG, "No Move Available");
    }
}
