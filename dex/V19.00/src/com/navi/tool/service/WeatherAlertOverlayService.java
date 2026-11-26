package com.navi.tool.service;

import android.R;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.navi.tool.protocol.AutoProtocolManager;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class WeatherAlertOverlayService extends Service {
    public static final String ACTION_DISMISS_ALERT = "com.navi.tool.action.DISMISS_WEATHER_ALERT";
    public static final String ACTION_SHOW_ALERT = "com.navi.tool.action.SHOW_WEATHER_ALERT";
    private static final long DISMISS_AFTER_TTS_MS = 3000;
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_HEADLINE = "extra_headline";
    public static final String EXTRA_ICON_CODE = "extra_icon_code";
    public static final String EXTRA_TTS_TEXT = "extra_tts_text";
    private static final int MAX_DESCRIPTION_CHARS = 200;
    private static final long MAX_FALLBACK_DISMISS_MS = 20000;
    private static final int MAX_TTS_RETRY_COUNT = 3;
    private static final long MIN_FALLBACK_DISMISS_MS = 7000;
    private static final String TAG = "WeatherAlertOverlay";
    private static final long TTS_RETRY_DELAY_MS = 500;
    private final Runnable dismissRunnable = new w0(this, 0);
    private Handler mainHandler;
    private View overlayView;
    private WindowManager windowManager;

    private String buildBoundStateLog(List<String> list) {
        StringBuilder sb = new StringBuilder("{");
        for (int i7 = 0; i7 < list.size(); i7++) {
            String str = list.get(i7);
            if (i7 > 0) {
                sb.append(", ");
            }
            sb.append(str);
            sb.append("=");
            sb.append(AutoProtocolManager.getInstance().isServiceBound.get(str));
        }
        sb.append("}");
        return sb.toString();
    }

    private WindowManager.LayoutParams buildLayoutParams() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(g4.n.weather_alert_overlay_width);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 262664;
        layoutParams.format = -3;
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = -2;
        layoutParams.gravity = 49;
        layoutParams.y = dpToPx(72);
        layoutParams.x = 0;
        return layoutParams;
    }

    private void clearOverlayState() {
        WindowManager windowManager;
        this.mainHandler.removeCallbacks(this.dismissRunnable);
        View view = this.overlayView;
        if (view == null || view.getParent() == null || (windowManager = this.windowManager) == null) {
            return;
        }
        windowManager.removeView(this.overlayView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissOverlayAndStop() {
        clearOverlayState();
        stopSelf();
    }

    private int dpToPx(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    private long getAutoDismissDuration(String str) {
        return Math.max(MIN_FALLBACK_DISMISS_MS, Math.min(MAX_FALLBACK_DISMISS_MS, (str.length() * 280) + 4500));
    }

    private List<String> getEnabledAutoNaviPackages(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : AppDetectionUtils.getAutoNaviPackages(context)) {
            if (SharedPreferencesUtils.isTtsAppEnabled(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private boolean hasBoundAutoNaviPackage(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (Boolean.TRUE.equals(AutoProtocolManager.getInstance().isServiceBound.get(it.next()))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAutoVoiceServiceNotConnectedToast$2() {
        ToastHelper.show(getApplicationContext(), "当前高德语音服务未连接，无法播报");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverlay$0(View view) {
        dismissOverlayAndStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$speakByAutoProtocol$1(String str, int i7) {
        speakByAutoProtocol(str, i7 + 1);
    }

    private String limitOverlayText(String str, int i7) {
        if (TextUtils.isEmpty(str) || i7 <= 0 || str.length() <= i7) {
            return str;
        }
        return str.substring(0, i7) + "...";
    }

    private String sanitize(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
    }

    private void scheduleDismiss(long j7) {
        this.mainHandler.removeCallbacks(this.dismissRunnable);
        this.mainHandler.postDelayed(this.dismissRunnable, j7);
    }

    private void showAutoVoiceServiceNotConnectedToast() {
        this.mainHandler.post(new w0(this, 1));
    }

    private void showOverlay(String str, String str2, String str3) {
        if (!Settings.canDrawOverlays(this) || this.windowManager == null) {
            return;
        }
        if (this.overlayView == null) {
            View inflate = LayoutInflater.from(this).inflate(g4.q.view_weather_alert_overlay, (ViewGroup) null, false);
            this.overlayView = inflate;
            inflate.setOnClickListener(new j(this, 2));
        }
        ImageView imageView = (ImageView) this.overlayView.findViewById(g4.p.img_alert_icon);
        TextView textView = (TextView) this.overlayView.findViewById(g4.p.text_alert_headline);
        TextView textView2 = (TextView) this.overlayView.findViewById(g4.p.text_alert_description);
        if (TextUtils.isEmpty(str2)) {
            str2 = "天气预警提醒";
        }
        textView.setText(str2);
        textView2.setText(TextUtils.isEmpty(str3) ? "请注意防范" : limitOverlayText(str3, 200));
        int k02 = n1.z.k0(this, str);
        if (k02 != 0) {
            imageView.setImageResource(k02);
        } else {
            imageView.setImageResource(R.drawable.ic_dialog_alert);
        }
        if (this.overlayView.getParent() == null) {
            WindowManager.LayoutParams buildLayoutParams = buildLayoutParams();
            int i7 = buildLayoutParams.type;
            this.windowManager.addView(this.overlayView, buildLayoutParams);
        } else {
            WindowManager.LayoutParams buildLayoutParams2 = buildLayoutParams();
            int i8 = buildLayoutParams2.type;
            this.windowManager.updateViewLayout(this.overlayView, buildLayoutParams2);
        }
    }

    private void speakByAutoProtocol(String str, int i7) {
        try {
            List<String> autoNaviPackages = AppDetectionUtils.getAutoNaviPackages(this);
            List<String> enabledAutoNaviPackages = getEnabledAutoNaviPackages(this);
            Objects.toString(autoNaviPackages);
            Objects.toString(enabledAutoNaviPackages);
            buildBoundStateLog(autoNaviPackages);
            if (enabledAutoNaviPackages.isEmpty()) {
                return;
            }
            AutoProtocolManager.getInstance().initializeMultiple(this, enabledAutoNaviPackages);
            if (hasBoundAutoNaviPackage(enabledAutoNaviPackages)) {
                AutoProtocolManager.getInstance().playSoundTextToAll(str);
                enabledAutoNaviPackages.toString();
            } else if (i7 < 3) {
                buildBoundStateLog(enabledAutoNaviPackages);
                this.mainHandler.postDelayed(new h1.a(this, str, i7, 4), TTS_RETRY_DELAY_MS);
            } else {
                buildBoundStateLog(enabledAutoNaviPackages);
                showAutoVoiceServiceNotConnectedToast();
            }
        } catch (Exception unused) {
        }
    }

    private void startSpeak(String str) {
        this.mainHandler.removeCallbacks(this.dismissRunnable);
        if (TextUtils.isEmpty(str)) {
            scheduleDismiss(3000L);
        } else {
            speakByAutoProtocol(str, 0);
            scheduleDismiss(getAutoDismissDuration(str));
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.windowManager = (WindowManager) getSystemService("window");
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    @Override // android.app.Service
    public void onDestroy() {
        clearOverlayState();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent == null) {
            return 2;
        }
        String action = intent.getAction();
        if (ACTION_DISMISS_ALERT.equals(action)) {
            dismissOverlayAndStop();
            return 2;
        }
        if (!ACTION_SHOW_ALERT.equals(action)) {
            return 2;
        }
        String stringExtra = intent.getStringExtra(EXTRA_ICON_CODE);
        String sanitize = sanitize(intent.getStringExtra(EXTRA_HEADLINE));
        String sanitize2 = sanitize(intent.getStringExtra(EXTRA_DESCRIPTION));
        String sanitize3 = sanitize(intent.getStringExtra(EXTRA_TTS_TEXT));
        showOverlay(stringExtra, sanitize, sanitize2);
        startSpeak(sanitize3);
        return 2;
    }
}
