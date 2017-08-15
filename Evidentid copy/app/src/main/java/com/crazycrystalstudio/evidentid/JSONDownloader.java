package com.crazycrystalstudio.evidentid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.crazycrystalstudio.evidentidmodel.DownloadingTaskListener;
import com.crazycrystalstudio.evidentidmodel.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by DharmeshDesai on 8/13/17.
 */

class JSONDownloader extends AsyncTask<Void, Void, Void> {
    private DownloadingTaskListener listner;
    private Weather rootNode;
    private String url;
    Bitmap bitmap = null;
    int index;

    public JSONDownloader(DownloadingTaskListener listner, String url, int currIndex) {
        if( Patterns.WEB_URL.matcher(url).matches()) {
            this.listner = listner;
            this.url = url;
            this.index = currIndex;
        }else{
            Log.e("URL: ","Invalid URL found");
            listner.invalidURL();
            throw new RuntimeException();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listner.onTaskStarted();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            rootNode =  mapper.readValue(new URL(url), Weather.class);
            Log.i("JSON", rootNode.toString());
            // Download Image from URL
            InputStream input = new java.net.URL("https://icons.wxug.com/i/c/k/"+rootNode.getHistory().getObservation(this.index).getIcon()+".gif").openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listner.onTaskFinished(rootNode, bitmap);
    }

    public Weather getWeather(){
        return rootNode;
    }

    public JSONDownloader setListener(DownloadingTaskListener listner){
        this.listner = listner;
        return this;
    }
}
