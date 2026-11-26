package com.navi.tool.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.navi.tool.utils.SharedPreferencesUtils;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LionMediaBridgeService extends Service {
    private static final String AIDL_DESCRIPTOR = "com.lion.component.mediacenter.sdk.IMediaSdk";
    private static final String CHANNEL_ID = "lion_media_bridge_channel";
    private static final String KEY_LAST_DESTROY = "lion_media_last_destroy";
    private static final String LION_ACTION = "com.lion.media.center.service";
    private static final String LION_PKG = "com.lion.media";
    private static final int NOTIFICATION_ID = 5001;
    private static final String TAG = "LionMediaBridgeSvc";
    private static final int TX_closeMedia = 21;
    private static final int TX_playMedia = 19;
    private static final int TX_sendMediaInfo = 2;
    private static final int TX_sendMediaPlayState = 3;
    private static final int TX_sendMediaProgress = 4;
    private Handler mAidlHandler;
    private HandlerThread mAidlThread;
    private AudioManager mAudioManager;
    private boolean mBound;
    private boolean mConnecting;
    private IBinder mMediaSdk;
    private SharedPreferences prefs;
    private String mCurrentLyric = s5.h0.FRAGMENT_ENCODE_SET;
    private String mLastTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String mLastArtist = s5.h0.FRAGMENT_ENCODE_SET;
    private String mLastCoverUrl = s5.h0.FRAGMENT_ENCODE_SET;
    private long mLastPosition = 0;
    private long mLastDuration = 0;
    private boolean mLastPlaying = false;
    private boolean mActivated = false;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private String mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String mSentArtist = s5.h0.FRAGMENT_ENCODE_SET;
    private String mSentCoverUrl = s5.h0.FRAGMENT_ENCODE_SET;
    private int mSentPlayState = -1;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.navi.tool.service.LionMediaBridgeService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LionMediaBridgeService.this.mMediaSdk = iBinder;
            LionMediaBridgeService.this.mBound = true;
            LionMediaBridgeService.this.mConnecting = false;
            try {
                iBinder.linkToDeath(LionMediaBridgeService.this.mDeathRecipient, 0);
            } catch (RemoteException unused) {
            }
            o4.c.c().f7934b.i(Boolean.TRUE);
            LionMediaBridgeService.this.activateOrResume();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LionMediaBridgeService.this.mMediaSdk = null;
            LionMediaBridgeService.this.mBound = false;
            o4.c.c().f7934b.i(Boolean.FALSE);
            LionMediaBridgeService.this.mActivated = false;
            LionMediaBridgeService.this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
            LionMediaBridgeService.this.mSentPlayState = -1;
            LionMediaBridgeService.this.scheduleReconnect();
        }
    };
    private final IBinder.DeathRecipient mDeathRecipient = new b(this, 1);

    /* JADX INFO: Access modifiers changed from: private */
    public void activateOrResume() {
        long j7 = this.prefs.getLong(KEY_LAST_DESTROY, 0L);
        int i7 = 1;
        if (j7 <= 0 || System.currentTimeMillis() - j7 >= t1.f.T_MS_MINUTE) {
            activateSource(0, "launcher_start", false, 2);
            sendSongInfo(0, "等待音乐", "畅听桥接服务", s5.h0.FRAGMENT_ENCODE_SET, s5.h0.FRAGMENT_ENCODE_SET);
            this.mHandler.postDelayed(new l(this, i7), 1000L);
            this.mHandler.postDelayed(new l(this, 2), 1100L);
            return;
        }
        this.prefs.edit().remove(KEY_LAST_DESTROY).apply();
        sendSongInfo(0, "等待音乐", "畅听桥接服务", s5.h0.FRAGMENT_ENCODE_SET, s5.h0.FRAGMENT_ENCODE_SET);
        this.mActivated = true;
        this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mSentPlayState = -1;
    }

    private void activateSource(int i7, String str, boolean z6, int i8) {
        if (isConnected()) {
            try {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(AIDL_DESCRIPTOR);
                obtain.writeInt(i7);
                obtain.writeString(str);
                obtain.writeInt(z6 ? 1 : 0);
                obtain.writeInt(i8);
                this.mMediaSdk.transact(19, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        }
    }

    private void closeMedia(int i7) {
        if (isConnected()) {
            try {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(AIDL_DESCRIPTOR);
                obtain.writeInt(i7);
                obtain.writeInt(1);
                this.mMediaSdk.transact(21, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToLionMedia() {
        if (this.mConnecting || this.mBound) {
            return;
        }
        this.mConnecting = true;
        Intent intent = new Intent();
        intent.setPackage(LION_PKG);
        intent.setAction(LION_ACTION);
        try {
            startService(intent);
            if (bindService(intent, this.mConnection, 1)) {
                return;
            }
            this.mConnecting = false;
            scheduleReconnect();
        } catch (Exception e7) {
            this.mConnecting = false;
            e7.getMessage();
            scheduleReconnect();
        }
    }

    private void disconnectFromLionMedia() {
        if (this.mBound) {
            try {
                IBinder iBinder = this.mMediaSdk;
                if (iBinder != null) {
                    iBinder.unlinkToDeath(this.mDeathRecipient, 0);
                }
                unbindService(this.mConnection);
            } catch (Exception unused) {
            }
        }
        this.mMediaSdk = null;
        this.mBound = false;
    }

    private void forwardSongInfo() {
        String str;
        String str2;
        if (this.mCurrentLyric.isEmpty()) {
            str = this.mLastTitle;
            str2 = this.mLastArtist;
        } else {
            if (this.mCurrentLyric.contains("\n")) {
                String str3 = this.mCurrentLyric;
                str = str3.substring(0, str3.indexOf("\n"));
            } else {
                str = this.mCurrentLyric;
            }
            if (this.mLastTitle.isEmpty()) {
                str2 = this.mLastArtist;
            } else {
                str2 = this.mLastTitle + " - " + this.mLastArtist;
            }
        }
        sendSongInfo(0, str, str2, s5.h0.FRAGMENT_ENCODE_SET, this.mLastCoverUrl);
    }

    private void forwardToLionMedia() {
        forwardSongInfo();
        sendPlayState(0, this.mLastPlaying ? 3 : 4);
        sendProgress(0, this.mLastPosition, this.mLastDuration);
    }

    private boolean isConnected() {
        return this.mBound && this.mMediaSdk != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$activateOrResume$2() {
        if (isConnected()) {
            closeMedia(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$activateOrResume$3() {
        if (isConnected()) {
            this.mActivated = true;
            this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
            this.mSentPlayState = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$new$0() {
        this.mMediaSdk = null;
        this.mBound = false;
        o4.c.c().f7934b.i(Boolean.FALSE);
        this.mActivated = false;
        this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mSentPlayState = -1;
        scheduleReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleReconnect$1() {
        if (this.prefs.getBoolean("lion_media_bridge_enabled", false)) {
            connectToLionMedia();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleReconnect() {
        this.mHandler.postDelayed(new l(this, 3), 5000L);
    }

    private void sendPlayState(int i7, int i8) {
        if (isConnected() && i8 != this.mSentPlayState) {
            this.mSentPlayState = i8;
            try {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(AIDL_DESCRIPTOR);
                obtain.writeInt(i7);
                obtain.writeInt(i8);
                this.mMediaSdk.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        }
    }

    private void sendProgress(int i7, long j7, long j8) {
        if (isConnected()) {
            try {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(AIDL_DESCRIPTOR);
                obtain.writeInt(i7);
                obtain.writeLong(j7);
                obtain.writeLong(j8);
                this.mMediaSdk.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        }
    }

    private void sendSongInfo(int i7, String str, String str2, String str3, String str4) {
        if (isConnected()) {
            if (str.equals(this.mSentTitle) && str2.equals(this.mSentArtist) && str4.equals(this.mSentCoverUrl)) {
                return;
            }
            this.mSentTitle = str;
            this.mSentArtist = str2;
            this.mSentCoverUrl = str4;
            try {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(AIDL_DESCRIPTOR);
                obtain.writeInt(i7);
                obtain.writeInt(1);
                obtain.writeString(String.valueOf(System.currentTimeMillis()));
                obtain.writeInt(i7);
                obtain.writeString(str);
                obtain.writeString(str2);
                obtain.writeString(s5.h0.FRAGMENT_ENCODE_SET);
                obtain.writeString(str3);
                obtain.writeString(str4);
                obtain.writeString(str4);
                obtain.writeInt(0);
                obtain.writeInt(0);
                obtain.writeInt(0);
                obtain.writeInt(-1);
                this.mMediaSdk.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (RemoteException e7) {
                e7.getMessage();
            }
        }
    }

    private void startForegroundNotification() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "畅听桥接服务", 2);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        startForeground(NOTIFICATION_ID, new Notification.Builder(this, CHANNEL_ID).setContentTitle("畅听桥接服务").setContentText("正在运行").setSmallIcon(g4.o.ic_launcher_foreground).build());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.prefs = SharedPreferencesUtils.getAppPrefs(this);
        this.mAudioManager = (AudioManager) getSystemService("audio");
        HandlerThread handlerThread = new HandlerThread("LionAidl");
        this.mAidlThread = handlerThread;
        handlerThread.start();
        this.mAidlHandler = new Handler(this.mAidlThread.getLooper());
        startForegroundNotification();
        i6.e.b().j(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.prefs.edit().putLong(KEY_LAST_DESTROY, System.currentTimeMillis()).apply();
        i6.e.b().l(this);
        disconnectFromLionMedia();
        o4.c.c().f7934b.i(Boolean.FALSE);
        this.mActivated = false;
        this.mConnecting = false;
        this.mSentTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mSentPlayState = -1;
        this.mHandler.removeCallbacksAndMessages(null);
        HandlerThread handlerThread = this.mAidlThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdateEvent(h4.k kVar) {
        if (this.mActivated && kVar.f6754b == this.prefs.getInt("desktop_music_source", 0)) {
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
        if (this.mActivated && mVar.f6768l == this.prefs.getInt("desktop_music_source", 0)) {
            String str = s5.h0.FRAGMENT_ENCODE_SET;
            String str2 = mVar.f6758b;
            if (str2 == null) {
                str2 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            String str3 = mVar.f6759c;
            if (str3 == null) {
                str3 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            String str4 = mVar.f6762f;
            if (str4 != null) {
                str = str4;
            }
            this.mLastTitle = str2;
            this.mLastArtist = str3;
            this.mLastCoverUrl = str;
            this.mLastPosition = mVar.f6760d;
            this.mLastDuration = mVar.f6761e;
            this.mLastPlaying = mVar.f6757a;
            forwardToLionMedia();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent != null) {
            int i9 = 0;
            if (intent.getBooleanExtra("boot_start", false)) {
                this.mHandler.postDelayed(new l(this, i9), 2000L);
                return 1;
            }
        }
        connectToLionMedia();
        return 1;
    }
}
