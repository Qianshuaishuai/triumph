package com.babyraising.triumph.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.util.T;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

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
        if (password.getText().toString().equals(confirmPassword.getText().toString())) {
            T.s("Two passwords are inconsistent");
            return;
        }
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
}
