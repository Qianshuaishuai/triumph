package com.babyraising.triumph.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.ui.other.InfoActivity;
import com.babyraising.triumph.util.TimeUtil;

import org.xutils.view.annotation.ContentView;

import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

    private int skipTimeCount = 4;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        ((TriumphApplication) getApplication()).saveCurrentTime(TimeUtil.getCurrentTime());
    }

    private void initData() {
        Intent intent = getIntent();
        int mode = intent.getIntExtra("mode", 0);

        if (mode == 111) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skipTimeCount--;
                            if (skipTimeCount < 0) {
                                timer.cancel();
                                finish();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 0, 1000);
        } else {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            skipTimeCount--;
                            if (skipTimeCount == -1) {
                                timer.cancel();
                                skip();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask, 0, 1000);
        }
    }

    private void skip() {
        Intent newIntent = new Intent(WelcomeActivity.this, InfoActivity.class);
        startActivity(newIntent);
        finish();
        //保存状态
        ((TriumphApplication) getApplication()).saveFirstGo(1);
    }
}
