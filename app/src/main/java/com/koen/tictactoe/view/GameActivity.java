package com.koen.tictactoe.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.koen.tictactoe.R;
import com.koen.tictactoe.controller.GameController;
import com.koen.tictactoe.controller.GameController.Difficulties;
import com.koen.tictactoe.controller.GameController.Figures;
import com.koen.tictactoe.model.Move;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    private GameController  gameController;
    private Figures         selectedFigure;
    private Difficulties    selectedDifficulty;

    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameactivity);

        // Get Data From Previous Activity
        Intent intent = getIntent();
        this.selectedDifficulty = (Difficulties) intent.getSerializableExtra("difficulty");
        this.selectedFigure = (Figures) intent.getSerializableExtra("figure");

        // Initialize Game
        gameController = new GameController(this.selectedDifficulty, this.selectedFigure);

        // Set Text
        final TextView textViewDifficulty = findViewById(R.id.textViewDifficulty);
        final TextView textViewFigure = findViewById(R.id.textViewPlayerFigure);
        final TextView textViewPlayerTurn = findViewById(R.id.textViewPlayerTurn);
        textViewDifficulty.setText(String.valueOf(this.selectedDifficulty));
        textViewFigure.setText("You are player " + this.selectedFigure);
        textViewPlayerTurn.setText("Waiting For " + this.selectedFigure);

        // Initialize Playfield
        final Button a1 = findViewById(R.id.buttonA1);
        final Button a2 = findViewById(R.id.buttonA2);
        final Button a3 = findViewById(R.id.buttonA3);
        final Button b1 = findViewById(R.id.buttonB1);
        final Button b2 = findViewById(R.id.buttonB2);
        final Button b3 = findViewById(R.id.buttonB3);
        final Button c1 = findViewById(R.id.buttonC1);
        final Button c2 = findViewById(R.id.buttonC2);
        final Button c3 = findViewById(R.id.buttonC3);

        final Button[] rowA = new Button[]{a1, a2, a3};
        final Button[] rowB = new Button[]{b1, b2, b3};
        final Button[] rowC = new Button[]{c1, c2, c3};

        final Button[][] boardTiles = new Button[][]{rowA, rowB, rowC};

        // Configure OnClickListener For Each Button
        for (int y = 0; y < boardTiles.length; y++) { // ROW
            for (int x = 0; x < boardTiles[y].length; x++) { // COLUMN

                // Remember To Makes Changes To Button
                final int tileNumberY = y;
                final int tileNumberX = x;

                boardTiles[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        textViewPlayerTurn.setText("Waiting For " + gameController.getTurn());
                        Move playerMove = gameController.makeMove(tileNumberY, tileNumberX);
                        boardTiles[playerMove.getY()][playerMove.getX()].setText(String.valueOf(playerMove.getFigure()));
                        if (playerMove.getFigure() == Figures.X) boardTiles[playerMove.getY()][playerMove.getX()].setTextColor(getResources().getColor(R.color.red));
                        else boardTiles[playerMove.getY()][playerMove.getX()].setTextColor(getResources().getColor(R.color.blue));
                        boardTiles[playerMove.getY()][playerMove.getX()].setEnabled(false);

                        if (playerMove.isEndingMove()) {
                            toEndingScreen(playerMove.getEndingMessage());
                            return;
                        }

                        if (gameController.isComputerConfigured()){
                            textViewPlayerTurn.setText("Waiting For " + gameController.getTurn());
                            Move computerMove = gameController.makeMoveComputer();
                            boardTiles[computerMove.getY()][computerMove.getX()].setText(String.valueOf(computerMove.getFigure()));
                            if (computerMove.getFigure() == Figures.X) boardTiles[computerMove.getY()][computerMove.getX()].setTextColor(getResources().getColor(R.color.red));
                            else boardTiles[computerMove.getY()][computerMove.getX()].setTextColor(getResources().getColor(R.color.blue));
                            boardTiles[computerMove.getY()][computerMove.getX()].setEnabled(false);

                            if (computerMove.isEndingMove()) {
                                toEndingScreen(computerMove.getEndingMessage());
                            }
                        }
                    }
                });
            }
        }
    }

    private void toEndingScreen(String message) {
        Intent intent = new Intent(getBaseContext(), EndGameActivity.class);
        intent.putExtra("endingMessage", message);
        intent.putExtra("difficulty", this.selectedDifficulty);
        intent.putExtra("figure", this.selectedFigure);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press Twice To Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    } // Disable Back Button Until Pressed Twice
}