package com.koen.tictactoe.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.koen.tictactoe.R;
import com.koen.tictactoe.controller.GameController.Computer;
import com.koen.tictactoe.controller.GameController.Player;

public class PregameActivity extends AppCompatActivity {
    private Computer computerType;
    private Player   selectedPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregameactivity);

        // init
        setDifficulty(computerType.MINMAX);
        setFigure(Player.X);

        //figure
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        buttonX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setFigure(Player.X); }
        });
        buttonO.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setFigure(Player.O); }
        });

        // difficulty
        Button buttonNone = findViewById(R.id.ComputerNone);
        Button buttonRandom = findViewById(R.id.ComputerRandom);
        Button buttonLogic = findViewById(R.id.ComputerLogic);
        Button buttonMinMax = findViewById(R.id.ComputerMinMax);
        Button buttonBigData = findViewById(R.id.ComputerBigData);
        buttonNone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(computerType.NONE); }
        });
        buttonRandom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(computerType.RANDOM); }
        });
        buttonLogic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(computerType.LOGIC); }
        });
        buttonMinMax.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(computerType.MINMAX); }
        });
        buttonBigData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { setDifficulty(computerType.BIGDATA); }
        });

        // start
        Button buttonStartGame = findViewById(R.id.startGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("computerType", computerType);
                intent.putExtra("selectedPlayer", selectedPlayer);
                startActivity(intent);
            }
        });
    }

    private void setDifficulty(Computer computerType) {
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

        switch (computerType) {
            case NONE:
                this.computerType = Computer.NONE;
                buttonNone.setBackground(buttonSelectedBackground);
                break;
            case RANDOM:
                this.computerType = Computer.RANDOM;
                buttonRandom.setBackground(buttonSelectedBackground);
                break;
            case LOGIC:
                this.computerType = Computer.LOGIC;
                buttonLogic.setBackground(buttonSelectedBackground);
                break;
            case MINMAX:
                this.computerType = Computer.MINMAX;
                buttonMinMax.setBackground(buttonSelectedBackground);
                break;
            case BIGDATA:
                this.computerType = Computer.BIGDATA;
                buttonBigData.setBackground(buttonSelectedBackground);
                break;
            default:
                throw new UnsupportedOperationException("This Type Of Computer Is Not Implemented.");
        }
    }

    private void setFigure(Player player) {
        Button buttonX = findViewById(R.id.X);
        Button buttonO = findViewById(R.id.O);
        buttonX.setTextColor(getResources().getColor(R.color.text_color));
        buttonO.setTextColor(getResources().getColor(R.color.text_color));

        this.selectedPlayer = player;

        switch (player) {
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
