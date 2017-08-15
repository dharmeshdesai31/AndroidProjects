package com.test.animations;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);


    }


    public void fadeAnim(View v){
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.animate().alpha(0f).setDuration(2000);

        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        //iv.setImageResource(R.drawable.bg);
        iv2.animate().alpha(1f).setDuration(2000);
    }
}
