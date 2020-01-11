package com.koen.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.koen.tictactoe.R;
import com.koen.tictactoe.controller.GameController.Computer;
import com.koen.tictactoe.controller.GameController.Player;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgameactivity);

        Intent intent = getIntent();

        final Computer computerType = (Computer) intent.getSerializableExtra("computerType");
        final Player selectedPlayer = (Player) intent.getSerializableExtra("selectedPlayer");
        final Player winningPlayer = (Player) intent.getSerializableExtra("winningPlayer");

        LottieAnimationView animation = findViewById(R.id.animation_view_ending);
        TextView textViewEndingMessage = findViewById(R.id.TextViewEndingMessage);
        TextView textViewWinner = findViewById(R.id.TextViewWinner);


        if(selectedPlayer == winningPlayer){
            animation.setAnimation("celebrate.json");
            textViewEndingMessage.setText("Congratulations!");
            textViewWinner.setText("You have won the game");
        }else if(winningPlayer == Player.BLANK){
            animation.setAnimation("heart-animation.json");
            textViewEndingMessage.setText("Oh no!");
            textViewWinner.setText("The game ended in a draw.");
        }else if(selectedPlayer != winningPlayer){
            animation.setAnimation("loading.json");
            textViewEndingMessage.setText("Oh no!");
            textViewWinner.setText("You have lost the game");
        }else{
            throw new IllegalArgumentException("ONGELOOFLIJK HET WERKT NIET!");
        }

        Button buttonOptions = findViewById(R.id.OptionsButton);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PregameActivity.class);
                intent.putExtra("computerType", computerType);
                intent.putExtra("selectedPlayer", selectedPlayer);
                startActivity(intent);
            }
        });

        Button buttonRestart = findViewById(R.id.RestartButton);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("computerType", computerType);
                intent.putExtra("selectedPlayer", selectedPlayer);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() { } // Disable Back Button
}
