package com.babyraising.triumph.ui.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.adapter.ModeSelectAdapter;
import com.babyraising.triumph.adapter.MotorSelectAdapter;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.BigMode;
import com.babyraising.triumph.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_mode)
public class ModeActivity extends BaseActivity {

    private List<BigMode> bigModeList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private int selectIndex = 0;
    private ModeSelectAdapter adapter;

    @ViewInject(R.id.current_mode)
    private TextView currentMode;

    @ViewInject(R.id.rv_mode)
    private RecyclerView rvMode;

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.choose_apply)
    private void chooseApply(View view) {
        if (bigModeList != null && bigModeList.size() > 0 && selectIndex >= 0) {
            ((TriumphApplication) getApplication()).saveBigModeIndex(selectIndex);
            ((TriumphApplication) getApplication()).setMotorList(bigModeList.get(selectIndex).getMotorList());
        }
        finish();
        T.s("Apply Success!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        bigModeList = ((TriumphApplication) getApplication()).getBigModeList();
        selectIndex = ((TriumphApplication) getApplication()).getBigModeIndex();
        if (bigModeList != null && bigModeList.size() > 0 && selectIndex >= 0) {
            currentMode.setText(bigModeList.get(selectIndex).getName());
        }
        adapter = new ModeSelectAdapter(this, bigModeList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter.setOnItemClickListener(new ModeSelectAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
        rvMode.setLayoutManager(manager);
        rvMode.setAdapter(adapter);
    }

    public void selectBigMode(int position) {
        selectIndex = position;
        if (bigModeList != null && bigModeList.size() > 0 && selectIndex >= 0) {
            currentMode.setText(bigModeList.get(selectIndex).getName());
        }
    }
}
