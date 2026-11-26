package g6;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w {
    public static final w INSTANCE = new w();

    /* renamed from: a, reason: collision with root package name */
    public static final v f6603a = new v(new byte[0], 0, 0, false);

    /* renamed from: b, reason: collision with root package name */
    public static final int f6604b;

    /* renamed from: c, reason: collision with root package name */
    public static final AtomicReference[] f6605c;

    static {
        int highestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f6604b = highestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[highestOneBit];
        for (int i7 = 0; i7 < highestOneBit; i7++) {
            atomicReferenceArr[i7] = new AtomicReference();
        }
        f6605c = atomicReferenceArr;
    }

    public static final void a(v vVar) {
        if (vVar.f6601f != null || vVar.f6602g != null) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (vVar.f6599d) {
            return;
        }
        INSTANCE.getClass();
        AtomicReference atomicReference = f6605c[(int) (Thread.currentThread().getId() & (f6604b - 1))];
        v vVar2 = f6603a;
        v vVar3 = (v) atomicReference.getAndSet(vVar2);
        if (vVar3 == vVar2) {
            return;
        }
        int i7 = vVar3 != null ? vVar3.f6598c : 0;
        if (i7 >= 65536) {
            atomicReference.set(vVar3);
            return;
        }
        vVar.f6601f = vVar3;
        vVar.f6597b = 0;
        vVar.f6598c = i7 + 8192;
        atomicReference.set(vVar);
    }

    public static final v b() {
        INSTANCE.getClass();
        AtomicReference atomicReference = f6605c[(int) (Thread.currentThread().getId() & (f6604b - 1))];
        v vVar = f6603a;
        v vVar2 = (v) atomicReference.getAndSet(vVar);
        if (vVar2 == vVar) {
            return new v();
        }
        if (vVar2 == null) {
            atomicReference.set(null);
            return new v();
        }
        atomicReference.set(vVar2.f6601f);
        vVar2.f6601f = null;
        vVar2.f6598c = 0;
        return vVar2;
    }
}
