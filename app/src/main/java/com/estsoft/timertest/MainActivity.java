package com.estsoft.timertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer.schedule(new MyTimerTask(), 1000, 1000);  //  1초 후에 1초마다 시작하라.

    }

    private class MyTimerTask extends TimerTask{
        private int seconds = 0;

        @Override
        public void run() {
            if(++seconds > 10){
                Log.d("---------------->", "Timer Cancel");
                timer.cancel();
                finish();
                return;
            }

            // UI 변경
            // 메인 스레드에서 돌려준다.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView  tv = (TextView)findViewById(R.id.textview);
                    tv.setText(seconds+"");
                }
            });

        }
    }
}
