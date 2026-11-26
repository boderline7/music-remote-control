package b2;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u extends w {
    public static final u EMPTY_STRING = new u(h0.FRAGMENT_ENCODE_SET);

    /* renamed from: a, reason: collision with root package name */
    public final String f2940a;

    /* renamed from: b, reason: collision with root package name */
    public final a1.b f2941b;

    public u(String str) {
        if (str == null) {
            throw new NullPointerException("string == null");
        }
        this.f2940a = str.intern();
        int length = str.length();
        byte[] bArr = new byte[length * 3];
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            char charAt = str.charAt(i8);
            if (charAt != 0 && charAt < 128) {
                bArr[i7] = (byte) charAt;
                i7++;
            } else if (charAt < 2048) {
                bArr[i7] = (byte) (((charAt >> 6) & 31) | z1.c.AND_LONG_2ADDR);
                bArr[i7 + 1] = (byte) ((charAt & '?') | 128);
                i7 += 2;
            } else {
                bArr[i7] = (byte) (((charAt >> '\f') & 15) | z1.c.SHL_INT_LIT8);
                bArr[i7 + 1] = (byte) (((charAt >> 6) & 63) | 128);
                bArr[i7 + 2] = (byte) ((charAt & '?') | 128);
                i7 += 3;
            }
        }
        byte[] bArr2 = new byte[i7];
        System.arraycopy(bArr, 0, bArr2, 0, i7);
        this.f2941b = new a1.b(bArr2);
    }

    @Override // c2.d
    public final c2.c b() {
        return c2.c.STRING;
    }

    @Override // e2.i
    public final String d() {
        String str = this.f2940a;
        int length = str.length();
        StringBuilder sb = new StringBuilder((length * 3) / 2);
        int i7 = 0;
        while (i7 < length) {
            char charAt = str.charAt(i7);
            if (charAt >= ' ' && charAt < 127) {
                if (charAt == '\'' || charAt == '\"' || charAt == '\\') {
                    sb.append('\\');
                }
                sb.append(charAt);
            } else if (charAt > 127) {
                sb.append("\\u");
                sb.append(Character.forDigit(charAt >> '\f', 16));
                sb.append(Character.forDigit((charAt >> '\b') & 15, 16));
                sb.append(Character.forDigit((charAt >> 4) & 15, 16));
                sb.append(Character.forDigit(charAt & 15, 16));
            } else if (charAt == '\t') {
                sb.append("\\t");
            } else if (charAt == '\n') {
                sb.append("\\n");
            } else if (charAt != '\r') {
                char charAt2 = i7 < length + (-1) ? str.charAt(i7 + 1) : (char) 0;
                boolean z6 = charAt2 >= '0' && charAt2 <= '7';
                sb.append('\\');
                for (int i8 = 6; i8 >= 0; i8 -= 3) {
                    char c7 = (char) (((charAt >> i8) & 7) + 48);
                    if (c7 != '0' || z6) {
                        sb.append(c7);
                        z6 = true;
                    }
                }
                if (!z6) {
                    sb.append('0');
                }
            } else {
                sb.append("\\r");
            }
            i7++;
        }
        return sb.toString();
    }

    @Override // b2.a
    public final int e(a aVar) {
        return this.f2940a.compareTo(((u) aVar).f2940a);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        return this.f2940a.equals(((u) obj).f2940a);
    }

    @Override // b2.a
    public final String f() {
        return "utf8";
    }

    public final String g() {
        return "\"" + d() + '\"';
    }

    public final int hashCode() {
        return this.f2940a.hashCode();
    }

    public final String toString() {
        return "string{\"" + d() + "\"}";
    }
}
