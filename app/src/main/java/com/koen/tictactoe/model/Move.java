package com.koen.tictactoe.model;

import com.koen.tictactoe.controller.GameController.Player;
import java.io.Serializable;

public class Move implements Serializable {
    private int     y;
    private int     x;
    private Player  player;
    private boolean isEndingMove = false;

    public Move(int y, int x, Player player){
        this.y = y;
        this.x = x;
        this.player = player;
    }

    public Move(Player player){
        this.isEndingMove = true;
        this.player = player;
    }

    public int      getY()          { return y; }
    public int      getX()          { return x; }
    public Player   getPlayer()     { return player; }
    public boolean  isEndingMove()  { return isEndingMove; }
}
