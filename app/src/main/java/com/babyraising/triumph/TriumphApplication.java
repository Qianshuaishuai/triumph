package com.babyraising.triumph;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.babyraising.triumph.util.T;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

public class TriumphApplication extends Application {
    private String TAG = "TriumphApplication";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private DbManager.DaoConfig daoConfig;

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
        initDb();
    }

    private void initDb() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName("triumph.db")
                // 不设置dbDir时, 默认存储在app的私有目录.
                .setDbDir(new File("/sdcard"))
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });
    }

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
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

