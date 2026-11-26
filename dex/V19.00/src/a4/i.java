package a4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i {

    /* renamed from: a, reason: collision with root package name */
    public static final char[] f294a = {'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};

    public static void a(f[][] fVarArr, f fVar) {
        int i7 = fVar.f287c + fVar.f288d;
        if (fVarArr[i7][fVar.e().ordinal()] == null || fVarArr[i7][fVar.e().ordinal()].f290f > fVar.f290f) {
            fVarArr[i7][fVar.e().ordinal()] = fVar;
        }
    }

    public static void b(g gVar, f[][] fVarArr, int i7, f fVar) {
        if (gVar.a(i7)) {
            a(fVarArr, new f(gVar, h.ASCII, i7, 1, fVar));
            return;
        }
        char charAt = gVar.charAt(i7);
        int i8 = 0;
        if (fVar == null || fVar.e() != h.EDF) {
            if (l6.f.L(charAt) && gVar.h(i7, 2) && l6.f.L(gVar.charAt(i7 + 1))) {
                a(fVarArr, new f(gVar, h.ASCII, i7, 2, fVar));
            } else {
                a(fVarArr, new f(gVar, h.ASCII, i7, 1, fVar));
            }
            h[] hVarArr = {h.C40, h.TEXT};
            for (int i9 = 0; i9 < 2; i9++) {
                h hVar = hVarArr[i9];
                int[] iArr = new int[1];
                if (c(gVar, i7, hVar == h.C40, iArr) > 0) {
                    a(fVarArr, new f(gVar, hVar, i7, iArr[0], fVar));
                }
            }
            if (gVar.h(i7, 3) && l6.f.S(gVar.charAt(i7)) && l6.f.S(gVar.charAt(i7 + 1)) && l6.f.S(gVar.charAt(i7 + 2))) {
                a(fVarArr, new f(gVar, h.X12, i7, 3, fVar));
            }
            a(fVarArr, new f(gVar, h.B256, i7, 1, fVar));
        }
        while (i8 < 3) {
            int i10 = i7 + i8;
            if (!gVar.h(i10, 1) || !l6.f.Q(gVar.charAt(i10))) {
                break;
            }
            i8++;
            a(fVarArr, new f(gVar, h.EDF, i7, i8, fVar));
        }
        if (i8 == 3 && gVar.h(i7, 4) && l6.f.Q(gVar.charAt(i7 + 3))) {
            a(fVarArr, new f(gVar, h.EDF, i7, 4, fVar));
        }
    }

    public static int c(g gVar, int i7, boolean z6, int[] iArr) {
        int i8 = i7;
        int i9 = 0;
        while (true) {
            Object obj = gVar.f831c;
            if (i8 >= ((int[]) obj).length) {
                iArr[0] = 0;
                return 0;
            }
            if (gVar.a(i8)) {
                iArr[0] = 0;
                return 0;
            }
            char charAt = gVar.charAt(i8);
            if ((z6 && l6.f.P(charAt)) || (!z6 && l6.f.R(charAt))) {
                i9++;
            } else if (d(charAt, gVar.f830b)) {
                int i10 = charAt & 255;
                i9 = (i10 < 128 || (!(z6 && l6.f.P((char) (i10 + (-128)))) && (z6 || !l6.f.R((char) (i10 + (-128)))))) ? i9 + 4 : i9 + 3;
            } else {
                i9 += 2;
            }
            if (i9 % 3 == 0 || ((i9 - 2) % 3 == 0 && i8 + 1 == ((int[]) obj).length)) {
                break;
            }
            i8++;
        }
        iArr[0] = (i8 - i7) + 1;
        return (int) Math.ceil(i9 / 3.0d);
    }

    public static boolean d(char c7, int i7) {
        return c7 != i7 && c7 >= 128 && c7 <= 255;
    }
}
