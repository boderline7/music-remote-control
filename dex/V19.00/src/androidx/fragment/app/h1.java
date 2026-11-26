package androidx.fragment.app;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class h1 {
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f1974a;

    /* renamed from: b, reason: collision with root package name */
    public int f1975b;

    /* renamed from: c, reason: collision with root package name */
    public int f1976c;

    /* renamed from: d, reason: collision with root package name */
    public int f1977d;

    /* renamed from: e, reason: collision with root package name */
    public int f1978e;

    /* renamed from: f, reason: collision with root package name */
    public int f1979f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1980g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1981h;

    /* renamed from: i, reason: collision with root package name */
    public String f1982i;

    /* renamed from: j, reason: collision with root package name */
    public int f1983j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f1984k;

    /* renamed from: l, reason: collision with root package name */
    public int f1985l;

    /* renamed from: m, reason: collision with root package name */
    public CharSequence f1986m;

    /* renamed from: n, reason: collision with root package name */
    public ArrayList f1987n;

    /* renamed from: o, reason: collision with root package name */
    public ArrayList f1988o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1989p;

    public final void b(g1 g1Var) {
        this.f1974a.add(g1Var);
        g1Var.f1960c = this.f1975b;
        g1Var.f1961d = this.f1976c;
        g1Var.f1962e = this.f1977d;
        g1Var.f1963f = this.f1978e;
    }

    public abstract void c(int i7, z zVar, String str, int i8);
}
