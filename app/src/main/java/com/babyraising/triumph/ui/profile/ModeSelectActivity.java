package com.babyraising.triumph.ui.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.Constant;
import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.adapter.MotorSelectAdapter;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.Motor;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_mode_select)
public class ModeSelectActivity extends BaseActivity {

    private MotorSelectAdapter adapter;
    private List<String> list = new ArrayList<>();
    private List<Motor> motorList = new ArrayList<>();
    private int mode = 0;
    private int motor = 0;

    @ViewInject(R.id.rv_mode_select)
    private RecyclerView rvModeSelect;

    @ViewInject(R.id.motor_number)
    private TextView motorNumber;

    @ViewInject(R.id.current_mode)
    private TextView currentMode;

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.no_need)
    private void noNeed(View view) {
        finish();
    }

    @Event(R.id.save)
    private void save(View view) {
        finish();
        motorList.get(motor).setMode(mode);
        ((TriumphApplication) getApplication()).setMotorList(motorList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mode = intent.getIntExtra("mode", 0);
        motor = intent.getIntExtra("index", 0);

        motorNumber.setText("Motor " + (motor + 1));
        currentMode.setText("Select Mode " + Constant.LETTLE[mode]);
        motorList = ((TriumphApplication) getApplication()).getMotorList();
    }

    private void initView() {
        list = ((TriumphApplication) getApplication()).getModeList();

        adapter = new MotorSelectAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter.setOnItemClickListener(new MotorSelectAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
        rvModeSelect.setLayoutManager(manager);
        rvModeSelect.setAdapter(adapter);
    }

    public void selectMode(int position) {
        mode = position;
        currentMode.setText("Select Mode " + Constant.LETTLE[mode]);
    }
}
