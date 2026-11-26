package l4;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.r;
import android.text.TextUtils;
import com.navi.tool.amapflying.util.kuwo.NativeKuwoEngine;
import com.navi.tool.service.UniversalMusicService;
import h4.l;
import h4.m;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import s5.h0;
import y0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: v, reason: collision with root package name */
    public static e f7426v;

    /* renamed from: w, reason: collision with root package name */
    public static final Pattern f7427w = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})\\.(\\d{1,3})\\](.*)");

    /* renamed from: x, reason: collision with root package name */
    public static boolean f7428x = false;

    /* renamed from: a, reason: collision with root package name */
    public final Context f7429a;

    /* renamed from: b, reason: collision with root package name */
    public final MediaSessionManager f7430b;

    /* renamed from: c, reason: collision with root package name */
    public final ComponentName f7431c;

    /* renamed from: d, reason: collision with root package name */
    public r f7432d;

    /* renamed from: e, reason: collision with root package name */
    public MediaController f7433e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f7434f = false;

    /* renamed from: g, reason: collision with root package name */
    public boolean f7435g = false;

    /* renamed from: h, reason: collision with root package name */
    public String f7436h = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: i, reason: collision with root package name */
    public String f7437i = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: j, reason: collision with root package name */
    public String f7438j = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: k, reason: collision with root package name */
    public long f7439k = 0;

    /* renamed from: l, reason: collision with root package name */
    public long f7440l = 0;

    /* renamed from: m, reason: collision with root package name */
    public long f7441m = 0;

    /* renamed from: n, reason: collision with root package name */
    public boolean f7442n = false;

    /* renamed from: o, reason: collision with root package name */
    public final TreeMap f7443o = new TreeMap();

    /* renamed from: p, reason: collision with root package name */
    public final Handler f7444p = new Handler(Looper.getMainLooper());

    /* renamed from: q, reason: collision with root package name */
    public String f7445q = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: r, reason: collision with root package name */
    public final a f7446r = new a(this, 0);

    /* renamed from: s, reason: collision with root package name */
    public final c f7447s = new c(this);

    /* renamed from: t, reason: collision with root package name */
    public final d f7448t = new d(this);

    /* renamed from: u, reason: collision with root package name */
    public final a f7449u = new a(this, 1);

    public e(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f7429a = applicationContext;
        this.f7430b = (MediaSessionManager) applicationContext.getSystemService("media_session");
        this.f7431c = new ComponentName(applicationContext, (Class<?>) UniversalMusicService.class);
    }

    public static MediaController a(List list, String str, boolean z6) {
        if (list != null && !TextUtils.isEmpty(str)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MediaController mediaController = (MediaController) it.next();
                if (mediaController != null && TextUtils.equals(str, mediaController.getPackageName())) {
                    if (!z6) {
                        return mediaController;
                    }
                    if (mediaController.getPlaybackState() != null && mediaController.getPlaybackState().getState() == 3) {
                        return mediaController;
                    }
                }
            }
        }
        return null;
    }

    public static synchronized e b(Context context) {
        e eVar;
        synchronized (e.class) {
            try {
                if (f7426v == null) {
                    f7426v = new e(context);
                }
                eVar = f7426v;
            } catch (Throwable th) {
                throw th;
            }
        }
        return eVar;
    }

    public final void c() {
        long j7;
        long currentTimeMillis;
        long j8;
        f();
        synchronized (this.f7443o) {
            try {
                if (!this.f7443o.isEmpty()) {
                    i6.e b7 = i6.e.b();
                    l lVar = new l(new TreeMap((SortedMap) this.f7443o));
                    lVar.f6756b = 6;
                    b7.f(lVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.f7442n) {
            j7 = this.f7439k;
            currentTimeMillis = SystemClock.elapsedRealtime();
            j8 = this.f7441m;
        } else {
            j7 = this.f7439k;
            currentTimeMillis = System.currentTimeMillis();
            j8 = this.f7441m;
        }
        long j9 = (currentTimeMillis - j8) + j7;
        i6.e b8 = i6.e.b();
        m mVar = new m(this.f7436h, this.f7437i, this.f7435g, j9, this.f7440l);
        mVar.f6768l = 6;
        b8.f(mVar);
    }

    public final void d() {
        try {
            this.f7430b.addOnActiveSessionsChangedListener(this.f7447s, this.f7431c);
            this.f7434f = true;
            f();
        } catch (SecurityException | Exception unused) {
        }
        NativeKuwoEngine.getInstance().setCallback(new b(this));
    }

    public final void e() {
        try {
            this.f7430b.removeOnActiveSessionsChangedListener(this.f7447s);
        } catch (Exception unused) {
        }
        this.f7444p.removeCallbacks(this.f7446r);
        r rVar = this.f7432d;
        if (rVar != null) {
            rVar.e(this.f7448t);
            this.f7432d = null;
        }
        this.f7433e = null;
        this.f7434f = false;
        this.f7435g = false;
        this.f7444p.removeCallbacks(this.f7449u);
        this.f7443o.clear();
        i6.e b7 = i6.e.b();
        m mVar = new m(h0.FRAGMENT_ENCODE_SET, h0.FRAGMENT_ENCODE_SET, false, 0L, 0L);
        mVar.f6768l = 6;
        b7.f(mVar);
    }

    public final void f() {
        MediaSessionManager mediaSessionManager = this.f7430b;
        if (mediaSessionManager == null) {
            return;
        }
        try {
            g(mediaSessionManager.getActiveSessions(this.f7431c));
            r rVar = this.f7432d;
            if (rVar != null) {
                PlaybackStateCompat b7 = rVar.b();
                if (b7 != null) {
                    i(b7);
                }
                MediaMetadataCompat a7 = this.f7432d.a();
                if (a7 != null) {
                    h(a7);
                }
            }
        } catch (SecurityException | Exception unused) {
        }
    }

    public final void g(List list) {
        String str;
        MediaController mediaController;
        Context context = this.f7429a;
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            str = context.getSharedPreferences("navi_tool_prefs", 0).getString("desktop_music_package", null);
        } catch (Exception unused) {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            mediaController = null;
        } else {
            mediaController = a(list, str, true);
            if (mediaController == null) {
                mediaController = a(list, str, false);
            }
        }
        if (mediaController == null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaController mediaController2 = (MediaController) it.next();
                if (mediaController2.getPlaybackState() != null && mediaController2.getPlaybackState().getState() == 3) {
                    mediaController = mediaController2;
                    break;
                }
            }
        }
        if (mediaController == null) {
            mediaController = (MediaController) list.get(0);
        }
        MediaController mediaController3 = this.f7433e;
        if (mediaController3 == null || !mediaController3.getSessionToken().equals(mediaController.getSessionToken())) {
            mediaController.getPackageName();
            r rVar = this.f7432d;
            d dVar = this.f7448t;
            if (rVar != null) {
                rVar.e(dVar);
                this.f7432d = null;
            }
            this.f7433e = mediaController;
            try {
                r rVar2 = new r(context, MediaSessionCompat$Token.l(mediaController.getSessionToken(), null));
                this.f7432d = rVar2;
                rVar2.d(dVar);
                PlaybackStateCompat b7 = this.f7432d.b();
                if (b7 != null) {
                    i(b7);
                }
                MediaMetadataCompat a7 = this.f7432d.a();
                if (a7 != null) {
                    h(a7);
                }
            } catch (Exception unused2) {
            }
            try {
                context.getSharedPreferences("navi_tool_prefs", 0).edit().putString("universal_music_current_app", mediaController.getPackageName()).apply();
            } catch (Exception unused3) {
            }
        }
    }

    public final void h(MediaMetadataCompat mediaMetadataCompat) {
        String o6 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_TITLE);
        String o7 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_ARTIST);
        String o8 = mediaMetadataCompat.o(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
        long j7 = mediaMetadataCompat.f525a.getLong(MediaMetadataCompat.METADATA_KEY_DURATION, 0L);
        if (TextUtils.isEmpty(o6)) {
            o6 = "未知歌曲";
        }
        if (TextUtils.isEmpty(o7)) {
            o7 = "未知歌手";
        }
        if (TextUtils.equals(this.f7436h, o6) && TextUtils.equals(this.f7437i, o7) && this.f7440l == j7 && TextUtils.equals(this.f7438j, o8)) {
            return;
        }
        this.f7436h = o6;
        this.f7437i = o7;
        this.f7440l = j7;
        this.f7438j = o8;
        this.f7439k = 0L;
        if (this.f7442n) {
            this.f7441m = SystemClock.elapsedRealtime();
        } else {
            this.f7441m = System.currentTimeMillis();
        }
        this.f7443o.clear();
        new Thread(new n(this, o6, o7, 3)).start();
    }

    public final void i(PlaybackStateCompat playbackStateCompat) {
        boolean z6 = playbackStateCompat.f573a == 3;
        this.f7439k = playbackStateCompat.f574b;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        System.currentTimeMillis();
        long j7 = playbackStateCompat.f580h;
        if (j7 <= 0) {
            this.f7441m = System.currentTimeMillis();
            this.f7442n = false;
        } else if (j7 > elapsedRealtime + 10000000) {
            this.f7441m = j7;
            this.f7442n = false;
        } else {
            this.f7441m = j7;
            this.f7442n = true;
        }
        if (this.f7435g != z6) {
            this.f7435g = z6;
            Handler handler = this.f7444p;
            a aVar = this.f7446r;
            if (z6) {
                handler.removeCallbacks(aVar);
                this.f7444p.removeCallbacks(this.f7449u);
                handler.post(this.f7449u);
            } else {
                handler.removeCallbacks(this.f7449u);
                handler.removeCallbacks(aVar);
                handler.postDelayed(aVar, 1000L);
            }
        }
        i6.e b7 = i6.e.b();
        m mVar = new m(this.f7436h, this.f7437i, this.f7435g, this.f7439k, this.f7440l);
        mVar.f6768l = 6;
        b7.f(mVar);
    }
}
