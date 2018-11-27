package com.skygatestudios.javniprevoz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar mProgressBar;
        CountDownTimer mCountDownTimer;
        final int[] i = {0};

        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setProgress(i[0]);
        mCountDownTimer=new CountDownTimer(2000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i[0] + millisUntilFinished);
                i[0]++;
                mProgressBar.setProgress(i[0]);

            }

            @Override
            public void onFinish() {
                Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);

                 MainActivity.this.startActivity(myIntent);
                i[0]++;
                mProgressBar.setProgress(i[0]);
                finish();
            }
        };
        mCountDownTimer.start();


    }
}
