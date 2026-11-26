package w1;

import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n {
    public static final boolean ALLOW_EXTENDED_OPCODES = true;

    public static String a(h hVar) {
        int n6 = ((y) hVar).n();
        int i7 = 0;
        if (n6 == ((short) n6)) {
            char[] cArr = new char[5];
            if (n6 < 0) {
                cArr[0] = '-';
                n6 = -n6;
            } else {
                cArr[0] = '+';
            }
            while (i7 < 4) {
                cArr[4 - i7] = Character.forDigit(n6 & 15, 16);
                n6 >>= 4;
                i7++;
            }
            return new String(cArr);
        }
        char[] cArr2 = new char[9];
        if (n6 < 0) {
            cArr2[0] = '-';
            n6 = -n6;
        } else {
            cArr2[0] = '+';
        }
        while (i7 < 8) {
            cArr2[8 - i7] = Character.forDigit(n6 & 15, 16);
            n6 >>= 4;
            i7++;
        }
        return new String(cArr2);
    }

    public static String c(h hVar) {
        int e7 = ((y) hVar).f9455e.e();
        return e7 == ((char) e7) ? n1.z.l1(e7) : n1.z.m1(e7);
    }

    public static short e(int i7, int i8) {
        if ((i7 & 255) != i7) {
            throw new IllegalArgumentException("low out of range 0..255");
        }
        if ((i8 & 255) == i8) {
            return (short) (i7 | (i8 << 8));
        }
        throw new IllegalArgumentException("high out of range 0..255");
    }

    public static String j(b2.o oVar, int i7) {
        StringBuilder sb = new StringBuilder(20);
        sb.append("#");
        long h7 = oVar instanceof b2.n ? ((b2.n) oVar).f2935a : oVar.h();
        if (i7 == 4) {
            sb.append(new String(new char[]{Character.forDigit(((int) h7) & 15, 16)}));
        } else if (i7 == 8) {
            sb.append(n1.z.k1((int) h7));
        } else if (i7 == 16) {
            sb.append(n1.z.l1((int) h7));
        } else if (i7 == 32) {
            sb.append(n1.z.m1((int) h7));
        } else {
            if (i7 != 64) {
                throw new RuntimeException("shouldn't happen");
            }
            sb.append(n1.z.n1(h7));
        }
        return sb.toString();
    }

    public static String k(b2.o oVar) {
        StringBuilder sb = new StringBuilder(100);
        sb.append('#');
        if (oVar instanceof b2.l) {
            sb.append("null");
        } else {
            sb.append(oVar.f());
            sb.append(' ');
            sb.append(oVar.d());
        }
        return sb.toString();
    }

    public static int l(int i7, int i8) {
        if ((i7 & 15) != i7) {
            throw new IllegalArgumentException("low out of range 0..15");
        }
        if ((i8 & 15) == i8) {
            return i7 | (i8 << 4);
        }
        throw new IllegalArgumentException("high out of range 0..15");
    }

    public static short m(int i7, h hVar) {
        if ((i7 & 255) != i7) {
            throw new IllegalArgumentException("arg out of range 0..255");
        }
        int i8 = hVar.f9427b.f9431a;
        if ((i8 & 255) == i8) {
            return (short) ((i7 << 8) | i8);
        }
        throw new IllegalArgumentException("opcode out of range 0..255");
    }

    public static String n(a2.n nVar) {
        int length = nVar.f6354b.length;
        StringBuilder sb = new StringBuilder((length * 5) + 2);
        sb.append('{');
        for (int i7 = 0; i7 < length; i7++) {
            if (i7 != 0) {
                sb.append(", ");
            }
            sb.append(((a2.m) nVar.f(i7)).h());
        }
        sb.append('}');
        return sb.toString();
    }

    public static String o(a2.n nVar) {
        int i7;
        int i8;
        int length = nVar.f6354b.length;
        StringBuilder sb = new StringBuilder(30);
        sb.append("{");
        if (length != 0) {
            if (length != 1) {
                a2.m mVar = (a2.m) nVar.f(length - 1);
                if (mVar.e() == 2 && i7 != (i8 = (i7 = mVar.f237a) + 1)) {
                    mVar = a2.m.f(i8, mVar.f238b);
                }
                sb.append(((a2.m) nVar.f(0)).h());
                sb.append("..");
                sb.append(mVar.h());
            } else {
                sb.append(((a2.m) nVar.f(0)).h());
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static boolean p(int i7) {
        return i7 == (i7 & 255);
    }

    public static boolean q(int i7) {
        return i7 == (i7 & 15);
    }

    public static boolean r(int i7) {
        return i7 == (65535 & i7);
    }

    public static void s(e2.a aVar, short s6, short s7) {
        e2.c cVar = (e2.c) aVar;
        cVar.k(s6);
        cVar.k(s7);
    }

    public boolean b(y yVar) {
        return this instanceof x1.q;
    }

    public abstract int d();

    public BitSet f(h hVar) {
        return new BitSet();
    }

    public abstract String g(h hVar);

    public abstract String h(h hVar);

    public abstract boolean i(h hVar);

    public abstract void t(e2.c cVar, h hVar);
}
