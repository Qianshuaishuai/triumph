package com.babyraising.triumph.ui.profile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.babyraising.triumph.R;
import com.babyraising.triumph.TriumphApplication;
import com.babyraising.triumph.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_system_setting)
public class SystemSettingActivity extends BaseActivity {

    private int RUNNING_REQUEST_CODE = 101;

    private int countDownValue = 0;
    private int audioLevelValue = 0;
    private int brightnessLevelValue = 0;

    private int audioLevelMax = 7;
    private float brightnessOffset = 25.5f;

    private AudioManager mAudioManager;

    @ViewInject(R.id.count_down_tv)
    private TextView countDown;

    @ViewInject(R.id.audio_level_tv)
    private TextView audioLevel;

    @ViewInject(R.id.brightness_level_tv)
    private TextView brightnessLevel;

    @Event(R.id.audio_level_down)
    private void audioLevelDown(View view) {
        if (audioLevelValue > 0) {
            audioLevelValue--;
        }

        audioLevel.setText("" + audioLevelValue);
        ((TriumphApplication) getApplication()).saveAudioLevel(audioLevelValue);
        setAppAudioLevel(audioLevelValue);
    }

    @Event(R.id.audio_level_up)
    private void audioLevelUp(View view) {
        if (audioLevelValue < audioLevelMax) {
            audioLevelValue++;
        }
        audioLevel.setText("" + audioLevelValue);
        ((TriumphApplication) getApplication()).saveAudioLevel(audioLevelValue);
        setAppAudioLevel(audioLevelValue);
    }

    @Event(R.id.brightness_level_up)
    private void brightnessLevelUp(View view) {
        if (brightnessLevelValue < 10) {
            brightnessLevelValue++;
        }
        brightnessLevel.setText("" + brightnessLevelValue);
        ((TriumphApplication) getApplication()).saveBrightnessLevel(brightnessLevelValue);

        setWindowBrightness(brightnessOffset * brightnessLevelValue);
    }

    @Event(R.id.brightness_level_down)
    private void brightnessLevelDown(View view) {
        if (brightnessLevelValue > 0) {
            brightnessLevelValue--;
        }

        brightnessLevel.setText("" + brightnessLevelValue);
        ((TriumphApplication) getApplication()).saveBrightnessLevel(brightnessLevelValue);

        setWindowBrightness(brightnessOffset * brightnessLevelValue);
    }

    @Event(R.id.count_down_up)
    private void countDownUp(View view) {
        if (countDownValue < 10) {
            countDownValue++;
        }
        countDown.setText("" + countDownValue);
        ((TriumphApplication) getApplication()).saveCountDown(countDownValue);
    }

    @Event(R.id.count_down_down)
    private void countDownDown(View view) {
        if (countDownValue > 0) {
            countDownValue--;
        }

        countDown.setText("" + countDownValue);
        ((TriumphApplication) getApplication()).saveCountDown(countDownValue);
    }

    @Event(R.id.back)
    private void back(View view) {
        finish();
    }

    @Event(R.id.confirm)
    private void confirm(View view) {
        finish();
        setResult(RUNNING_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        countDownValue = ((TriumphApplication) getApplication()).getCountDown();
        audioLevelValue = ((TriumphApplication) getApplication()).getAudioLevel();
        brightnessLevelValue = ((TriumphApplication) getApplication()).getBrightnessLevel();

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioLevelMax = getMaxAudioLevel();

        audioLevelValue = getAudioLevel();

        brightnessLevelValue = (int) (getSystemBrightness() / brightnessOffset);

        brightnessLevel.setText("" + brightnessLevelValue);
        audioLevel.setText("" + audioLevelValue);
        countDown.setText("" + countDownValue);
    }

    private void setWindowBrightness(float brightness) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = brightness / 255.0f;
        window.setAttributes(lp);
    }

    /**
     * 获取系统亮度
     */
    public int getSystemBrightness() {
        return Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);
    }

    public void setAppAudioLevel(int audioLevel) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, audioLevel, 0);
    }

    public int getAudioLevel() {
        return mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public int getMaxAudioLevel() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
    }
}
