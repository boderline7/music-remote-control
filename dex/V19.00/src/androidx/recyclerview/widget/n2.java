package androidx.recyclerview.widget;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n2 {

    /* renamed from: a, reason: collision with root package name */
    public int f2552a;

    /* renamed from: b, reason: collision with root package name */
    public int f2553b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2554c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2555d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2556e;

    /* renamed from: f, reason: collision with root package name */
    public int[] f2557f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f2558g;

    public n2(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.f2558g = staggeredGridLayoutManager;
        a();
    }

    public final void a() {
        this.f2552a = -1;
        this.f2553b = Integer.MIN_VALUE;
        this.f2554c = false;
        this.f2555d = false;
        this.f2556e = false;
        int[] iArr = this.f2557f;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
