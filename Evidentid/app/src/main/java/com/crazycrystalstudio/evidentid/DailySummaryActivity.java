package com.crazycrystalstudio.evidentid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.crazycrystalstudio.evidentidmodel.DailySummary;

public class DailySummaryActivity extends AppCompatActivity {

    //summery text views
    TextView mintempi;
    TextView meanwindspdi;
    TextView meanvisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_summery);
        DailySummary summery = getIntent().getParcelableExtra(MainActivity.Daily_Summary_Obj);

        //daily summery text views
        mintempi = (TextView) findViewById(R.id.mintempi);
        meanwindspdi = (TextView) findViewById(R.id.meanwindspdi);
        meanvisi = (TextView) findViewById(R.id.meanvisi);

        //set daily summary components
        mintempi.setText("Mean temperature: "+summery.getMintempi()+ " \u2109"+"/"+summery.getMaxtempi()+ " \u2109");
        meanwindspdi.setText("Mean wind speed in Mph: "+summery.getMeanwindspdi());
        meanvisi.setText("Mean visibility in Mph: "+summery.getMeanvisi());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back);
    }
}
