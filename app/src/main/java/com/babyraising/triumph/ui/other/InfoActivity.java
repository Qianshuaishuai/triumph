package com.babyraising.triumph.ui.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_info)
public class InfoActivity extends BaseActivity {

    @Event(R.id.contract)
    private void contract(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    @Event(R.id.about)
    private void about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    @Event(R.id.next)
    private void next(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
