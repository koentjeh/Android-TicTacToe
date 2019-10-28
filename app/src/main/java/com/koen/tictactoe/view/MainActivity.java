package com.koen.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.koen.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        Button buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PregameActivity.class);
                startActivity(intent);
            }
        });

        final RequestQueue queue = Volley.newRequestQueue(this);

        Button buttonAboutUs = findViewById(R.id.buttonAboutUs);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}
