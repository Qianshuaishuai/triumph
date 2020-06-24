package com.babyraising.triumph.ui.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.babyraising.triumph.Constant;
import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_help)
public class HelpActivity extends BaseActivity {

    @ViewInject(R.id.main_iv)
    private ImageView ivMain;

    @Event(R.id.contract)
    private void contract(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.image().bind(ivMain, "assets://helpBig.png");
    }
}
