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
        setDifficulty(Difficulties.NONE);
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
        Button buttonNone = findViewById(R.id.ComputerNone);
        Button buttonRandom = findViewById(R.id.ComputerRandom);
        Button buttonLogic = findViewById(R.id.ComputerLogic);
        Button buttonMinMax = findViewById(R.id.ComputerMinMax);
        Button buttonBigData = findViewById(R.id.ComputerBigData);
        buttonNone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.NONE); }
        });
        buttonRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.RANDOM); }
        });
        buttonLogic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.LOGIC); }
        });
        buttonMinMax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.MINMAX); }
        });
        buttonBigData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(Difficulties.BIGDATA); }
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
        Button buttonNone = findViewById(R.id.ComputerNone);
        Button buttonRandom = findViewById(R.id.ComputerRandom);
        Button buttonLogic = findViewById(R.id.ComputerLogic);
        Button buttonMinMax = findViewById(R.id.ComputerMinMax);
        Button buttonBigData = findViewById(R.id.ComputerBigData);
        int secondaryColor = getResources().getColor(R.color.secondary);

        buttonNone.setBackgroundColor(secondaryColor);
        buttonRandom.setBackgroundColor(secondaryColor);
        buttonLogic.setBackgroundColor(secondaryColor);
        buttonMinMax.setBackgroundColor(secondaryColor);
        buttonBigData.setBackgroundColor(secondaryColor);

        switch (d) {
            case NONE:
                this.difficulty = Difficulties.NONE;
                buttonNone.setBackgroundColor(Color.GREEN);
                break;
            case RANDOM:
                this.difficulty = Difficulties.RANDOM;
                buttonRandom.setBackgroundColor(Color.GREEN);
                break;
            case LOGIC:
                this.difficulty = Difficulties.LOGIC;
                buttonLogic.setBackgroundColor(Color.GREEN);
                break;
            case MINMAX:
                this.difficulty = Difficulties.MINMAX;
                buttonMinMax.setBackgroundColor(Color.GREEN);
                break;
            case BIGDATA:
                this.difficulty = Difficulties.BIGDATA;
                buttonBigData.setBackgroundColor(Color.GREEN);
                break;
            default:
                throw new UnsupportedOperationException("This Type Of Computer Is Not Implemented.");
        }
    }

    private void setFigure(Figures f) {
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        int secondaryColor = getResources().getColor(R.color.secondary);

        buttonX.setBackgroundColor(secondaryColor);
        buttonO.setBackgroundColor(secondaryColor);

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
