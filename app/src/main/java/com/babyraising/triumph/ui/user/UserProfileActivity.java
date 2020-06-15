package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_user_profile)
public class UserProfileActivity extends BaseActivity {

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @ViewInject(R.id.regist_date)
    private TextView registDate;

    @ViewInject(R.id.username)
    private TextView userName;

    @ViewInject(R.id.password)
    private TextView password;

    @ViewInject(R.id.user_phone)
    private TextView userPhone;

    @ViewInject(R.id.user_add)
    private TextView userAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
