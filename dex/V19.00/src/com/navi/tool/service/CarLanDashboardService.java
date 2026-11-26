package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.desaysv.ivi.vdb.event.id.carlan.bean.VDMusicID3Info;
import com.desaysv.ivi.vdb.event.id.carlan.bean.VDMusicPlayStatus;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarLanDashboardService extends Service {
    private static final String CARLAN_ACTION = "action.desaysv.ivi.vds.carlan.SERVICE";
    private static final String CARLAN_PKG = "com.desaysv.ivi.vds.carlan";
    private static final String CHANNEL_ID = "carlan_dashboard_channel";
    private static final int EVENT_MUSIC_ID3_INFO = 721397;
    private static final int EVENT_MUSIC_PLAY_STATUS = 721398;
    private static final int NOTIFICATION_ID = 5002;
    private static final String TAG = "CarLanDashboardSvc";
    private static final ExecutorService VDB_EXECUTOR = Executors.newSingleThreadExecutor();
    private boolean mActivated;
    private long mCurrentDuration;
    private boolean mCurrentPlaying;
    private long mCurrentPosition;
    private boolean mVdbBound;
    private boolean mVdbConnecting;
    private Handler mVdbHandler;
    private SharedPreferences mVdbPrefs;
    private g2.c mVdbService;
    private HandlerThread mVdbThread;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private String mCurrentTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String mCurrentArtist = s5.h0.FRAGMENT_ENCODE_SET;
    private String mCurrentCoverUrl = s5.h0.FRAGMENT_ENCODE_SET;
    private String mCurrentLyric = s5.h0.FRAGMENT_ENCODE_SET;
    private String mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String mSentArtist = s5.h0.FRAGMENT_ENCODE_SET;
    private String mSentCoverUrl = s5.h0.FRAGMENT_ENCODE_SET;
    private int mSentPlayState = -1;
    private int mSentOrigin = -1;
    private int mCurrentSourceType = -1;
    private final ServiceConnection mVdbConnection = new ServiceConnection() { // from class: com.navi.tool.service.CarLanDashboardService.1
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v4, types: [g2.c] */
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            g2.a aVar;
            CarLanDashboardService carLanDashboardService = CarLanDashboardService.this;
            int i7 = g2.b.f6432a;
            if (iBinder == null) {
                aVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface(g2.c.DESCRIPTOR);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof g2.c)) {
                    g2.a aVar2 = new g2.a();
                    aVar2.f6431a = iBinder;
                    aVar = aVar2;
                } else {
                    aVar = (g2.c) queryLocalInterface;
                }
            }
            carLanDashboardService.mVdbService = aVar;
            CarLanDashboardService.this.mVdbBound = true;
            CarLanDashboardService.this.mVdbConnecting = false;
            try {
                iBinder.linkToDeath(CarLanDashboardService.this.mDeathRecipient, 0);
            } catch (RemoteException unused) {
            }
            o4.c.c().f7935c.i(Boolean.TRUE);
            CarLanDashboardService.this.sendInitialData();
            CarLanDashboardService.this.mActivated = true;
            CarLanDashboardService.this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
            CarLanDashboardService.this.mSentPlayState = -1;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            CarLanDashboardService.this.mVdbService = null;
            CarLanDashboardService.this.mVdbBound = false;
            o4.c.c().f7935c.i(Boolean.FALSE);
            CarLanDashboardService.this.mActivated = false;
            CarLanDashboardService.this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
            CarLanDashboardService.this.mSentPlayState = -1;
            CarLanDashboardService.this.scheduleReconnect();
        }
    };
    private final IBinder.DeathRecipient mDeathRecipient = new b(this, 0);

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToCarLan() {
        if (this.mVdbConnecting || this.mVdbBound) {
            return;
        }
        this.mVdbConnecting = true;
        Intent intent = new Intent();
        intent.setPackage(CARLAN_PKG);
        intent.setAction(CARLAN_ACTION);
        try {
            startService(intent);
            if (bindService(intent, this.mVdbConnection, 1)) {
                return;
            }
            this.mVdbConnecting = false;
            scheduleReconnect();
        } catch (Exception e7) {
            this.mVdbConnecting = false;
            e7.getMessage();
            scheduleReconnect();
        }
    }

    private void disconnectFromCarLan() {
        if (this.mVdbBound) {
            try {
                g2.c cVar = this.mVdbService;
                if (cVar != null) {
                    ((g2.a) cVar).f6431a.unlinkToDeath(this.mDeathRecipient, 0);
                }
                unbindService(this.mVdbConnection);
            } catch (Exception unused) {
            }
        }
        this.mVdbService = null;
        this.mVdbBound = false;
    }

    private void forwardSongInfo() {
        String str;
        String str2;
        if (this.mCurrentLyric.isEmpty()) {
            str = this.mCurrentTitle;
            str2 = this.mCurrentArtist;
        } else {
            if (this.mCurrentLyric.contains("\n")) {
                String str3 = this.mCurrentLyric;
                str = str3.substring(0, str3.indexOf("\n"));
            } else {
                str = this.mCurrentLyric;
            }
            if (this.mCurrentTitle.isEmpty()) {
                str2 = this.mCurrentArtist;
            } else {
                str2 = this.mCurrentTitle + " - " + this.mCurrentArtist;
            }
        }
        sendId3Data(str, str2, s5.h0.FRAGMENT_ENCODE_SET, this.mCurrentCoverUrl);
    }

    private void forwardToDashboard() {
        forwardSongInfo();
        if (this.mCurrentPlaying) {
            sendPlayStatus(true);
        }
    }

    private boolean isConnected() {
        return this.mVdbBound && this.mVdbService != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$new$0() {
        this.mVdbService = null;
        this.mVdbBound = false;
        o4.c.c().f7935c.i(Boolean.FALSE);
        this.mActivated = false;
        this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mSentPlayState = -1;
        scheduleReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleReconnect$1() {
        if (this.mVdbPrefs.getBoolean("carlan_dashboard_enabled", false)) {
            connectToCarLan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendId3Data$2(g2.c cVar, VDEvent vDEvent) {
        SystemClock.elapsedRealtime();
        try {
            try {
                ((g2.a) cVar).i(vDEvent);
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        } finally {
            SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendPlayStatus$3(g2.c cVar, VDEvent vDEvent) {
        SystemClock.elapsedRealtime();
        try {
            try {
                ((g2.a) cVar).i(vDEvent);
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        } finally {
            SystemClock.elapsedRealtime();
        }
    }

    private int resolveCarLanOrigin(int i7) {
        if (i7 != 0) {
            return i7 != 2 ? 1 : 7;
        }
        return 18;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleReconnect() {
        this.mHandler.postDelayed(new c(this, 1), 5000L);
    }

    private void sendId3Data(String str, String str2, String str3, String str4) {
        if (isConnected()) {
            int i7 = this.mCurrentSourceType;
            if (i7 < 0) {
                i7 = this.mVdbPrefs.getInt("desktop_music_source", 0);
            }
            int resolveCarLanOrigin = resolveCarLanOrigin(i7);
            if (str.equals(this.mSentTitle) && str2.equals(this.mSentArtist) && str4.equals(this.mSentCoverUrl) && resolveCarLanOrigin == this.mSentOrigin) {
                return;
            }
            this.mSentTitle = str;
            this.mSentArtist = str2;
            this.mSentCoverUrl = str4;
            this.mSentOrigin = resolveCarLanOrigin;
            VDMusicID3Info vDMusicID3Info = new VDMusicID3Info();
            vDMusicID3Info.f3193a = resolveCarLanOrigin;
            vDMusicID3Info.f3194b = 0;
            vDMusicID3Info.f3195c = str;
            if (str2 == null) {
                str2 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            vDMusicID3Info.f3196d = str2;
            if (str3 == null) {
                str3 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            vDMusicID3Info.f3197e = str3;
            vDMusicID3Info.f3198f = s5.h0.FRAGMENT_ENCODE_SET;
            vDMusicID3Info.f3199g = s5.h0.FRAGMENT_ENCODE_SET;
            vDMusicID3Info.f3200h = (str4 == null || str4.isEmpty()) ? "0" : "1";
            if (str4 == null) {
                str4 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            vDMusicID3Info.f3201i = str4;
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", vDMusicID3Info);
            VDEvent vDEvent = new VDEvent(EVENT_MUSIC_ID3_INFO, bundle);
            g2.c cVar = this.mVdbService;
            if (cVar == null) {
                return;
            }
            VDB_EXECUTOR.execute(new d(cVar, vDEvent, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendInitialData() {
        sendId3Data("等待音乐", "仪表盘桥接服务", s5.h0.FRAGMENT_ENCODE_SET, s5.h0.FRAGMENT_ENCODE_SET);
        sendPlayStatus(true);
    }

    private void sendPlayStatus(boolean z6) {
        if (isConnected() && z6 != this.mSentPlayState) {
            this.mSentPlayState = z6 ? 1 : 0;
            VDMusicPlayStatus vDMusicPlayStatus = new VDMusicPlayStatus();
            vDMusicPlayStatus.f3202a = z6 ? "1" : "0";
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", vDMusicPlayStatus);
            VDEvent vDEvent = new VDEvent(EVENT_MUSIC_PLAY_STATUS, bundle);
            g2.c cVar = this.mVdbService;
            if (cVar == null) {
                return;
            }
            VDB_EXECUTOR.execute(new d(cVar, vDEvent, 0));
        }
    }

    private void startForegroundNotification() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "仪表盘桥接服务", 2);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(NOTIFICATION_ID, new Notification.Builder(this, CHANNEL_ID).setContentTitle("仪表盘桥接服务").setContentText("正在运行").setSmallIcon(g4.o.ic_launcher_foreground).build());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mVdbPrefs = SharedPreferencesUtils.getAppPrefs(this);
        HandlerThread handlerThread = new HandlerThread("CarLanVdb");
        this.mVdbThread = handlerThread;
        handlerThread.start();
        this.mVdbHandler = new Handler(this.mVdbThread.getLooper());
        startForegroundNotification();
        i6.e.b().j(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mVdbPrefs.edit().putLong("carlan_dashboard_last_destroy", System.currentTimeMillis()).apply();
        i6.e.b().l(this);
        disconnectFromCarLan();
        o4.c.c().f7935c.i(Boolean.FALSE);
        this.mActivated = false;
        this.mVdbConnecting = false;
        this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mSentPlayState = -1;
        this.mHandler.removeCallbacksAndMessages(null);
        HandlerThread handlerThread = this.mVdbThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdateEvent(h4.k kVar) {
        if (this.mActivated && kVar.f6754b == this.mVdbPrefs.getInt("desktop_music_source", 0)) {
            this.mCurrentSourceType = kVar.f6754b;
            if (this.mVdbPrefs.getBoolean("carlan_song_name_only", false)) {
                return;
            }
            String str = kVar.f6753a;
            if (str == null) {
                str = s5.h0.FRAGMENT_ENCODE_SET;
            }
            this.mCurrentLyric = str;
            forwardSongInfo();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onMusicStatusEvent(h4.m mVar) {
        if (this.mActivated && mVar.f6768l == this.mVdbPrefs.getInt("desktop_music_source", 0)) {
            this.mCurrentSourceType = mVar.f6768l;
            String str = s5.h0.FRAGMENT_ENCODE_SET;
            String str2 = mVar.f6758b;
            if (str2 == null) {
                str2 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            String str3 = mVar.f6759c;
            if (str3 == null) {
                str3 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            if (str2.isEmpty() && str3.isEmpty()) {
                return;
            }
            if (!str2.equals(this.mCurrentTitle)) {
                this.mCurrentLyric = s5.h0.FRAGMENT_ENCODE_SET;
            }
            this.mCurrentTitle = str2;
            this.mCurrentArtist = str3;
            String str4 = mVar.f6762f;
            if (str4 != null) {
                str = str4;
            }
            this.mCurrentCoverUrl = str;
            this.mCurrentPosition = mVar.f6760d;
            this.mCurrentDuration = mVar.f6761e;
            this.mCurrentPlaying = mVar.f6757a;
            forwardToDashboard();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent != null) {
            int i9 = 0;
            if (intent.getBooleanExtra("boot_start", false)) {
                this.mHandler.postDelayed(new c(this, i9), 2000L);
                return 1;
            }
        }
        connectToCarLan();
        return 1;
    }
}
