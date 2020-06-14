package com.itproject.gamebash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
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
}