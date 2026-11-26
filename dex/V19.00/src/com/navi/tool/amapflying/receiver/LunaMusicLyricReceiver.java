package com.navi.tool.amapflying.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.navi.tool.service.PhoneNotificationRelayService;
import h4.a;
import h4.k;
import h4.l;
import h4.m;
import i6.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;
import t1.f;
import y0.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LunaMusicLyricReceiver extends BroadcastReceiver {

    /* renamed from: g, reason: collision with root package name */
    public static Context f4510g;

    /* renamed from: a, reason: collision with root package name */
    public static final long[] f4504a = {300, 600, 1200};

    /* renamed from: b, reason: collision with root package name */
    public static final Pattern f4505b = Pattern.compile("\\[(\\d{1,2}):(\\d{1,2})[\\.,](\\d{1,3})\\]");

    /* renamed from: c, reason: collision with root package name */
    public static final Pattern f4506c = Pattern.compile("\\[(\\d+),(\\d+)\\]");

    /* renamed from: d, reason: collision with root package name */
    public static final Pattern f4507d = Pattern.compile("<\\d+,\\d+,\\d+>");

    /* renamed from: e, reason: collision with root package name */
    public static final TreeMap f4508e = new TreeMap();

    /* renamed from: f, reason: collision with root package name */
    public static final Handler f4509f = new Handler(Looper.getMainLooper());

    /* renamed from: h, reason: collision with root package name */
    public static int f4511h = 0;

    /* renamed from: i, reason: collision with root package name */
    public static String f4512i = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: j, reason: collision with root package name */
    public static long f4513j = 0;

    /* renamed from: k, reason: collision with root package name */
    public static long f4514k = 0;

    /* renamed from: l, reason: collision with root package name */
    public static String f4515l = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: m, reason: collision with root package name */
    public static String f4516m = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: n, reason: collision with root package name */
    public static String f4517n = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: o, reason: collision with root package name */
    public static String f4518o = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: p, reason: collision with root package name */
    public static String f4519p = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: q, reason: collision with root package name */
    public static long f4520q = 0;

    /* renamed from: r, reason: collision with root package name */
    public static long f4521r = 0;

    /* renamed from: s, reason: collision with root package name */
    public static long f4522s = 0;

    /* renamed from: t, reason: collision with root package name */
    public static boolean f4523t = false;

    /* renamed from: u, reason: collision with root package name */
    public static boolean f4524u = false;

    /* renamed from: v, reason: collision with root package name */
    public static boolean f4525v = false;

    /* renamed from: w, reason: collision with root package name */
    public static int f4526w = -1;

    /* renamed from: x, reason: collision with root package name */
    public static final p f4527x = new p(1);

    /* renamed from: y, reason: collision with root package name */
    public static final p f4528y = new p(2);

    public static boolean a(Intent intent, String str, boolean z6) {
        Object d7 = d(intent, str);
        if (d7 instanceof Boolean) {
            return ((Boolean) d7).booleanValue();
        }
        if (!(d7 instanceof String)) {
            return z6;
        }
        String trim = ((String) d7).trim();
        return "true".equalsIgnoreCase(trim) || "1".equals(trim);
    }

    public static long b(Intent intent, String str) {
        Object d7 = d(intent, str);
        if (d7 instanceof Number) {
            return ((Number) d7).longValue();
        }
        if (!(d7 instanceof String)) {
            return 0L;
        }
        try {
            return Long.parseLong(((String) d7).trim());
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String c(Intent intent, String str) {
        Object d7 = d(intent, str);
        return d7 == null ? h0.FRAGMENT_ENCODE_SET : d7 instanceof String ? (String) d7 : String.valueOf(d7);
    }

    public static Object d(Intent intent, String str) {
        try {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                return null;
            }
            return extras.get(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long e() {
        if (!f4523t || f4521r <= 0) {
            return f4520q;
        }
        long elapsedRealtime = (SystemClock.elapsedRealtime() - f4521r) + f4520q;
        long j7 = f4522s;
        if (j7 > 0 && elapsedRealtime > j7) {
            elapsedRealtime = j7;
        }
        if (elapsedRealtime < 0) {
            return 0L;
        }
        return elapsedRealtime;
    }

    public static void f(String str) {
        Map.Entry floorEntry;
        Map.Entry higherEntry;
        long e7 = e();
        TreeMap treeMap = f4508e;
        synchronized (treeMap) {
            try {
                if (!treeMap.isEmpty() && (floorEntry = treeMap.floorEntry(Long.valueOf(e7))) != null) {
                    str = (String) floorEntry.getValue();
                    if (str == null) {
                        str = h0.FRAGMENT_ENCODE_SET;
                    }
                    if (f4524u && (higherEntry = treeMap.higherEntry((Long) floorEntry.getKey())) != null && !TextUtils.isEmpty((CharSequence) higherEntry.getValue())) {
                        str = str + "\n" + ((String) higherEntry.getValue());
                    }
                } else if (str == null) {
                    str = h0.FRAGMENT_ENCODE_SET;
                }
            } finally {
            }
        }
        if (TextUtils.equals(str, f4518o)) {
            return;
        }
        f4518o = str;
        e b7 = e.b();
        k kVar = new k(str);
        kVar.f6754b = 5;
        b7.f(kVar);
    }

    public static void g() {
        Handler handler = f4509f;
        handler.removeCallbacks(f4527x);
        handler.removeCallbacks(f4528y);
        f4511h = 0;
        f4510g = null;
        f4523t = false;
        f4525v = false;
        f4520q = 0L;
        f4521r = 0L;
        f4522s = 0L;
        f4515l = h0.FRAGMENT_ENCODE_SET;
        f4516m = h0.FRAGMENT_ENCODE_SET;
        f4517n = h0.FRAGMENT_ENCODE_SET;
        f4518o = h0.FRAGMENT_ENCODE_SET;
        f4519p = h0.FRAGMENT_ENCODE_SET;
        f4512i = h0.FRAGMENT_ENCODE_SET;
        f4513j = 0L;
        f4514k = 0L;
        TreeMap treeMap = f4508e;
        synchronized (treeMap) {
            treeMap.clear();
        }
    }

    public static boolean h(Intent intent) {
        String trim;
        String c7 = c(intent, "title");
        String c8 = c(intent, "artist");
        String c9 = c(intent, "coverUrl");
        if (TextUtils.isEmpty(c9)) {
            trim = h0.FRAGMENT_ENCODE_SET;
        } else {
            trim = c9.trim();
            if (trim.startsWith("//")) {
                trim = "https:".concat(trim);
            }
        }
        String str = c7 + "|" + c8;
        boolean z6 = (TextUtils.isEmpty(c7) && TextUtils.isEmpty(c8)) ? false : true;
        intent.getAction();
        SystemClock.elapsedRealtime();
        if (z6 && !TextUtils.equals(str, f4519p) && TextUtils.equals(str, f4512i) && SystemClock.elapsedRealtime() - f4513j <= 2000) {
            intent.getAction();
            return true;
        }
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.containsKey("isCollected")) {
                    f4525v = a(intent, "isCollected", false);
                }
            }
        } catch (Throwable unused) {
        }
        Object d7 = d(intent, "loopMode");
        if (d7 instanceof String) {
            String str2 = (String) d7;
            if ("LIST".equals(str2)) {
                f4526w = 0;
            } else if ("SINGLE".equals(str2)) {
                f4526w = 1;
            } else if ("SHUFFLE".equals(str2)) {
                f4526w = 2;
            }
        }
        if (z6 && !TextUtils.equals(str, f4519p)) {
            f4512i = f4519p;
            f4513j = SystemClock.elapsedRealtime();
            f4514k = SystemClock.elapsedRealtime();
            f4519p = str;
            f4518o = h0.FRAGMENT_ENCODE_SET;
            f4520q = 0L;
            f4521r = 0L;
            TreeMap treeMap = f4508e;
            synchronized (treeMap) {
                treeMap.clear();
            }
            e b7 = e.b();
            l lVar = new l(new TreeMap());
            lVar.f6756b = 5;
            b7.f(lVar);
            e b8 = e.b();
            k kVar = new k(h0.FRAGMENT_ENCODE_SET);
            kVar.f6754b = 5;
            b8.f(kVar);
            Handler handler = f4509f;
            p pVar = f4528y;
            handler.removeCallbacks(pVar);
            f4511h = 0;
            handler.postDelayed(pVar, f4504a[0]);
        }
        if (!TextUtils.isEmpty(c7)) {
            f4515l = c7;
        }
        if (!TextUtils.isEmpty(c8)) {
            f4516m = c8;
        }
        if (!TextUtils.isEmpty(trim)) {
            if (!TextUtils.equals(f4517n, trim)) {
                e b9 = e.b();
                a aVar = new a(trim);
                aVar.f6725c = 5;
                b9.f(aVar);
            }
            f4517n = trim;
        }
        return false;
    }

    public static void i(Intent intent, boolean z6) {
        if (intent != null) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    if (extras.containsKey("isPlaying")) {
                        z6 = a(intent, "isPlaying", z6);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        boolean z7 = z6;
        Handler handler = f4509f;
        p pVar = f4527x;
        handler.removeCallbacks(pVar);
        if (z7) {
            if (!f4523t) {
                f4521r = SystemClock.elapsedRealtime();
            }
            f4523t = true;
            handler.postDelayed(pVar, 1800L);
        } else {
            f4520q = e();
            f4521r = 0L;
            f4523t = false;
        }
        m mVar = new m(TextUtils.isEmpty(f4515l) ? "汽水音乐" : f4515l, f4516m, z7, e(), f4522s);
        mVar.f6768l = 5;
        if (!TextUtils.isEmpty(f4517n)) {
            mVar.f6762f = f4517n;
        }
        mVar.f6766j = f4525v;
        mVar.f6767k = f4526w;
        e.b().f(mVar);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z6;
        long parseLong;
        long parseLong2;
        String group;
        long parseLong3;
        long j7;
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (context != null && f4510g == null) {
            f4510g = context.getApplicationContext();
        }
        String action = intent.getAction();
        if (!"com.luna.music.car.LYRIC_FULL".equals(action)) {
            if ("com.luna.music.car.LYRIC_LINE".equals(action)) {
                if (h(intent)) {
                    return;
                }
                long b7 = b(intent, "position");
                long b8 = b(intent, "duration");
                if (b8 > 0) {
                    f4522s = b8;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                z6 = elapsedRealtime - f4514k > PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS;
                if (b7 >= 0 && z6) {
                    f4520q = b7;
                    f4521r = elapsedRealtime;
                }
                c(intent, "title");
                f(c(intent, "currentLine"));
                i(intent, true);
                return;
            }
            if (!"com.luna.music.car.PLAYER_STATE".equals(action) || h(intent)) {
                return;
            }
            long b9 = b(intent, "position");
            long b10 = b(intent, "duration");
            if (b10 > 0) {
                f4522s = b10;
            }
            boolean z7 = !TextUtils.isEmpty(c(intent, "title"));
            boolean z8 = SystemClock.elapsedRealtime() - f4514k > PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS;
            if (b9 >= 0 && z7 && z8) {
                f4520q = b9;
            }
            c(intent, "title");
            f(null);
            i(intent, f4523t);
            return;
        }
        if (h(intent)) {
            return;
        }
        String c7 = c(intent, "lrc");
        if (!TextUtils.isEmpty(c7)) {
            TreeMap treeMap = f4508e;
            synchronized (treeMap) {
                try {
                    treeMap.clear();
                    f4518o = h0.FRAGMENT_ENCODE_SET;
                    if (!TextUtils.isEmpty(c7)) {
                        String[] split = c7.split("\n");
                        int length = split.length;
                        for (int i7 = 0; i7 < length; i7++) {
                            String str = split[i7];
                            String trim = str == null ? h0.FRAGMENT_ENCODE_SET : str.replace("\r", h0.FRAGMENT_ENCODE_SET).trim();
                            if (!TextUtils.isEmpty(trim)) {
                                Matcher matcher = f4505b.matcher(trim);
                                ArrayList arrayList = new ArrayList();
                                while (matcher.find()) {
                                    try {
                                        parseLong = Long.parseLong(matcher.group(1));
                                        parseLong2 = Long.parseLong(matcher.group(2));
                                        group = matcher.group(3);
                                        parseLong3 = Long.parseLong(group);
                                    } catch (Exception unused) {
                                    }
                                    if (group.length() != 1) {
                                        j7 = group.length() == 2 ? 10L : 100L;
                                        arrayList.add(Long.valueOf((parseLong2 * 1000) + (parseLong * f.T_MS_MINUTE) + parseLong3));
                                    }
                                    parseLong3 *= j7;
                                    arrayList.add(Long.valueOf((parseLong2 * 1000) + (parseLong * f.T_MS_MINUTE) + parseLong3));
                                }
                                if (arrayList.isEmpty()) {
                                    Pattern pattern = f4506c;
                                    Matcher matcher2 = pattern.matcher(trim);
                                    if (matcher2.find()) {
                                        long parseLong4 = Long.parseLong(matcher2.group(1));
                                        String trim2 = f4507d.matcher(pattern.matcher(trim).replaceFirst(h0.FRAGMENT_ENCODE_SET)).replaceAll(h0.FRAGMENT_ENCODE_SET).trim();
                                        if (!TextUtils.isEmpty(trim2)) {
                                            f4508e.put(Long.valueOf(parseLong4), trim2);
                                        }
                                    }
                                } else {
                                    String trim3 = f4505b.matcher(trim).replaceAll(h0.FRAGMENT_ENCODE_SET).trim();
                                    if (!TextUtils.isEmpty(trim3)) {
                                        try {
                                            Iterator it = arrayList.iterator();
                                            while (it.hasNext()) {
                                                f4508e.put((Long) it.next(), trim3);
                                            }
                                        } catch (Exception unused2) {
                                        }
                                    }
                                }
                            }
                        }
                        e b11 = e.b();
                        l lVar = new l(new TreeMap((SortedMap) f4508e));
                        lVar.f6756b = 5;
                        b11.f(lVar);
                    }
                } finally {
                }
            }
            f4509f.removeCallbacks(f4528y);
            f4511h = 0;
        }
        long b12 = b(intent, "position");
        long b13 = b(intent, "duration");
        if (b13 > 0) {
            f4522s = b13;
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        z6 = elapsedRealtime2 - f4514k > PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS;
        if (b12 >= 0 && z6) {
            f4520q = b12;
            f4521r = elapsedRealtime2;
        }
        c(intent, "title");
        f(c(intent, "currentLine"));
        i(intent, true);
    }
}
