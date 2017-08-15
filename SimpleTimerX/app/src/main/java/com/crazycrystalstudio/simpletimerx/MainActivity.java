package com.crazycrystalstudio.simpletimerx;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SeekBar controlBar;
    TextView timerView;
    boolean isActive = false;
    Button btn;
    CountDownTimer countDownTimer;

    public void updateTimer(int progress){
        int min = (int) progress/60;
        int sec = progress - min *60;
        String secString = "";

        if(sec <= 9 )
            secString = "0";

        timerView.setText(min+":"+secString+""+sec);
    }

    public void resetAll(){
        isActive = false;
        timerView.setText("0:30");
        controlBar.setProgress(30);
        countDownTimer.cancel();
        btn.setText("START");
        controlBar.setEnabled(true);
    }

    public void startTimer(View v)
    {
        if(!isActive) {
            isActive = true;
            controlBar.setEnabled(false);
            btn.setText("STOP");

           countDownTimer = new CountDownTimer(controlBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetAll();
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mp.start();
                }
            }.start();
        }else{
            resetAll();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        controlBar = (SeekBar) findViewById(R.id.seekBar2);
        controlBar.setMax(600);
        controlBar.setProgress(30);

        controlBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
