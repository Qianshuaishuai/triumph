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

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    @ViewInject(R.id.motor)
    private TextView motor;

    @ViewInject(R.id.exposure_time)
    private TextView exposureTime;

    @ViewInject(R.id.edge_time)
    private TextView edgeTime;

    @ViewInject(R.id.turn_direction)
    private TextView turnDirection;

    @ViewInject(R.id.mode)
    private TextView mode;

    @ViewInject(R.id.turn_degree)
    private TextView turnDegree;

    @ViewInject(R.id.start_position)
    private TextView startPosition;

    @ViewInject(R.id.turn_frequency)
    private TextView turnFrequency;

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.reset)
    private void reset(View view) {

    }

    @Event(R.id.layout_motor)
    private void motor(View view) {

    }

    @Event(R.id.layout_edge_time)
    private void edgeTime(View view) {

    }

    @Event(R.id.layout_exposure_time)
    private void exposureTime(View view) {

    }

    @Event(R.id.layout_turn_direction)
    private void turnDirection(View view) {

    }

    @Event(R.id.layout_mode)
    private void mode(View view) {

    }

    @Event(R.id.layout_turn_degree)
    private void turnDegree(View view) {

    }

    @Event(R.id.start_position)
    private void startPosition(View view) {

    }

    @Event(R.id.turn_frequency)
    private void turnFrequency(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
