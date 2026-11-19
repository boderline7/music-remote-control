package com.luna.music.car;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 唯一入口：把主应用拉到前台，然后立刻退出。
 *
 * 本包存在的意义只是让「com.luna.music.car」出现在车机助手的音源候选里
 * （它按包名 com.luna.music.car → source 5 映射成「汽水音乐车机版」）。
 * 真正干活的播放器是 com.cloudmusic.car，它通过 com.luna.music.car.* 广播与车机助手直连。
 */
public class LaunchStub extends Activity {

    private static final String TARGET_PACKAGE = "com.cloudmusic.car";
    private static final String TARGET_ACTIVITY = "com.cloudmusic.car.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClassName(TARGET_PACKAGE, TARGET_ACTIVITY);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "未安装 com.cloudmusic.car", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
