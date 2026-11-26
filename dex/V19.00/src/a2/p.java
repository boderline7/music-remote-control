package a2;

import java.util.Arrays;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {
    public static final int BRANCH_GOTO = 3;
    public static final int BRANCH_IF = 4;
    public static final int BRANCH_MAX = 6;
    public static final int BRANCH_MIN = 1;
    public static final int BRANCH_NONE = 1;
    public static final int BRANCH_RETURN = 2;
    public static final int BRANCH_SWITCH = 5;
    public static final int BRANCH_THROW = 6;

    /* renamed from: a, reason: collision with root package name */
    public final int f241a;

    /* renamed from: b, reason: collision with root package name */
    public final c2.c f242b;

    /* renamed from: c, reason: collision with root package name */
    public final c2.e f243c;

    /* renamed from: d, reason: collision with root package name */
    public final c2.e f244d;

    /* renamed from: e, reason: collision with root package name */
    public final int f245e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f246f;

    /* renamed from: g, reason: collision with root package name */
    public final String f247g;

    public p(int i7, c2.b bVar, c2.b bVar2) {
        this(i7, c2.c.VOID, bVar, bVar2, 6, true, null);
    }

    public p(int i7, c2.c cVar, c2.b bVar, int i8, String str) {
        this(i7, cVar, bVar, c2.b.EMPTY, i8, false, str);
    }

    public p(int i7, c2.c cVar, c2.b bVar, c2.b bVar2, int i8, boolean z6, String str) {
        if (cVar == null) {
            throw new NullPointerException("result == null");
        }
        if (bVar == null) {
            throw new NullPointerException("sources == null");
        }
        if (bVar2 == null) {
            throw new NullPointerException("exceptions == null");
        }
        if (i8 < 1 || i8 > 6) {
            throw new IllegalArgumentException(a.a.d("invalid branchingness: ", i8));
        }
        if (bVar2.f6354b.length != 0 && i8 != 6) {
            throw new IllegalArgumentException("exceptions / branchingness mismatch");
        }
        this.f241a = i7;
        this.f242b = cVar;
        this.f243c = bVar;
        this.f244d = bVar2;
        this.f245e = i8;
        this.f246f = z6;
        this.f247g = str;
    }

    public p(int i7, c2.c cVar, c2.b bVar, c2.b bVar2, String str) {
        this(i7, cVar, bVar, bVar2, 6, false, str);
    }

    public p(int i7, c2.c cVar, c2.b bVar, String str) {
        this(i7, cVar, bVar, c2.b.EMPTY, 1, false, str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f241a == pVar.f241a && this.f245e == pVar.f245e && this.f242b == pVar.f242b && this.f243c.equals(pVar.f243c) && this.f244d.equals(pVar.f244d);
    }

    public final int hashCode() {
        return Arrays.hashCode(((e2.d) this.f244d).f6354b) + ((Arrays.hashCode(((e2.d) this.f243c).f6354b) + ((this.f242b.f3040a.hashCode() + (((this.f241a * 31) + this.f245e) * 31)) * 31)) * 31);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder(40);
        sb.append("Rop{");
        int i7 = this.f241a;
        switch (i7) {
            case 1:
                str = "nop";
                break;
            case 2:
                str = "move";
                break;
            case 3:
                str = "move-param";
                break;
            case 4:
                str = "move-exception";
                break;
            case 5:
                str = "const";
                break;
            case 6:
                str = "goto";
                break;
            case 7:
                str = "if-eq";
                break;
            case 8:
                str = "if-ne";
                break;
            case 9:
                str = "if-lt";
                break;
            case 10:
                str = "if-ge";
                break;
            case 11:
                str = "if-le";
                break;
            case 12:
                str = "if-gt";
                break;
            case 13:
                str = "switch";
                break;
            case 14:
                str = "add";
                break;
            case 15:
                str = "sub";
                break;
            case 16:
                str = "mul";
                break;
            case 17:
                str = "div";
                break;
            case 18:
                str = "rem";
                break;
            case 19:
                str = "neg";
                break;
            case 20:
                str = "and";
                break;
            case 21:
                str = "or";
                break;
            case 22:
                str = "xor";
                break;
            case 23:
                str = "shl";
                break;
            case 24:
                str = "shr";
                break;
            case 25:
                str = "ushr";
                break;
            case 26:
                str = "not";
                break;
            case 27:
                str = "cmpl";
                break;
            case 28:
                str = "cmpg";
                break;
            case 29:
                str = "conv";
                break;
            case 30:
                str = "to-byte";
                break;
            case 31:
                str = "to-char";
                break;
            case 32:
                str = "to-short";
                break;
            case 33:
                str = "return";
                break;
            case 34:
                str = "array-length";
                break;
            case 35:
                str = "throw";
                break;
            case 36:
                str = "monitor-enter";
                break;
            case 37:
                str = "monitor-exit";
                break;
            case 38:
                str = "aget";
                break;
            case 39:
                str = "aput";
                break;
            case 40:
                str = "new-instance";
                break;
            case 41:
                str = "new-array";
                break;
            case 42:
                str = "filled-new-array";
                break;
            case 43:
                str = "check-cast";
                break;
            case 44:
                str = "instance-of";
                break;
            case 45:
                str = "get-field";
                break;
            case 46:
                str = "get-static";
                break;
            case 47:
                str = "put-field";
                break;
            case 48:
                str = "put-static";
                break;
            case 49:
                str = "invoke-static";
                break;
            case 50:
                str = "invoke-virtual";
                break;
            case 51:
                str = "invoke-super";
                break;
            case 52:
                str = "invoke-direct";
                break;
            case 53:
                str = "invoke-interface";
                break;
            case 54:
            default:
                str = "unknown-".concat(z.k1(i7));
                break;
            case 55:
                str = "move-result";
                break;
            case z1.c.IF_EQZ /* 56 */:
                str = "move-result-pseudo";
                break;
            case z1.c.IF_NEZ /* 57 */:
                str = "fill-array-data";
                break;
            case z1.c.IF_LTZ /* 58 */:
                str = "invoke-polymorphic";
                break;
            case z1.c.IF_GEZ /* 59 */:
                str = "invoke-custom";
                break;
        }
        sb.append(str);
        c2.c cVar = c2.c.VOID;
        c2.c cVar2 = this.f242b;
        if (cVar2 != cVar) {
            sb.append(" ");
            sb.append(cVar2);
        } else {
            sb.append(" .");
        }
        sb.append(" <-");
        c2.e eVar = this.f243c;
        int length = ((e2.d) eVar).f6354b.length;
        if (length == 0) {
            sb.append(" .");
        } else {
            for (int i8 = 0; i8 < length; i8++) {
                sb.append(' ');
                sb.append(eVar.a(i8));
            }
        }
        if (this.f246f) {
            sb.append(" call");
        }
        c2.e eVar2 = this.f244d;
        int length2 = ((e2.d) eVar2).f6354b.length;
        if (length2 != 0) {
            sb.append(" throws");
            for (int i9 = 0; i9 < length2; i9++) {
                sb.append(' ');
                if (eVar2.a(i9) == c2.c.THROWABLE) {
                    sb.append("<any>");
                } else {
                    sb.append(eVar2.a(i9));
                }
            }
        } else {
            int i10 = this.f245e;
            if (i10 == 1) {
                sb.append(" flows");
            } else if (i10 == 2) {
                sb.append(" returns");
            } else if (i10 == 3) {
                sb.append(" gotos");
            } else if (i10 == 4) {
                sb.append(" ifs");
            } else if (i10 != 5) {
                sb.append(" ".concat(z.k1(i10)));
            } else {
                sb.append(" switches");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
