package com.babyraising.triumph.ui.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.clear_all)
    private void clearAll(View view) {
        registDate.setText("");
        userName.setText("");
        password.setText("");
        confirmPassword.setText("");
        userPhone.setText("");
        userAdd.setText("");
    }

    @Event(R.id.regist)
    private void regist(View view) {
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            T.s("Two passwords are inconsistent");
            return;
        }

        if (TextUtils.isEmpty(userName.getText().toString())) {
            T.s("User Name cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            T.s("Password Name cannot be empty");
            return;
        }

        User user = new User();
        user.setUsername(userName.getText().toString());
        user.setPassword(password.getText().toString());
        user.setAddress(userAdd.getText().toString());
        user.setDate(registDate.getText().toString());
        user.setPhone(userPhone.getText().toString());
        register(user);
    }

    @ViewInject(R.id.regist_date)
    private TextView registDate;

    @ViewInject(R.id.username)
    private TextView userName;

    @ViewInject(R.id.password)
    private TextView password;

    @ViewInject(R.id.confirm_password)
    private TextView confirmPassword;

    @ViewInject(R.id.user_phone)
    private TextView userPhone;

    @ViewInject(R.id.user_add)
    private TextView userAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void register(User user) {
        try {
            DbManager db = x.getDb(((TriumphApplication) getApplication()).getDaoConfig());
            User checkUser = db.selector(User.class).where("username", "in", new String[]{user.getUsername()}).findFirst();
            if (checkUser != null) {
                T.s("User name is used");
                return;
            }
            db = x.getDb(((TriumphApplication) getApplication()).getDaoConfig());
            db.save(user);
            startSignActivity();
        } catch (DbException e) {
            e.printStackTrace();
            T.s("register failed");
        }
    }

    private void startSignActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
