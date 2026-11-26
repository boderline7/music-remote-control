package z0;

import java.nio.ByteBuffer;
import p3.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    public int f10244a;

    /* renamed from: b, reason: collision with root package name */
    public ByteBuffer f10245b;

    /* renamed from: c, reason: collision with root package name */
    public int f10246c;

    /* renamed from: d, reason: collision with root package name */
    public int f10247d;

    public c() {
        if (f.f8235b == null) {
            f.f8235b = new f(9);
        }
    }

    public final int a(int i7) {
        if (i7 < this.f10247d) {
            return this.f10245b.getShort(this.f10246c + i7);
        }
        return 0;
    }
}
