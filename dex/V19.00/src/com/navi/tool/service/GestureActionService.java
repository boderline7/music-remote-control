package com.navi.tool.service;

import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GestureActionService extends Service {
    private static final String CHANNEL_ID = "GestureActionService";
    private static final int NOTIFICATION_ID = 2001;
    private static final String PKG_ADB_SWITCH = "com.navi.tool.action.adbswitch";
    private static final String TAG = "GestureActionService";
    private static boolean isServiceRunning;
    private WindowManager.LayoutParams animationParams;
    private View animationView;
    private float downX;
    private float downY;
    private WindowManager.LayoutParams mirrorTriggerParams;
    private View mirrorTriggerView;
    private LinearLayout panelContainer;
    private LinearLayout panelIconContainer;
    private WindowManager.LayoutParams panelParams;
    private Handler panelTimeoutHandler;
    private final Runnable panelTimeoutRunnable = new Runnable() { // from class: com.navi.tool.service.GestureActionService.1
        @Override // java.lang.Runnable
        public void run() {
            GestureActionService.this.hidePanelView();
        }
    };
    private View panelView;
    private boolean tracking;
    private WindowManager.LayoutParams triggerParams;
    private View triggerView;
    private WindowManager windowManager;

    public class GestureAnimationView extends View {
        private ValueAnimator animator;
        private int direction;
        private Interpolator interpolator;
        private boolean isRightSide;
        private Paint paint;
        private Path path;
        private float progress;

        public GestureAnimationView(Context context) {
            super(context);
            this.isRightSide = false;
            Paint paint = new Paint(1);
            this.paint = paint;
            paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(0);
            this.path = new Path();
            this.interpolator = new OvershootInterpolator(1.5f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$startAnimation$0(ValueAnimator valueAnimator) {
            this.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.progress <= CropImageView.DEFAULT_ASPECT_RATIO) {
                return;
            }
            int width = getWidth();
            int height = getHeight();
            this.path.reset();
            float f7 = this.progress;
            this.paint.setAlpha((int) ((f7 > 0.7f ? 1.0f - ((f7 - 0.7f) / 0.3f) : 1.0f) * 255.0f));
            int i7 = this.direction;
            if (i7 == 1) {
                if (this.isRightSide) {
                    float f8 = width;
                    this.paint.setShader(new LinearGradient(f8, CropImageView.DEFAULT_ASPECT_RATIO, (1.0f - this.progress) * f8, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{-1289644301, 2201331}, (float[]) null, Shader.TileMode.CLAMP));
                    float f9 = (1.0f - this.progress) * f8;
                    this.path.moveTo(f8, CropImageView.DEFAULT_ASPECT_RATIO);
                    float f10 = height;
                    this.path.quadTo(f9 - ((this.progress * f8) * 0.5f), f10 / 2.0f, f8, f10);
                    this.path.close();
                } else {
                    float f11 = width;
                    this.paint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f11 * this.progress, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{-1289644301, 2201331}, (float[]) null, Shader.TileMode.CLAMP));
                    float f12 = f11 * this.progress;
                    this.path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                    float f13 = height;
                    this.path.quadTo(f12 * 1.5f, f13 / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, f13);
                    this.path.close();
                }
            } else if (i7 == 2) {
                float f14 = height;
                this.paint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, f14, CropImageView.DEFAULT_ASPECT_RATIO, (1.0f - this.progress) * f14, new int[]{-1289644301, 2201331}, (float[]) null, Shader.TileMode.CLAMP));
                float f15 = (1.0f - this.progress) * f14;
                this.path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, f14);
                float f16 = width;
                this.path.lineTo(f16, f14);
                this.path.lineTo(f16, f14);
                float f17 = f16 / 2.0f;
                this.path.quadTo(f17, f14 - ((f14 - f15) * 1.5f), CropImageView.DEFAULT_ASPECT_RATIO, f14);
                this.path.reset();
                this.path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, f14);
                this.path.lineTo(f16, f14);
                this.path.quadTo(f17, (f14 - (this.progress * f14)) - (0.2f * f14), CropImageView.DEFAULT_ASPECT_RATIO, f14);
                this.path.close();
            } else if (i7 == 3) {
                float f18 = height;
                this.paint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f18 * this.progress, new int[]{-1289644301, 2201331}, (float[]) null, Shader.TileMode.CLAMP));
                float f19 = this.progress * f18;
                this.path.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                float f20 = width;
                this.path.lineTo(f20, CropImageView.DEFAULT_ASPECT_RATIO);
                this.path.quadTo(f20 / 2.0f, (f18 * 0.2f) + f19, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                this.path.close();
            }
            canvas.drawPath(this.path, this.paint);
        }

        public void startAnimation(int i7, boolean z6) {
            this.direction = i7;
            this.isRightSide = z6;
            ValueAnimator valueAnimator = this.animator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.animator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.animator = ofFloat;
            ofFloat.setDuration(500L);
            this.animator.setInterpolator(this.interpolator);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.navi.tool.service.k
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    GestureActionService.GestureAnimationView.this.lambda$startAnimation$0(valueAnimator2);
                }
            });
            this.animator.start();
        }
    }

    private void addAppToPanel(LinearLayout linearLayout, final String str, PackageManager packageManager, int i7, int i8, int i9, boolean z6) {
        CharSequence applicationLabel;
        Drawable drawable;
        if ("com.navi.tool.fileassistant".equals(str)) {
            int i10 = g4.o.ic_folder_yellow;
            Object obj = g0.g.f6423a;
            drawable = g0.c.b(this, i10);
            applicationLabel = "文件助手";
        } else if ("com.navi.tool.permissionassistant".equals(str)) {
            int i11 = g4.o.ic_security;
            Object obj2 = g0.g.f6423a;
            drawable = g0.c.b(this, i11);
            applicationLabel = "权限助手";
        } else if ("com.navi.tool.desktopassistant".equals(str)) {
            int i12 = g4.o.ic_dashboard;
            Object obj3 = g0.g.f6423a;
            drawable = g0.c.b(this, i12);
            applicationLabel = "桌面助手";
        } else if (PKG_ADB_SWITCH.equals(str)) {
            int i13 = g4.o.ic_adb_switch;
            Object obj4 = g0.g.f6423a;
            drawable = g0.c.b(this, i13);
            applicationLabel = "ADB界面";
        } else {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
            applicationLabel = packageManager.getApplicationLabel(applicationInfo);
            drawable = applicationIcon;
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        String string = appPrefs.getString("app_custom_name_" + str, null);
        if (string != null && !string.isEmpty()) {
            applicationLabel = string;
        }
        LinearLayout linearLayout2 = new LinearLayout(this);
        int i14 = 1;
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(1);
        ImageView imageView = new ImageView(this);
        String string2 = appPrefs.getString("gesture_panel_app_icon_" + str, null);
        if (string2 != null && !string2.isEmpty()) {
            drawable = Drawable.createFromPath(string2);
        }
        imageView.setImageDrawable(drawable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i7, i7);
        layoutParams.topMargin = i8;
        int i15 = i8 / 2;
        layoutParams.bottomMargin = i15;
        layoutParams.leftMargin = i9;
        layoutParams.rightMargin = i9;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        linearLayout2.addView(imageView);
        if (z6) {
            TextView textView = new TextView(this);
            textView.setText(applicationLabel);
            textView.setTextColor(-1);
            textView.setTextSize(2, 14.0f);
            textView.setMaxLines(2);
            textView.setEllipsize(null);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.leftMargin = i9;
            layoutParams2.rightMargin = i9;
            layoutParams2.bottomMargin = i15;
            textView.setLayoutParams(layoutParams2);
            textView.setGravity(1);
            linearLayout2.addView(textView);
        }
        linearLayout2.setOnTouchListener(new h(this, i14));
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.service.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GestureActionService.this.lambda$addAppToPanel$4(str, view);
            }
        });
        linearLayout.addView(linearLayout2);
    }

    private void cancelPanelAutoHide() {
        Handler handler = this.panelTimeoutHandler;
        if (handler != null) {
            handler.removeCallbacks(this.panelTimeoutRunnable);
        }
    }

    private Notification createNotification() {
        e0.v vVar = new e0.v(this, "GestureActionService");
        vVar.f6327e = e0.v.c("手势动作已开启");
        vVar.f6328f = e0.v.c("左侧边缘手势已启用");
        vVar.f6341s.icon = g4.o.ic_info;
        vVar.d(2, true);
        vVar.f6331i = -2;
        return vVar.a();
    }

    private void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(new NotificationChannel("GestureActionService", "手势动作服务", 1));
        }
    }

    private int dp(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    private void ensureAnimationView() {
        if (this.animationView != null) {
            return;
        }
        this.animationView = new GestureAnimationView(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(dp(80), dp(200), 2038, 536, -3);
        this.animationParams = layoutParams;
        layoutParams.gravity = 8388659;
        WindowManager.LayoutParams layoutParams2 = this.triggerParams;
        if (layoutParams2 != null) {
            layoutParams.width = layoutParams2.width * 2;
            layoutParams.height = layoutParams2.height;
            layoutParams.x = layoutParams2.x;
            layoutParams.y = layoutParams2.y;
        } else {
            this.animationParams.y = getResources().getDisplayMetrics().heightPixels / 4;
        }
        try {
            this.windowManager.addView(this.animationView, this.animationParams);
        } catch (Exception unused) {
        }
    }

    private void ensureMirrorTriggerView() {
        if (this.mirrorTriggerView != null) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(d1.MEASURED_STATE_MASK);
        gradientDrawable.setCornerRadius(dp(4));
        linearLayout.setBackground(gradientDrawable);
        linearLayout.setClickable(true);
        linearLayout.setFocusable(false);
        linearLayout.setOnTouchListener(new h(this, 0));
        linearLayout.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        this.mirrorTriggerView = linearLayout;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(dp(80), dp(200), 2038, 520, -3);
        this.mirrorTriggerParams = layoutParams;
        layoutParams.gravity = 8388661;
        layoutParams.x = 0;
        layoutParams.y = 0;
        try {
            this.windowManager.addView(this.mirrorTriggerView, layoutParams);
        } catch (Exception unused) {
        }
    }

    private void ensurePanelView(boolean z6) {
        View view = this.panelView;
        if (view != null) {
            WindowManager.LayoutParams layoutParams = this.panelParams;
            if (layoutParams != null) {
                layoutParams.gravity = (z6 ? 8388613 : 8388611) | 48;
                try {
                    this.windowManager.updateViewLayout(view, layoutParams);
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(0);
        int i7 = 1;
        linearLayout.setClickable(true);
        linearLayout.setFocusable(false);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        int i8 = SharedPreferencesUtils.getAppPrefs(this).getInt("gesture_panel_alpha_percent", 50);
        if (i8 < 0) {
            i8 = 0;
        }
        if (i8 > 100) {
            i8 = 100;
        }
        gradientDrawable.setColor((((100 - i8) * 255) / 100) << 24);
        gradientDrawable.setCornerRadius(dp(8));
        linearLayout2.setBackground(gradientDrawable);
        linearLayout2.setPadding(0, 0, 0, 0);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(1);
        linearLayout3.setGravity(1);
        scrollView.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
        scrollView.setOnTouchListener(new h(this, 2));
        linearLayout2.addView(scrollView, new LinearLayout.LayoutParams(-1, -1));
        View view2 = new View(this);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (z6) {
            linearLayout.addView(view2, layoutParams2);
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(dp(100), -1));
        } else {
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(dp(100), -1));
            linearLayout.addView(view2, layoutParams2);
        }
        this.panelContainer = linearLayout2;
        this.panelIconContainer = linearLayout3;
        this.panelView = linearLayout;
        view2.setOnClickListener(new j(this, i7));
        WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(-1, -1, 2038, 520, -3);
        this.panelParams = layoutParams3;
        layoutParams3.gravity = (z6 ? 8388613 : 8388611) | 48;
        layoutParams3.x = 0;
        layoutParams3.y = 0;
        try {
            this.windowManager.addView(this.panelView, layoutParams3);
        } catch (Exception unused2) {
        }
    }

    public static boolean ensureRunning(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (isServiceRunning) {
                return false;
            }
            Context applicationContext = context.getApplicationContext();
            if (!SharedPreferencesUtils.getAppPrefs(applicationContext).getBoolean("gesture_actions_enabled", false) || !Settings.canDrawOverlays(applicationContext)) {
                return false;
            }
            start(applicationContext);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private void ensureTriggerView() {
        if (this.triggerView != null) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(d1.MEASURED_STATE_MASK);
        gradientDrawable.setCornerRadius(dp(4));
        linearLayout.setBackground(gradientDrawable);
        linearLayout.setClickable(true);
        linearLayout.setFocusable(false);
        linearLayout.setOnTouchListener(new h(this, 3));
        linearLayout.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        this.triggerView = linearLayout;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(dp(80), dp(200), 2038, 520, -3);
        this.triggerParams = layoutParams;
        layoutParams.gravity = 8388659;
        layoutParams.x = 0;
        layoutParams.y = 0;
        try {
            this.windowManager.addView(this.triggerView, layoutParams);
        } catch (Exception unused) {
        }
    }

    private List<String> getPanelPackages(SharedPreferences sharedPreferences) {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z6 = sharedPreferences.getBoolean("file_assistant_enabled", false);
        boolean z7 = sharedPreferences.getBoolean("permission_assistant_enabled", false);
        if (z6 && sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.fileassistant", false)) {
            linkedHashSet.add("com.navi.tool.fileassistant");
        }
        if (z7 && sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.permissionassistant", false)) {
            linkedHashSet.add("com.navi.tool.permissionassistant");
        }
        if (sharedPreferences.getBoolean("desktop_assistant_enabled", false) && sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.desktopassistant", false)) {
            linkedHashSet.add("com.navi.tool.desktopassistant");
        }
        if (sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.action.adbswitch", true)) {
            linkedHashSet.add(PKG_ADB_SWITCH);
        }
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            if (sharedPreferences.getBoolean("gesture_panel_app_" + str, false)) {
                linkedHashSet.add(str);
            }
        }
        ArrayList arrayList = new ArrayList();
        String string = sharedPreferences.getString("gesture_panel_app_order", s5.h0.FRAGMENT_ENCODE_SET);
        if (string != null && !string.isEmpty()) {
            for (String str2 : string.split(",")) {
                String trim = str2.trim();
                if (!trim.isEmpty() && linkedHashSet.contains(trim) && !arrayList.contains(trim)) {
                    arrayList.add(trim);
                }
            }
        }
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            String str3 = (String) it2.next();
            if (!arrayList.contains(str3)) {
                arrayList.add(str3);
            }
        }
        if (string != null && !string.isEmpty()) {
            for (String str4 : string.split(",")) {
                if (PKG_ADB_SWITCH.equals(str4.trim())) {
                    break;
                }
            }
        }
        if (arrayList.contains(PKG_ADB_SWITCH)) {
            arrayList.remove(PKG_ADB_SWITCH);
            arrayList.add(0, PKG_ADB_SWITCH);
        }
        return arrayList;
    }

    private void handleGesture(float f7, float f8, float f9, float f10, boolean z6) {
        float f11 = f9 - f7;
        float f12 = f10 - f8;
        float abs = Math.abs(f11);
        float abs2 = Math.abs(f12);
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        int i7 = appPrefs.getInt("gesture_sensitivity", 4);
        if (i7 < 1) {
            i7 = 1;
        }
        if (i7 > 5) {
            i7 = 5;
        }
        float dp = dp(z1.c.INVOKE_INTERFACE_RANGE) - ((i7 - 1) * dp(20));
        if (abs >= dp || abs2 >= dp) {
            boolean z7 = appPrefs.getBoolean("gesture_animation_enabled", false);
            if (abs <= abs2) {
                if (f12 < CropImageView.DEFAULT_ASPECT_RATIO) {
                    if (appPrefs.getBoolean("gesture_up_enabled", false)) {
                        if (z7) {
                            showGestureAnimation(2, z6);
                        }
                        performAction(appPrefs.getInt("gesture_up_action", 2), z6);
                        return;
                    }
                    return;
                }
                if (appPrefs.getBoolean("gesture_down_enabled", false)) {
                    if (z7) {
                        showGestureAnimation(3, z6);
                    }
                    performAction(appPrefs.getInt("gesture_down_action", 3), z6);
                    return;
                }
                return;
            }
            if (z6) {
                if (f11 >= CropImageView.DEFAULT_ASPECT_RATIO || !appPrefs.getBoolean("gesture_right_enabled", false)) {
                    return;
                }
                if (z7) {
                    showGestureAnimation(1, z6);
                }
                performAction(appPrefs.getInt("gesture_right_action", 1), z6);
                return;
            }
            if (f11 <= CropImageView.DEFAULT_ASPECT_RATIO || !appPrefs.getBoolean("gesture_right_enabled", false)) {
                return;
            }
            if (z7) {
                showGestureAnimation(1, z6);
            }
            performAction(appPrefs.getInt("gesture_right_action", 1), z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePanelView() {
        View view = this.panelView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public static boolean isServiceRunning() {
        return isServiceRunning;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addAppToPanel$3(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            cancelPanelAutoHide();
            return false;
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return false;
        }
        schedulePanelAutoHide();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addAppToPanel$4(String str, View view) {
        launchPackageMainActivity(str);
        hidePanelView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$ensurePanelView$0(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            cancelPanelAutoHide();
            return false;
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return false;
        }
        schedulePanelAutoHide();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensurePanelView$1(View view) {
        hidePanelView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePanelLayoutFromPrefs$2(View view) {
        hidePanelView();
    }

    private void launchPackageMainActivity(String str) {
        ResolveInfo resolveInfo;
        if ("com.navi.tool.fileassistant".equals(str)) {
            Intent intent = new Intent(this, (Class<?>) FileManagerActivity.class);
            intent.addFlags(872546304);
            startActivity(intent);
            return;
        }
        if ("com.navi.tool.permissionassistant".equals(str)) {
            Intent intent2 = new Intent(this, (Class<?>) PermissionManagerActivity.class);
            intent2.addFlags(872546304);
            startActivity(intent2);
            return;
        }
        if ("com.navi.tool.desktopassistant".equals(str)) {
            Intent intent3 = new Intent(this, (Class<?>) DesktopAssistantActivity.class);
            intent3.addFlags(872546304);
            startActivity(intent3);
            return;
        }
        if (PKG_ADB_SWITCH.equals(str)) {
            AppDetectionUtils.launchAdbSwitch(this);
            return;
        }
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent4 = new Intent("android.intent.action.MAIN");
            intent4.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent4, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                } else {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo.packageName.equals(str)) {
                        break;
                    }
                }
            }
            if (resolveInfo != null) {
                Intent intent5 = new Intent("android.intent.action.MAIN");
                intent5.addCategory("android.intent.category.LAUNCHER");
                intent5.setClassName(str, resolveInfo.activityInfo.name);
                intent5.addFlags(872546304);
                startActivity(intent5);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onTriggerTouch(View view, MotionEvent motionEvent) {
        boolean z6 = view == this.mirrorTriggerView;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.tracking = true;
            this.downX = motionEvent.getRawX();
            this.downY = motionEvent.getRawY();
        } else if (actionMasked == 1 || actionMasked == 3) {
            if (this.tracking) {
                handleGesture(this.downX, this.downY, motionEvent.getRawX(), motionEvent.getRawY(), z6);
            }
            this.tracking = false;
        }
        return true;
    }

    private void performAction(int i7, boolean z6) {
        if (i7 == 1) {
            GestureAccessibilityService.performBack();
            return;
        }
        if (i7 == 2) {
            showAppPanel(z6);
        } else if (i7 == 3) {
            GestureAccessibilityService.performHome();
        } else if (i7 == 4) {
            launchPackageMainActivity("com.navi.tool.desktopassistant");
        }
    }

    public static void previewPanel(Context context, boolean z6) {
        Intent intent = new Intent(context, (Class<?>) GestureActionService.class);
        intent.setAction(z6 ? "preview_panel_show" : "preview_panel_hide");
        startServicePreferred(context, intent);
    }

    public static void previewTrigger(Context context, boolean z6) {
        Intent intent = new Intent(context, (Class<?>) GestureActionService.class);
        intent.setAction(z6 ? "preview_trigger_show" : "preview_trigger_hide");
        startServicePreferred(context, intent);
    }

    private void removeAnimationView() {
        View view = this.animationView;
        if (view != null) {
            try {
                this.windowManager.removeView(view);
            } catch (Exception unused) {
            }
            this.animationView = null;
            this.animationParams = null;
        }
    }

    private void removeMirrorTriggerView() {
        View view = this.mirrorTriggerView;
        if (view != null) {
            try {
                this.windowManager.removeView(view);
            } catch (Exception unused) {
            }
            this.mirrorTriggerView = null;
            this.mirrorTriggerParams = null;
        }
    }

    private void removePanelView() {
        View view = this.panelView;
        if (view != null) {
            try {
                this.windowManager.removeView(view);
            } catch (Exception unused) {
            }
            this.panelView = null;
            this.panelParams = null;
            this.panelContainer = null;
            this.panelIconContainer = null;
        }
    }

    private void removeTriggerView() {
        View view = this.triggerView;
        if (view != null) {
            try {
                this.windowManager.removeView(view);
            } catch (Exception unused) {
            }
            this.triggerView = null;
            this.triggerParams = null;
        }
    }

    private void schedulePanelAutoHide() {
        View view = this.panelView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        if (this.panelTimeoutHandler == null) {
            this.panelTimeoutHandler = new Handler(Looper.getMainLooper());
        }
        int i7 = SharedPreferencesUtils.getAppPrefs(this).getInt("gesture_panel_timeout_seconds", 2);
        if (i7 < 1) {
            i7 = 1;
        }
        if (i7 > 5) {
            i7 = 5;
        }
        this.panelTimeoutHandler.removeCallbacks(this.panelTimeoutRunnable);
        this.panelTimeoutHandler.postDelayed(this.panelTimeoutRunnable, i7 * 1000);
    }

    private void setTriggerPreviewVisible(boolean z6) {
        View view = this.triggerView;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (view != null) {
            view.setAlpha(z6 ? 0.7f : 0.0f);
        }
        View view2 = this.mirrorTriggerView;
        if (view2 == null || view2.getVisibility() != 0) {
            return;
        }
        View view3 = this.mirrorTriggerView;
        if (z6) {
            f7 = 0.7f;
        }
        view3.setAlpha(f7);
    }

    private void showAppPanel(boolean z6) {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        List<String> panelPackages = getPanelPackages(appPrefs);
        if (panelPackages.isEmpty()) {
            return;
        }
        ensurePanelView(z6);
        updatePanelLayoutFromPrefs(z6);
        LinearLayout linearLayout = this.panelIconContainer;
        if (linearLayout == null) {
            linearLayout = this.panelContainer;
        }
        LinearLayout linearLayout2 = linearLayout;
        if (linearLayout2 == null) {
            return;
        }
        linearLayout2.removeAllViews();
        PackageManager packageManager = getPackageManager();
        int dp = dp(12);
        int dp2 = dp(6);
        LinearLayout linearLayout3 = this.panelContainer;
        int i7 = (linearLayout3 == null || linearLayout3.getLayoutParams() == null) ? 0 : this.panelContainer.getLayoutParams().width;
        if (i7 <= 0) {
            i7 = dp(60);
        }
        int i8 = i7 - (dp2 * 2);
        int dp3 = dp(32);
        int i9 = i8 < dp3 ? dp3 : i8;
        boolean z7 = appPrefs.getBoolean("gesture_panel_show_app_name", false);
        Iterator<String> it = panelPackages.iterator();
        while (it.hasNext()) {
            try {
                addAppToPanel(linearLayout2, it.next(), packageManager, i9, dp, dp2, z7);
            } catch (Exception unused) {
            }
        }
        this.panelView.setVisibility(0);
        this.panelView.setSystemUiVisibility(5894);
        WindowManager.LayoutParams layoutParams = this.panelParams;
        if (layoutParams != null) {
            layoutParams.flags = 768 & (-9);
        }
        try {
            this.windowManager.updateViewLayout(this.panelView, layoutParams);
        } catch (Exception unused2) {
        }
        schedulePanelAutoHide();
    }

    private void showGestureAnimation(int i7, boolean z6) {
        WindowManager.LayoutParams layoutParams;
        ensureAnimationView();
        View view = this.animationView;
        if (view != null && (layoutParams = this.animationParams) != null) {
            if (z6) {
                layoutParams.gravity = 8388661;
                WindowManager.LayoutParams layoutParams2 = this.mirrorTriggerParams;
                if (layoutParams2 != null) {
                    layoutParams.width = layoutParams2.width * 2;
                    layoutParams.height = layoutParams2.height;
                    layoutParams.x = 0;
                    layoutParams.y = layoutParams2.y;
                }
            } else {
                layoutParams.gravity = 8388659;
                WindowManager.LayoutParams layoutParams3 = this.triggerParams;
                if (layoutParams3 != null) {
                    layoutParams.width = layoutParams3.width * 2;
                    layoutParams.height = layoutParams3.height;
                    layoutParams.x = 0;
                    layoutParams.y = layoutParams3.y;
                }
            }
            try {
                this.windowManager.updateViewLayout(view, layoutParams);
            } catch (Exception unused) {
            }
        }
        View view2 = this.animationView;
        if (view2 instanceof GestureAnimationView) {
            ((GestureAnimationView) view2).startAnimation(i7, z6);
        }
    }

    private void showPanelPreview() {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        List<String> panelPackages = getPanelPackages(appPrefs);
        ensurePanelView(false);
        updatePanelLayoutFromPrefs(false);
        LinearLayout linearLayout = this.panelIconContainer;
        if (linearLayout == null) {
            linearLayout = this.panelContainer;
        }
        LinearLayout linearLayout2 = linearLayout;
        if (linearLayout2 == null) {
            return;
        }
        if (linearLayout2.getChildCount() == 0) {
            PackageManager packageManager = getPackageManager();
            int dp = dp(12);
            int dp2 = dp(6);
            LinearLayout linearLayout3 = this.panelContainer;
            int i7 = (linearLayout3 == null || linearLayout3.getLayoutParams() == null) ? 0 : this.panelContainer.getLayoutParams().width;
            if (i7 <= 0) {
                i7 = dp(60);
            }
            int i8 = i7 - (dp2 * 2);
            int dp3 = dp(32);
            int i9 = i8 < dp3 ? dp3 : i8;
            boolean z6 = appPrefs.getBoolean("gesture_panel_show_app_name", false);
            Iterator<String> it = panelPackages.iterator();
            while (it.hasNext()) {
                try {
                    addAppToPanel(linearLayout2, it.next(), packageManager, i9, dp, dp2, z6);
                } catch (Exception unused) {
                }
            }
        }
        this.panelView.setVisibility(0);
        try {
            this.windowManager.updateViewLayout(this.panelView, this.panelParams);
        } catch (Exception unused2) {
        }
        schedulePanelAutoHide();
    }

    public static void start(Context context) {
        if (context == null) {
            return;
        }
        startServicePreferred(context, new Intent(context, (Class<?>) GestureActionService.class));
    }

    private static void startServicePreferred(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        try {
            try {
                context.startService(intent);
            } catch (Throwable unused) {
                context.startForegroundService(intent);
            }
        } catch (Throwable unused2) {
        }
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class<?>) GestureActionService.class));
    }

    public static void updatePanelConfig(Context context) {
        Intent intent = new Intent(context, (Class<?>) GestureActionService.class);
        intent.setAction("update_panel");
        startServicePreferred(context, intent);
    }

    private void updatePanelLayoutFromPrefs(boolean z6) {
        if (this.panelParams == null || this.panelContainer == null) {
            return;
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        int i7 = appPrefs.getInt("gesture_panel_width_percent", 50);
        int i8 = 0;
        if (i7 < 0) {
            i7 = 0;
        }
        if (i7 > 100) {
            i7 = 100;
        }
        int i9 = appPrefs.getInt("gesture_panel_alpha_percent", 50);
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 > 100) {
            i9 = 100;
        }
        int dp = dp(60);
        int dp2 = (((dp(z1.c.INVOKE_INTERFACE_RANGE) - dp) * i7) / 100) + dp;
        ViewGroup.LayoutParams layoutParams = this.panelContainer.getLayoutParams();
        layoutParams.width = dp2;
        layoutParams.height = -1;
        this.panelContainer.setLayoutParams(layoutParams);
        View view = this.panelView;
        if (view instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) view;
            if (linearLayout.getChildCount() == 2) {
                View childAt = linearLayout.getChildAt(0);
                View childAt2 = linearLayout.getChildAt(1);
                boolean z7 = childAt2 == this.panelContainer;
                if (z7 != z6) {
                    linearLayout.removeAllViews();
                    if (!z7) {
                        childAt = childAt2;
                    }
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 1.0f);
                    if (z6) {
                        linearLayout.addView(childAt, layoutParams2);
                        linearLayout.addView(this.panelContainer);
                    } else {
                        linearLayout.addView(this.panelContainer);
                        linearLayout.addView(childAt, layoutParams2);
                    }
                    childAt.setOnClickListener(new j(this, i8));
                }
            }
        }
        int dp3 = dp(6);
        int i10 = dp2 - (dp3 * 2);
        int dp4 = dp(32);
        if (i10 < dp4) {
            i10 = dp4;
        }
        LinearLayout linearLayout2 = this.panelIconContainer;
        if (linearLayout2 == null) {
            linearLayout2 = this.panelContainer;
        }
        if (linearLayout2 != null) {
            for (int i11 = 0; i11 < linearLayout2.getChildCount(); i11++) {
                View childAt3 = linearLayout2.getChildAt(i11);
                if (childAt3 instanceof ImageView) {
                    ViewGroup.LayoutParams layoutParams3 = childAt3.getLayoutParams();
                    LinearLayout.LayoutParams layoutParams4 = layoutParams3 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams3 : new LinearLayout.LayoutParams(i10, i10);
                    layoutParams4.width = i10;
                    layoutParams4.height = i10;
                    layoutParams4.leftMargin = dp3;
                    layoutParams4.rightMargin = dp3;
                    childAt3.setLayoutParams(layoutParams4);
                } else if (childAt3 instanceof LinearLayout) {
                    LinearLayout linearLayout3 = (LinearLayout) childAt3;
                    for (int i12 = 0; i12 < linearLayout3.getChildCount(); i12++) {
                        View childAt4 = linearLayout3.getChildAt(i12);
                        if (childAt4 instanceof ImageView) {
                            ViewGroup.LayoutParams layoutParams5 = childAt4.getLayoutParams();
                            LinearLayout.LayoutParams layoutParams6 = layoutParams5 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams5 : new LinearLayout.LayoutParams(i10, i10);
                            layoutParams6.width = i10;
                            layoutParams6.height = i10;
                            layoutParams6.leftMargin = dp3;
                            layoutParams6.rightMargin = dp3;
                            childAt4.setLayoutParams(layoutParams6);
                        }
                    }
                }
            }
        }
        if (this.panelContainer.getBackground() instanceof GradientDrawable) {
            ((GradientDrawable) this.panelContainer.getBackground()).setColor((((100 - i9) * 255) / 100) << 24);
        }
        try {
            this.windowManager.updateViewLayout(this.panelView, this.panelParams);
        } catch (Exception unused) {
        }
    }

    public static void updateTriggerConfig(Context context) {
        Intent intent = new Intent(context, (Class<?>) GestureActionService.class);
        intent.setAction("update_trigger");
        startServicePreferred(context, intent);
    }

    private void updateTriggerLayoutFromPrefs() {
        WindowManager.LayoutParams layoutParams;
        if (this.triggerParams == null || this.triggerView == null) {
            return;
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        int i7 = appPrefs.getInt("gesture_trigger_height_percent", 66);
        if (i7 < 10) {
            i7 = 10;
        }
        if (i7 > 100) {
            i7 = 100;
        }
        int i8 = appPrefs.getInt("gesture_trigger_width_percent", 50);
        if (i8 < 0) {
            i8 = 0;
        }
        if (i8 > 100) {
            i8 = 100;
        }
        int i9 = appPrefs.getInt("gesture_trigger_position_percent", 50);
        if (i9 < 0) {
            i9 = 0;
        }
        if (i9 > 100) {
            i9 = 100;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int i10 = displayMetrics.heightPixels;
        int dp = dp(20);
        int dp2 = (((dp(50) - dp) * i8) / 100) + dp;
        int i11 = (i7 * i10) / 100;
        if (i11 > i10) {
            i11 = i10;
        }
        int i12 = i10 / 4;
        if (i11 < i12) {
            i11 = i12;
        }
        int i13 = i10 - i11;
        int i14 = ((100 - i9) * i13) / 100;
        if (i14 < 0) {
            i14 = 0;
        }
        if (i14 + i11 <= i10) {
            i13 = i14;
        }
        WindowManager.LayoutParams layoutParams2 = this.triggerParams;
        layoutParams2.width = dp2;
        layoutParams2.height = i11;
        layoutParams2.x = 0;
        layoutParams2.y = i13;
        try {
            this.windowManager.updateViewLayout(this.triggerView, layoutParams2);
            View view = this.animationView;
            if (view != null && (layoutParams = this.animationParams) != null) {
                layoutParams.width = dp2 * 2;
                layoutParams.height = i11;
                layoutParams.x = 0;
                layoutParams.y = i13;
                this.windowManager.updateViewLayout(view, layoutParams);
            }
        } catch (Exception unused) {
        }
        if (!appPrefs.getBoolean("gesture_mirror_enabled", false)) {
            View view2 = this.mirrorTriggerView;
            if (view2 != null) {
                view2.setVisibility(8);
                return;
            }
            return;
        }
        ensureMirrorTriggerView();
        WindowManager.LayoutParams layoutParams3 = this.mirrorTriggerParams;
        layoutParams3.width = dp2;
        layoutParams3.height = i11;
        layoutParams3.x = 0;
        layoutParams3.y = i13;
        this.mirrorTriggerView.setVisibility(0);
        try {
            this.windowManager.updateViewLayout(this.mirrorTriggerView, this.mirrorTriggerParams);
        } catch (Exception unused2) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        isServiceRunning = true;
        this.windowManager = (WindowManager) getSystemService("window");
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
        ensureTriggerView();
        ensureMirrorTriggerView();
        updateTriggerLayoutFromPrefs();
        new Handler(getMainLooper()).postDelayed(new g4.k(1), 5000L);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        cancelPanelAutoHide();
        removeTriggerView();
        removeMirrorTriggerView();
        removeAnimationView();
        removePanelView();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent != null) {
            String action = intent.getAction();
            if ("update_trigger".equals(action)) {
                updateTriggerLayoutFromPrefs();
            } else if ("update_panel".equals(action)) {
                updatePanelLayoutFromPrefs(false);
            } else if ("preview_trigger_show".equals(action)) {
                setTriggerPreviewVisible(true);
            } else if ("preview_trigger_hide".equals(action)) {
                setTriggerPreviewVisible(false);
            } else if ("preview_panel_show".equals(action)) {
                showPanelPreview();
            } else if ("preview_panel_hide".equals(action)) {
                hidePanelView();
            }
        }
        return 1;
    }
}
