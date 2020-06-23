package com.babyraising.triumph.ui.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;
import com.babyraising.triumph.bean.User;
import com.babyraising.triumph.ui.user.RegisterActivity;
import com.babyraising.triumph.ui.user.SignInActivity;
import com.babyraising.triumph.util.T;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_info)
public class InfoActivity extends BaseActivity {

    private User user = null;

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
        if (user == null) {
            T.s("You first use,Please register first!");
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        try {
            DbManager db = x.getDb(((TriumphApplication) getApplication()).getDaoConfig());
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
