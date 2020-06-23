package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.User;
import com.babyraising.triumph.util.T;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_forget)
public class ForgetActivity extends BaseActivity {

    private User user = null;
    private DbManager db;

    @ViewInject(R.id.user_phone)
    private TextView userPhone;

    @ViewInject(R.id.password)
    private TextView password;

    @ViewInject(R.id.confirm_password)
    private TextView confirmPassword;

    @Event(R.id.confirm)
    private void confirm(View view) {
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            T.s("Two passwords are inconsistent");
            return;
        }

        if (userPhone.getText().toString().equals(user.getPhone())) {
            changePassword(password.getText().toString());
        } else {
            T.s("User phone number is Incorrect");
        }
    }

    private void changePassword(String newPwd) {
        if (user == null) {
            T.s("User information not found");
            return;
        }
        user.setPassword(newPwd);
        try {
            db.update(user);
            T.s("success");
            finish();
        } catch (DbException e) {
            e.printStackTrace();
            T.s("Change new password failed");
        }
    }

    @Event(R.id.reset)
    private void reset(View view) {
        userPhone.setText("");
        password.setText("");
        confirmPassword.setText("");
    }

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        try {
            db = x.getDb(((TriumphApplication) getApplication()).getDaoConfig());
            User checkUser = db.selector(User.class).findFirst();
            if (checkUser != null) {
                user = checkUser;
            }
        } catch (DbException e) {
            e.printStackTrace();
            System.out.println("Get user data failed");
        }
    }
}
