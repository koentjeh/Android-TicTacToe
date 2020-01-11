package com.koen.tictactoe.controller;

import com.koen.tictactoe.controller.GameController.Computer;
import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.controller.computer.BigDataComputer;
import com.koen.tictactoe.controller.computer.IComputer;
import com.koen.tictactoe.controller.computer.LogicComputer;
import com.koen.tictactoe.controller.computer.MinMaxComputer;
import com.koen.tictactoe.controller.computer.RandomComputer;
import com.koen.tictactoe.model.Board;
import com.koen.tictactoe.model.Move;

public class ComputerController {

    private IComputer computer;
    private Computer type;

    public ComputerController(Computer computer, Player player){
        configureComputer(computer, player);
    }

    private void configureComputer(Computer computer, Player player) {
        this.type = computer;

        switch (computer){
            case NONE:
                this.computer = null;
                break;
            case RANDOM:
                this.computer = new RandomComputer(player);
                break;
            case LOGIC:
                this.computer = new LogicComputer(player);
                break;
            case MINMAX:
                this.computer = new MinMaxComputer(player);
                break;
            case BIGDATA:
                this.computer = new BigDataComputer(player);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + computer);
        }
    }

    public Computer getType(){
        return this.type;
    }

    public Move makeMove(Board board){
        return this.computer.makeMove(board);
    }
}
