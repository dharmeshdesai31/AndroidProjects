package com.crazycrystalstudio.evidentid;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crazycrystalstudio.evidentidmodel.ConfigReader;
import com.crazycrystalstudio.evidentidmodel.DownloadingTaskListener;
import com.crazycrystalstudio.evidentidmodel.Weather;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity implements DownloadingTaskListener {
    SeekBar seeckbar;
    ProgressDialog pDialog;
    String url;

    //all text view to display information on screen
    TextView preety;
    TextView tempm;
    TextView conds;
    TextView dewptm;
    TextView hum;
    TextView wspdi;
    TextView visi;

    //summery text views
    TextView mintempm;
   // TextView maxtempm;
    TextView meanwindspdm;
    TextView meanvism;



    ImageView image;

    //current index data which we have to show on screen
    private int current_index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets the url from configuration file
        url = ConfigReader.getConfigValues(this, "url");

        //initialize text view
        preety = (TextView) findViewById(R.id.pretty);
        tempm = (TextView) findViewById(R.id.tempm);
        conds = (TextView) findViewById(R.id.conds);
        dewptm = (TextView) findViewById(R.id.dewptm);
        hum = (TextView) findViewById(R.id.hum);
        wspdi = (TextView) findViewById(R.id.wspdi);
        visi = (TextView) findViewById(R.id.visi);

        image = (ImageView) findViewById(R.id.imageView);

        mintempm = (TextView) findViewById(R.id.mintempm);
        //maxtempm = (TextView) findViewById(R.id.maxtempm);
        meanwindspdm = (TextView) findViewById(R.id.meanwindspdm);
        meanvism = (TextView) findViewById(R.id.meanvism);

        //initializing progress dialog
        pDialog = new ProgressDialog(MainActivity.this);

        JSONDownloader downloader = new JSONDownloader(MainActivity.this, url, current_index);
        downloader.execute();

        //seeck bar to change time which change the content of the screen
        seeckbar = (SeekBar) findViewById(R.id.seekBar);

        //listner to get the change in value
        seeckbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Progress ", ""+progress);
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

    public void updateUIComponents(Weather rootNode, Bitmap bitmap){
        Log.i("AsyncTask", "onProgressUpdate");
        image.setImageBitmap(bitmap);
        preety.setText(rootNode.getHistory().getObservation(current_index).getDate().getPretty());
        tempm.setText(""+(int)rootNode.getHistory().getObservation(current_index).getTempm()+ "\u2109");
        conds.setText(""+rootNode.getHistory().getObservation(current_index).getConds());
        dewptm.setText("Dewpoint in C: "+rootNode.getHistory().getObservation(current_index).getDewptm());
        hum.setText("Humidity: "+rootNode.getHistory().getObservation(current_index).getHum()+" %");
        wspdi.setText("Windspeed in mph: "+rootNode.getHistory().getObservation(current_index).getWspdm());
        visi.setText("Visability in Miles: "+rootNode.getHistory().getObservation(current_index).getVisi());

        mintempm.setText("TEMP: "+rootNode.getHistory().getDailysummary(0).getMintempm()+ " \u2109"+"/"+rootNode.getHistory().getDailysummary(0).getMaxtempm()+ " \u2109");
        //maxtempm.setText("maxtempm"+rootNode.getHistory().getDailysummary(0).getMaxtempm());
        meanwindspdm.setText("meanwindspdm:"+rootNode.getHistory().getDailysummary(0).getMeanwindspdm());
        meanvism.setText("meanvism:"+rootNode.getHistory().getDailysummary(0).getMeanvism());
    }

    @Override
    public void onTaskStarted() {
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void onTaskFinished(Weather node, Bitmap bitmap) {
        if (pDialog.isShowing())
            pDialog.dismiss();
        updateUIComponents(node, bitmap);
    }

    @Override
    public void invalidURL() {
        Toast.makeText(MainActivity.this, "Invalid URL, Please enter valid URL", Toast.LENGTH_LONG);
    }

    /*private class MyJsonDownloader extends AsyncTask<Void, Void, Void> {

        Weather rootNode;

        public MyJsonDownloader() {
            // String url = ConfigReader.getConfigValues(this, "url")
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                rootNode =  mapper.readValue(new URL(url), Weather.class);
                Log.i("JSON", rootNode.toString());
                publishProgress();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.i("AsyncTask", "onProgressUpdate");
            preety.setText("Wether: "+rootNode.getHistory().getObservation(current_index).getDate().getPretty());
            tempm.setText("Temp in C: "+rootNode.getHistory().getObservation(current_index).getTempm());
            dewptm.setText("Dewpoint in C: "+rootNode.getHistory().getObservation(current_index).getDewptm());
            hum.setText("Humidity: "+rootNode.getHistory().getObservation(current_index).getHum()+" %");
            wspdi.setText("Windspeed in mph: "+rootNode.getHistory().getObservation(current_index).getWspdm());
            visi.setText("Visability in Miles: "+rootNode.getHistory().getObservation(current_index).getVisi());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }*/

}
