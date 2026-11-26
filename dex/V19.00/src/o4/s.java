package o4;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s {
    public static final int INVALID_PRESSURE = -1;
    public static final int INVALID_TEMP = Integer.MIN_VALUE;
    public static final long STALE_MS = 1800000;

    /* renamed from: a, reason: collision with root package name */
    public int f8019a;

    /* renamed from: b, reason: collision with root package name */
    public int f8020b;

    /* renamed from: c, reason: collision with root package name */
    public int f8021c;

    /* renamed from: d, reason: collision with root package name */
    public int f8022d;

    /* renamed from: e, reason: collision with root package name */
    public int f8023e;

    /* renamed from: f, reason: collision with root package name */
    public int f8024f;

    /* renamed from: g, reason: collision with root package name */
    public int f8025g;

    /* renamed from: h, reason: collision with root package name */
    public int f8026h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f8027i;

    /* renamed from: j, reason: collision with root package name */
    public String f8028j;

    /* renamed from: k, reason: collision with root package name */
    public long f8029k;

    /* renamed from: l, reason: collision with root package name */
    public long f8030l;

    /* JADX WARN: Removed duplicated region for block: B:27:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static o4.s a(org.json.JSONObject r7) {
        /*
            o4.s r0 = new o4.s
            r0.<init>()
            r1 = -1
            r0.f8019a = r1
            r0.f8020b = r1
            r0.f8021c = r1
            r0.f8022d = r1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r0.f8023e = r2
            r0.f8024f = r2
            r0.f8025g = r2
            r0.f8026h = r2
            java.lang.String r2 = ""
            r0.f8028j = r2
            r3 = 0
            r0.f8029k = r3
            r0.f8030l = r3
            java.lang.String r5 = "fl"
            int r5 = r7.optInt(r5, r1)
            if (r5 > 0) goto L2b
            r5 = r1
        L2b:
            r0.f8019a = r5
            java.lang.String r5 = "fr"
            int r5 = r7.optInt(r5, r1)
            if (r5 > 0) goto L36
            r5 = r1
        L36:
            r0.f8020b = r5
            java.lang.String r5 = "rl"
            int r5 = r7.optInt(r5, r1)
            if (r5 > 0) goto L41
            r5 = r1
        L41:
            r0.f8021c = r5
            java.lang.String r5 = "rr"
            int r5 = r7.optInt(r5, r1)
            if (r5 > 0) goto L4c
            goto L4d
        L4c:
            r1 = r5
        L4d:
            r0.f8022d = r1
            java.lang.String r1 = "flT"
            r5 = 255(0xff, float:3.57E-43)
            int r1 = r7.optInt(r1, r5)
            int r1 = e(r1)
            r0.f8023e = r1
            java.lang.String r1 = "frT"
            int r1 = r7.optInt(r1, r5)
            int r1 = e(r1)
            r0.f8024f = r1
            java.lang.String r1 = "rlT"
            int r1 = r7.optInt(r1, r5)
            int r1 = e(r1)
            r0.f8025g = r1
            java.lang.String r1 = "rrT"
            int r1 = r7.optInt(r1, r5)
            int r1 = e(r1)
            r0.f8026h = r1
            java.lang.String r1 = "online"
            r5 = 0
            int r1 = r7.optInt(r1, r5)
            r6 = 1
            if (r1 != r6) goto L8c
            r5 = r6
        L8c:
            r0.f8027i = r5
            java.lang.String r1 = "src"
            java.lang.String r1 = r7.optString(r1, r2)
            r0.f8028j = r1
            java.lang.String r1 = "serverTime"
            r7.optString(r1, r2)
            long r1 = java.lang.System.currentTimeMillis()
            r0.f8029k = r1
            java.lang.String r1 = r0.f8028j
            if (r1 == 0) goto Ld2
            java.lang.String r2 = r1.trim()
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto Lb0
            goto Ld2
        Lb0:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat     // Catch: java.lang.Exception -> Ld2
            java.lang.String r5 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r6 = java.util.Locale.US     // Catch: java.lang.Exception -> Ld2
            r2.<init>(r5, r6)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r5 = "Asia/Shanghai"
            java.util.TimeZone r5 = java.util.TimeZone.getTimeZone(r5)     // Catch: java.lang.Exception -> Ld2
            r2.setTimeZone(r5)     // Catch: java.lang.Exception -> Ld2
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Exception -> Ld2
            java.util.Date r1 = r2.parse(r1)     // Catch: java.lang.Exception -> Ld2
            if (r1 != 0) goto Lcd
            goto Ld2
        Lcd:
            long r1 = r1.getTime()     // Catch: java.lang.Exception -> Ld2
            goto Ld3
        Ld2:
            r1 = r3
        Ld3:
            r0.f8030l = r1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto Le1
            java.lang.String r1 = "ts"
            long r1 = r7.optLong(r1, r3)
            r0.f8030l = r1
        Le1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: o4.s.a(org.json.JSONObject):o4.s");
    }

    public static int e(int i7) {
        if (i7 >= 254 || i7 <= -100) {
            return Integer.MIN_VALUE;
        }
        return i7;
    }

    public final boolean b() {
        return this.f8019a > 0 || this.f8020b > 0 || this.f8021c > 0 || this.f8022d > 0;
    }

    public final boolean c() {
        long j7 = this.f8030l;
        if (j7 <= 0) {
            j7 = this.f8029k;
        }
        return j7 <= 0 || System.currentTimeMillis() - j7 > STALE_MS;
    }

    public final String d() {
        if (!b()) {
            return "胎压未上报";
        }
        String str = this.f8028j;
        String substring = (str == null || str.length() < 16) ? h0.FRAGMENT_ENCODE_SET : this.f8028j.substring(11, 16);
        String str2 = (c() || !this.f8027i) ? "缓存" : "在线";
        if (substring.isEmpty()) {
            return str2;
        }
        return str2 + " · " + substring;
    }
}
