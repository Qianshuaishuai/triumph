package com.babyraising.triumph.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.util.TimeUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_shut_down)
public class ShutDownActivity extends BaseActivity {

    private Timer timer;
    private TimerTask timerTask;
    private int needCountDown = 10;
    private int pointCount = 0;
    private String titleStr = "Shut Down ......";

    @ViewInject(R.id.title)
    private TextView title;

    @ViewInject(R.id.train_duration)
    private TextView trainDuration;

    @ViewInject(R.id.train_time)
    private TextView trainTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initCountDown();
    }

    private void initView() {
        String lastTime = ((TriumphApplication) getApplication()).getCurrentTime();
        if (!TextUtils.isEmpty(lastTime)) {
            String currentTime = TimeUtil.getCurrentTime();
            int offsetMinute = TimeUtil.getGapMinutes(lastTime, currentTime);

            int oldUseMinute = ((TriumphApplication) getApplication()).getUseAllTime();

            ((TriumphApplication) getApplication()).saveUseAllTime(oldUseMinute + offsetMinute);

            int allMinute = (oldUseMinute + offsetMinute) % 60;
            int allHour = (oldUseMinute + offsetMinute) / 60;

            int currentMinute = offsetMinute % 60;
            int currentHour = offsetMinute / 60;

            trainTime.setText("Total training time " + allHour + " hours " + allMinute + " minutes");
            trainDuration.setText("Training Duration " + currentHour + " hours " + currentMinute + " minutes");

        }
    }

    private void initCountDown() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        needCountDown--;
                        pointCount++;
                        if (needCountDown < 0) {
                            timer.cancel();
                            startWelComeActivity();
                        }

                        if (pointCount == 6) {
                            pointCount = 0;
                        }

                        titleStr = "Shut Down ";
                        for (int i = 0; i < pointCount; i++) {
                            titleStr = titleStr + ".";
                        }
                        title.setText(titleStr);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void startWelComeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mode", 111);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}
