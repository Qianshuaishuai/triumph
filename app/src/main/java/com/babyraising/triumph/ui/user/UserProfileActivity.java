package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.User;
import com.babyraising.triumph.util.T;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_user_profile)
public class UserProfileActivity extends BaseActivity {

    private User user = null;

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
        initData();
    }

    private void initData() {
        DbManager db = null;
        try {
            User checkUser = db.selector(User.class).findFirst();
            if (checkUser != null) {
                user = checkUser;
                userAdd.setText(checkUser.getAddress());
                userPhone.setText(checkUser.getPhone());
                password.setText(checkUser.getPassword());
                userName.setText(checkUser.getUsername());
                registDate.setText(checkUser.getDate());

                String oldPassword = checkUser.getPassword();
                int pwdLength = oldPassword.length();
                if (pwdLength <= 3) {
                    password.setText("***");
                } else {
                    String showPwd = "";
                    for (int i = 0; i < pwdLength - 3; i++) {
                        showPwd = showPwd + "*";
                    }

                    showPwd = showPwd + oldPassword.substring(pwdLength - 3, pwdLength - 1);
                    password.setText(showPwd);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
            T.s("Get user data failed");
        }
    }
}
