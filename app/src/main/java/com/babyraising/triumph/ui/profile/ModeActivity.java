package com.babyraising.triumph.ui.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_mode)
public class ModeActivity extends BaseActivity {

    @ViewInject(R.id.current_mode)
    private TextView currentMode;

    @ViewInject(R.id.rv_mode)
    private RecyclerView rvMode;

    @Event(R.id.back)
    private void back(View view){
        finish();
    }

    @Event(R.id.choose_apply)
    private void chooseApply(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
