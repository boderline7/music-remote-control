package a2;

import com.android.dx.util.MutabilityException;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements e2.g {

    /* renamed from: a, reason: collision with root package name */
    public final int f221a;

    /* renamed from: b, reason: collision with root package name */
    public final h f222b;

    /* renamed from: c, reason: collision with root package name */
    public final e2.f f223c;

    /* renamed from: d, reason: collision with root package name */
    public final int f224d;

    public b(int i7, h hVar, e2.f fVar, int i8) {
        int i9;
        int i10;
        if (i7 < 0) {
            throw new IllegalArgumentException("label < 0");
        }
        try {
            if (hVar.f6364a) {
                throw new MutabilityException("mutable instance", null);
            }
            int length = hVar.f6354b.length;
            if (length == 0) {
                throw new IllegalArgumentException("insns.size() == 0");
            }
            for (int i11 = length - 2; i11 >= 0; i11--) {
                if (((g) hVar.f(i11)).f228a.f245e != 1) {
                    throw new IllegalArgumentException(a.a.e("insns[", i11, "] is a branch or can throw"));
                }
            }
            if (((g) hVar.f(length - 1)).f228a.f245e == 1) {
                throw new IllegalArgumentException("insns does not end with a branch or throwing instruction");
            }
            try {
                if (fVar.f6364a) {
                    throw new MutabilityException("mutable instance", null);
                }
                int i12 = -1;
                if (i8 < -1) {
                    throw new IllegalArgumentException("primarySuccessor < -1");
                }
                if (i8 >= 0) {
                    int i13 = fVar.f6362c;
                    if (!fVar.f6363d) {
                        i10 = 0;
                        while (true) {
                            if (i10 >= i13) {
                                i10 = -i13;
                                break;
                            } else if (fVar.f6361b[i10] == i8) {
                                break;
                            } else {
                                i10++;
                            }
                        }
                    } else {
                        int i14 = i13;
                        while (i14 > i12 + 1) {
                            int i15 = ((i14 - i12) >> 1) + i12;
                            if (i8 <= fVar.f6361b[i15]) {
                                i14 = i15;
                            } else {
                                i12 = i15;
                            }
                        }
                        if (i14 == i13) {
                            i9 = -i13;
                        } else if (i8 == fVar.f6361b[i14]) {
                            i10 = i14;
                        } else {
                            i9 = -i14;
                        }
                        i10 = i9 - 1;
                    }
                    if (i10 < 0 || i10 < 0) {
                        throw new IllegalArgumentException("primarySuccessor " + i8 + " not in successors " + fVar);
                    }
                }
                this.f221a = i7;
                this.f222b = hVar;
                this.f223c = fVar;
                this.f224d = i8;
            } catch (NullPointerException unused) {
                throw new NullPointerException("successors == null");
            }
        } catch (NullPointerException unused2) {
            throw new NullPointerException("insns == null");
        }
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "{" + z.l1(this.f221a) + '}';
    }
}
