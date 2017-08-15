package com.crazycrystalstudio.videoplayer;

import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
public class MainActivity extends AppCompatActivity {
    VideoView mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        mVideo = (VideoView) findViewById(R.id.videoView);
        MediaController controller = new MediaController(this);
        mVideo.setMediaController(controller);
        mVideo.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=XkN2kMHA0C4"));
        mVideo.start();*/

        mVideo = (VideoView) findViewById(R.id.videoView);
        mVideo.setVideoPath("android:resource://"+getPackageName()+"/"+R.raw.video);

        MediaController mController = new MediaController(this);
        mController.setAnchorView(mVideo);
        mVideo.setMediaController(mController);

        mVideo.start();


    }
}
