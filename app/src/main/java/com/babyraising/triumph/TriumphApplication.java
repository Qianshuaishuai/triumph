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

    public void saveAudioLevel(int level) {
        editor.putInt("audio-level", level);
        editor.commit();
    }

    public int getAudioLevel() {
        return sp.getInt("audio-level", 0);
    }

    public void saveCountDown(int level) {
        editor.putInt("count-down", level);
        editor.commit();
    }

    public int getCountDown() {
        return sp.getInt("count-down", 0);
    }

    public void saveBrightnessLevel(int level) {
        editor.putInt("brightness-level", level);
        editor.commit();
    }

    public int getBrightnessLevel() {
        return sp.getInt("brightness-level", 0);
    }

    public void saveUseAllTime(int useAllTime) {
        editor.putInt("use-all-time", useAllTime);
        editor.commit();
    }

    public int getUseAllTime() {
        return sp.getInt("use-all-time", 0);
    }

    public void saveCurrentTime(String currentTime) {
        editor.putString("current-time", currentTime);
        editor.commit();
    }

    public String getCurrentTime() {
        return sp.getString("current-time", "");
    }
}

