package com.babyraising.triumph.ui.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.User;
import com.babyraising.triumph.ui.main.MotorSetupActivity;
import com.babyraising.triumph.util.T;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends BaseActivity {

    private User user = null;

    @ViewInject(R.id.username)
    private TextView username;

    @ViewInject(R.id.password)
    private EditText password;

    @Event(R.id.sign)
    private void sign(View view) {
        if (TextUtils.isEmpty(password.getText().toString())) {
            T.s("Password Name cannot be empty");
            return;
        }

        sign();
    }

    @Event(R.id.user_profile)
    private void userProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    @Event(R.id.forget)
    private void forget(View view) {
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        DbManager db = null;
        try {
            User checkUser = db.selector(User.class).findFirst();
            if (checkUser != null) {
                username.setText(checkUser.getUsername());
                user = checkUser;
            }
        } catch (DbException e) {
            e.printStackTrace();
            T.s("Get user data failed");
        }
    }

    private void initView() {

    }

    private void sign() {
        if (user != null && user.getPassword().equals(password.getText().toString())) {
            T.s("Sign success");
            Intent intent = new Intent(this, MotorSetupActivity.class);
            startActivity(intent);
            finish();
        } else {
            T.s("Password is Incorrect");
        }
    }

}
