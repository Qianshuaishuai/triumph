package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends BaseActivity {

    @ViewInject(R.id.username)
    private TextView username;

    @ViewInject(R.id.password)
    private EditText password;

    @Event(R.id.sign)
    private void sign(View view) {

    }

    @Event(R.id.user_profile)
    private void userProfile(View view) {

    }

    @Event(R.id.forget)
    private void forget(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {

    }
}
