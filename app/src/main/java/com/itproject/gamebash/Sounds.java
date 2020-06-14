package com.itproject.gamebash;

import android.app.Activity;
import android.media.MediaPlayer;
import android.widget.ImageView;

class Sounds {

    // Sound Settings
    static boolean enableSound;
    static MediaPlayer game_music;

    final MediaPlayer change;
    final MediaPlayer eat;
    final MediaPlayer game_end;
    final MediaPlayer game_start;
    final MediaPlayer tap;

    Sounds(Activity activity) {
        change = MediaPlayer.create(activity, R.raw.change);
        eat = MediaPlayer.create(activity, R.raw.eat);
        game_start = MediaPlayer.create(activity, R.raw.start_game);
        tap = MediaPlayer.create(activity, R.raw.tap);
        game_end = MediaPlayer.create(activity, R.raw.game_end);
    }

    static void play(MediaPlayer sound) {
        if (enableSound) {
            sound.start();
        }
    }

    static void pause(MediaPlayer sound) {
        if (enableSound) {
            sound.pause();
        }
    }

    static void mute(ImageView muteButton) {
        if(enableSound) {
            pause(Sounds.game_music);
            enableSound = false;
            muteButton.setImageResource(R.drawable.ic_music_off_white_24dp);
        } else {
            enableSound = true;
            play(Sounds.game_music);
            muteButton.setImageResource(R.drawable.ic_audiotrack_white_24dp);
        }
    }
}