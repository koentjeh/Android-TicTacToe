package com.koen.tictactoe.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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
        setDifficulty(Difficulties.MINMAX);
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
        Drawable buttonBackground = ResourcesCompat.getDrawable(getResources(), R.drawable.button_background, null);
        Drawable buttonSelectedBackground = ResourcesCompat.getDrawable(getResources(), R.drawable.button_selected_background, null);

        buttonNone.setBackground(buttonBackground);
        buttonRandom.setBackground(buttonBackground);
        buttonLogic.setBackground(buttonBackground);
        buttonMinMax.setBackground(buttonBackground);
        buttonBigData.setBackground(buttonBackground);

        switch (d) {
            case NONE:
                this.difficulty = Difficulties.NONE;
                buttonNone.setBackground(buttonSelectedBackground);
                break;
            case RANDOM:
                this.difficulty = Difficulties.RANDOM;
                buttonRandom.setBackground(buttonSelectedBackground);
                break;
            case LOGIC:
                this.difficulty = Difficulties.LOGIC;
                buttonLogic.setBackground(buttonSelectedBackground);
                break;
            case MINMAX:
                this.difficulty = Difficulties.MINMAX;
                buttonMinMax.setBackground(buttonSelectedBackground);
                break;
            case BIGDATA:
                this.difficulty = Difficulties.BIGDATA;
                buttonBigData.setBackground(buttonSelectedBackground);
                break;
            default:
                throw new UnsupportedOperationException("This Type Of Computer Is Not Implemented.");
        }
    }

    private void setFigure(Figures f) {
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        buttonX.setTextColor(getResources().getColor(R.color.text_color));
        buttonO.setTextColor(getResources().getColor(R.color.text_color));

        this.figure = f;

        switch (f) {
            case X:
                buttonX.setTextColor(getResources().getColor(R.color.red));
                break;
            case O:
                buttonO.setTextColor(getResources().getColor(R.color.blue));
                break;
            default:
                break;
        }
    }
}
