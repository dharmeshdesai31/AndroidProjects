package com.test.ndk_lessontest;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GL2JNIView mView;
    static AssetManager assets;
    @Override protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        assets = getAssets();
        GL2JNILib.onCreate( assets );
        mView = new GL2JNIView(getApplication());
        setContentView(mView);
    }

    @Override protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override protected void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    protected void onDestroy() {

        GL2JNILib.onDestroy();

        super.onDestroy();
    }
}
