package com.babyraising.triumph.ui.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.Constant;
import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.Motor;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    private int mode = 0;
    private int motor = 0;
    private List<Motor> list = new ArrayList<>();

    @ViewInject(R.id.motor)
    private TextView motorTv;

    @ViewInject(R.id.exposure_time)
    private TextView exposureTime;

    @ViewInject(R.id.edge_time)
    private TextView edgeTime;

    @ViewInject(R.id.turn_direction)
    private TextView turnDirection;

    @ViewInject(R.id.mode)
    private TextView modeTv;

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
        Intent intent = new Intent(this, ModeSelectActivity.class);
        intent.putExtra("index", motor);
        intent.putExtra("mode", mode);
        startActivity(intent);
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

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mode = intent.getIntExtra("mode", 0);
        motor = intent.getIntExtra("index", 0);

        motorTv.setText("" + (motor + 1));
        modeTv.setText(Constant.LETTLE[mode]);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Motor> updateList = ((TriumphApplication) getApplication()).getMotorList();
        list.clear();
        for (int i = 0; i < updateList.size(); i++) {
            list.add(updateList.get(i));
        }

        modeTv.setText(Constant.LETTLE[list.get(motor).getMode()]);
    }
}
