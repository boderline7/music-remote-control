package b4;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n implements w3.d {

    /* renamed from: a, reason: collision with root package name */
    public static final Pattern f2969a = Pattern.compile("[0-9]+");

    public static int b(boolean[] zArr, int i7, int[] iArr, boolean z6) {
        int i8 = 0;
        for (int i9 : iArr) {
            int i10 = 0;
            while (i10 < i9) {
                zArr[i7] = z6;
                i10++;
                i7++;
            }
            i8 += i9;
            z6 = !z6;
        }
        return i8;
    }

    public static void c(String str) {
        if (!f2969a.matcher(str).matches()) {
            throw new IllegalArgumentException("Input should only contain digits 0-9");
        }
    }

    @Override // w3.d
    public final y3.b a(String str, w3.a aVar, int i7, int i8, HashMap hashMap) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i7 < 0 || i8 < 0) {
            throw new IllegalArgumentException("Negative size is not allowed. Input: " + i7 + 'x' + i8);
        }
        Set g7 = g();
        if (g7 != null && !g7.contains(aVar)) {
            throw new IllegalArgumentException("Can only encode " + g7 + ", but got " + aVar);
        }
        int f7 = f();
        if (hashMap != null) {
            w3.b bVar = w3.b.MARGIN;
            if (hashMap.containsKey(bVar)) {
                f7 = Integer.parseInt(hashMap.get(bVar).toString());
            }
        }
        boolean[] e7 = e(str, hashMap);
        int length = e7.length;
        int i9 = f7 + length;
        int max = Math.max(i7, i9);
        int max2 = Math.max(1, i8);
        int i10 = max / i9;
        int i11 = (max - (length * i10)) / 2;
        y3.b bVar2 = new y3.b(max, max2);
        int i12 = 0;
        while (i12 < length) {
            if (e7[i12]) {
                bVar2.c(i11, 0, i10, max2);
            }
            i12++;
            i11 += i10;
        }
        return bVar2;
    }

    public abstract boolean[] d(String str);

    public boolean[] e(String str, HashMap hashMap) {
        return d(str);
    }

    public int f() {
        return 10;
    }

    public abstract Set g();
}
