package guesswhoami.crazycrystalstudio.com.guesswhoami;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
    }

    public void screenTapped(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

