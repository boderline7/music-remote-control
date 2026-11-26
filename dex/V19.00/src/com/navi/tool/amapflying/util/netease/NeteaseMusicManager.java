package com.navi.tool.amapflying.util.netease;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.d;
import android.support.v4.media.g;
import android.support.v4.media.m;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.h;
import android.support.v4.media.session.p;
import android.support.v4.media.session.r;
import android.text.TextUtils;
import com.navi.tool.amapflying.util.netease.NativeNeteaseEngine;
import com.navi.tool.service.PhoneNotificationRelayService;
import h4.a;
import h4.k;
import h4.l;
import i6.e;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NeteaseMusicManager {
    private static final String KEY_METADATA_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    private static final String KEY_METADATA_ART_URI = "android.media.metadata.ART_URI";
    private static final String KEY_METADATA_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    private static final int MAX_RETRY_COUNT = 5;
    private static final String PACKAGE_NAME = "com.netease.cloudmusic.iot";
    private static final String SERVICE_NAME = "com.netease.cloudmusic.service.AutomotiveService";
    private static final String TAG = "NeteaseMusicManager";
    private static final long UPDATE_INTERVAL = 200;
    private static NeteaseMusicManager sInstance;
    private Context mContext;
    private m mMediaBrowser;
    private r mMediaController;
    private static final Pattern LRC_PATTERN = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})\\.(\\d{1,3})\\](.*)");
    private static boolean sIsDoubleLine = false;
    private boolean mIsConnected = false;
    private boolean mIsPlaying = false;
    private String mCurrentTitle = h0.FRAGMENT_ENCODE_SET;
    private String mCurrentArtist = h0.FRAGMENT_ENCODE_SET;
    private String mCurrentSongId = h0.FRAGMENT_ENCODE_SET;
    private String mCurrentCoverUrl = h0.FRAGMENT_ENCODE_SET;
    private long mLastPosition = 0;
    private long mTotalDuration = 0;
    private long mLastPositionUpdateTime = 0;
    private String mPendingKeyword = null;
    private boolean mUseElapsedRealtime = false;
    private TreeMap<Long, String> mLyricMap = new TreeMap<>();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
    private int mRetryCount = 0;
    private boolean mIsStarted = false;
    private final d mConnectionCallback = new d() { // from class: com.navi.tool.amapflying.util.netease.NeteaseMusicManager.3
        @Override // android.support.v4.media.d
        public void onConnected() {
            NeteaseMusicManager.this.mIsConnected = true;
            NeteaseMusicManager.this.mRetryCount = 0;
            try {
                NeteaseMusicManager neteaseMusicManager = NeteaseMusicManager.this;
                Context context = neteaseMusicManager.mContext;
                g gVar = NeteaseMusicManager.this.mMediaBrowser.f551a;
                if (gVar.f539h == null) {
                    gVar.f539h = MediaSessionCompat$Token.l(gVar.f533b.getSessionToken(), null);
                }
                neteaseMusicManager.mMediaController = new r(context, gVar.f539h);
                NeteaseMusicManager.this.mMediaController.d(NeteaseMusicManager.this.mControllerCallback);
                PlaybackStateCompat b7 = NeteaseMusicManager.this.mMediaController.b();
                if (b7 != null) {
                    NeteaseMusicManager.this.updatePlaybackState(b7);
                }
                MediaMetadataCompat a7 = NeteaseMusicManager.this.mMediaController.a();
                if (a7 != null) {
                    NeteaseMusicManager.this.updateMetadata(a7);
                }
                if (TextUtils.isEmpty(NeteaseMusicManager.this.mPendingKeyword)) {
                    return;
                }
                String str = NeteaseMusicManager.this.mPendingKeyword;
                NeteaseMusicManager.this.mPendingKeyword = null;
                NeteaseMusicManager.this.searchAndPlayInternal(str);
            } catch (Exception unused) {
            }
        }

        @Override // android.support.v4.media.d
        public void onConnectionFailed() {
            NeteaseMusicManager.this.mIsConnected = false;
            NeteaseMusicManager.this.scheduleRetry();
        }

        @Override // android.support.v4.media.d
        public void onConnectionSuspended() {
            NeteaseMusicManager.this.mIsConnected = false;
            NeteaseMusicManager.this.mMediaController = null;
            NeteaseMusicManager.this.stopTimer();
            NeteaseMusicManager.this.scheduleRetry();
        }
    };
    private final h mControllerCallback = new h() { // from class: com.navi.tool.amapflying.util.netease.NeteaseMusicManager.4
        @Override // android.support.v4.media.session.h
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                NeteaseMusicManager.this.updateMetadata(mediaMetadataCompat);
            }
        }

        @Override // android.support.v4.media.session.h
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat != null) {
                NeteaseMusicManager.this.updatePlaybackState(playbackStateCompat);
            }
        }
    };
    private Runnable mUpdateTask = new Runnable() { // from class: com.navi.tool.amapflying.util.netease.NeteaseMusicManager.5
        @Override // java.lang.Runnable
        public void run() {
            long j7;
            long currentTimeMillis;
            long j8;
            if (NeteaseMusicManager.this.mIsPlaying) {
                if (NeteaseMusicManager.this.mUseElapsedRealtime) {
                    j7 = NeteaseMusicManager.this.mLastPosition;
                    currentTimeMillis = SystemClock.elapsedRealtime();
                    j8 = NeteaseMusicManager.this.mLastPositionUpdateTime;
                } else {
                    j7 = NeteaseMusicManager.this.mLastPosition;
                    currentTimeMillis = System.currentTimeMillis();
                    j8 = NeteaseMusicManager.this.mLastPositionUpdateTime;
                }
                long j9 = (currentTimeMillis - j8) + j7;
                NeteaseMusicManager.this.updateLyricDisplay(j9);
                NeteaseMusicManager.this.postMusicStatusEvent(j9);
                NeteaseMusicManager.this.mHandler.postDelayed(this, NeteaseMusicManager.UPDATE_INTERVAL);
            }
        }
    };

    private NeteaseMusicManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private void dispatchAlbumArt(MediaMetadataCompat mediaMetadataCompat) {
        Bitmap m6 = mediaMetadataCompat.m(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON);
        if (m6 == null) {
            m6 = mediaMetadataCompat.m(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
        }
        if (m6 == null) {
            m6 = mediaMetadataCompat.m(MediaMetadataCompat.METADATA_KEY_ART);
        }
        if (m6 != null) {
            e b7 = e.b();
            a aVar = new a();
            aVar.f6724b = m6;
            aVar.f6725c = 3;
            b7.f(aVar);
            return;
        }
        if (TextUtils.isEmpty(this.mCurrentCoverUrl)) {
            return;
        }
        e b8 = e.b();
        a aVar2 = new a(this.mCurrentCoverUrl);
        aVar2.f6725c = 3;
        b8.f(aVar2);
    }

    private String extractCoverUrl(MediaMetadataCompat mediaMetadataCompat) {
        String o6 = mediaMetadataCompat.o("android.media.metadata.DISPLAY_ICON_URI");
        if (TextUtils.isEmpty(o6)) {
            o6 = mediaMetadataCompat.o("android.media.metadata.ART_URI");
        }
        if (TextUtils.isEmpty(o6)) {
            o6 = mediaMetadataCompat.o("android.media.metadata.ALBUM_ART_URI");
        }
        if (TextUtils.isEmpty(o6) && mediaMetadataCompat.n() != null && mediaMetadataCompat.n().f518f != null) {
            o6 = mediaMetadataCompat.n().f518f.toString();
        }
        return o6 == null ? h0.FRAGMENT_ENCODE_SET : o6;
    }

    private long getCurrentPlaybackPosition() {
        long max = Math.max(0L, this.mLastPosition);
        if (this.mIsPlaying) {
            return (this.mUseElapsedRealtime ? Math.max(0L, SystemClock.elapsedRealtime() - this.mLastPositionUpdateTime) : Math.max(0L, System.currentTimeMillis() - this.mLastPositionUpdateTime)) + max;
        }
        return max;
    }

    public static synchronized NeteaseMusicManager getInstance(Context context) {
        NeteaseMusicManager neteaseMusicManager;
        synchronized (NeteaseMusicManager.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NeteaseMusicManager(context);
                }
                neteaseMusicManager = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return neteaseMusicManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseLyrics(String str) {
        synchronized (this.mLyricMap) {
            this.mLyricMap.clear();
            for (String str2 : str.split("\n")) {
                String trim = str2.replaceAll("<[^>]+>", h0.FRAGMENT_ENCODE_SET).trim();
                if (!TextUtils.isEmpty(trim)) {
                    Matcher matcher = LRC_PATTERN.matcher(trim);
                    if (matcher.find()) {
                        try {
                            int parseInt = Integer.parseInt(matcher.group(1));
                            int parseInt2 = Integer.parseInt(matcher.group(2));
                            String group = matcher.group(3);
                            int parseInt3 = Integer.parseInt(group);
                            if (group.length() == 1) {
                                parseInt3 *= 100;
                            } else if (group.length() == 2) {
                                parseInt3 *= 10;
                            }
                            long j7 = (parseInt2 * 1000) + (parseInt * 60000) + parseInt3;
                            String trim2 = matcher.group(4).trim();
                            if (!TextUtils.isEmpty(trim2)) {
                                this.mLyricMap.put(Long.valueOf(j7), trim2);
                            }
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                }
            }
        }
        this.mLyricMap.size();
        e b7 = e.b();
        l lVar = new l(this.mLyricMap);
        lVar.f6756b = 3;
        b7.f(lVar);
    }

    private void postLyric(String str) {
        this.mLastPostedLyric = str;
        e b7 = e.b();
        k kVar = new k(str);
        kVar.f6754b = 3;
        b7.f(kVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postMusicStatusEvent(long j7) {
        h4.m mVar = new h4.m(this.mCurrentTitle, this.mCurrentArtist, this.mIsPlaying, j7, this.mTotalDuration);
        mVar.f6768l = 3;
        mVar.f6765i = this.mCurrentSongId;
        if (!TextUtils.isEmpty(this.mCurrentCoverUrl)) {
            mVar.f6762f = this.mCurrentCoverUrl;
        }
        e.b().f(mVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRetry() {
        int i7;
        if (!this.mIsStarted || (i7 = this.mRetryCount) >= 5) {
            return;
        }
        this.mRetryCount = i7 + 1;
        this.mHandler.postDelayed(new Runnable() { // from class: com.navi.tool.amapflying.util.netease.NeteaseMusicManager.2
            @Override // java.lang.Runnable
            public void run() {
                if (NeteaseMusicManager.this.mIsStarted) {
                    NeteaseMusicManager.this.startInternal();
                }
            }
        }, PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchAndPlayInternal(String str) {
        if (this.mMediaController == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("keywords", str);
            r rVar = this.mMediaController;
            rVar.getClass();
            if (TextUtils.isEmpty("search")) {
                throw new IllegalArgumentException("command must neither be null nor empty");
            }
            rVar.f600a.f594a.sendCommand("search", bundle, null);
        } catch (Exception unused) {
        }
    }

    private void searchLyric(String str, String str2) {
        NativeNeteaseEngine.getInstance().searchAndFetch(str + " " + str2);
    }

    public static void setDoubleLine(boolean z6) {
        sIsDoubleLine = z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInternal() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(PACKAGE_NAME, SERVICE_NAME));
            this.mContext.startForegroundService(intent);
        } catch (Exception unused) {
        }
        if (this.mMediaBrowser == null) {
            this.mMediaBrowser = new m(this.mContext, new ComponentName(PACKAGE_NAME, SERVICE_NAME), this.mConnectionCallback);
        }
        if (!this.mMediaBrowser.f551a.f533b.isConnected()) {
            try {
                this.mMediaBrowser.a();
            } catch (Exception unused2) {
            }
            try {
                this.mMediaBrowser.f551a.f533b.connect();
            } catch (Exception unused3) {
                scheduleRetry();
            }
        }
        NativeNeteaseEngine.getInstance().setCallback(new NativeNeteaseEngine.Callback() { // from class: com.navi.tool.amapflying.util.netease.NeteaseMusicManager.1
            @Override // com.navi.tool.amapflying.util.netease.NativeNeteaseEngine.Callback
            public void onLyricsResolved(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                NeteaseMusicManager.this.parseLyrics(str);
            }
        });
    }

    private void startTimer() {
        stopTimer();
        this.mHandler.post(this.mUpdateTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTimer() {
        this.mHandler.removeCallbacks(this.mUpdateTask);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMetadata(MediaMetadataCompat mediaMetadataCompat) {
        String o6 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_TITLE);
        String o7 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_ARTIST);
        String o8 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
        this.mTotalDuration = mediaMetadataCompat.f525a.getLong(MediaMetadataCompat.METADATA_KEY_DURATION, 0L);
        this.mCurrentCoverUrl = extractCoverUrl(mediaMetadataCompat);
        dispatchAlbumArt(mediaMetadataCompat);
        if (TextUtils.isEmpty(o6)) {
            o6 = "网易云音乐";
        }
        if (TextUtils.isEmpty(o7)) {
            o7 = "未知歌手";
        }
        if (!TextUtils.equals(this.mCurrentTitle, o6) || !TextUtils.equals(this.mCurrentArtist, o7) || !TextUtils.equals(this.mCurrentSongId, o8)) {
            this.mCurrentTitle = o6;
            this.mCurrentArtist = o7;
            this.mCurrentSongId = o8;
            this.mLastPosition = 0L;
            if (this.mUseElapsedRealtime) {
                this.mLastPositionUpdateTime = SystemClock.elapsedRealtime();
            } else {
                this.mLastPositionUpdateTime = System.currentTimeMillis();
            }
            this.mLyricMap.clear();
            this.mLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
            e b7 = e.b();
            l lVar = new l(new TreeMap());
            lVar.f6756b = 3;
            b7.f(lVar);
            postLyric(h0.FRAGMENT_ENCODE_SET);
            searchLyric(o6, o7);
        }
        postMusicStatusEvent(getCurrentPlaybackPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaybackState(PlaybackStateCompat playbackStateCompat) {
        boolean z6 = playbackStateCompat.f573a == 3;
        this.mLastPosition = playbackStateCompat.f574b;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        System.currentTimeMillis();
        long j7 = playbackStateCompat.f580h;
        if (j7 <= 0) {
            this.mLastPositionUpdateTime = System.currentTimeMillis();
            this.mUseElapsedRealtime = false;
        } else if (j7 > elapsedRealtime + 10000000) {
            this.mLastPositionUpdateTime = j7;
            this.mUseElapsedRealtime = false;
        } else {
            this.mLastPositionUpdateTime = j7;
            this.mUseElapsedRealtime = true;
        }
        if (this.mIsPlaying != z6) {
            this.mIsPlaying = z6;
            if (z6) {
                startTimer();
            } else {
                stopTimer();
            }
        }
        postMusicStatusEvent(this.mLastPosition);
    }

    public void dispatchControl(boolean z6, int i7) {
        if (this.mMediaController == null) {
            start();
        }
        r rVar = this.mMediaController;
        if (rVar == null) {
            return;
        }
        MediaController.TransportControls transportControls = rVar.c().f599a;
        try {
            if (i7 == 0) {
                if (z6) {
                    transportControls.pause();
                } else {
                    transportControls.play();
                }
            } else if (i7 == 1) {
                transportControls.skipToPrevious();
            } else if (i7 != 2) {
            } else {
                transportControls.skipToNext();
            }
        } catch (Exception unused) {
        }
    }

    public void favoriteCurrentSong() {
        if (this.mMediaController == null) {
            start();
        }
        r rVar = this.mMediaController;
        if (rVar == null) {
            return;
        }
        p c7 = rVar.c();
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_star", true);
            c7.f599a.sendCustomAction("star_action", bundle);
        } catch (Exception unused) {
        }
    }

    public boolean isControllerReady() {
        r rVar = this.mMediaController;
        if (rVar == null) {
            return false;
        }
        rVar.c();
        return true;
    }

    public void refreshState() {
        if (this.mIsConnected) {
            r rVar = this.mMediaController;
            if (rVar != null) {
                PlaybackStateCompat b7 = rVar.b();
                if (b7 != null) {
                    updatePlaybackState(b7);
                }
                MediaMetadataCompat a7 = this.mMediaController.a();
                if (a7 != null) {
                    updateMetadata(a7);
                }
            }
            synchronized (this.mLyricMap) {
                try {
                    if (!this.mLyricMap.isEmpty()) {
                        e b8 = e.b();
                        l lVar = new l(new TreeMap((SortedMap) this.mLyricMap));
                        lVar.f6756b = 3;
                        b8.f(lVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            postMusicStatusEvent(getCurrentPlaybackPosition());
        }
    }

    public void searchAndPlay(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.mMediaController != null) {
            searchAndPlayInternal(str);
        } else {
            this.mPendingKeyword = str;
            start();
        }
    }

    public void start() {
        this.mRetryCount = 0;
        this.mIsStarted = true;
        startInternal();
    }

    public void stop() {
        this.mIsStarted = false;
        r rVar = this.mMediaController;
        if (rVar != null) {
            try {
                rVar.e(this.mControllerCallback);
            } catch (Exception unused) {
            }
        }
        m mVar = this.mMediaBrowser;
        if (mVar != null) {
            if (mVar.f551a.f533b.isConnected()) {
                this.mMediaBrowser.a();
            }
            this.mMediaBrowser = null;
        }
        this.mMediaController = null;
        this.mIsConnected = false;
        this.mIsPlaying = false;
        stopTimer();
        this.mLyricMap.clear();
        this.mCurrentCoverUrl = h0.FRAGMENT_ENCODE_SET;
        this.mLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
        e b7 = e.b();
        h4.m mVar2 = new h4.m(h0.FRAGMENT_ENCODE_SET, h0.FRAGMENT_ENCODE_SET, false, 0L, 0L);
        mVar2.f6768l = 3;
        b7.f(mVar2);
        NativeNeteaseEngine.getInstance().setCallback(null);
    }

    public void unfavoriteCurrentSong() {
        if (this.mMediaController == null) {
            start();
        }
        r rVar = this.mMediaController;
        if (rVar == null) {
            return;
        }
        p c7 = rVar.c();
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_star", false);
            c7.f599a.sendCustomAction("star_action", bundle);
        } catch (Exception unused) {
        }
    }
}
