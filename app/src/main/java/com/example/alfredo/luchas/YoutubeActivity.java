package com.example.alfredo.luchas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    final private String key = "AIzaSyCRhzfxNUADl6Kq86X8imeu_P8-dlYDLio"; //llave de API Youtube
    private String uri;
    //ArrayList<String> listaDeVideos;
    //private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        uri = (String)getIntent().getSerializableExtra("idvideo");
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.you);
        youTubePlayerView.initialize(key, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        //this.player = youTubePlayer;
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        if(!b){
            youTubePlayer.cueVideo(uri);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Youtube Error Not Available", Toast.LENGTH_LONG).show();
    }
}
