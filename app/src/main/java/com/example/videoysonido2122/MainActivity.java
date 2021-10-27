package com.example.videoysonido2122;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button buttonPlay, buttonStop, buttonPause;
    TextView textView;
    MediaPlayer mediaPlayer;
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPause = findViewById(R.id.buttonPause);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonStop = findViewById(R.id.buttonStop);
        textView = findViewById(R.id.textView);
        videoView = findViewById(R.id.videoView);

        mediaPlayer = MediaPlayer.create(this, R.raw.cancion);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    textView.setText("Ya está sonando");
                } else {
                    mediaPlayer.start();
                    textView.setText("La canción está sonando");
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    textView.setText("Canción PARADA");
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    textView.setText("Ya está parada!!");
                }

            }
        });
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    textView.setText("PAUSADA");
                } else {
                    textView.setText("No está sonando");
                }
            }
        });

        //Configuro el vídeo
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView); //Se ancla al videoview
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.rana;
        videoView.setVideoURI(Uri.parse(uri));

        videoView.setMediaController(mediaController); //Asociamos el videoview al nuestro mediaControler

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                mediaController.show();
            }
        });


    }
}