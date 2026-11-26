package b4;

import java.util.Collections;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends n {

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f2966b = {1, 1, 1, 1};

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f2967c = {3, 1, 1};

    /* renamed from: d, reason: collision with root package name */
    public static final int[][] f2968d = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    @Override // b4.n
    public final boolean[] d(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        }
        if (length > 80) {
            throw new IllegalArgumentException(a.a.d("Requested contents should be less than 80 digits long, but got ", length));
        }
        n.c(str);
        boolean[] zArr = new boolean[(length * 9) + 9];
        int b7 = n.b(zArr, 0, f2966b, true);
        for (int i7 = 0; i7 < length; i7 += 2) {
            int digit = Character.digit(str.charAt(i7), 10);
            int digit2 = Character.digit(str.charAt(i7 + 1), 10);
            int[] iArr = new int[10];
            for (int i8 = 0; i8 < 5; i8++) {
                int i9 = i8 * 2;
                int[][] iArr2 = f2968d;
                iArr[i9] = iArr2[digit][i8];
                iArr[i9 + 1] = iArr2[digit2][i8];
            }
            b7 += n.b(zArr, b7, iArr, true);
        }
        n.b(zArr, b7, f2967c, true);
        return zArr;
    }

    @Override // b4.n
    public final Set g() {
        return Collections.singleton(w3.a.ITF);
    }
}
