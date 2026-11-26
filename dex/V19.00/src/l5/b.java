package l5;

import java.util.Iterator;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class b implements Iterable {
    public static final a Companion = new a();

    /* renamed from: a, reason: collision with root package name */
    public final int f7450a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7451b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7452c;

    public b(int i7, int i8, int i9) {
        if (i9 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i9 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f7450a = i7;
        this.f7451b = z.q0(i7, i8, i9);
        this.f7452c = i9;
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            if (!isEmpty() || !((b) obj).isEmpty()) {
                b bVar = (b) obj;
                if (this.f7450a != bVar.f7450a || this.f7451b != bVar.f7451b || this.f7452c != bVar.f7452c) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f7450a * 31) + this.f7451b) * 31) + this.f7452c;
    }

    public boolean isEmpty() {
        int i7 = this.f7452c;
        int i8 = this.f7451b;
        int i9 = this.f7450a;
        if (i7 > 0) {
            if (i9 <= i8) {
                return false;
            }
        } else if (i9 >= i8) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new c(this.f7450a, this.f7451b, this.f7452c);
    }

    public String toString() {
        StringBuilder sb;
        int i7 = this.f7451b;
        int i8 = this.f7450a;
        int i9 = this.f7452c;
        if (i9 > 0) {
            sb = new StringBuilder();
            sb.append(i8);
            sb.append("..");
            sb.append(i7);
            sb.append(" step ");
            sb.append(i9);
        } else {
            sb = new StringBuilder();
            sb.append(i8);
            sb.append(" downTo ");
            sb.append(i7);
            sb.append(" step ");
            sb.append(-i9);
        }
        return sb.toString();
    }
}
