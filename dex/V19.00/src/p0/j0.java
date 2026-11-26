package p0;

import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j0 {

    /* renamed from: a, reason: collision with root package name */
    public final int f8149a;

    /* renamed from: b, reason: collision with root package name */
    public final Class f8150b;

    /* renamed from: c, reason: collision with root package name */
    public final int f8151c;

    /* renamed from: d, reason: collision with root package name */
    public final int f8152d;

    public j0(int i7, Class cls, int i8, int i9) {
        this.f8149a = i7;
        this.f8150b = cls;
        this.f8152d = i8;
        this.f8151c = i9;
    }

    public final Object a(View view) {
        if (Build.VERSION.SDK_INT < this.f8151c) {
            Object tag = view.getTag(this.f8149a);
            if (this.f8150b.isInstance(tag)) {
                return tag;
            }
            return null;
        }
        int i7 = ((h0) this).f8139e;
        switch (i7) {
            case 0:
                switch (i7) {
                    case 0:
                        return Boolean.valueOf(w0.d(view));
                    default:
                        return Boolean.valueOf(w0.c(view));
                }
            case 1:
                switch (i7) {
                    case 1:
                        return w0.b(view);
                    default:
                        return y0.a(view);
                }
            case 2:
                switch (i7) {
                    case 1:
                        return w0.b(view);
                    default:
                        return y0.a(view);
                }
            default:
                switch (i7) {
                    case 0:
                        return Boolean.valueOf(w0.d(view));
                    default:
                        return Boolean.valueOf(w0.c(view));
                }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0103, code lost:
    
        if (r0 == r1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0086, code lost:
    
        if (r0 == r1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0088, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00a3, code lost:
    
        if (r0 == r1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00ea, code lost:
    
        if (r0 == r1) goto L37;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x0065. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(android.view.View r6, java.lang.Object r7) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p0.j0.b(android.view.View, java.lang.Object):void");
    }
}
