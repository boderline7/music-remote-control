package p5;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import n0.k;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends k implements a {

    /* renamed from: c, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f8367c = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_state");
    private volatile Object _state;

    /* renamed from: b, reason: collision with root package name */
    public int f8368b;

    public b(Object obj) {
        this._state = obj;
    }

    public final void e(Object obj) {
        int i7;
        z[] zVarArr;
        if (obj == null) {
            obj = q5.a.NULL;
        }
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f8367c;
            if (i5.a.c(atomicReferenceFieldUpdater.get(this), obj)) {
                return;
            }
            atomicReferenceFieldUpdater.set(this, obj);
            int i8 = this.f8368b;
            if ((i8 & 1) != 0) {
                this.f8368b = i8 + 2;
                return;
            }
            int i9 = i8 + 1;
            this.f8368b = i9;
            z[] zVarArr2 = (z[]) this.f7582a;
            while (true) {
                if (zVarArr2 != null) {
                    for (z zVar : zVarArr2) {
                    }
                }
                synchronized (this) {
                    i7 = this.f8368b;
                    if (i7 == i9) {
                        this.f8368b = i9 + 1;
                        return;
                    }
                    zVarArr = (z[]) this.f7582a;
                }
                zVarArr2 = zVarArr;
                i9 = i7;
            }
        }
    }
}
