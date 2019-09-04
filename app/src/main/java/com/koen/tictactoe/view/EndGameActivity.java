package com.koen.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.koen.tictactoe.R;
import com.koen.tictactoe.controller.GameController.Difficulties;
import com.koen.tictactoe.controller.GameController.Figures;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgameactivity);

        Intent intent = getIntent();
        String winnerText = intent.getStringExtra("endingMessage");
        final Difficulties difficulty = (Difficulties) intent.getSerializableExtra("difficulty");
        final Figures figure = (Figures) intent.getSerializableExtra("figure");

        TextView textViewWinner = findViewById(R.id.TextViewWinner);
        textViewWinner.setText(winnerText);

        Button buttonOptions = findViewById(R.id.OptionsButton);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PregameActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("figure", figure);
                startActivity(intent);
            }
        });

        Button buttonRestart = findViewById(R.id.RestartButton);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("figure", figure);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() { } // Disable Back Button
}
