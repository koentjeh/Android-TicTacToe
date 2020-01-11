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
import com.koen.tictactoe.controller.GameController.Computer;
import com.koen.tictactoe.controller.GameController.Player;
import com.koen.tictactoe.model.Move;

import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    private GameController gameController;
    private Computer       computerType;
    private Player         selectedPlayer;

    boolean doubleBackToExitPressedOnce = false;

    TextView textViewDifficulty;
    TextView textViewFigure;
    TextView textViewPlayerTurn;

    // Initialize Playfield
    Button a1;
    Button a2;
    Button a3;
    Button b1;
    Button b2;
    Button b3;
    Button c1;
    Button c2;
    Button c3;

    Button[] rowA;
    Button[] rowB;
    Button[] rowC;

    Button[][] boardTiles;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameactivity);

        // Get Data From Previous Activity
        Intent intent = getIntent();
        this.computerType = (Computer) intent.getSerializableExtra("computerType");
        this.selectedPlayer = (Player) intent.getSerializableExtra("selectedPlayer");

        // Initialize Game
        gameController = new GameController(this.computerType, this.selectedPlayer);

        // Initialize Playfield
        textViewDifficulty = findViewById(R.id.textViewDifficulty);
        textViewFigure = findViewById(R.id.textViewDifficulty);
        textViewPlayerTurn = findViewById(R.id.textViewPlayerTurn);

        a1 = findViewById(R.id.buttonA1);
        a2 = findViewById(R.id.buttonA2);
        a3 = findViewById(R.id.buttonA3);
        b1 = findViewById(R.id.buttonB1);
        b2 = findViewById(R.id.buttonB2);
        b3 = findViewById(R.id.buttonB3);
        c1 = findViewById(R.id.buttonC1);
        c2 = findViewById(R.id.buttonC2);
        c3 = findViewById(R.id.buttonC3);

        rowA = new Button[]{a1, a2, a3};
        rowB = new Button[]{b1, b2, b3};
        rowC = new Button[]{c1, c2, c3};

        boardTiles = new Button[][]{rowA, rowB, rowC};

        // Set Text
        textViewDifficulty.setText(String.valueOf(selectedPlayer));
        textViewFigure.setText("You are player " + this.selectedPlayer);
        textViewPlayerTurn.setText("Waiting For " + this.selectedPlayer);

        // Configure OnClickListener For Each Button
        for (int y = 0; y < boardTiles.length; y++) { // ROW
            for (int x = 0; x < boardTiles[y].length; x++) { // COLUMN

                // Remember To Makes Changes To Button
                final int _Y = y;
                final int _X = x;

                boardTiles[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeMove(new Move(_Y, _X, gameController.getTurn()));

                        if(gameController.playingAgainstComputer())
                            makeMove(new Move(gameController.getTurn()));
                    }
                });
            }
        }
    }

    private void makeMove(Move move) {
        move = gameController.makeMove(move.getY(), move.getX());

        textViewPlayerTurn.setText("tWaiting For " + move.getPlayer().toString());

        if(!move.isEndingMove()){
            int color = (move.getPlayer() == Player.X) ? getResources().getColor(R.color.red) : getResources().getColor(R.color.blue);
            Button button = boardTiles[move.getY()][move.getX()];
            button.setText(move.getPlayer().toString());
            button.setEnabled(false);
            button.setTextColor(color);
        }else toEndingScreen(move);
    }

    private void toEndingScreen(Move move) {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {}

        Intent intent = new Intent(getBaseContext(), EndGameActivity.class);
        intent.putExtra("computerType", this.computerType);
        intent.putExtra("selectedPlayer", this.selectedPlayer);
        intent.putExtra("winningPlayer", move.getPlayer());
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