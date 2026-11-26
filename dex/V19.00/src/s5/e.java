package s5;

import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {
    public static final e FORCE_CACHE;

    /* renamed from: a, reason: collision with root package name */
    public final boolean f8770a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f8771b;

    /* renamed from: c, reason: collision with root package name */
    public final int f8772c;

    /* renamed from: d, reason: collision with root package name */
    public final int f8773d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f8774e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f8775f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f8776g;

    /* renamed from: h, reason: collision with root package name */
    public final int f8777h;

    /* renamed from: i, reason: collision with root package name */
    public final int f8778i;

    /* renamed from: j, reason: collision with root package name */
    public final boolean f8779j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f8780k;

    /* renamed from: l, reason: collision with root package name */
    public final boolean f8781l;

    /* renamed from: m, reason: collision with root package name */
    public String f8782m;
    public static final d Companion = new d();
    public static final e FORCE_NETWORK = new e(true, false, -1, -1, false, false, false, -1, -1, false, false, false, null);

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        i5.a.g("timeUnit", timeUnit);
        long seconds = timeUnit.toSeconds(androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
        FORCE_CACHE = new e(false, false, -1, -1, false, false, false, seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds, -1, true, false, false, null);
    }

    public e(boolean z6, boolean z7, int i7, int i8, boolean z8, boolean z9, boolean z10, int i9, int i10, boolean z11, boolean z12, boolean z13, String str) {
        this.f8770a = z6;
        this.f8771b = z7;
        this.f8772c = i7;
        this.f8773d = i8;
        this.f8774e = z8;
        this.f8775f = z9;
        this.f8776g = z10;
        this.f8777h = i9;
        this.f8778i = i10;
        this.f8779j = z11;
        this.f8780k = z12;
        this.f8781l = z13;
        this.f8782m = str;
    }

    public final String toString() {
        String str = this.f8782m;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f8770a) {
            sb.append("no-cache, ");
        }
        if (this.f8771b) {
            sb.append("no-store, ");
        }
        int i7 = this.f8772c;
        if (i7 != -1) {
            sb.append("max-age=");
            sb.append(i7);
            sb.append(", ");
        }
        int i8 = this.f8773d;
        if (i8 != -1) {
            sb.append("s-maxage=");
            sb.append(i8);
            sb.append(", ");
        }
        if (this.f8774e) {
            sb.append("private, ");
        }
        if (this.f8775f) {
            sb.append("public, ");
        }
        if (this.f8776g) {
            sb.append("must-revalidate, ");
        }
        int i9 = this.f8777h;
        if (i9 != -1) {
            sb.append("max-stale=");
            sb.append(i9);
            sb.append(", ");
        }
        int i10 = this.f8778i;
        if (i10 != -1) {
            sb.append("min-fresh=");
            sb.append(i10);
            sb.append(", ");
        }
        if (this.f8779j) {
            sb.append("only-if-cached, ");
        }
        if (this.f8780k) {
            sb.append("no-transform, ");
        }
        if (this.f8781l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        sb.delete(sb.length() - 2, sb.length());
        String sb2 = sb.toString();
        i5.a.f("StringBuilder().apply(builderAction).toString()", sb2);
        this.f8782m = sb2;
        return sb2;
    }
}
