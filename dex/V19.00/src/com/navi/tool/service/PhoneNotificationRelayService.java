package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.display.DisplayManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.navi.tool.MainActivity;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PhoneNotificationRelayService extends Service {
    private static final String ACTION_SHOW_CLUSTER_PREVIEW = "com.navi.tool.action.SHOW_PHONE_NOTIFICATION_CLUSTER_PREVIEW";
    private static final String ACTION_SHOW_FORWARDED_PAYLOAD = "com.navi.tool.action.SHOW_FORWARDED_PHONE_NOTIFICATION";
    private static final String CHANNEL_ID = "phone_notification_relay_service";
    private static final long CLUSTER_PREVIEW_DURATION_MS = 3000;
    private static final String CMD_ACK = "notify_ack";
    private static final String CMD_BATTERY = "notify_phone_battery";
    private static final String CMD_CLIPBOARD = "notify_phone_clipboard";
    private static final String CMD_HEARTBEAT = "notify_heartbeat";
    private static final String CMD_HELLO = "notify_hello";
    private static final String CMD_NOTIFICATION = "notify_phone_notification";
    private static final String CMD_TIRE = "notify_phone_tire";
    public static final long CONNECTION_MODE_TRANSITION_GRACE_MS = 3000;
    private static final String EXTRA_FORWARDED_PAYLOAD = "extra_forwarded_payload";
    private static final int MAX_OVERLAY_MESSAGE_CACHE = 30;
    private static final String MESSAGE_CHANNEL_ID = "phone_notification_relay_message";
    private static final int NOTIFICATION_ID = 10331;
    public static final int OVERLAY_BODY_TEXT_SIZE_DEFAULT_SP = 22;
    public static final int OVERLAY_BODY_TEXT_SIZE_MAX_SP = 28;
    public static final int OVERLAY_BODY_TEXT_SIZE_MIN_SP = 18;
    public static final int OVERLAY_DISMISS_DEFAULT_SECONDS = 5;
    public static final int OVERLAY_DISMISS_MAX_SECONDS = 30;
    public static final int OVERLAY_DISMISS_MIN_SECONDS = 3;
    public static final String OVERLAY_POSITION_CENTER = "center";
    public static final String OVERLAY_POSITION_LEFT = "left";
    public static final String OVERLAY_POSITION_RIGHT = "right";
    private static final float OVERLAY_SWIPE_DISMISS_THRESHOLD_DP = 54.0f;
    private static final int PORT = 34679;
    public static final String PREF_CLICK_EXPAND_ENABLED = "phone_notification_click_expand_enabled";
    public static final String PREF_CLUSTER_OVERLAY_ENABLED = "phone_notification_cluster_overlay_enabled";
    public static final String PREF_CLUSTER_OVERLAY_X = "phone_notification_cluster_overlay_x";
    public static final String PREF_CLUSTER_OVERLAY_Y = "phone_notification_cluster_overlay_y";
    public static final String PREF_CONNECTION_MODE = "phone_notification_connection_mode";
    public static final String PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS = "phone_notification_connection_mode_transition_until_ms";
    private static final String PREF_ENABLED = "mobile_hud_phone_notification_enabled";
    public static final String PREF_HIDE_MAIN_SCREEN_OVERLAY = "phone_notification_hide_main_screen_overlay";
    public static final String PREF_OVERLAY_BODY_TEXT_SIZE_SP = "phone_notification_overlay_body_text_size_sp";
    public static final String PREF_OVERLAY_DISMISS_SECONDS = "phone_notification_overlay_dismiss_seconds";
    public static final String PREF_OVERLAY_POSITION = "phone_notification_overlay_position";
    public static final String PREF_SOUND_ENABLED = "phone_notification_sound_enabled";
    private static final String PREF_THEME_MODE = "theme_mode";
    public static final String PREF_TIRE_CARD_DYNAMIC_ENABLED = "tire_card_dynamic_enabled";
    public static final String PREF_TIRE_CARD_DYNAMIC_SECONDS = "tire_card_dynamic_seconds";
    public static final String PREF_TIRE_CARD_ENABLED = "tire_card_enabled";
    public static final String PREF_TIRE_CARD_SHOWING = "tire_card_showing";
    private static final int SERVER_ACCEPT_TIMEOUT_MS = 3000;
    private static final String TAG = "PhoneNotifyRelayCar";
    private static final int THEME_DARK = 1;
    private static final int THEME_LIGHT = 0;
    private static final int THEME_SYSTEM = 2;
    public static final int TIRE_CARD_DYNAMIC_SECONDS_DEFAULT = 30;
    public static final int TIRE_CARD_DYNAMIC_SECONDS_MAX = 60;
    public static final int TIRE_CARD_DYNAMIC_SECONDS_MIN = 10;
    private static final String TYPE_BATTERY = "battery";
    private static final String TYPE_CLIPBOARD = "clipboard";
    private static final String TYPE_TIRE = "tire";
    private static final Object sChimeLock = new Object();
    private static MediaPlayer sChimePlayer;
    private String activeClientHost;
    private Socket activeClientSocket;
    private Context clusterDisplayContext;
    private View clusterOverlayView;
    private OverlayMessage clusterPreviewMessage;
    private WindowManager clusterWindowManager;
    private DisplayManager displayManager;
    private long lastChimeAtMs;
    private boolean overlayExpanded;
    private float overlayTouchDownY;
    private View overlayView;
    private SharedPreferences prefs;
    private volatile boolean running;
    private volatile boolean serverLoopStarted;
    private ServerSocket serverSocket;
    private WindowManager windowManager;
    private final ExecutorService serverExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService clientExecutor = Executors.newSingleThreadExecutor();
    private final Object clientLock = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable dismissOverlayRunnable = new k0(this, 5);
    private final Runnable dismissClusterPreviewRunnable = new k0(this, 6);
    private final SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new p0(this, 0);
    private long overlayDismissDurationMs = 5000;
    private final List<OverlayMessage> overlayMessages = new ArrayList();

    public static final class OverlayMessage {
        private final String appName;
        private final String contentText;
        private final Bitmap largeIcon;
        private final String stableKey;
        private final String title;

        private OverlayMessage(String str, String str2, String str3, String str4, Bitmap bitmap) {
            this.stableKey = str;
            this.appName = str2;
            this.title = str3;
            this.contentText = str4;
            this.largeIcon = bitmap;
        }
    }

    private void applyOverlayAppearance(View view, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, LinearLayout linearLayout) {
        boolean resolveNightMode = resolveNightMode();
        if (view != null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dpToPx(28));
            gradientDrawable.setColor(Color.parseColor(resolveNightMode ? "#EE1B1F27" : "#F2FFFFFF"));
            view.setBackground(gradientDrawable);
        }
        if (textView != null) {
            textView.setTextColor(Color.parseColor(resolveNightMode ? "#B3D5E4FF" : "#8A000000"));
        }
        if (textView2 != null) {
            textView2.setTextColor(Color.parseColor(resolveNightMode ? "#FFF5F7FF" : "#DE000000"));
        }
        if (textView3 != null) {
            textView3.setTextColor(Color.parseColor(resolveNightMode ? "#D9E6EBFF" : "#B3000000"));
            textView3.setTextSize(2, getOverlayBodyTextSizeSp());
        }
        if (textView4 != null) {
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setShape(0);
            gradientDrawable2.setCornerRadius(dpToPx(12));
            gradientDrawable2.setColor(Color.parseColor(resolveNightMode ? "#CC3C5EA8" : "#FF356BE3"));
            textView4.setBackground(gradientDrawable2);
        }
        if (textView5 != null) {
            textView5.setTextColor(Color.parseColor(resolveNightMode ? "#99D5E4FF" : "#8A000000"));
        }
        if (textView6 != null) {
            textView6.setTextColor(Color.parseColor(resolveNightMode ? "#FFF5F7FF" : "#DE000000"));
        }
        if (linearLayout != null) {
            linearLayout.setShowDividers(0);
        }
    }

    private void attachOverlayInteractions(View view) {
        if (view == null) {
            return;
        }
        View findViewById = view.findViewById(g4.p.layout_phone_notification_overlay_summary);
        View findViewById2 = view.findViewById(g4.p.layout_phone_notification_overlay_header);
        TextView textView = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_close);
        if (findViewById != null) {
            final int i7 = 0;
            findViewById.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.navi.tool.service.q0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ PhoneNotificationRelayService f4913b;

                {
                    this.f4913b = this;
                }

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    boolean handleOverlayTouch;
                    int i8 = i7;
                    handleOverlayTouch = this.f4913b.handleOverlayTouch(view2, motionEvent);
                    return handleOverlayTouch;
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.r0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ PhoneNotificationRelayService f4917b;

                {
                    this.f4917b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i8 = i7;
                    PhoneNotificationRelayService phoneNotificationRelayService = this.f4917b;
                    switch (i8) {
                        case 0:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$11(view2);
                            break;
                        case 1:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$12(view2);
                            break;
                        default:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$13(view2);
                            break;
                    }
                }
            });
        }
        if (findViewById2 != null) {
            final int i8 = 1;
            findViewById2.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.navi.tool.service.q0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ PhoneNotificationRelayService f4913b;

                {
                    this.f4913b = this;
                }

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    boolean handleOverlayTouch;
                    int i82 = i8;
                    handleOverlayTouch = this.f4913b.handleOverlayTouch(view2, motionEvent);
                    return handleOverlayTouch;
                }
            });
            findViewById2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.r0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ PhoneNotificationRelayService f4917b;

                {
                    this.f4917b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i82 = i8;
                    PhoneNotificationRelayService phoneNotificationRelayService = this.f4917b;
                    switch (i82) {
                        case 0:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$11(view2);
                            break;
                        case 1:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$12(view2);
                            break;
                        default:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$13(view2);
                            break;
                    }
                }
            });
        }
        if (textView != null) {
            final int i9 = 2;
            textView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.r0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ PhoneNotificationRelayService f4917b;

                {
                    this.f4917b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i82 = i9;
                    PhoneNotificationRelayService phoneNotificationRelayService = this.f4917b;
                    switch (i82) {
                        case 0:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$11(view2);
                            break;
                        case 1:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$12(view2);
                            break;
                        default:
                            phoneNotificationRelayService.lambda$attachOverlayInteractions$13(view2);
                            break;
                    }
                }
            });
        }
    }

    private WindowManager.LayoutParams buildClusterOverlayLayoutParams(Context context) {
        int i7 = context.getResources().getDisplayMetrics().widthPixels;
        int dpToPx = dpToPx(260);
        int dpToPx2 = dpToPx(520);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 262664;
        layoutParams.format = -3;
        layoutParams.width = Math.max(dpToPx(320), Math.min(i7 - dpToPx, dpToPx2));
        layoutParams.height = -2;
        layoutParams.gravity = 8388659;
        layoutParams.x = getClusterOverlayX();
        layoutParams.y = getClusterOverlayY();
        return layoutParams;
    }

    private WindowManager.LayoutParams buildMainOverlayLayoutParams(Context context) {
        int i7 = context.getResources().getDisplayMetrics().widthPixels;
        int dpToPx = dpToPx(260);
        int dpToPx2 = dpToPx(520);
        int dpToPx3 = dpToPx(48);
        SharedPreferences sharedPreferences = this.prefs;
        String string = sharedPreferences == null ? OVERLAY_POSITION_LEFT : sharedPreferences.getString(PREF_OVERLAY_POSITION, OVERLAY_POSITION_LEFT);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 262664;
        layoutParams.format = -3;
        layoutParams.width = Math.max(dpToPx(320), Math.min(i7 - dpToPx, dpToPx2));
        layoutParams.height = -2;
        if (OVERLAY_POSITION_LEFT.equals(string)) {
            layoutParams.gravity = 8388659;
            layoutParams.x = dpToPx3;
        } else if (OVERLAY_POSITION_RIGHT.equals(string)) {
            layoutParams.gravity = 8388661;
            layoutParams.x = dpToPx3;
        } else {
            layoutParams.gravity = 49;
            layoutParams.x = 0;
        }
        layoutParams.y = dpToPx(28);
        return layoutParams;
    }

    private WindowManager.LayoutParams buildOverlayLayoutParams(View view, Context context) {
        return view == this.clusterOverlayView ? buildClusterOverlayLayoutParams(context) : buildMainOverlayLayoutParams(context);
    }

    private void cacheOverlayMessage(OverlayMessage overlayMessage) {
        for (int size = this.overlayMessages.size() - 1; size >= 0; size--) {
            if (this.overlayMessages.get(size).stableKey.equals(overlayMessage.stableKey)) {
                this.overlayMessages.remove(size);
            }
        }
        this.overlayMessages.add(0, overlayMessage);
        while (this.overlayMessages.size() > 30) {
            this.overlayMessages.remove(r4.size() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOverlayMessagesAndDismiss() {
        this.overlayMessages.clear();
        dismissOverlay();
    }

    private void closeActiveClient() {
        Socket socket = this.activeClientSocket;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
            this.activeClientSocket = null;
            this.activeClientHost = null;
        }
    }

    private void closeServerSocket() {
        ServerSocket serverSocket = this.serverSocket;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException unused) {
            }
            this.serverSocket = null;
        }
    }

    private void createChannels() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager == null) {
            return;
        }
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "手机通知接收服务", 2);
        notificationChannel.setDescription("用于维持手机助手通知专线");
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationChannel notificationChannel2 = new NotificationChannel(MESSAGE_CHANNEL_ID, "手机通知转发", 4);
        notificationChannel2.setDescription("用于显示手机助手转发过来的通知");
        notificationManager.createNotificationChannel(notificationChannel2);
    }

    private Notification createForegroundNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 331, new Intent(this, (Class<?>) MainActivity.class), 201326592);
        String str = this.activeClientHost;
        String str2 = (str == null || str.trim().isEmpty()) ? "等待手机连接" : "已连手机";
        e0.v vVar = new e0.v(this, CHANNEL_ID);
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6327e = e0.v.c("手机通知");
        vVar.f6328f = e0.v.c(str2);
        vVar.f6329g = activity;
        vVar.d(2, true);
        vVar.f6331i = -1;
        return vVar.a();
    }

    private Bitmap decodeLargeIcon(String str) {
        if (str != null) {
            try {
                if (!str.trim().isEmpty()) {
                    byte[] decode = Base64.decode(str, 0);
                    return BitmapFactory.decodeByteArray(decode, 0, decode.length);
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private void dismissClusterOverlayOnly() {
        removeOverlayFromWindow(this.clusterOverlayView, this.clusterWindowManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissClusterPreview() {
        this.clusterPreviewMessage = null;
        this.mainHandler.removeCallbacks(this.dismissClusterPreviewRunnable);
        if (this.overlayMessages.isEmpty()) {
            dismissClusterOverlayOnly();
        } else {
            refreshClusterOverlayState();
        }
        maybeStopAfterTransientAction();
    }

    private void dismissMainOverlayOnly() {
        removeOverlayFromWindow(this.overlayView, this.windowManager);
    }

    private void dismissOverlay() {
        this.mainHandler.removeCallbacks(this.dismissOverlayRunnable);
        this.mainHandler.removeCallbacks(this.dismissClusterPreviewRunnable);
        this.overlayExpanded = false;
        this.clusterPreviewMessage = null;
        removeOverlayFromWindow(this.overlayView, this.windowManager);
        removeOverlayFromWindow(this.clusterOverlayView, this.clusterWindowManager);
    }

    private int dpToPx(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    private void ensureClusterOverlayView() {
        Context context;
        if (this.clusterOverlayView != null || (context = this.clusterDisplayContext) == null) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(g4.q.view_phone_notification_overlay, (ViewGroup) null, false);
        this.clusterOverlayView = inflate;
        attachOverlayInteractions(inflate);
    }

    private String ensureNotificationChimeFile() {
        try {
            File file = new File(getFilesDir(), "phone_notify_sounds");
            if (!file.exists() && !file.mkdirs()) {
                return null;
            }
            File file2 = new File(file, "nfc_success.ogg");
            if (file2.exists() && file2.length() > 0) {
                return file2.getAbsolutePath();
            }
            InputStream openRawResource = getResources().openRawResource(g4.t.nfc_success);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = openRawResource.read(bArr);
                        if (read <= 0) {
                            String absolutePath = file2.getAbsolutePath();
                            fileOutputStream.close();
                            openRawResource.close();
                            return absolutePath;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } finally {
                }
            } finally {
            }
        } catch (Exception unused) {
            return null;
        }
    }

    private void ensureOverlayView() {
        if (this.overlayView != null) {
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(g4.q.view_phone_notification_overlay, (ViewGroup) null, false);
        this.overlayView = inflate;
        attachOverlayInteractions(inflate);
    }

    private int getClusterOverlayX() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences == null ? getDefaultClusterOverlayX(this) : sharedPreferences.getInt(PREF_CLUSTER_OVERLAY_X, getDefaultClusterOverlayX(this));
    }

    private int getClusterOverlayY() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences == null ? getDefaultClusterOverlayY(this) : sharedPreferences.getInt(PREF_CLUSTER_OVERLAY_Y, getDefaultClusterOverlayY(this));
    }

    private static String getConnectionMode(SharedPreferences sharedPreferences) {
        return MobileHudService.CONNECTION_MODE_WIFI.equals(sharedPreferences.getString(PREF_CONNECTION_MODE, MobileHudService.CONNECTION_MODE_BLUETOOTH)) ? MobileHudService.CONNECTION_MODE_WIFI : MobileHudService.CONNECTION_MODE_BLUETOOTH;
    }

    public static int getDefaultClusterOverlayX(Context context) {
        return 0;
    }

    public static int getDefaultClusterOverlayY(Context context) {
        return 0;
    }

    private int getOverlayBodyTextSizeSp() {
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = sharedPreferences != null ? sharedPreferences.getInt(PREF_OVERLAY_BODY_TEXT_SIZE_SP, 22) : 22;
        if (i7 < 18) {
            i7 = 18;
        }
        if (i7 > 28) {
            return 28;
        }
        return i7;
    }

    private long getOverlayDismissDuration(String str) {
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = sharedPreferences != null ? sharedPreferences.getInt(PREF_OVERLAY_DISMISS_SECONDS, 5) : 5;
        if (i7 < 3) {
            i7 = 3;
        }
        if (i7 > 30) {
            i7 = 30;
        }
        return i7 * 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x012b, code lost:
    
        if (r4.trim().isEmpty() != false) goto L51;
     */
    /* JADX WARN: Finally extract failed */
    /* renamed from: handleClient, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void lambda$startServerLoopIfNeeded$1(java.net.Socket r9) {
        /*
            Method dump skipped, instructions count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.PhoneNotificationRelayService.lambda$startServerLoopIfNeeded$1(java.net.Socket):void");
    }

    private void handleClusterPreviewIntent() {
        this.mainHandler.post(new k0(this, 3));
    }

    private void handleForwardedPayloadIntent(String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("phone_notification".equals(jSONObject.optString("type"))) {
                jSONObject.optString("packageName");
                jSONObject.optString("title");
                jSONObject.optString("text").length();
                showForwardedPhoneNotification(jSONObject);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleOverlayTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mainHandler.removeCallbacks(this.dismissOverlayRunnable);
            this.overlayTouchDownY = motionEvent.getRawY();
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked != 3) {
                return false;
            }
            if (!this.overlayExpanded && isAnyOverlayVisible()) {
                scheduleOverlayDismiss(this.overlayDismissDurationMs);
            }
            return true;
        }
        if ((-(motionEvent.getRawY() - this.overlayTouchDownY)) >= dpToPx(54)) {
            clearOverlayMessagesAndDismiss();
            return true;
        }
        if (!this.overlayExpanded && isAnyOverlayVisible()) {
            scheduleOverlayDismiss(this.overlayDismissDurationMs);
        }
        return false;
    }

    private boolean hasClusterOverlayContent() {
        return (this.clusterPreviewMessage == null && this.overlayMessages.isEmpty()) ? false : true;
    }

    private boolean isAnyOverlayVisible() {
        View view;
        View view2 = this.overlayView;
        return ((view2 == null || view2.getParent() == null) && ((view = this.clusterOverlayView) == null || view.getParent() == null)) ? false : true;
    }

    private boolean isOverlayClickExpandEnabled() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences == null || sharedPreferences.getBoolean(PREF_CLICK_EXPAND_ENABLED, true);
    }

    private static boolean isTransitionGraceActive(SharedPreferences sharedPreferences) {
        return System.currentTimeMillis() < sharedPreferences.getLong(PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachOverlayInteractions$11(View view) {
        onOverlaySummaryClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachOverlayInteractions$12(View view) {
        clearOverlayMessagesAndDismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$attachOverlayInteractions$13(View view) {
        clearOverlayMessagesAndDismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleClusterPreviewIntent$3() {
        if (!Settings.canDrawOverlays(this) || !shouldShowClusterOverlay()) {
            maybeStopAfterTransientAction();
            return;
        }
        if (!prepareClusterDisplayContext()) {
            maybeStopAfterTransientAction();
            return;
        }
        ensureClusterOverlayView();
        this.overlayExpanded = false;
        this.clusterPreviewMessage = new OverlayMessage("cluster_preview", "手机通知", "仪表通知位置预览", "这里是仪表通知悬浮窗预览", null);
        showOverlayOnWindow(this.clusterOverlayView, this.clusterWindowManager, this.clusterDisplayContext);
        this.mainHandler.removeCallbacks(this.dismissClusterPreviewRunnable);
        this.mainHandler.postDelayed(this.dismissClusterPreviewRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(SharedPreferences sharedPreferences, String str) {
        if (PREF_OVERLAY_BODY_TEXT_SIZE_SP.equals(str) || PREF_OVERLAY_POSITION.equals(str) || PREF_CLUSTER_OVERLAY_X.equals(str) || PREF_CLUSTER_OVERLAY_Y.equals(str) || PREF_THEME_MODE.equals(str)) {
            refreshOverlayAppearance();
            refreshOverlayPosition();
        } else if (PREF_HIDE_MAIN_SCREEN_OVERLAY.equals(str)) {
            refreshMainOverlayState();
        } else if (PREF_CLUSTER_OVERLAY_ENABLED.equals(str)) {
            refreshClusterOverlayState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$playNotificationChimeIfVisible$6(MediaPlayer mediaPlayer, int i7, int i8) {
        lambda$playNotificationChimeIfVisible$5(mediaPlayer);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playNotificationChimeIfVisible$7(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.start();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshClusterOverlayState$15() {
        if (!shouldShowClusterOverlay()) {
            this.mainHandler.removeCallbacks(this.dismissClusterPreviewRunnable);
            this.clusterPreviewMessage = null;
            dismissClusterOverlayOnly();
        } else if (!hasClusterOverlayContent() || !Settings.canDrawOverlays(this)) {
            dismissClusterOverlayOnly();
        } else if (!prepareClusterDisplayContext()) {
            dismissClusterOverlayOnly();
        } else {
            ensureClusterOverlayView();
            showOverlayOnWindow(this.clusterOverlayView, this.clusterWindowManager, this.clusterDisplayContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshMainOverlayState$14() {
        if (!shouldShowMainOverlay() || this.overlayMessages.isEmpty() || !Settings.canDrawOverlays(this) || this.windowManager == null) {
            dismissMainOverlayOnly();
        } else {
            ensureOverlayView();
            showOverlayOnWindow(this.overlayView, this.windowManager, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshOverlayAppearance$10() {
        renderOverlay(this.overlayView);
        renderOverlay(this.clusterOverlayView);
        updateMainOverlayLayout();
        updateClusterOverlayLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshOverlayPosition$9() {
        updateMainOverlayLayout();
        updateClusterOverlayLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showForwardedPhoneNotification$4() {
        if (isAnyOverlayVisible()) {
            playNotificationChimeIfVisible();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOverlay$8(OverlayMessage overlayMessage) {
        if (this.windowManager == null || !Settings.canDrawOverlays(this)) {
            return;
        }
        ensureOverlayView();
        View view = this.overlayView;
        boolean z6 = (view == null || view.getParent() == null) ? false : true;
        cacheOverlayMessage(overlayMessage);
        if (!z6 || !this.overlayExpanded) {
            this.overlayExpanded = false;
        }
        showMainOverlayIfNeeded();
        showClusterOverlayIfNeeded();
        if (this.overlayExpanded) {
            this.mainHandler.removeCallbacks(this.dismissOverlayRunnable);
        } else {
            scheduleOverlayDismiss(getOverlayDismissDuration(overlayMessage.contentText));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startServerLoopIfNeeded$2() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            this.serverSocket = serverSocket;
            serverSocket.setSoTimeout(SERVER_ACCEPT_TIMEOUT_MS);
            while (this.running && !this.serverSocket.isClosed()) {
                try {
                    Socket accept = this.serverSocket.accept();
                    replaceActiveClient(accept);
                    this.clientExecutor.execute(new l0(9, this, accept));
                } catch (SocketTimeoutException unused) {
                }
            }
        } catch (IOException unused2) {
        } catch (Throwable th) {
            this.serverLoopStarted = false;
            throw th;
        }
        this.serverLoopStarted = false;
    }

    private void maybeStopAfterTransientAction() {
        if (shouldRun(this.prefs) || !this.overlayMessages.isEmpty()) {
            return;
        }
        stopSelf();
    }

    private void onOverlaySummaryClicked() {
        if (this.overlayMessages.isEmpty()) {
            dismissOverlay();
            return;
        }
        if (!isOverlayClickExpandEnabled()) {
            clearOverlayMessagesAndDismiss();
            return;
        }
        if (this.overlayMessages.size() <= 1) {
            clearOverlayMessagesAndDismiss();
            return;
        }
        this.overlayExpanded = !this.overlayExpanded;
        renderOverlay(this.overlayView);
        renderOverlay(this.clusterOverlayView);
        if (this.overlayExpanded) {
            this.mainHandler.removeCallbacks(this.dismissOverlayRunnable);
        } else {
            scheduleOverlayDismiss(getOverlayDismissDuration(this.overlayMessages.get(0).contentText));
        }
    }

    private void playNotificationChimeIfVisible() {
        MediaPlayer mediaPlayer;
        if (this.prefs.getBoolean(PREF_SOUND_ENABLED, false)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastChimeAtMs < 800) {
                return;
            }
            this.lastChimeAtMs = currentTimeMillis;
            String ensureNotificationChimeFile = ensureNotificationChimeFile();
            if (ensureNotificationChimeFile == null) {
                return;
            }
            try {
                Object obj = sChimeLock;
                synchronized (obj) {
                    mediaPlayer = sChimePlayer;
                    sChimePlayer = null;
                }
                releaseChimePlayer(mediaPlayer);
                MediaPlayer mediaPlayer2 = new MediaPlayer();
                mediaPlayer2.setAudioAttributes(new AudioAttributes.Builder().setUsage(5).setContentType(4).build());
                synchronized (obj) {
                    sChimePlayer = mediaPlayer2;
                }
                mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.navi.tool.service.m0
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer3) {
                        PhoneNotificationRelayService.this.lambda$playNotificationChimeIfVisible$5(mediaPlayer3);
                    }
                });
                mediaPlayer2.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.navi.tool.service.n0
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public final boolean onError(MediaPlayer mediaPlayer3, int i7, int i8) {
                        boolean lambda$playNotificationChimeIfVisible$6;
                        lambda$playNotificationChimeIfVisible$6 = PhoneNotificationRelayService.this.lambda$playNotificationChimeIfVisible$6(mediaPlayer3, i7, i8);
                        return lambda$playNotificationChimeIfVisible$6;
                    }
                });
                mediaPlayer2.setOnPreparedListener(new o0());
                mediaPlayer2.setDataSource(ensureNotificationChimeFile);
                mediaPlayer2.prepareAsync();
            } catch (Exception unused) {
                releaseChimePlayer();
            }
        }
    }

    private void populateExpandedMessages(LinearLayout linearLayout) {
        linearLayout.removeAllViews();
        if (this.overlayMessages.size() <= 1) {
            return;
        }
        LayoutInflater from = LayoutInflater.from(linearLayout.getContext());
        boolean resolveNightMode = resolveNightMode();
        for (int i7 = 0; i7 < this.overlayMessages.size(); i7++) {
            OverlayMessage overlayMessage = this.overlayMessages.get(i7);
            View inflate = from.inflate(g4.q.item_phone_notification_overlay_message, (ViewGroup) linearLayout, false);
            TextView textView = (TextView) inflate.findViewById(g4.p.text_phone_notification_overlay_item_app);
            TextView textView2 = (TextView) inflate.findViewById(g4.p.text_phone_notification_overlay_item_title);
            TextView textView3 = (TextView) inflate.findViewById(g4.p.text_phone_notification_overlay_item_body);
            View findViewById = inflate.findViewById(g4.p.view_phone_notification_overlay_item_divider);
            textView.setText(safeMultilineText(overlayMessage.appName));
            textView2.setText(safeMultilineText(overlayMessage.title));
            textView3.setText(safeMultilineText(overlayMessage.contentText));
            textView.setTextColor(Color.parseColor(resolveNightMode ? "#99D5E4FF" : "#8A000000"));
            textView2.setTextColor(Color.parseColor(resolveNightMode ? "#FFF5F7FF" : "#DE000000"));
            textView3.setTextColor(Color.parseColor(resolveNightMode ? "#D9E6EBFF" : "#B3000000"));
            textView3.setTextSize(2, getOverlayBodyTextSizeSp());
            findViewById.setBackgroundColor(Color.parseColor(resolveNightMode ? "#263A4F78" : "#1F000000"));
            if (i7 == this.overlayMessages.size() - 1) {
                findViewById.setVisibility(8);
            }
            linearLayout.addView(inflate);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
    
        if (r0.getDisplayId() != r5.getDisplayId()) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean prepareClusterDisplayContext() {
        /*
            r7 = this;
            android.hardware.display.DisplayManager r0 = r7.displayManager
            if (r0 != 0) goto Le
            java.lang.String r0 = "display"
            java.lang.Object r0 = r7.getSystemService(r0)
            android.hardware.display.DisplayManager r0 = (android.hardware.display.DisplayManager) r0
            r7.displayManager = r0
        Le:
            android.hardware.display.DisplayManager r0 = r7.displayManager
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            android.view.Display[] r0 = r0.getDisplays()
            int r2 = r0.length
            r3 = r1
        L1a:
            r4 = 0
            if (r3 >= r2) goto L2b
            r5 = r0[r3]
            if (r5 == 0) goto L28
            int r6 = r5.getDisplayId()
            if (r6 == 0) goto L28
            goto L2c
        L28:
            int r3 = r3 + 1
            goto L1a
        L2b:
            r5 = r4
        L2c:
            if (r5 != 0) goto L33
            r7.clusterDisplayContext = r4
            r7.clusterWindowManager = r4
            return r1
        L33:
            android.content.Context r0 = r7.clusterDisplayContext
            if (r0 == 0) goto L4d
            android.view.Display r0 = com.google.android.material.internal.l0.h(r0)
            if (r0 == 0) goto L4d
            android.content.Context r0 = r7.clusterDisplayContext
            android.view.Display r0 = com.google.android.material.internal.l0.h(r0)
            int r0 = r0.getDisplayId()
            int r2 = r5.getDisplayId()
            if (r0 == r2) goto L66
        L4d:
            r7.dismissClusterOverlayOnly()
            r7.clusterOverlayView = r4
            android.content.Context r0 = r7.createDisplayContext(r5)
            r7.clusterDisplayContext = r0
            if (r0 != 0) goto L5b
            goto L64
        L5b:
            java.lang.String r2 = "window"
            java.lang.Object r0 = r0.getSystemService(r2)
            r4 = r0
            android.view.WindowManager r4 = (android.view.WindowManager) r4
        L64:
            r7.clusterWindowManager = r4
        L66:
            android.content.Context r0 = r7.clusterDisplayContext
            if (r0 == 0) goto L6f
            android.view.WindowManager r0 = r7.clusterWindowManager
            if (r0 == 0) goto L6f
            r1 = 1
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.PhoneNotificationRelayService.prepareClusterDisplayContext():boolean");
    }

    private void refreshClusterOverlayState() {
        this.mainHandler.post(new k0(this, 1));
    }

    private void refreshMainOverlayState() {
        this.mainHandler.post(new k0(this, 2));
    }

    private void refreshOverlayAppearance() {
        this.mainHandler.post(new k0(this, 4));
    }

    private void refreshOverlayPosition() {
        this.mainHandler.post(new k0(this, 8));
    }

    private void releaseChimePlayer() {
        MediaPlayer mediaPlayer;
        synchronized (sChimeLock) {
            mediaPlayer = sChimePlayer;
            sChimePlayer = null;
        }
        releaseChimePlayer(mediaPlayer);
    }

    private void releaseChimePlayer(MediaPlayer mediaPlayer) {
        if (mediaPlayer == null) {
            return;
        }
        try {
            mediaPlayer.stop();
        } catch (Exception unused) {
        }
        try {
            mediaPlayer.release();
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: releaseChimePlayerIfCurrent, reason: merged with bridge method [inline-methods] */
    public void lambda$playNotificationChimeIfVisible$5(MediaPlayer mediaPlayer) {
        synchronized (sChimeLock) {
            try {
                if (sChimePlayer != mediaPlayer) {
                    return;
                }
                sChimePlayer = null;
                releaseChimePlayer(mediaPlayer);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void removeOverlayFromWindow(View view, WindowManager windowManager) {
        if (view == null || windowManager == null || view.getParent() == null) {
            return;
        }
        try {
            windowManager.removeView(view);
        } catch (Exception unused) {
        }
    }

    private void renderOverlay(View view) {
        OverlayMessage resolveOverlayMessage;
        if (view == null || (resolveOverlayMessage = resolveOverlayMessage(view)) == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(g4.p.image_phone_notification_overlay_icon);
        TextView textView = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_app);
        TextView textView2 = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_count);
        TextView textView3 = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_title);
        TextView textView4 = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_body);
        View findViewById = view.findViewById(g4.p.layout_phone_notification_overlay_expanded);
        TextView textView5 = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_hint);
        TextView textView6 = (TextView) view.findViewById(g4.p.text_phone_notification_overlay_close);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(g4.p.layout_phone_notification_overlay_messages);
        ScrollView scrollView = (ScrollView) view.findViewById(g4.p.scroll_phone_notification_overlay_list);
        applyOverlayAppearance(view, textView, textView3, textView4, textView2, textView5, textView6, linearLayout);
        textView.setText(safeMultilineText(resolveOverlayMessage.appName));
        textView3.setText(safeMultilineText(resolveOverlayMessage.title));
        textView4.setText(safeMultilineText(resolveOverlayMessage.contentText));
        if (resolveOverlayMessage.largeIcon != null) {
            imageView.setImageBitmap(resolveOverlayMessage.largeIcon);
        } else {
            imageView.setImageResource(g4.o.ic_launcher_foreground);
        }
        boolean z6 = view == this.clusterOverlayView && this.clusterPreviewMessage != null;
        int size = z6 ? 1 : this.overlayMessages.size();
        if (textView2 != null) {
            if (size > 1) {
                textView2.setVisibility(0);
                textView2.setText(String.valueOf(size));
            } else {
                textView2.setVisibility(8);
            }
        }
        if (textView5 != null) {
            if (z6) {
                textView5.setText("调整位置后会显示在这里");
            } else if (isOverlayClickExpandEnabled()) {
                textView5.setText(size > 1 ? a.a.e("共 ", size, " 条，向下滚动查看，上滑或点击关闭清空") : "单击可关闭");
            } else {
                textView5.setText("点击消息框会直接隐藏");
            }
        }
        boolean z7 = !z6 && this.overlayExpanded && size > 1;
        if (findViewById != null) {
            findViewById.setVisibility(z7 ? 0 : 8);
        }
        if (linearLayout != null) {
            if (z6) {
                linearLayout.removeAllViews();
            } else {
                populateExpandedMessages(linearLayout);
            }
        }
        if (!z7 || scrollView == null) {
            return;
        }
        scrollView.post(new b0(4, scrollView));
    }

    private void replaceActiveClient(Socket socket) {
        synchronized (this.clientLock) {
            closeActiveClient();
            this.activeClientSocket = socket;
            this.activeClientHost = socket.getInetAddress() == null ? null : socket.getInetAddress().getHostAddress();
            updateForegroundNotification();
        }
    }

    private boolean resolveNightMode() {
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = sharedPreferences != null ? sharedPreferences.getInt(PREF_THEME_MODE, 2) : 2;
        if (i7 == 0) {
            return false;
        }
        return i7 == 1 || (getResources().getConfiguration().uiMode & 48) == 32;
    }

    private OverlayMessage resolveOverlayMessage(View view) {
        OverlayMessage overlayMessage;
        if (view == this.clusterOverlayView && (overlayMessage = this.clusterPreviewMessage) != null) {
            return overlayMessage;
        }
        if (this.overlayMessages.isEmpty()) {
            return null;
        }
        return this.overlayMessages.get(0);
    }

    private CharSequence safeMultilineText(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim().replace("\r\n", "\n").replace('\r', '\n');
    }

    private void scheduleOverlayDismiss(long j7) {
        this.overlayDismissDurationMs = j7;
        this.mainHandler.removeCallbacks(this.dismissOverlayRunnable);
        this.mainHandler.postDelayed(this.dismissOverlayRunnable, j7);
    }

    private static boolean shouldRun(Context context) {
        return shouldRun(SharedPreferencesUtils.getAppPrefs(context));
    }

    private static boolean shouldRun(SharedPreferences sharedPreferences) {
        if (sharedPreferences.getBoolean("mobile_hud_phone_notification_enabled", false)) {
            return MobileHudService.CONNECTION_MODE_WIFI.equals(getConnectionMode(sharedPreferences)) || isTransitionGraceActive(sharedPreferences);
        }
        return false;
    }

    private boolean shouldShowClusterOverlay() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences != null && sharedPreferences.getBoolean(PREF_CLUSTER_OVERLAY_ENABLED, false);
    }

    private boolean shouldShowMainOverlay() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences == null || !sharedPreferences.getBoolean(PREF_HIDE_MAIN_SCREEN_OVERLAY, false);
    }

    private void showClusterOverlayIfNeeded() {
        if (!shouldShowClusterOverlay()) {
            this.mainHandler.removeCallbacks(this.dismissClusterPreviewRunnable);
            this.clusterPreviewMessage = null;
            dismissClusterOverlayOnly();
        } else if (!prepareClusterDisplayContext()) {
            dismissClusterOverlayOnly();
        } else {
            ensureClusterOverlayView();
            showOverlayOnWindow(this.clusterOverlayView, this.clusterWindowManager, this.clusterDisplayContext);
        }
    }

    public static void showClusterOverlayPreview(Context context) {
        Intent intent = new Intent(context, (Class<?>) PhoneNotificationRelayService.class);
        intent.setAction(ACTION_SHOW_CLUSTER_PREVIEW);
        context.startForegroundService(intent);
    }

    public static void showForwardedPayload(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) PhoneNotificationRelayService.class);
        intent.setAction(ACTION_SHOW_FORWARDED_PAYLOAD);
        intent.putExtra(EXTRA_FORWARDED_PAYLOAD, str);
        context.startForegroundService(intent);
    }

    private void showForwardedPhoneNotification(JSONObject jSONObject) {
        MobileHudService.cacheHudPhoneNotification(this, jSONObject.toString());
        String optString = jSONObject.optString("appName", "手机通知");
        String optString2 = jSONObject.optString("title");
        String optString3 = jSONObject.optString("text");
        String str = (optString3 == null || optString3.trim().isEmpty()) ? optString : optString3;
        if ((optString2 == null || optString2.trim().isEmpty()) && str.trim().isEmpty()) {
            return;
        }
        String str2 = (optString2 == null || optString2.trim().isEmpty()) ? optString : optString2;
        Bitmap decodeLargeIcon = decodeLargeIcon(jSONObject.optString("largeIconBase64"));
        if (!Settings.canDrawOverlays(this) || this.windowManager == null) {
            showSystemNotificationFallback(jSONObject, optString, str2, str, decodeLargeIcon);
            return;
        }
        showOverlay(new OverlayMessage(jSONObject.optString("packageName", "phone") + "|" + str2 + "|" + jSONObject.optLong("postTime"), optString, str2, str, decodeLargeIcon));
        this.mainHandler.post(new k0(this, 0));
    }

    private void showMainOverlayIfNeeded() {
        if (!shouldShowMainOverlay()) {
            dismissMainOverlayOnly();
        } else {
            ensureOverlayView();
            showOverlayOnWindow(this.overlayView, this.windowManager, this);
        }
    }

    private void showOverlay(OverlayMessage overlayMessage) {
        this.mainHandler.post(new l0(0, this, overlayMessage));
    }

    private void showOverlayOnWindow(View view, WindowManager windowManager, Context context) {
        if (view == null || windowManager == null || context == null) {
            return;
        }
        renderOverlay(view);
        WindowManager.LayoutParams buildOverlayLayoutParams = buildOverlayLayoutParams(view, context);
        try {
            if (view.getParent() == null) {
                windowManager.addView(view, buildOverlayLayoutParams);
            } else {
                windowManager.updateViewLayout(view, buildOverlayLayoutParams);
            }
        } catch (Exception unused) {
        }
    }

    private void showSystemNotificationFallback(JSONObject jSONObject, String str, String str2, String str3, Bitmap bitmap) {
        e0.v vVar = new e0.v(this, MESSAGE_CHANNEL_ID);
        vVar.f6341s.icon = g4.o.ic_launcher_foreground;
        vVar.f6327e = e0.v.c(str2);
        vVar.f6328f = e0.v.c(str3);
        e0.u uVar = new e0.u();
        uVar.f6322b = e0.v.c(str3);
        vVar.e(uVar);
        vVar.f6331i = 1;
        vVar.d(16, true);
        vVar.f6337o = "msg";
        if (bitmap != null) {
            if (Build.VERSION.SDK_INT < 27) {
                Resources resources = vVar.f6323a.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(d0.b.compat_notification_large_icon_max_width);
                int dimensionPixelSize2 = resources.getDimensionPixelSize(d0.b.compat_notification_large_icon_max_height);
                if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                    double min = Math.min(dimensionPixelSize / Math.max(1, bitmap.getWidth()), dimensionPixelSize2 / Math.max(1, bitmap.getHeight()));
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(bitmap.getWidth() * min), (int) Math.ceil(bitmap.getHeight() * min), true);
                }
            }
            vVar.f6330h = bitmap;
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.notify((jSONObject.optString("packageName", "phone") + "|" + jSONObject.optString("title") + "|" + jSONObject.optLong("postTime")).hashCode(), vVar.a());
        }
    }

    public static void start(Context context) {
        context.startForegroundService(new Intent(context, (Class<?>) PhoneNotificationRelayService.class));
    }

    private void startServerLoopIfNeeded() {
        if (this.serverLoopStarted) {
            return;
        }
        this.serverLoopStarted = true;
        this.serverExecutor.execute(new k0(this, 7));
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class<?>) PhoneNotificationRelayService.class));
    }

    public static void syncService(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (shouldRun(applicationContext)) {
            start(applicationContext);
        } else {
            stop(applicationContext);
        }
    }

    private void updateClusterOverlayLayout() {
        updateOverlayLayout(this.clusterOverlayView, this.clusterWindowManager, this.clusterDisplayContext);
    }

    private void updateForegroundNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, createForegroundNotification());
        }
    }

    private void updateMainOverlayLayout() {
        updateOverlayLayout(this.overlayView, this.windowManager, this);
    }

    private void updateOverlayLayout(View view, WindowManager windowManager, Context context) {
        if (view == null || windowManager == null || context == null || view.getParent() == null) {
            return;
        }
        try {
            windowManager.updateViewLayout(view, buildOverlayLayoutParams(view, context));
        } catch (Exception unused) {
        }
    }

    private void writeClipboard(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(TYPE_CLIPBOARD);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("phone", str));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        refreshOverlayAppearance();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        this.prefs = appPrefs;
        appPrefs.registerOnSharedPreferenceChangeListener(this.prefsListener);
        this.displayManager = (DisplayManager) getSystemService("display");
        this.windowManager = (WindowManager) getSystemService("window");
        createChannels();
        this.running = true;
        startForeground(NOTIFICATION_ID, createForegroundNotification());
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.running = false;
        this.mainHandler.removeCallbacksAndMessages(null);
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.prefsListener);
        }
        dismissOverlay();
        closeServerSocket();
        closeActiveClient();
        releaseChimePlayer();
        this.clusterDisplayContext = null;
        this.clusterWindowManager = null;
        this.clusterOverlayView = null;
        this.serverExecutor.shutdownNow();
        this.clientExecutor.shutdownNow();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        boolean z6 = false;
        boolean z7 = intent != null && ACTION_SHOW_FORWARDED_PAYLOAD.equals(intent.getAction());
        if (intent != null && ACTION_SHOW_CLUSTER_PREVIEW.equals(intent.getAction())) {
            z6 = true;
        }
        if (!shouldRun(this.prefs) && !z7 && !z6) {
            stopSelf();
            return 2;
        }
        this.running = true;
        startForeground(NOTIFICATION_ID, createForegroundNotification());
        if (shouldRun(this.prefs)) {
            startServerLoopIfNeeded();
        } else {
            closeServerSocket();
            closeActiveClient();
        }
        refreshOverlayPosition();
        refreshMainOverlayState();
        refreshClusterOverlayState();
        if (z7) {
            handleForwardedPayloadIntent(intent.getStringExtra(EXTRA_FORWARDED_PAYLOAD));
        } else if (z6) {
            handleClusterPreviewIntent();
        }
        return 1;
    }
}
