package com.babyraising.triumph.ui.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.ui.other.HelpActivity;
import com.babyraising.triumph.ui.profile.SystemSettingActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Timer;
import java.util.TimerTask;


@ContentView(R.layout.activity_motor_setup)
public class MotorSetupActivity extends BaseActivity {

    private int RUNNING_REQUEST_CODE = 101;
    private Timer timer;
    private TimerTask timerTask;
    private int needCountDown = 5;

    @Event(R.id.use)
    private void use(View view) {

    }

    @Event(R.id.stop_all)
    private void stopAll(View view) {

    }

    @Event(R.id.running_all)
    private void runningAll(View view) {
        Intent intent = new Intent(this, SystemSettingActivity.class);
        startActivityForResult(intent, RUNNING_REQUEST_CODE);
    }

    @Event(R.id.help)
    private void help(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    @Event(R.id.save_mode)
    private void saveMode(View view) {

    }

    @ViewInject(R.id.count_down)
    private TextView countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RUNNING_REQUEST_CODE) {
            countDown();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        countDown.setVisibility(View.GONE);
    }

    private void countDown() {
        needCountDown = ((TriumphApplication) getApplication()).getCountDown() + 1;
        countDown.setText("" + needCountDown);
        countDown.setVisibility(View.VISIBLE);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        needCountDown--;
                        countDown.setText("" + needCountDown);
                        if (needCountDown < 0) {
                            timer.cancel();
                            countDown.setVisibility(View.GONE);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
}
