package androidx.activity;

import android.window.BackEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c {
    public static final b Companion = new b();
    public static final int EDGE_LEFT = 0;
    public static final int EDGE_RIGHT = 1;

    /* renamed from: a, reason: collision with root package name */
    public final float f608a;

    /* renamed from: b, reason: collision with root package name */
    public final float f609b;

    /* renamed from: c, reason: collision with root package name */
    public final float f610c;

    /* renamed from: d, reason: collision with root package name */
    public final int f611d;

    public c(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        a aVar = a.INSTANCE;
        float d7 = aVar.d(backEvent);
        float e7 = aVar.e(backEvent);
        float b7 = aVar.b(backEvent);
        int c7 = aVar.c(backEvent);
        this.f608a = d7;
        this.f609b = e7;
        this.f610c = b7;
        this.f611d = c7;
    }

    public final String toString() {
        return "BackEventCompat{touchX=" + this.f608a + ", touchY=" + this.f609b + ", progress=" + this.f610c + ", swipeEdge=" + this.f611d + '}';
    }
}
