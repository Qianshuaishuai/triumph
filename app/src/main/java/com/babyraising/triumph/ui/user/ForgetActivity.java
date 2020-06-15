package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_forget)
public class ForgetActivity extends BaseActivity {

    @ViewInject(R.id.user_phone)
    private TextView userPhone;

    @ViewInject(R.id.password)
    private TextView password;

    @ViewInject(R.id.confirm_password)
    private TextView confirmPassword;

    @Event(R.id.forget)
    private void forget(View view) {
        if (password.getText().toString().equals(confirmPassword.getText().toString())) {
            T.s("Two passwords are inconsistent");
            return;
        }
    }

    @Event(R.id.reset)
    private void reset(View view) {

    }

    @Event(R.id.back)
    private void back(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
