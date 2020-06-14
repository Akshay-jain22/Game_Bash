package com.itproject.gamebash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Popup Views and Widgets
    PopupWindow newGamePopUp;
    View newGamePopUpView;
    Button playTTT, playFishie;
    EditText player1;
    EditText player2;

    ImageView tic_tac_toe;
    ImageView fishie;

    static Sounds sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sounds = new Sounds(this);
        settings();

        final ImageView info = findViewById(R.id.info);
        final ImageView mute = findViewById(R.id.mute);

        tic_tac_toe = findViewById(R.id.game0);
        fishie = findViewById(R.id.game1);

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.play(sounds.tap);
                Sounds.mute(mute);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.play(sounds.change);
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);
            }
        });

        tic_tac_toe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.play(sounds.change);
                startTTTGame();
            }
        });

        fishie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.play(sounds.change);
                startFishieGame();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        Sounds.pause(Sounds.game_music);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Sounds.play(Sounds.game_music);
    }


    @SuppressLint({"InflateParams", "SetTextI18n"})
    void startTTTGame() {
        newGamePopUpView = getLayoutInflater().inflate(R.layout.pop_up_new_ttt, null);
        newGamePopUp = new PopupWindow(newGamePopUpView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        playTTT = newGamePopUpView.findViewById(R.id.play_ttt);
        player1 = newGamePopUpView.findViewById(R.id.player1_edit_text);
        player2 = newGamePopUpView.findViewById(R.id.player2_edit_text);

        playTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sounds.play(sounds.change);
                if(player1.getText().toString().equals("") || player2.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Player names cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    newGamePopUp.dismiss();
                    Intent intent1 = new Intent(MainActivity.this, TicTacToe.class);
                    intent1.putExtra("player1name", player1.getText().toString());
                    intent1.putExtra("player2name", player2.getText().toString());
                    startActivity(intent1);
                }
            }
        });
        player1.setText("Player 1");
        player2.setText("Player 2");
        newGamePopUp.setAnimationStyle(R.style.pop_animation);
        newGamePopUp.showAtLocation(newGamePopUpView, Gravity.CENTER, 0, 0);
    }

    @SuppressLint("InflateParams")
    void startFishieGame() {
        newGamePopUpView = getLayoutInflater().inflate(R.layout.pop_up_new_fishie, null);
        newGamePopUp = new PopupWindow(newGamePopUpView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        playFishie = newGamePopUpView.findViewById(R.id.play_fishie);

        playFishie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Sounds.play(sounds.change);
                    Sounds.play(sounds.game_start);
                    newGamePopUp.dismiss();
                    Intent intent1 = new Intent(MainActivity.this, Fishie.class);
                    startActivity(intent1);
            }
        });
        newGamePopUp.setAnimationStyle(R.style.pop_animation);
        newGamePopUp.showAtLocation(newGamePopUpView, Gravity.CENTER, 0, 0);
    }

    void settings() {
        Sounds.enableSound = true;

        Sounds.game_music = MediaPlayer.create(this, R.raw.game_music);
        Sounds.game_music.setLooping(true);
    }
}