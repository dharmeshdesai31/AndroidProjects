package com.crazycrystalstudio.evidentid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.crazycrystalstudio.evidentidmodel.ConfigReader;
import com.crazycrystalstudio.evidentidmodel.DailySummary;
import com.crazycrystalstudio.evidentidmodel.DownloadingTaskListener;
import com.crazycrystalstudio.evidentidmodel.Weather;


public class MainActivity extends AppCompatActivity implements DownloadingTaskListener {

    public static String Daily_Summary_Obj = "DailySummeryObj";
    SeekBar seekbar;
    ProgressDialog pDialog;
    String url;

    //all text view to display information on screen
    TextView preety;
    TextView tempi;
    TextView conds;
    TextView dewpti;
    TextView hum;
    TextView wspdi;
    TextView visi;

    DailySummary dailySummery;
    ImageView image;

    //current index data which we have to show on screen
    private int current_index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets the url from configuration file
        String localurl = ConfigReader.getConfigValues(this, "url");

        String dateSelected=getIntent().getExtras().getString(DateSelectionActivity.KEY);
        String location = "/q/GA/Alpharetta.json";
        url =localurl + dateSelected+location;
        Log.i("URL", url);
        init();


        JSONDownloader downloader = new JSONDownloader(MainActivity.this, url, current_index);
        downloader.execute();

        //seeck bar to change time which change the content of the screen
        seekbar = (SeekBar) findViewById(R.id.seekBar);


        //listner to get the change in value
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                current_index = progress;
                new JSONDownloader(MainActivity.this, url, current_index).execute();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void init(){
        //initialize text view
        preety = (TextView) findViewById(R.id.pretty);
        tempi = (TextView) findViewById(R.id.tempi);
        conds = (TextView) findViewById(R.id.conds);
        dewpti = (TextView) findViewById(R.id.dewpti);
        hum = (TextView) findViewById(R.id.hum);
        wspdi = (TextView) findViewById(R.id.wspdi);
        visi = (TextView) findViewById(R.id.visi);

        image = (ImageView) findViewById(R.id.imageView);

        //initializing progress dialog
        pDialog = new ProgressDialog(MainActivity.this);
    }

    public void updateUIComponents(Weather rootNode, Bitmap bitmap){
        Log.i("AsyncTask", "onProgressUpdate");
        image.setImageBitmap(bitmap);
        preety.setText(rootNode.getHistory().getObservation(current_index).getDate().getPretty());
        tempi.setText(""+(int)rootNode.getHistory().getObservation(current_index).getTempi()+ "\u2109");
        conds.setText(""+rootNode.getHistory().getObservation(current_index).getConds());
        dewpti.setText("Dewpoint in C: "+rootNode.getHistory().getObservation(current_index).getDewpti());
        hum.setText("Humidity: "+rootNode.getHistory().getObservation(current_index).getHum()+" %");
        wspdi.setText("Windspeed in mph: "+rootNode.getHistory().getObservation(current_index).getWspdi());
        visi.setText("Visability in Miles: "+rootNode.getHistory().getObservation(current_index).getVisi());
/*
        */
    }

    @Override
    public void onTaskStarted() {
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void onTaskFinished(Weather node, Bitmap bitmap) {
        //sets the seekbar value as per the number of observations available
        seekbar.setMax(node.getHistory().getObservations().length);
        if (pDialog.isShowing())
            pDialog.dismiss();

        dailySummery = new DailySummary (node.getHistory().getDailysummary(0));
        updateUIComponents(node, bitmap);
    }

    @Override
    public void invalidURL() {
        Toast.makeText(MainActivity.this, "Invalid URL, Please enter valid URL", Toast.LENGTH_LONG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.summary:
            {
                Intent intentToDailySummery = new Intent(MainActivity.this, DailySummaryActivity.class);

                intentToDailySummery.putExtra(Daily_Summary_Obj, dailySummery);
                startActivity(intentToDailySummery);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

            }
            break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back);
    }
}
