package com.navi.tool.amapflying.util.kugou;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.kugou.auto.proxy.IEventListener;
import com.kugou.auto.proxy.IKugouAutoService;
import com.kugou.auto.proxy.KgMusic;
import com.navi.tool.amapflying.util.kugou.NativeKugouEngine;
import com.navi.tool.service.PhoneNotificationRelayService;
import h4.k;
import h4.l;
import h4.m;
import i6.e;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class KugouMusicManager {
    private static final int MAX_RETRY_COUNT = 5;
    private static final String TAG = "KugouMusicManager";
    private static final long UPDATE_INTERVAL = 200;
    private static KugouMusicManager sInstance;
    private Context mContext;
    private KgMusic mCurrentMusic;
    private IKugouAutoService mService;
    private static final Pattern KRC_LINE_PATTERN = Pattern.compile("\\[(\\d+),(\\d+)\\](.*)");
    private static boolean sIsDoubleLine = false;
    private boolean mIsBound = false;
    private boolean mIsStarted = false;
    private boolean mIsPlaying = false;
    private TreeMap<Long, String> mLyricMap = new TreeMap<>();
    private String mLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private long mLastPosition = 0;
    private long mLastPositionUpdateTime = 0;
    private Runnable mUpdateTask = new Runnable() { // from class: com.navi.tool.amapflying.util.kugou.KugouMusicManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (KugouMusicManager.this.mIsPlaying) {
                long j7 = KugouMusicManager.this.mLastPosition;
                if (KugouMusicManager.this.mLastPositionUpdateTime > 0) {
                    j7 += System.currentTimeMillis() - KugouMusicManager.this.mLastPositionUpdateTime;
                }
                if (KugouMusicManager.this.mCurrentMusic != null && j7 > KugouMusicManager.this.mCurrentMusic.duration) {
                    j7 = KugouMusicManager.this.mCurrentMusic.duration;
                }
                long j8 = j7;
                KugouMusicManager.this.updateLyricDisplay(j8);
                m mVar = new m(KugouMusicManager.this.mCurrentMusic != null ? KugouMusicManager.this.mCurrentMusic.musicName : "酷狗音乐", KugouMusicManager.this.mCurrentMusic != null ? KugouMusicManager.this.mCurrentMusic.singerName : h0.FRAGMENT_ENCODE_SET, true, j8, KugouMusicManager.this.mCurrentMusic != null ? KugouMusicManager.this.mCurrentMusic.duration : 0L);
                mVar.f6768l = 2;
                if (KugouMusicManager.this.mCurrentMusic != null) {
                    mVar.f6764h = KugouMusicManager.this.mCurrentMusic.hashValue;
                }
                e.b().f(mVar);
            }
            KugouMusicManager.this.mHandler.postDelayed(this, KugouMusicManager.UPDATE_INTERVAL);
        }
    };
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.navi.tool.amapflying.util.kugou.KugouMusicManager.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            KugouMusicManager.this.mService = new KugouServiceProxy(iBinder);
            try {
                KugouMusicManager.this.mService.registerListener(KugouMusicManager.this.mListener);
                KugouMusicManager.this.mService.sendCommand("get_music_info", new Bundle());
                KugouMusicManager.this.mService.sendCommand("get_play_state", new Bundle());
            } catch (RemoteException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            KugouMusicManager.this.mService = null;
            KugouMusicManager.this.mIsBound = false;
            KugouMusicManager.this.scheduleRetry();
        }
    };
    private IEventListener mListener = new IEventListener.Stub() { // from class: com.navi.tool.amapflying.util.kugou.KugouMusicManager.4
        @Override // com.kugou.auto.proxy.IEventListener.Stub, android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // com.kugou.auto.proxy.IEventListener
        public void onEvent(int i7, Bundle bundle) {
        }

        @Override // com.kugou.auto.proxy.IEventListener.Stub, android.os.Binder
        public boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i8) {
            Bundle bundle;
            if (i7 == 1598968902) {
                parcel2.writeString(IEventListener.DESCRIPTOR);
                return true;
            }
            if (i7 != 1) {
                return super.onTransact(i7, parcel, parcel2, i8);
            }
            try {
                parcel.enforceInterface(IEventListener.DESCRIPTOR);
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    bundle.setClassLoader(KgMusic.class.getClassLoader());
                    KugouMusicManager.this.bundleToString(bundle);
                } else {
                    bundle = null;
                }
                if (readString != null) {
                    if (readString.equals("PLAY_PROGRESS_UPDATE")) {
                        if (bundle != null) {
                            long j7 = bundle.getLong("PLAY_POSITION", -1L);
                            if (j7 != -1) {
                                KugouMusicManager.this.mLastPosition = j7;
                                KugouMusicManager.this.mLastPositionUpdateTime = System.currentTimeMillis();
                                if (j7 > 0 && !KugouMusicManager.this.mIsPlaying) {
                                    KugouMusicManager.this.handlePlayStateChange(true);
                                }
                            }
                        }
                    } else if (readString.equals("PLAY_STATE_UPDATE")) {
                        if (bundle != null) {
                            KugouMusicManager.this.handlePlayStateChange(bundle.getInt("PLAY_STATE", 0) == 1);
                        }
                    } else if (readString.equals("PLAY_MUSIC_UPDATE") && bundle != null && bundle.containsKey("KG_MUSIC")) {
                        KgMusic kgMusic = (KgMusic) bundle.getParcelable("KG_MUSIC");
                        Objects.toString(kgMusic);
                        KugouMusicManager.this.handleMusicChange(kgMusic);
                    }
                }
            } catch (Exception unused) {
            }
            parcel2.writeNoException();
            return true;
        }
    };
    private int mRetryCount = 0;

    public static class KugouServiceProxy implements IKugouAutoService {
        private String mDescriptor;
        private IBinder mRemote;

        public KugouServiceProxy(IBinder iBinder) {
            this.mRemote = iBinder;
            try {
                this.mDescriptor = iBinder.getInterfaceDescriptor();
            } catch (RemoteException unused) {
                this.mDescriptor = IKugouAutoService.DESCRIPTOR;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.mRemote;
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public void registerListener(IEventListener iEventListener) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(this.mDescriptor);
                obtain.writeStrongBinder(iEventListener != null ? iEventListener.asBinder() : null);
                this.mRemote.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public Bundle sendCommand(String str, Bundle bundle) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(this.mDescriptor);
                obtain.writeString(str);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                Bundle bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                obtain2.recycle();
                obtain.recycle();
                return bundle2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        @Override // com.kugou.auto.proxy.IKugouAutoService
        public void unregisterListener(IEventListener iEventListener) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(this.mDescriptor);
                obtain.writeStrongBinder(iEventListener != null ? iEventListener.asBinder() : null);
                this.mRemote.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }
    }

    private KugouMusicManager(Context context) {
        this.mContext = context.getApplicationContext();
        NativeKugouEngine.getInstance().setCallback(new NativeKugouEngine.Callback() { // from class: com.navi.tool.amapflying.util.kugou.KugouMusicManager.2
            @Override // com.navi.tool.amapflying.util.kugou.NativeKugouEngine.Callback
            public void onLyricsResolved(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                KugouMusicManager.this.parseKrc(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("Bundle{");
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            sb.append(str);
            sb.append("=");
            sb.append(obj);
            sb.append(";");
        }
        sb.append("}");
        return sb.toString();
    }

    public static synchronized KugouMusicManager getInstance(Context context) {
        KugouMusicManager kugouMusicManager;
        synchronized (KugouMusicManager.class) {
            try {
                if (sInstance == null) {
                    sInstance = new KugouMusicManager(context);
                }
                kugouMusicManager = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return kugouMusicManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMusicChange(KgMusic kgMusic) {
        if (kgMusic == null) {
            return;
        }
        kgMusic.toString();
        KgMusic kgMusic2 = this.mCurrentMusic;
        if (kgMusic2 == null || !TextUtils.equals(kgMusic2.hashValue, kgMusic.hashValue) || TextUtils.isEmpty(kgMusic.hashValue)) {
            if (this.mCurrentMusic != null && TextUtils.isEmpty(kgMusic.hashValue) && !TextUtils.isEmpty(kgMusic.musicName) && TextUtils.equals(this.mCurrentMusic.musicName, kgMusic.musicName) && TextUtils.equals(this.mCurrentMusic.singerName, kgMusic.singerName)) {
                return;
            }
            this.mCurrentMusic = kgMusic;
            this.mLastPosition = 0L;
            this.mLastPositionUpdateTime = System.currentTimeMillis();
            synchronized (this.mLyricMap) {
                this.mLyricMap.clear();
            }
            postLyric(h0.FRAGMENT_ENCODE_SET);
            if (!TextUtils.isEmpty(kgMusic.hashValue)) {
                NativeKugouEngine.getInstance().fetchLyrics(kgMusic.hashValue, kgMusic.musicName, kgMusic.duration);
                return;
            }
            if (TextUtils.isEmpty(kgMusic.musicName)) {
                return;
            }
            NativeKugouEngine.getInstance().fetchLyrics(h0.FRAGMENT_ENCODE_SET, kgMusic.musicName + " " + kgMusic.singerName, kgMusic.duration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePlayStateChange(boolean z6) {
        if (this.mIsPlaying != z6) {
            this.mIsPlaying = z6;
            KgMusic kgMusic = this.mCurrentMusic;
            String str = kgMusic != null ? kgMusic.musicName : "酷狗音乐";
            String str2 = kgMusic != null ? kgMusic.singerName : h0.FRAGMENT_ENCODE_SET;
            long j7 = this.mLastPosition;
            if (z6) {
                this.mHandler.removeCallbacks(this.mUpdateTask);
                this.mHandler.post(this.mUpdateTask);
                this.mLastPositionUpdateTime = System.currentTimeMillis();
            } else {
                this.mHandler.removeCallbacks(this.mUpdateTask);
                e b7 = e.b();
                m mVar = new m(false, str, str2, j7);
                mVar.f6768l = 2;
                b7.f(mVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseKrc(String str) {
        synchronized (this.mLyricMap) {
            this.mLyricMap.clear();
            for (String str2 : str.split("\n")) {
                String trim = str2.trim();
                if (!TextUtils.isEmpty(trim)) {
                    Matcher matcher = KRC_LINE_PATTERN.matcher(trim);
                    if (matcher.find()) {
                        try {
                            long parseLong = Long.parseLong(matcher.group(1));
                            String replaceAll = matcher.group(3).replaceAll("<[^>]+>", h0.FRAGMENT_ENCODE_SET);
                            if (!TextUtils.isEmpty(replaceAll)) {
                                this.mLyricMap.put(Long.valueOf(parseLong), replaceAll);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        this.mLyricMap.size();
        e b7 = e.b();
        l lVar = new l(new TreeMap((SortedMap) this.mLyricMap));
        lVar.f6756b = 2;
        b7.f(lVar);
    }

    private void postLyric(String str) {
        this.mLastPostedLyric = str;
        e b7 = e.b();
        k kVar = new k(str);
        kVar.f6754b = 2;
        b7.f(kVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRetry() {
        int i7;
        if (!this.mIsStarted || (i7 = this.mRetryCount) >= 5) {
            return;
        }
        this.mRetryCount = i7 + 1;
        this.mHandler.postDelayed(new Runnable() { // from class: com.navi.tool.amapflying.util.kugou.KugouMusicManager.5
            @Override // java.lang.Runnable
            public void run() {
                if (KugouMusicManager.this.mIsStarted) {
                    KugouMusicManager.this.startInternal();
                }
            }
        }, PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
    }

    public static void setDoubleLine(boolean z6) {
        sIsDoubleLine = z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInternal() {
        String str;
        if (this.mContext == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.kugou.android.auto", "com.kugou.auto.proxy.service.KugouAutoService"));
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 131072);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.kugou.android.auto");
            queryIntentServices = packageManager.queryIntentServices(intent2, 131072);
        }
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && (str = serviceInfo.name) != null && (str.contains("KugouAutoService") || resolveInfo.serviceInfo.name.contains("Proxy") || resolveInfo.serviceInfo.name.contains("AutoSdkAIDLRemoteService"))) {
                    ServiceInfo serviceInfo2 = resolveInfo.serviceInfo;
                    intent.setComponent(new ComponentName(serviceInfo2.packageName, serviceInfo2.name));
                    String str2 = resolveInfo.serviceInfo.name;
                    break;
                }
            }
        }
        try {
            if (intent.getComponent() != null) {
                this.mContext.startForegroundService(intent);
            }
            boolean bindService = this.mContext.bindService(intent, this.mConnection, 1);
            Objects.toString(intent.getComponent());
            if (!bindService) {
                scheduleRetry();
            } else {
                this.mIsBound = true;
                this.mRetryCount = 0;
            }
        } catch (Exception unused) {
            scheduleRetry();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLyricDisplay(long j7) {
        Map.Entry<Long, String> higherEntry;
        String str = h0.FRAGMENT_ENCODE_SET;
        synchronized (this.mLyricMap) {
            try {
                if (this.mLyricMap.isEmpty()) {
                    return;
                }
                Map.Entry<Long, String> floorEntry = this.mLyricMap.floorEntry(Long.valueOf(j7));
                if (floorEntry != null) {
                    str = floorEntry.getValue();
                    if (sIsDoubleLine && (higherEntry = this.mLyricMap.higherEntry(floorEntry.getKey())) != null) {
                        str = str + "\n" + higherEntry.getValue();
                    }
                }
                if (TextUtils.equals(str, this.mLastPostedLyric)) {
                    return;
                }
                postLyric(str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void refreshState() {
        IKugouAutoService iKugouAutoService = this.mService;
        if (iKugouAutoService != null) {
            try {
                iKugouAutoService.sendCommand("get_music_info", new Bundle());
                this.mService.sendCommand("get_play_state", new Bundle());
            } catch (RemoteException unused) {
            }
        }
        if (this.mCurrentMusic != null) {
            synchronized (this.mLyricMap) {
                try {
                    if (!this.mLyricMap.isEmpty()) {
                        e b7 = e.b();
                        l lVar = new l(new TreeMap((SortedMap) this.mLyricMap));
                        lVar.f6756b = 2;
                        b7.f(lVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            KgMusic kgMusic = this.mCurrentMusic;
            String str = kgMusic.musicName;
            String str2 = kgMusic.singerName;
            e b8 = e.b();
            m mVar = new m(this.mIsPlaying, str, str2, this.mLastPosition);
            mVar.f6768l = 2;
            b8.f(mVar);
        }
    }

    public void start() {
        if (this.mIsStarted) {
            return;
        }
        this.mIsStarted = true;
        this.mRetryCount = 0;
        startInternal();
    }

    public void stop() {
        IKugouAutoService iKugouAutoService;
        if (this.mIsStarted) {
            this.mIsStarted = false;
            if (this.mIsBound && (iKugouAutoService = this.mService) != null) {
                try {
                    iKugouAutoService.unregisterListener(this.mListener);
                } catch (RemoteException unused) {
                }
                this.mContext.unbindService(this.mConnection);
                this.mIsBound = false;
                this.mService = null;
            }
            postLyric(h0.FRAGMENT_ENCODE_SET);
            this.mHandler.removeCallbacks(this.mUpdateTask);
        }
    }
}
