package x3;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f9614b = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};

    /* renamed from: c, reason: collision with root package name */
    public static final int[][] f9615c = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};

    /* renamed from: d, reason: collision with root package name */
    public static final int[][] f9616d;

    /* renamed from: e, reason: collision with root package name */
    public static final int[][] f9617e;

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f9618a;

    static {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 5, 256);
        f9616d = iArr;
        iArr[0][32] = 1;
        for (int i7 = 65; i7 <= 90; i7++) {
            f9616d[0][i7] = i7 - 63;
        }
        f9616d[1][32] = 1;
        for (int i8 = 97; i8 <= 122; i8++) {
            f9616d[1][i8] = i8 - 95;
        }
        f9616d[2][32] = 1;
        for (int i9 = 48; i9 <= 57; i9++) {
            f9616d[2][i9] = i9 - 46;
        }
        int[] iArr2 = f9616d[2];
        iArr2[44] = 12;
        iArr2[46] = 13;
        int[] iArr3 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, z1.c.NOT_LONG, z1.c.NEG_FLOAT};
        for (int i10 = 0; i10 < 28; i10++) {
            f9616d[3][iArr3[i10]] = i10;
        }
        int[] iArr4 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, z1.c.NEG_INT, z1.c.NEG_LONG};
        for (int i11 = 0; i11 < 31; i11++) {
            int i12 = iArr4[i11];
            if (i12 > 0) {
                f9616d[4][i12] = i11;
            }
        }
        int[][] iArr5 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 6);
        f9617e = iArr5;
        for (int[] iArr6 : iArr5) {
            Arrays.fill(iArr6, -1);
        }
        int[][] iArr7 = f9617e;
        iArr7[0][4] = 0;
        int[] iArr8 = iArr7[1];
        iArr8[4] = 0;
        iArr8[0] = 28;
        iArr7[3][4] = 0;
        int[] iArr9 = iArr7[2];
        iArr9[4] = 0;
        iArr9[0] = 15;
    }

    public c(byte[] bArr, Charset charset) {
        this.f9618a = bArr;
    }

    public static LinkedList a(LinkedList linkedList) {
        LinkedList linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            Iterator it2 = linkedList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    linkedList2.addFirst(eVar);
                    break;
                }
                e eVar2 = (e) it2.next();
                if (eVar2.c(eVar)) {
                    break;
                }
                if (eVar.c(eVar2)) {
                    it2.remove();
                }
            }
        }
        return linkedList2;
    }
}
