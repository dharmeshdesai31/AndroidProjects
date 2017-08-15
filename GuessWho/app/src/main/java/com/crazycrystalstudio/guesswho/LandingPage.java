package com.crazycrystalstudio.guesswho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by DhavalDesai on 7/19/17.
 */

public class LandingPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void screenTapped(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
