package com.koen.tictactoe.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.koen.tictactoe.R;
import com.koen.tictactoe.controller.GameController.Difficulties;
import com.koen.tictactoe.controller.GameController.Figures;

public class PregameActivity extends AppCompatActivity {
    private Difficulties    difficulty;
    private Figures         figure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregameactivity);

        // init
        setDifficulty(Difficulties.IMPOSSIBLE);
        setFigure(Figures.X);

        //figure
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        buttonX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setFigure(Figures.X); }
        });
        buttonO.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setFigure(Figures.O); }
        });

        // difficulty
        Button buttonFriend = findViewById(R.id.Friend);
        Button buttonNormal = findViewById(R.id.ComputerNormal);
        Button buttonImpossible = findViewById(R.id.ComputerImpossible);
        buttonFriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.NONE); }
        });
        buttonNormal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.NORMAL);
            }
        });
        buttonImpossible.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.IMPOSSIBLE); }
        });

        // start
        Button buttonStartGame = findViewById(R.id.startGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("figure", figure);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        });
    }

    private void setDifficulty(Difficulties d) {
        Button buttonFriend = findViewById(R.id.Friend);
        Button buttonNormal = findViewById(R.id.ComputerNormal);
        Button buttonImpossible = findViewById(R.id.ComputerImpossible);
        int accentColor = getResources().getColor(R.color.accent);

        buttonFriend.setBackgroundColor(accentColor);
        buttonNormal.setBackgroundColor(accentColor);
        buttonImpossible.setBackgroundColor(accentColor);

        switch (d) {
            case NONE:
                this.difficulty = Difficulties.NONE;
                buttonFriend.setBackgroundColor(Color.GREEN);
                break;
            case NORMAL:
                this.difficulty = Difficulties.NORMAL;
                buttonNormal.setBackgroundColor(Color.GREEN);
                break;
            case IMPOSSIBLE:
                this.difficulty = Difficulties.IMPOSSIBLE;
                buttonImpossible.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    private void setFigure(Figures f) {
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        int accentColor = getResources().getColor(R.color.accent);

        buttonX.setBackgroundColor(accentColor);
        buttonO.setBackgroundColor(accentColor);

        this.figure = f;

        switch (f) {
            case X:
                buttonX.setBackgroundColor(Color.GREEN);
                break;
            case O:
                buttonO.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
    }
}
