package a6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final g6.l CONNECTION_PREFACE;
    public static final int FLAG_ACK = 1;
    public static final int FLAG_COMPRESSED = 32;
    public static final int FLAG_END_HEADERS = 4;
    public static final int FLAG_END_PUSH_PROMISE = 4;
    public static final int FLAG_END_STREAM = 1;
    public static final int FLAG_NONE = 0;
    public static final int FLAG_PADDED = 8;
    public static final int FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final h INSTANCE = new h();
    public static final int TYPE_CONTINUATION = 9;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 1;
    public static final int TYPE_PING = 6;
    public static final int TYPE_PRIORITY = 2;
    public static final int TYPE_PUSH_PROMISE = 5;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_WINDOW_UPDATE = 8;

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f425a;

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f426b;

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f427c;

    static {
        g6.l.Companion.getClass();
        CONNECTION_PREFACE = g6.k.b("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        f425a = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        f426b = new String[64];
        String[] strArr = new String[256];
        for (int i7 = 0; i7 < 256; i7++) {
            String binaryString = Integer.toBinaryString(i7);
            i5.a.f("toBinaryString(it)", binaryString);
            String replace = t5.b.h("%8s", binaryString).replace(' ', '0');
            i5.a.f("this as java.lang.String…replace(oldChar, newChar)", replace);
            strArr[i7] = replace;
        }
        f427c = strArr;
        String[] strArr2 = f426b;
        strArr2[0] = s5.h0.FRAGMENT_ENCODE_SET;
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i8 = iArr[0];
        strArr2[i8 | 8] = a.a.i(new StringBuilder(), strArr2[i8], "|PADDED");
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i9 = 0; i9 < 3; i9++) {
            int i10 = iArr2[i9];
            int i11 = iArr[0];
            String[] strArr3 = f426b;
            int i12 = i11 | i10;
            strArr3[i12] = strArr3[i11] + '|' + strArr3[i10];
            StringBuilder sb = new StringBuilder();
            sb.append(strArr3[i11]);
            sb.append('|');
            strArr3[i12 | 8] = a.a.i(sb, strArr3[i10], "|PADDED");
        }
        int length = f426b.length;
        for (int i13 = 0; i13 < length; i13++) {
            String[] strArr4 = f426b;
            if (strArr4[i13] == null) {
                strArr4[i13] = f427c[i13];
            }
        }
    }

    public static String a(int i7, int i8, int i9, int i10, boolean z6) {
        String str;
        String str2;
        String[] strArr = f425a;
        String h7 = i9 < strArr.length ? strArr[i9] : t5.b.h("0x%02x", Integer.valueOf(i9));
        if (i10 == 0) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        } else {
            String[] strArr2 = f427c;
            if (i9 != 2 && i9 != 3) {
                if (i9 == 4 || i9 == 6) {
                    str = i10 == 1 ? "ACK" : strArr2[i10];
                } else if (i9 != 7 && i9 != 8) {
                    String[] strArr3 = f426b;
                    if (i10 < strArr3.length) {
                        str2 = strArr3[i10];
                        i5.a.d(str2);
                    } else {
                        str2 = strArr2[i10];
                    }
                    str = (i9 != 5 || (i10 & 4) == 0) ? (i9 != 0 || (i10 & 32) == 0) ? str2 : o5.k.N(str2, "PRIORITY", "COMPRESSED") : o5.k.N(str2, "HEADERS", "PUSH_PROMISE");
                }
            }
            str = strArr2[i10];
        }
        return t5.b.h("%s 0x%08x %5d %-13s %s", z6 ? "<<" : ">>", Integer.valueOf(i7), Integer.valueOf(i8), h7, str);
    }
}
