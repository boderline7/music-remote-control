package com.navi.tool.ui.cast;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.navi.tool.MainActivity;
import com.navi.tool.service.m;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import e0.v;
import g4.s;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ScreenCastingFloatingService extends Service {
    private static final String TAG = "ScreenCastingFloatingService";
    private boolean canDrag;
    private GestureDetector gestureDetector;
    private Handler handler;
    private ImageView iconView;
    private WindowManager.LayoutParams params;
    private boolean toggling;
    private WindowManager windowManager;

    private void createNotification() {
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("screen_casting", "投屏应用", 2));
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 67108864 | 134217728);
        v vVar = new v(this, "screen_casting");
        vVar.f6327e = v.c("投屏应用运行中");
        vVar.f6341s.icon = s.ic_launcher;
        vVar.f6329g = activity;
        startForeground(3, vVar.a());
    }

    private int findSecondaryDisplayId() {
        for (Display display : ((DisplayManager) getSystemService("display")).getDisplays()) {
            if (display.getDisplayId() != 0) {
                return display.getDisplayId();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupIconView$0(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2) {
            if (this.canDrag) {
                this.params.x = ((int) motionEvent.getRawX()) - (size() / 2);
                this.params.y = ((int) motionEvent.getRawY()) - (size() / 2);
                this.windowManager.updateViewLayout(this.iconView, this.params);
            }
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            if (this.canDrag) {
                WindowManager.LayoutParams layoutParams = this.params;
                SharedPreferencesUtils.setScreenCastingIconPosition(this, layoutParams.x, layoutParams.y);
            }
            this.canDrag = false;
        }
        this.gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleScreenForSelectedApp$1(ActivityOptions activityOptions, int i7, Intent intent, String str) {
        try {
            activityOptions.setLaunchDisplayId(i7);
            intent.addFlags(738328576);
            startActivity(intent, activityOptions.toBundle());
            SharedPreferencesUtils.setScreenCastingAppState(this, str, "instrument");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleScreenForSelectedApp$2() {
        this.toggling = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleScreenForSelectedApp$3() {
        this.toggling = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleScreenForSelectedApp$4() {
        this.toggling = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$toggleScreenForSelectedApp$5() {
        this.toggling = false;
    }

    private /* synthetic */ void lambda$toggleScreenForSelectedApp$6() {
        this.toggling = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateToTab() {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.putExtra("target_tab", "screen_casting");
        intent.addFlags(872415232);
        startActivity(intent);
    }

    private void sendClusterEnlargeCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"仪表导航\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void setupIconView() {
        this.iconView = new ImageView(this);
        int screenCastingIconSize = SharedPreferencesUtils.getScreenCastingIconSize(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(screenCastingIconSize, screenCastingIconSize, 2038, 520, -3);
        this.params = layoutParams;
        layoutParams.x = SharedPreferencesUtils.getScreenCastingIconX(this);
        this.params.y = SharedPreferencesUtils.getScreenCastingIconY(this);
        WindowManager.LayoutParams layoutParams2 = this.params;
        layoutParams2.gravity = 8388659;
        this.windowManager.addView(this.iconView, layoutParams2);
        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.cast.ScreenCastingFloatingService.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                ScreenCastingFloatingService.this.canDrag = true;
            }
        });
        this.gestureDetector = gestureDetector;
        gestureDetector.setOnDoubleTapListener(new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.cast.ScreenCastingFloatingService.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                ScreenCastingFloatingService.this.navigateToTab();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                ScreenCastingFloatingService.this.toggleScreenForSelectedApp();
                return true;
            }
        });
        this.iconView.setOnTouchListener(new View.OnTouchListener() { // from class: com.navi.tool.ui.cast.b
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$setupIconView$0;
                lambda$setupIconView$0 = ScreenCastingFloatingService.this.lambda$setupIconView$0(view, motionEvent);
                return lambda$setupIconView$0;
            }
        });
    }

    private int size() {
        return SharedPreferencesUtils.getScreenCastingIconSize(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleScreenForSelectedApp() {
        String screenCastingSelectedPkg;
        ResolveInfo resolveInfo;
        if (this.toggling || (screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(this)) == null) {
            return;
        }
        String screenCastingAppState = SharedPreferencesUtils.getScreenCastingAppState(this, screenCastingSelectedPkg);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        final int i7 = 0;
        Iterator<ResolveInfo> it = getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                resolveInfo = null;
                break;
            } else {
                resolveInfo = it.next();
                if (resolveInfo.activityInfo.packageName.equals(screenCastingSelectedPkg)) {
                    break;
                }
            }
        }
        if (resolveInfo == null) {
            return;
        }
        final int i8 = 1;
        this.toggling = true;
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent2.setClassName(screenCastingSelectedPkg, resolveInfo.activityInfo.name);
        intent2.addFlags(268435456);
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        if (screenCastingAppState.equals("main")) {
            int findSecondaryDisplayId = findSecondaryDisplayId();
            if (findSecondaryDisplayId == -1) {
                intent2.addFlags(738328576);
                startActivity(intent2);
                SharedPreferencesUtils.setScreenCastingAppState(this, screenCastingSelectedPkg, "main");
                final int i9 = 2;
                this.handler.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.cast.a

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ ScreenCastingFloatingService f4976b;

                    {
                        this.f4976b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i10 = i9;
                        ScreenCastingFloatingService screenCastingFloatingService = this.f4976b;
                        switch (i10) {
                            case 0:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$2();
                                break;
                            case 1:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$3();
                                break;
                            case 2:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$4();
                                break;
                            default:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$5();
                                break;
                        }
                    }
                }, 1200L);
            } else if (SharedPreferencesUtils.isClusterEnlargeOnCasting(this)) {
                sendClusterEnlargeCommand(this);
                this.handler.postDelayed(new m(this, makeBasic, findSecondaryDisplayId, intent2, screenCastingSelectedPkg, 1), 2000L);
                this.handler.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.cast.a

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ ScreenCastingFloatingService f4976b;

                    {
                        this.f4976b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i10 = i7;
                        ScreenCastingFloatingService screenCastingFloatingService = this.f4976b;
                        switch (i10) {
                            case 0:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$2();
                                break;
                            case 1:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$3();
                                break;
                            case 2:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$4();
                                break;
                            default:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$5();
                                break;
                        }
                    }
                }, 3200L);
            } else {
                makeBasic.setLaunchDisplayId(findSecondaryDisplayId);
                intent2.addFlags(738328576);
                startActivity(intent2, makeBasic.toBundle());
                SharedPreferencesUtils.setScreenCastingAppState(this, screenCastingSelectedPkg, "instrument");
                this.handler.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.cast.a

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ ScreenCastingFloatingService f4976b;

                    {
                        this.f4976b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i10 = i8;
                        ScreenCastingFloatingService screenCastingFloatingService = this.f4976b;
                        switch (i10) {
                            case 0:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$2();
                                break;
                            case 1:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$3();
                                break;
                            case 2:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$4();
                                break;
                            default:
                                screenCastingFloatingService.lambda$toggleScreenForSelectedApp$5();
                                break;
                        }
                    }
                }, 1200L);
            }
        } else {
            makeBasic.setLaunchDisplayId(0);
            intent2.addFlags(738328576);
            startActivity(intent2, makeBasic.toBundle());
            SharedPreferencesUtils.setScreenCastingAppState(this, screenCastingSelectedPkg, "main");
            final int i10 = 3;
            this.handler.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.cast.a

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ScreenCastingFloatingService f4976b;

                {
                    this.f4976b = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i102 = i10;
                    ScreenCastingFloatingService screenCastingFloatingService = this.f4976b;
                    switch (i102) {
                        case 0:
                            screenCastingFloatingService.lambda$toggleScreenForSelectedApp$2();
                            break;
                        case 1:
                            screenCastingFloatingService.lambda$toggleScreenForSelectedApp$3();
                            break;
                        case 2:
                            screenCastingFloatingService.lambda$toggleScreenForSelectedApp$4();
                            break;
                        default:
                            screenCastingFloatingService.lambda$toggleScreenForSelectedApp$5();
                            break;
                    }
                }
            }, 1200L);
        }
        updateIconImage();
    }

    private void updateIconAlpha() {
        this.iconView.setAlpha(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(1.0f, SharedPreferencesUtils.getScreenCastingIconAlpha(this) / 100.0f)));
    }

    private void updateIconImage() {
        Drawable drawable;
        String screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(this);
        try {
            drawable = screenCastingSelectedPkg != null ? getPackageManager().getApplicationIcon(screenCastingSelectedPkg) : getPackageManager().getApplicationIcon(getPackageName());
        } catch (Exception unused) {
            drawable = getResources().getDrawable(s.ic_launcher);
        }
        this.iconView.setImageDrawable(drawable);
    }

    private void updateIconSize() {
        int size = size();
        WindowManager.LayoutParams layoutParams = this.params;
        layoutParams.width = size;
        layoutParams.height = size;
        this.windowManager.updateViewLayout(this.iconView, layoutParams);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.windowManager = (WindowManager) getSystemService("window");
        this.handler = new Handler(getMainLooper());
        createNotification();
        setupIconView();
        updateIconImage();
        updateIconAlpha();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ImageView imageView = this.iconView;
        if (imageView != null) {
            this.windowManager.removeView(imageView);
            this.iconView = null;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        updateIconSize();
        updateIconImage();
        updateIconAlpha();
        return 1;
    }
}
