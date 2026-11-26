package androidx.recyclerview.widget;

import com.navi.tool.tire.api.TireEngine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2486a;

    /* renamed from: b, reason: collision with root package name */
    public long f2487b;

    /* renamed from: c, reason: collision with root package name */
    public Object f2488c;

    public i(int i7) {
        this.f2486a = i7;
        if (i7 != 2) {
            this.f2487b = 0L;
        } else {
            this.f2488c = Collections.synchronizedList(new ArrayList());
        }
    }

    public i(TireEngine tireEngine, long j7) {
        this.f2486a = 1;
        this.f2488c = tireEngine;
        this.f2487b = j7;
    }

    public final void a(int i7) {
        if (i7 < 64) {
            this.f2487b &= ~(1 << i7);
            return;
        }
        Object obj = this.f2488c;
        if (((i) obj) != null) {
            ((i) obj).a(i7 - 64);
        }
    }

    public final int b(int i7) {
        Object obj = this.f2488c;
        if (((i) obj) == null) {
            return i7 >= 64 ? Long.bitCount(this.f2487b) : Long.bitCount(this.f2487b & ((1 << i7) - 1));
        }
        if (i7 < 64) {
            return Long.bitCount(this.f2487b & ((1 << i7) - 1));
        }
        return Long.bitCount(this.f2487b) + ((i) obj).b(i7 - 64);
    }

    public final void c() {
        if (((i) this.f2488c) == null) {
            this.f2488c = new i(0);
        }
    }

    public final void d(a5.a aVar) {
        this.f2487b++;
        Thread thread = new Thread(aVar);
        thread.setDaemon(true);
        thread.setName("NanoHttpd Request Processor (#" + this.f2487b + ")");
        ((List) this.f2488c).add(aVar);
        thread.start();
    }

    public final boolean e(int i7) {
        if (i7 < 64) {
            return (this.f2487b & (1 << i7)) != 0;
        }
        c();
        return ((i) this.f2488c).e(i7 - 64);
    }

    public final void f(int i7, boolean z6) {
        if (i7 >= 64) {
            c();
            ((i) this.f2488c).f(i7 - 64, z6);
            return;
        }
        long j7 = this.f2487b;
        boolean z7 = (Long.MIN_VALUE & j7) != 0;
        long j8 = (1 << i7) - 1;
        this.f2487b = ((j7 & (~j8)) << 1) | (j7 & j8);
        if (z6) {
            i(i7);
        } else {
            a(i7);
        }
        if (z7 || ((i) this.f2488c) != null) {
            c();
            ((i) this.f2488c).f(0, z7);
        }
    }

    public final boolean g(int i7) {
        if (i7 >= 64) {
            c();
            return ((i) this.f2488c).g(i7 - 64);
        }
        long j7 = 1 << i7;
        long j8 = this.f2487b;
        boolean z6 = (j8 & j7) != 0;
        long j9 = j8 & (~j7);
        this.f2487b = j9;
        long j10 = j7 - 1;
        this.f2487b = (j9 & j10) | Long.rotateRight((~j10) & j9, 1);
        Object obj = this.f2488c;
        if (((i) obj) != null) {
            if (((i) obj).e(0)) {
                i(63);
            }
            ((i) this.f2488c).g(0);
        }
        return z6;
    }

    public final void h() {
        this.f2487b = 0L;
        Object obj = this.f2488c;
        if (((i) obj) != null) {
            ((i) obj).h();
        }
    }

    public final void i(int i7) {
        if (i7 < 64) {
            this.f2487b |= 1 << i7;
        } else {
            c();
            ((i) this.f2488c).i(i7 - 64);
        }
    }

    public final String toString() {
        switch (this.f2486a) {
            case 0:
                if (((i) this.f2488c) == null) {
                    return Long.toBinaryString(this.f2487b);
                }
                return ((i) this.f2488c).toString() + "xx" + Long.toBinaryString(this.f2487b);
            default:
                return super.toString();
        }
    }
}
