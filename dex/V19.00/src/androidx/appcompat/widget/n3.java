package androidx.appcompat.widget;

import android.util.SparseIntArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n3 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1361a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1362b;

    /* renamed from: c, reason: collision with root package name */
    public Object f1363c;

    /* renamed from: d, reason: collision with root package name */
    public Object f1364d;

    public n3(int i7) {
        if (i7 != 1) {
            return;
        }
        this.f1363c = new SparseIntArray();
        this.f1364d = new SparseIntArray();
        this.f1361a = false;
        this.f1362b = false;
    }

    public final int a(int i7, int i8) {
        if (!this.f1362b) {
            return c(i7, i8);
        }
        int i9 = ((SparseIntArray) this.f1364d).get(i7, -1);
        if (i9 != -1) {
            return i9;
        }
        int c7 = c(i7, i8);
        ((SparseIntArray) this.f1364d).put(i7, c7);
        return c7;
    }

    public final int b(int i7, int i8) {
        if (!this.f1361a) {
            return i7 % i8;
        }
        int i9 = ((SparseIntArray) this.f1363c).get(i7, -1);
        if (i9 != -1) {
            return i9;
        }
        int i10 = i7 % i8;
        ((SparseIntArray) this.f1363c).put(i7, i10);
        return i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int c(int r8, int r9) {
        /*
            r7 = this;
            boolean r0 = r7.f1362b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L49
            java.lang.Object r0 = r7.f1364d
            android.util.SparseIntArray r0 = (android.util.SparseIntArray) r0
            int r3 = r0.size()
            int r3 = r3 - r1
            r4 = r2
        L10:
            if (r4 > r3) goto L22
            int r5 = r4 + r3
            int r5 = r5 >>> r1
            int r6 = r0.keyAt(r5)
            if (r6 >= r8) goto L1e
            int r4 = r5 + 1
            goto L10
        L1e:
            int r5 = r5 + (-1)
            r3 = r5
            goto L10
        L22:
            int r4 = r4 - r1
            r3 = -1
            if (r4 < 0) goto L31
            int r5 = r0.size()
            if (r4 >= r5) goto L31
            int r0 = r0.keyAt(r4)
            goto L32
        L31:
            r0 = r3
        L32:
            if (r0 == r3) goto L49
            java.lang.Object r3 = r7.f1364d
            android.util.SparseIntArray r3 = (android.util.SparseIntArray) r3
            int r3 = r3.get(r0)
            int r4 = r0 + 1
            int r0 = r7.b(r0, r9)
            int r0 = r0 + r1
            if (r0 != r9) goto L4c
            int r3 = r3 + 1
            r0 = r2
            goto L4c
        L49:
            r0 = r2
            r3 = r0
            r4 = r3
        L4c:
            if (r4 >= r8) goto L5e
            int r0 = r0 + 1
            if (r0 != r9) goto L56
            int r3 = r3 + 1
            r0 = r2
            goto L5b
        L56:
            if (r0 <= r9) goto L5b
            int r3 = r3 + 1
            r0 = r1
        L5b:
            int r4 = r4 + 1
            goto L4c
        L5e:
            int r0 = r0 + r1
            if (r0 <= r9) goto L63
            int r3 = r3 + 1
        L63:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.n3.c(int, int):int");
    }

    public final void d() {
        ((SparseIntArray) this.f1363c).clear();
    }
}
