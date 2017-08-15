package com.android.cebletandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JobDescription extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        String description = getIntent().getStringExtra("description");

        tv = (TextView) findViewById(R.id.jobD);
        tv.setText(description);
    }
}
