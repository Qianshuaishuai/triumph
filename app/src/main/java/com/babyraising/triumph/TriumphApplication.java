package com.babyraising.triumph;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.babyraising.triumph.bean.BigMode;
import com.babyraising.triumph.bean.Motor;
import com.babyraising.triumph.util.T;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        initModeConfig();
        initBigModeConfig();
        initMotorConfig();
    }

    private void initModeConfig() {
        List<String> checkList = getModeList();
        if (checkList == null || checkList.size() == 0) {
            List<String> newList = new ArrayList<>();
            newList.add("A: Simple Mode");
            newList.add("B: Holstered Mode");
            newList.add("C: Unholstered Mode");
            newList.add("D: User Upload Mode");
            setModeList(newList);
        }
    }

    private void initBigModeConfig() {
        int index = getBigModeIndex();
        List<BigMode> bigModeList = getBigModeList();
        if (index >= 0 && bigModeList != null && bigModeList.size() > 0) {
            setMotorList(bigModeList.get(index).getMotorList());
        }
    }

    private void initMotorConfig() {
        List<Motor> checkList = getMotorList();
        if (checkList == null || checkList.size() == 0) {
            List<Motor> newList = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                Motor motor = new Motor();
                motor.setMode(0);
                motor.setName("SN");
                motor.setStatus(1);
                newList.add(motor);
            }
            setMotorList(newList);
        }
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

    public List<Motor> getMotorList() {
        return gson.fromJson(sp.getString("motor-list", ""), new TypeToken<List<Motor>>() {
        }.getType());
    }

    public void setMotorList(List<Motor> list) {
        String beanString = gson.toJson(list);
        editor.putString("motor-list", beanString);
        editor.commit();
    }

    public List<BigMode> getBigModeList() {
        return gson.fromJson(sp.getString("bigMode-list", ""), new TypeToken<List<BigMode>>() {
        }.getType());
    }

    public void setBigModeList(List<BigMode> list) {
        String beanString = gson.toJson(list);
        editor.putString("bigMode-list", beanString);
        editor.commit();
    }

    public List<String> getModeList() {
        return gson.fromJson(sp.getString("mode-list", ""), new TypeToken<List<String>>() {
        }.getType());
    }

    public void setModeList(List<String> list) {
        String beanString = gson.toJson(list);
        editor.putString("mode-list", beanString);
        editor.commit();
    }


    public void saveBigModeIndex(int index) {
        editor.putInt("big-mode-index", index);
        editor.commit();
    }

    public int getBigModeIndex() {
        return sp.getInt("big-mode-index", 0);
    }
}

