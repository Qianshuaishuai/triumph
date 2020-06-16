package com.babyraising.triumph.ui.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_system_setting)
public class SystemSettingActivity extends BaseActivity {

    @ViewInject(R.id.count_down)
    private TextView countDown;

    @ViewInject(R.id.audio_level)
    private TextView audioLevel;

    @ViewInject(R.id.brightness_level)
    private TextView brightnessLevel;

    @Event(R.id.audio_level_down)
    private void audioLevelDown(View view) {

    }

    @Event(R.id.audio_level_up)
    private void audioLevelUp(View view) {

    }

    @Event(R.id.brightness_level_up)
    private void brightnessLevelUp(View view) {

    }

    @Event(R.id.brightness_level_down)
    private void brightnessLevelDown(View view) {

    }

    @Event(R.id.count_down_up)
    private void countDownUp(View view) {

    }

    @Event(R.id.count_down_down)
    private void countDownDown(View view) {

    }

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.confirm)
    private void confirm(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
