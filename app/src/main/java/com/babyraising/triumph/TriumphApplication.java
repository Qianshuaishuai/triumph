package com.babyraising.triumph;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.babyraising.triumph.util.T;
import com.google.gson.Gson;

import org.xutils.x;

public class TriumphApplication extends Application {
    private String TAG = "TriumphApplication";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        T.init(this);
        if (Constant.DEBUG) {
            Log.d(TAG, "init Xuitils");
        }

        initSp();
    }

    private void initSp() {
        sp = getSharedPreferences("prod", Context.MODE_PRIVATE);
        editor = sp.edit();
        gson = new Gson();
    }

    public void saveFirstGo(int mode) {
        editor.putInt("first", mode);
        editor.commit();
    }

    public int getFirstGo() {
        return sp.getInt("first", 0);
    }
}

