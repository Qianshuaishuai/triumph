package com.babyraising.triumph.ui.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.adapter.MotorAdapter;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.BigMode;
import com.babyraising.triumph.bean.Motor;
import com.babyraising.triumph.ui.other.HelpActivity;
import com.babyraising.triumph.ui.profile.ModeActivity;
import com.babyraising.triumph.ui.profile.ModeSelectActivity;
import com.babyraising.triumph.ui.profile.SettingActivity;
import com.babyraising.triumph.ui.profile.SystemSettingActivity;
import com.babyraising.triumph.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@ContentView(R.layout.activity_motor_setup)
public class MotorSetupActivity extends BaseActivity {

    private int RUNNING_REQUEST_CODE = 101;
    private Timer timer;
    private TimerTask timerTask;
    private int needCountDown = 5;
    private AlertDialog saveModeDialog;

    private MotorAdapter adapter;
    private List<Motor> list = new ArrayList<>();

    @ViewInject(R.id.rv_motor)
    private RecyclerView rvMotor;

    @Event(R.id.use)
    private void use(View view) {
        List<BigMode> bigModeList = ((TriumphApplication) getApplication()).getBigModeList();
        if (bigModeList == null || bigModeList.size() == 0) {
            T.s("Mode list not has save data");
            return;
        }
        Intent intent = new Intent(this, ModeActivity.class);
        startActivity(intent);
    }

    @Event(R.id.stop_all)
    private void stopAll(View view) {
        Intent intent = new Intent(this, StopActivity.class);
        startActivity(intent);
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
        saveModeDialog.show();
    }

    @ViewInject(R.id.count_down)
    private TextView countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initSaveModeDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Motor> updateList = ((TriumphApplication) getApplication()).getMotorList();
        list.clear();
        for (int i = 0; i < updateList.size(); i++) {
            list.add(updateList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        list = ((TriumphApplication) getApplication()).getMotorList();
        adapter = new MotorAdapter(this, list);
        adapter.setOnItemClickListener(new MotorAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        rvMotor.setAdapter(adapter);
        rvMotor.setLayoutManager(manager);
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
        if (timer != null) {
            timer.cancel();
        }
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

    public void startSettingActivity(int position, int mode) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("index", position);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }

    public void startModeActivity(int position, int mode) {
        Intent intent = new Intent(this, ModeSelectActivity.class);
        intent.putExtra("index", position);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }

    public void changePairing(int position) {

    }

    private void initSaveModeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 创建一个view，并且将布局加入view中
        View view = LayoutInflater.from(this).inflate(
                R.layout.dialog_save_mode, null, false);
        // 将view添加到builder中
        builder.setView(view);
        // 创建dialog
        saveModeDialog = builder.create();
        // 初始化控件，注意这里是通过view.findViewById
        Button leftButton = (Button) view.findViewById(R.id.left);
        Button rightButton = (Button) view.findViewById(R.id.right);
        final EditText input = (EditText) view.findViewById(R.id.input);

        leftButton.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                saveModeDialog.cancel();
            }
        });

        rightButton.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (TextUtils.isEmpty(input.getText().toString())) {
                    T.s("New rule name cannot be empty");
                    return;
                }
                saveModeDialog.cancel();
                saveBigMode(input.getText().toString());
            }
        });

        saveModeDialog.setCancelable(false);
    }

    private void saveBigMode(String name) {
        List<BigMode> bigModeList = ((TriumphApplication) getApplication()).getBigModeList();
        if (bigModeList == null) {
            bigModeList = new ArrayList<>();
        }
        BigMode newBigMode = new BigMode();
        newBigMode.setMotorList(list);
        newBigMode.setName(name);
        bigModeList.add(newBigMode);
        ((TriumphApplication) getApplication()).setBigModeList(bigModeList);
        T.s("Save Success!");
        ((TriumphApplication) getApplication()).saveBigModeIndex(bigModeList.size() - 1);
    }
}
