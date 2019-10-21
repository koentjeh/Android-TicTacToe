package com.koen.tictactoe.model;

import com.koen.tictactoe.controller.GameController.Figures;

public class Move {
    private int         y;
    private int         x;
    private Figures     figure;
    private boolean     isEndingMove = false;
    private String      endingMessage;

    public Move(int y, int x, Figures figure) {
        this.y = y;
        this.x = x;
        this.figure = figure;
    }

    public int      getY()              { return y; }
    public int      getX()              { return x; }
    public Figures  getFigure()         { return figure; }
    public boolean  isEndingMove()      { return isEndingMove; }
    public String   getEndingMessage()  { return endingMessage; }

    public void setFigure(Figures figure) {
        this.figure = figure;
    }

    public void setEndingMove(String message) {
        this.isEndingMove = true;
        this.endingMessage = message;
    }
}
