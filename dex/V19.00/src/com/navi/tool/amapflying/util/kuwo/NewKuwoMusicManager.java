package com.navi.tool.amapflying.util.kuwo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.kuwo.base.bean.Music;
import com.navi.tool.service.PhoneNotificationRelayService;
import h4.l;
import h4.m;
import i6.e;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import s1.a;
import s1.c;
import s1.d;
import s1.f;
import s1.g;
import s1.h;
import s1.i;
import s1.n;
import s1.u;
import s1.v;
import s5.h0;
import t1.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NewKuwoMusicManager {
    private static final int MAX_RETRY_COUNT = 5;
    private static final String TAG = "NewKuwoMusicManager";
    private static final long UPDATE_INTERVAL = 200;
    private static NewKuwoMusicManager sInstance;
    private Context mContext;
    private Music mCurrentMusicObj;
    private c mKwapi;
    private KuwoAidlPositionHelper mPositionHelper;
    private static final Pattern LRC_PATTERN = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})\\.(\\d{1,3})\\](.*)");
    private static boolean sIsDoubleLine = false;
    private long mCurrentRid = 0;
    private boolean mIsPlaying = false;
    private boolean mIsConnected = false;
    private boolean mIsRequesting = false;
    private String mPendingKeyword = null;
    private TreeMap<Long, String> mLyricMap = new TreeMap<>();
    private String mLastPostedLyric = h0.FRAGMENT_ENCODE_SET;
    private long mLastCoverRid = 0;
    private String mLastCoverUrl = null;
    private boolean mIsRequestingCover = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int mRetryCount = 0;
    private final f mLyricsListener = new f() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.1
        @Override // s1.f
        public void sendSyncNotice_LyricsFailed(Music music) {
            if (NewKuwoMusicManager.this.isCurrentMusic(music)) {
                NewKuwoMusicManager.this.mIsRequesting = false;
                Objects.toString(music != null ? Long.valueOf(music.f3163b) : "null");
            }
        }

        @Override // s1.f
        public void sendSyncNotice_LyricsFinished(Music music, String str) {
            if (!NewKuwoMusicManager.this.isCurrentMusic(music)) {
                Objects.toString(music != null ? Long.valueOf(music.f3163b) : "null");
                return;
            }
            NewKuwoMusicManager.this.mIsRequesting = false;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            NewKuwoMusicManager.this.parseLyrics(str);
        }

        @Override // s1.f
        public void sendSyncNotice_LyricsNone(Music music) {
            if (NewKuwoMusicManager.this.isCurrentMusic(music)) {
                NewKuwoMusicManager.this.mIsRequesting = false;
                Objects.toString(music != null ? Long.valueOf(music.f3163b) : "null");
            }
        }

        @Override // s1.f
        public void sendSyncNotice_LyricsStart(Music music) {
            Objects.toString(music != null ? Long.valueOf(music.f3163b) : "null");
        }
    };
    private Runnable mUpdateTask = new Runnable() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.2
        @Override // java.lang.Runnable
        public void run() {
            if (!NewKuwoMusicManager.this.mIsPlaying || NewKuwoMusicManager.this.mKwapi == null) {
                return;
            }
            NewKuwoMusicManager.this.checkCurrentMusic();
            long currentPositionCompat = NewKuwoMusicManager.this.getCurrentPositionCompat();
            NewKuwoMusicManager.this.updateLyricDisplay(currentPositionCompat);
            Music d7 = NewKuwoMusicManager.this.mKwapi.d();
            m mVar = new m(d7 != null ? d7.f3164c : "酷我音乐", d7 != null ? d7.f3165d : h0.FRAGMENT_ENCODE_SET, true, currentPositionCompat, d7 != null ? d7.f3168g : 0L);
            mVar.f6768l = 4;
            if (d7 != null) {
                mVar.f6763g = String.valueOf(d7.f3163b);
                NewKuwoMusicManager.this.attachSdkCoverToEvent(d7, mVar);
            }
            e.b().f(mVar);
            NewKuwoMusicManager.this.mHandler.postDelayed(this, NewKuwoMusicManager.UPDATE_INTERVAL);
        }
    };
    private Runnable mRetryTask = new Runnable() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.5
        @Override // java.lang.Runnable
        public void run() {
            NewKuwoMusicManager.this.startInternal();
        }
    };
    private i mPlayerStatusListener = new i() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.6
        @Override // s1.i
        public void onPlayerStatus(v vVar, Music music) {
            Objects.toString(vVar);
            Objects.toString(music != null ? Long.valueOf(music.f3163b) : "null");
            NewKuwoMusicManager.this.mIsConnected = true;
            boolean z6 = vVar == v.PLAYING || vVar == v.BUFFERING;
            if (music != null) {
                NewKuwoMusicManager.this.handleMusicChange(music);
            }
            if (z6 && !NewKuwoMusicManager.this.mIsPlaying) {
                NewKuwoMusicManager.this.mIsPlaying = true;
                NewKuwoMusicManager.this.startTimer();
                return;
            }
            if (z6 || !NewKuwoMusicManager.this.mIsPlaying) {
                return;
            }
            NewKuwoMusicManager.this.mIsPlaying = false;
            NewKuwoMusicManager.this.stopTimer();
            if (NewKuwoMusicManager.this.mKwapi != null) {
                long currentPositionCompat = NewKuwoMusicManager.this.getCurrentPositionCompat();
                NewKuwoMusicManager.this.updateLyricDisplay(currentPositionCompat);
                m mVar = new m(NewKuwoMusicManager.this.mCurrentMusicObj != null ? NewKuwoMusicManager.this.mCurrentMusicObj.f3164c : "新酷我音乐", NewKuwoMusicManager.this.mCurrentMusicObj != null ? NewKuwoMusicManager.this.mCurrentMusicObj.f3165d : h0.FRAGMENT_ENCODE_SET, false, currentPositionCompat, NewKuwoMusicManager.this.mCurrentMusicObj != null ? NewKuwoMusicManager.this.mCurrentMusicObj.f3168g : 0L);
                mVar.f6768l = 4;
                if (NewKuwoMusicManager.this.mCurrentMusicObj != null) {
                    mVar.f6763g = String.valueOf(NewKuwoMusicManager.this.mCurrentMusicObj.f3163b);
                    NewKuwoMusicManager newKuwoMusicManager = NewKuwoMusicManager.this;
                    newKuwoMusicManager.attachSdkCoverToEvent(newKuwoMusicManager.mCurrentMusicObj, mVar);
                }
                e.b().f(mVar);
            }
        }
    };
    private h mPlayerModeListener = new h() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.7
        @Override // s1.h
        public void onPlayerMode(int i7) {
            int i8 = 1;
            if (i7 != 0) {
                if (i7 != 1) {
                    i8 = 2;
                    if (i7 == 2) {
                        i8 = 0;
                    } else if (i7 != 3) {
                        return;
                    }
                } else {
                    i8 = 3;
                }
            }
            m mVar = new m(NewKuwoMusicManager.this.mIsPlaying, NewKuwoMusicManager.this.mCurrentMusicObj != null ? NewKuwoMusicManager.this.mCurrentMusicObj.f3164c : h0.FRAGMENT_ENCODE_SET, NewKuwoMusicManager.this.mCurrentMusicObj != null ? NewKuwoMusicManager.this.mCurrentMusicObj.f3165d : h0.FRAGMENT_ENCODE_SET, 0L);
            mVar.f6768l = 4;
            mVar.f6767k = i8;
            e.b().f(mVar);
        }
    };

    private NewKuwoMusicManager(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPositionHelper = new KuwoAidlPositionHelper(applicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attachSdkCoverToEvent(Music music, m mVar) {
        if (music == null || mVar == null) {
            return;
        }
        String sdkCoverUrl = getSdkCoverUrl(music);
        if (TextUtils.isEmpty(sdkCoverUrl)) {
            requestCoverFromSdk(music);
        } else {
            mVar.f6762f = sdkCoverUrl;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheCoverUrl(long j7, String str) {
        if (j7 == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        this.mLastCoverRid = j7;
        this.mLastCoverUrl = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCurrentMusic() {
        Music d7;
        c cVar = this.mKwapi;
        if (cVar == null || (d7 = cVar.d()) == null) {
            return;
        }
        handleMusicChange(d7);
    }

    private void fetchLyrics(String str) {
        Music music;
        c cVar = this.mKwapi;
        if (cVar == null || (music = this.mCurrentMusicObj) == null) {
            this.mIsRequesting = false;
            return;
        }
        try {
            k.a(new cn.kuwo.autosdk.e(music, cVar.f8656b.f3107a, this.mLyricsListener));
        } catch (Exception unused) {
            this.mIsRequesting = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentPositionCompat() {
        int currentPosition = this.mPositionHelper.getCurrentPosition();
        if (currentPosition >= 0) {
            return currentPosition;
        }
        if (this.mKwapi != null) {
            return r0.c();
        }
        return 0L;
    }

    public static synchronized NewKuwoMusicManager getInstance(Context context) {
        NewKuwoMusicManager newKuwoMusicManager;
        synchronized (NewKuwoMusicManager.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NewKuwoMusicManager(context);
                }
                newKuwoMusicManager = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return newKuwoMusicManager;
    }

    private String getSdkCoverUrl(Music music) {
        if (music == null) {
            return null;
        }
        String normalizeCoverUrl = normalizeCoverUrl(music.f3180s);
        if (!TextUtils.isEmpty(normalizeCoverUrl)) {
            cacheCoverUrl(music.f3163b, normalizeCoverUrl);
            return normalizeCoverUrl;
        }
        long j7 = music.f3163b;
        if (j7 == 0 || j7 != this.mLastCoverRid || TextUtils.isEmpty(this.mLastCoverUrl)) {
            return null;
        }
        return this.mLastCoverUrl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMusicChange(Music music) {
        boolean isEmpty;
        if (music == null) {
            return;
        }
        this.mCurrentMusicObj = music;
        boolean z6 = music.f3163b != this.mCurrentRid;
        synchronized (this.mLyricMap) {
            isEmpty = this.mLyricMap.isEmpty();
        }
        if (z6 || isEmpty) {
            long j7 = music.f3163b;
            if (j7 == 0 || this.mIsRequesting) {
                return;
            }
            this.mCurrentRid = j7;
            if (z6) {
                resetCoverCache(j7);
                synchronized (this.mLyricMap) {
                    this.mLyricMap.clear();
                }
                postLyric(h0.FRAGMENT_ENCODE_SET);
            }
            requestCoverFromSdk(music);
            this.mIsRequesting = true;
            fetchLyrics(String.valueOf(this.mCurrentRid));
        }
    }

    private void initListeners() {
        c cVar = this.mKwapi;
        d dVar = new d() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.3
            @Override // s1.d
            public void onConnectChangeListener(boolean z6) {
                NewKuwoMusicManager.this.mIsConnected = z6;
                if (!z6) {
                    NewKuwoMusicManager.this.mIsPlaying = false;
                    NewKuwoMusicManager.this.stopTimer();
                    NewKuwoMusicManager.this.scheduleRetry();
                    return;
                }
                NewKuwoMusicManager.this.mRetryCount = 0;
                NewKuwoMusicManager.this.mHandler.removeCallbacks(NewKuwoMusicManager.this.mRetryTask);
                if (NewKuwoMusicManager.this.mKwapi != null) {
                    if (!TextUtils.isEmpty(NewKuwoMusicManager.this.mPendingKeyword)) {
                        String unused = NewKuwoMusicManager.this.mPendingKeyword;
                        NewKuwoMusicManager.this.mKwapi.g(NewKuwoMusicManager.this.mPendingKeyword);
                        NewKuwoMusicManager.this.mPendingKeyword = null;
                    }
                    v e7 = NewKuwoMusicManager.this.mKwapi.e();
                    if (e7 == v.PLAYING || e7 == v.BUFFERING) {
                        NewKuwoMusicManager.this.mIsPlaying = true;
                        NewKuwoMusicManager.this.startTimer();
                    }
                    NewKuwoMusicManager.this.checkCurrentMusic();
                }
            }
        };
        cVar.f8656b.getClass();
        a.a().f8652b = dVar;
        this.mKwapi.f8657c = new s1.e() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.4
            @Override // s1.e
            public void onExit() {
                NewKuwoMusicManager.this.mIsPlaying = false;
                NewKuwoMusicManager.this.stopTimer();
                NewKuwoMusicManager.this.postLyric(h0.FRAGMENT_ENCODE_SET);
                NewKuwoMusicManager.this.scheduleRetry();
            }
        };
        c cVar2 = this.mKwapi;
        i iVar = this.mPlayerStatusListener;
        cn.kuwo.autosdk.d dVar2 = cVar2.f8656b;
        if (!dVar2.f3116j) {
            cn.kuwo.autosdk.d.a(cVar2.f8658d, "cn.kuwo.kwmusicauto.action.PLAYER_STATUS", dVar2.f3115i);
            dVar2.f3116j = true;
        }
        dVar2.f3114h = iVar;
        c cVar3 = this.mKwapi;
        h hVar = this.mPlayerModeListener;
        cn.kuwo.autosdk.d dVar3 = cVar3.f8656b;
        if (!dVar3.f3117k) {
            cn.kuwo.autosdk.d.a(cVar3.f8658d, "cn.kuwo.kwmusicauto.action.PLAYER_MODE", dVar3.f3108b);
            dVar3.f3117k = true;
        }
        dVar3.f3109c = hVar;
        this.mKwapi.f();
        checkCurrentMusic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCurrentMusic(Music music) {
        if (music != null) {
            long j7 = music.f3163b;
            if (j7 != 0 && j7 == this.mCurrentRid) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String normalizeCoverUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("http")) {
            return trim;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0080 A[Catch: all -> 0x008a, Exception -> 0x008c, TRY_LEAVE, TryCatch #0 {Exception -> 0x008c, blocks: (B:15:0x0038, B:18:0x005a, B:19:0x0066, B:21:0x0080, B:24:0x005d), top: B:14:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseLyrics(java.lang.String r19) {
        /*
            r18 = this;
            r1 = r18
            java.util.TreeMap<java.lang.Long, java.lang.String> r2 = r1.mLyricMap
            monitor-enter(r2)
            java.util.TreeMap<java.lang.Long, java.lang.String> r0 = r1.mLyricMap     // Catch: java.lang.Throwable -> L8a
            r0.clear()     // Catch: java.lang.Throwable -> L8a
            java.lang.String r0 = "\n"
            r3 = r19
            java.lang.String[] r0 = r3.split(r0)     // Catch: java.lang.Throwable -> L8a
            int r3 = r0.length     // Catch: java.lang.Throwable -> L8a
            r4 = 0
        L14:
            if (r4 >= r3) goto L8f
            r6 = r0[r4]     // Catch: java.lang.Throwable -> L8a
            java.lang.String r7 = "<[^>]+>"
            java.lang.String r8 = ""
            java.lang.String r6 = r6.replaceAll(r7, r8)     // Catch: java.lang.Throwable -> L8a
            java.lang.String r6 = r6.trim()     // Catch: java.lang.Throwable -> L8a
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> L8a
            if (r7 == 0) goto L2b
            goto L8c
        L2b:
            java.util.regex.Pattern r7 = com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.LRC_PATTERN     // Catch: java.lang.Throwable -> L8a
            java.util.regex.Matcher r6 = r7.matcher(r6)     // Catch: java.lang.Throwable -> L8a
            boolean r7 = r6.find()     // Catch: java.lang.Throwable -> L8a
            if (r7 == 0) goto L8c
            r7 = 1
            java.lang.String r8 = r6.group(r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            long r8 = java.lang.Long.parseLong(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r10 = 2
            java.lang.String r11 = r6.group(r10)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            long r11 = java.lang.Long.parseLong(r11)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r13 = 3
            java.lang.String r13 = r6.group(r13)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            long r14 = java.lang.Long.parseLong(r13)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            int r5 = r13.length()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            if (r5 != r7) goto L5d
            r16 = 100
        L5a:
            long r14 = r14 * r16
            goto L66
        L5d:
            int r5 = r13.length()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            if (r5 != r10) goto L66
            r16 = 10
            goto L5a
        L66:
            r16 = 60000(0xea60, double:2.9644E-319)
            long r8 = r8 * r16
            r16 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 * r16
            long r11 = r11 + r8
            long r11 = r11 + r14
            r5 = 4
            java.lang.String r5 = r6.group(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            if (r6 != 0) goto L8c
            java.util.TreeMap<java.lang.Long, java.lang.String> r6 = r1.mLyricMap     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            java.lang.Long r7 = java.lang.Long.valueOf(r11)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            r6.put(r7, r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8c
            goto L8c
        L8a:
            r0 = move-exception
            goto Lac
        L8c:
            int r4 = r4 + 1
            goto L14
        L8f:
            java.util.TreeMap<java.lang.Long, java.lang.String> r0 = r1.mLyricMap     // Catch: java.lang.Throwable -> L8a
            r0.size()     // Catch: java.lang.Throwable -> L8a
            i6.e r0 = i6.e.b()     // Catch: java.lang.Throwable -> L8a
            h4.l r3 = new h4.l     // Catch: java.lang.Throwable -> L8a
            java.util.TreeMap r4 = new java.util.TreeMap     // Catch: java.lang.Throwable -> L8a
            java.util.TreeMap<java.lang.Long, java.lang.String> r5 = r1.mLyricMap     // Catch: java.lang.Throwable -> L8a
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L8a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L8a
            r4 = 4
            r3.f6756b = r4     // Catch: java.lang.Throwable -> L8a
            r0.f(r3)     // Catch: java.lang.Throwable -> L8a
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L8a
            return
        Lac:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L8a
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.parseLyrics(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postLyric(String str) {
        this.mLastPostedLyric = str;
        e b7 = e.b();
        h4.k kVar = new h4.k(str);
        kVar.f6754b = 4;
        b7.f(kVar);
    }

    private void requestCoverFromSdk(final Music music) {
        if (this.mKwapi == null || music == null || music.f3163b == 0) {
            return;
        }
        String normalizeCoverUrl = normalizeCoverUrl(music.f3180s);
        if (!TextUtils.isEmpty(normalizeCoverUrl)) {
            cacheCoverUrl(music.f3163b, normalizeCoverUrl);
            return;
        }
        if (music.f3163b != this.mLastCoverRid || (!this.mIsRequestingCover && TextUtils.isEmpty(this.mLastCoverUrl))) {
            resetCoverCache(music.f3163b);
            this.mIsRequestingCover = true;
            try {
                c cVar = this.mKwapi;
                g gVar = new g() { // from class: com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager.8
                    @Override // s1.g
                    public void onGetSongImgUrlFailed(Music music2, int i7) {
                        NewKuwoMusicManager.this.mIsRequestingCover = false;
                        if (music2 != null) {
                            return;
                        }
                        long j7 = music.f3163b;
                    }

                    @Override // s1.g
                    public void onGetSongImgUrlSucessed(Music music2, String str) {
                        NewKuwoMusicManager.this.mIsRequestingCover = false;
                        String normalizeCoverUrl2 = NewKuwoMusicManager.this.normalizeCoverUrl(str);
                        if (TextUtils.isEmpty(normalizeCoverUrl2)) {
                            return;
                        }
                        long j7 = music2 != null ? music2.f3163b : music.f3163b;
                        NewKuwoMusicManager.this.cacheCoverUrl(j7, normalizeCoverUrl2);
                        if (music2 != null) {
                            music2.f3180s = normalizeCoverUrl2;
                        }
                        if (NewKuwoMusicManager.this.mCurrentMusicObj == null || NewKuwoMusicManager.this.mCurrentMusicObj.f3163b != j7) {
                            return;
                        }
                        NewKuwoMusicManager.this.mCurrentMusicObj.f3180s = normalizeCoverUrl2;
                        e b7 = e.b();
                        h4.a aVar = new h4.a(normalizeCoverUrl2);
                        aVar.f6725c = 4;
                        b7.f(aVar);
                    }
                };
                cVar.f8656b.getClass();
                k.a(new cn.kuwo.autosdk.e(music, gVar));
            } catch (Exception unused) {
                this.mIsRequestingCover = false;
            }
        }
    }

    private void resetCoverCache(long j7) {
        this.mLastCoverRid = j7;
        this.mLastCoverUrl = null;
        this.mIsRequestingCover = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRetry() {
        int i7 = this.mRetryCount;
        if (i7 < 5) {
            this.mRetryCount = i7 + 1;
            this.mHandler.postDelayed(this.mRetryTask, PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
        }
    }

    public static void setDoubleLine(boolean z6) {
        sIsDoubleLine = z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInternal() {
        this.mPositionHelper.start();
        if (this.mKwapi == null) {
            this.mKwapi = c.b(this.mContext);
            initListeners();
        }
        this.mKwapi.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimer() {
        this.mHandler.removeCallbacks(this.mUpdateTask);
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

    public void dispatchControl(boolean z6, int i7) {
        if (this.mKwapi == null) {
            start();
        }
        c cVar = this.mKwapi;
        if (cVar == null) {
            return;
        }
        if (!this.mIsConnected) {
            try {
                cVar.a();
            } catch (Exception unused) {
            }
        }
        u uVar = i7 == 0 ? z6 ? u.STATE_PAUSE : u.STATE_PLAY : i7 == 1 ? u.STATE_PRE : i7 == 2 ? u.STATE_NEXT : null;
        if (uVar == null) {
            return;
        }
        try {
            this.mKwapi.j(uVar);
            uVar.toString();
        } catch (Exception unused2) {
        }
    }

    public void refreshState() {
        c cVar = this.mKwapi;
        if (cVar != null) {
            v e7 = cVar.e();
            boolean z6 = e7 == v.PLAYING || e7 == v.BUFFERING;
            this.mIsPlaying = z6;
            Music d7 = this.mKwapi.d();
            if (d7 == null) {
                stopTimer();
                e b7 = e.b();
                m mVar = new m("新酷我音乐", h0.FRAGMENT_ENCODE_SET, false, 0L, 0L);
                mVar.f6768l = 4;
                b7.f(mVar);
                return;
            }
            handleMusicChange(d7);
            long currentPositionCompat = getCurrentPositionCompat();
            if (z6) {
                startTimer();
            } else {
                stopTimer();
                updateLyricDisplay(currentPositionCompat);
            }
            synchronized (this.mLyricMap) {
                try {
                    if (!this.mLyricMap.isEmpty()) {
                        e b8 = e.b();
                        l lVar = new l(new TreeMap((SortedMap) this.mLyricMap));
                        lVar.f6756b = 4;
                        b8.f(lVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            m mVar2 = new m(d7.f3164c, d7.f3165d, z6, currentPositionCompat, d7.f3168g);
            mVar2.f6768l = 4;
            mVar2.f6763g = String.valueOf(d7.f3163b);
            attachSdkCoverToEvent(d7, mVar2);
            e.b().f(mVar2);
        }
    }

    public void searchAndPlay(String str) {
        v vVar;
        if (this.mKwapi == null) {
            start();
        }
        c cVar = this.mKwapi;
        if (cVar == null) {
            return;
        }
        try {
            vVar = cVar.e();
        } catch (Throwable unused) {
            vVar = null;
        }
        if (vVar != null || this.mIsConnected) {
            this.mIsConnected = true;
            this.mKwapi.g(str);
        } else {
            this.mPendingKeyword = str;
            this.mKwapi.a();
        }
    }

    public void setPlayMode(int i7) {
        n nVar;
        c cVar = this.mKwapi;
        if (cVar == null) {
            return;
        }
        if (i7 == 0) {
            nVar = n.MODE_ALL_CIRCLE;
        } else if (i7 == 1) {
            nVar = n.MODE_SINGLE_CIRCLE;
        } else if (i7 == 2) {
            nVar = n.MODE_ALL_RANDOM;
        } else if (i7 != 3) {
            return;
        } else {
            nVar = n.MODE_ALL_ORDER;
        }
        try {
            cVar.i(this.mContext, nVar);
            Objects.toString(nVar);
        } catch (Exception unused) {
        }
    }

    public void start() {
        this.mRetryCount = 0;
        startInternal();
    }

    public void stop() {
        this.mHandler.removeCallbacks(this.mRetryTask);
        c cVar = this.mKwapi;
        if (cVar != null) {
            cn.kuwo.autosdk.d dVar = cVar.f8656b;
            Context context = cVar.f8658d;
            if (dVar.f3116j) {
                try {
                    context.unregisterReceiver(dVar.f3115i);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                dVar.f3116j = false;
            }
            c cVar2 = this.mKwapi;
            cn.kuwo.autosdk.d dVar2 = cVar2.f8656b;
            Context context2 = cVar2.f8658d;
            if (dVar2.f3117k) {
                try {
                    context2.unregisterReceiver(dVar2.f3108b);
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
                dVar2.f3117k = false;
            }
            c cVar3 = this.mKwapi;
            Context context3 = cVar3.f8658d;
            cn.kuwo.autosdk.d dVar3 = cVar3.f8656b;
            if (dVar3.f3118l) {
                try {
                    context3.unregisterReceiver(dVar3.f3111e);
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                dVar3.f3118l = false;
            }
            c cVar4 = this.mKwapi;
            cn.kuwo.autosdk.d dVar4 = cVar4.f8656b;
            Context context4 = cVar4.f8658d;
            if (dVar4.f3116j) {
                try {
                    context4.unregisterReceiver(dVar4.f3115i);
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
                dVar4.f3116j = false;
            }
            if (dVar4.f3117k) {
                try {
                    context4.unregisterReceiver(dVar4.f3108b);
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                dVar4.f3117k = false;
            }
            this.mKwapi = null;
        }
        this.mPositionHelper.stop();
        stopTimer();
        this.mIsConnected = false;
        this.mIsPlaying = false;
        this.mIsRequesting = false;
        this.mPendingKeyword = null;
        this.mRetryCount = 0;
        this.mCurrentRid = 0L;
        this.mLastCoverRid = 0L;
        this.mLastCoverUrl = null;
        this.mIsRequestingCover = false;
        this.mLyricMap.clear();
    }
}
