package s5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d0 implements Iterable {
    public static final c0 Companion = new c0();

    /* renamed from: a, reason: collision with root package name */
    public final String[] f8767a;

    public d0(String[] strArr) {
        this.f8767a = strArr;
    }

    public final String a(String str) {
        i5.a.g("name", str);
        Companion.getClass();
        String[] strArr = this.f8767a;
        int length = strArr.length - 2;
        int q02 = n1.z.q0(length, 0, -2);
        if (q02 <= length) {
            while (!o5.k.C(str, strArr[length])) {
                if (length != q02) {
                    length -= 2;
                }
            }
            return strArr[length + 1];
        }
        return null;
    }

    public final String b(int i7) {
        return this.f8767a[i7 * 2];
    }

    public final b0 c() {
        b0 b0Var = new b0();
        ArrayList arrayList = b0Var.f8763a;
        i5.a.g("<this>", arrayList);
        String[] strArr = this.f8767a;
        i5.a.g("elements", strArr);
        List asList = Arrays.asList(strArr);
        i5.a.f("asList(this)", asList);
        arrayList.addAll(asList);
        return b0Var;
    }

    public final String d(int i7) {
        return this.f8767a[(i7 * 2) + 1];
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d0) {
            if (Arrays.equals(this.f8767a, ((d0) obj).f8767a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f8767a);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int size = size();
        b5.a[] aVarArr = new b5.a[size];
        for (int i7 = 0; i7 < size; i7++) {
            aVarArr[i7] = new b5.a(b(i7), d(i7));
        }
        return new c5.d(aVarArr);
    }

    public final int size() {
        return this.f8767a.length / 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i7 = 0; i7 < size; i7++) {
            String b7 = b(i7);
            String d7 = d(i7);
            sb.append(b7);
            sb.append(": ");
            if (t5.b.p(b7)) {
                d7 = "██";
            }
            sb.append(d7);
            sb.append("\n");
        }
        String sb2 = sb.toString();
        i5.a.f("StringBuilder().apply(builderAction).toString()", sb2);
        return sb2;
    }
}
