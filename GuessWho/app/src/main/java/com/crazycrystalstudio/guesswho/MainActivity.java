package com.crazycrystalstudio.guesswho;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> Celeb_urls = new ArrayList<String>();
    ArrayList<String> Celeb_names = new ArrayList<String>();
    int choosenImage = 0;
    ImageView selImage;
    int locationOfCorrectAns = 0;
    String[] ansArr = new String[4];

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void buttonClicked(View view){
        System.out.println("button clicked ");
        Log.i("button clicked","");
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Answer! It was "+Celeb_names.get(choosenImage), Toast.LENGTH_LONG).show();
        }
        CreateNewQuestion();
    }



    //this asyncTask is to download the image
    public class ImageDownloader extends  AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }


    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream is = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(is);

                int data = reader.read();

                while(data != -1){
                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }
                return result;

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selImage = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button1);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        button3 = (Button) findViewById(R.id.button4);

        DownloadTask task = new DownloadTask();
        String result = "";

        try {
            result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResults = result.split("<div class=\"sidebarContainer\">");

            Pattern p = Pattern.compile("<img src=\"(.*?)\"");

            Matcher m = p.matcher(splitResults[0]);

            while(m.find()){
                //System.out.println(m.group(1));
                Celeb_urls.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResults[0]);

            while(m.find()){
                //System.out.println(m.group(1));
                Celeb_names.add(m.group(1));
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        CreateNewQuestion();
    }

    public void CreateNewQuestion(){
        Random rand = new Random();
        choosenImage = rand.nextInt(Celeb_urls.size());

        ImageDownloader imageDownloadTask = new ImageDownloader();
        Bitmap celebImage;

        try {
            celebImage = imageDownloadTask.execute(Celeb_urls.get(choosenImage)).get();
            selImage.setImageBitmap(celebImage);

            locationOfCorrectAns = rand.nextInt(4);

            int incorrectAnsLocation = 0;

            for(int i=0; i<4; i++){
                if(i == locationOfCorrectAns){
                    ansArr[i] = Celeb_names.get(choosenImage);
                }else{

                    incorrectAnsLocation = rand.nextInt(Celeb_urls.size());

                    while (incorrectAnsLocation == choosenImage){
                        incorrectAnsLocation = rand.nextInt(Celeb_urls.size());
                    }
                    ansArr[i] = Celeb_names.get(incorrectAnsLocation);
                }
            }

            button0.setText(ansArr[0]);
            button1.setText(ansArr[1]);
            button2.setText(ansArr[2]);
            button3.setText(ansArr[3]);
            //Log.i("URL Content", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
