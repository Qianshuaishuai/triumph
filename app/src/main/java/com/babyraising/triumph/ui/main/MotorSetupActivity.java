package com.babyraising.triumph.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;


@ContentView(R.layout.activity_motor_setup)
public class MotorSetupActivity extends BaseActivity {

    @Event(R.id.use)
    private void use(View view){

    }

    @Event(R.id.stop_all)
    private void stopAll(View view){

    }

    @Event(R.id.running_all)
    private void runningAll(View view){

    }

    @Event(R.id.help)
    private void help(View view){

    }

    @Event(R.id.save_mode)
    private void saveMode(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
