package t1;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l {
    public static final String SONGLIST_TAG_URL = e.SONGLIST_TAG_URL.a();

    /* renamed from: a, reason: collision with root package name */
    public static String f9053a;

    /* renamed from: b, reason: collision with root package name */
    public static final String f9054b;

    /* renamed from: c, reason: collision with root package name */
    public static String f9055c;

    static {
        e.HOST_URL_SUB_LIST.a();
        e.HOST_URL_N.a();
        f9054b = "http://artistpicserver.kuwo.cn/pic.web?";
    }

    public static String a(long j7, String str, String str2, String str3, int i7) {
        StringBuilder sb;
        int i8;
        int i9;
        byte[] bArr;
        int i10;
        int i11;
        int i12;
        int i13;
        StringBuilder sb2 = new StringBuilder("type=lyric");
        try {
            if (TextUtils.isEmpty(str)) {
                sb2.append("&songname=");
            } else {
                sb2.append("&songname=" + URLEncoder.encode(str, "utf-8"));
            }
            if (TextUtils.isEmpty(str2)) {
                sb2.append("&artist=");
            } else {
                sb2.append("&artist=" + URLEncoder.encode(str2, "utf-8"));
            }
            if (TextUtils.isEmpty(str3)) {
                sb2.append("&filename=");
            } else {
                sb2.append("&filename=" + URLEncoder.encode(str3, "utf-8"));
            }
        } catch (UnsupportedEncodingException e7) {
            e7.printStackTrace();
        }
        sb2.append("&duration=" + i7);
        sb2.append("&req=2&lrcx=1");
        if (j7 > 0) {
            sb2.append("&rid=" + j7);
        }
        sb2.append("&encode=utf8");
        String sb3 = sb2.toString();
        synchronized (l.class) {
            try {
                sb = new StringBuilder();
                String str4 = f9053a;
                if (str4 == null) {
                    sb.append("user=");
                    sb.append(k.f9050d);
                    sb.append("&prod=");
                    sb.append(k.f9051e);
                    sb.append("&corp=kuwo&vipver=9.4.7.0&source=");
                    sb.append(k.f9052f);
                    sb.append("&");
                    f9053a = sb.toString();
                } else {
                    sb.append(str4);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        sb.append(sb3);
        byte[] bytes = sb.toString().getBytes();
        int length = bytes.length;
        byte[] bArr2 = d.SECRET_KEY;
        synchronized (d.class) {
            long j8 = 0;
            for (int i14 = 0; i14 < 8; i14++) {
                try {
                    j8 |= bArr2[i14] << (i14 * 8);
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            int i15 = length / 8;
            long[] jArr = new long[16];
            for (int i16 = 0; i16 < 16; i16++) {
                jArr[i16] = 0;
            }
            long[] jArr2 = new long[i15];
            for (int i17 = 0; i17 < i15; i17++) {
                for (int i18 = 0; i18 < 8; i18++) {
                    jArr2[i17] = (bytes[(i17 * 8) + i18] << (i18 * 8)) | jArr2[i17];
                }
            }
            i8 = (((i15 + 1) * 8) + 1) / 8;
            long[] jArr3 = new long[i8];
            long a7 = d.a(d.f9031g, 56, j8);
            int i19 = 0;
            for (int i20 = 16; i19 < i20; i20 = 16) {
                long[] jArr4 = d.f9034j;
                int i21 = d.f9033i[i19];
                long j9 = jArr4[i21];
                a7 = ((a7 & j9) << (28 - i21)) | (((~j9) & a7) >> i21);
                jArr[i19] = d.a(d.f9032h, 64, a7);
                i19++;
            }
            for (int i22 = 0; i22 < i15; i22++) {
                jArr3[i22] = d.b(jArr, jArr2[i22]);
            }
            int i23 = length % 8;
            int i24 = i15 * 8;
            int i25 = length - i24;
            System.arraycopy(bytes, i24, new byte[i25], 0, i25);
            long j10 = 0;
            for (int i26 = 0; i26 < i23; i26++) {
                j10 |= r8[i26] << (i26 * 8);
            }
            jArr3[i15] = d.b(jArr, j10);
            i9 = i8 * 8;
            bArr = new byte[i9];
            int i27 = 0;
            for (int i28 = 0; i28 < i8; i28++) {
                for (int i29 = 0; i29 < 8; i29++) {
                    bArr[i27] = (byte) ((jArr3[i28] >> (i29 * 8)) & 255);
                    i27++;
                }
            }
        }
        String str5 = a.key;
        int i30 = ((i8 * 32) + 2) / 3;
        char[] cArr = new char[((i9 + 2) / 3) * 4];
        int i31 = 0;
        int i32 = 0;
        while (i31 < i9) {
            int i33 = i31 + 1;
            byte b7 = bArr[i31];
            int i34 = b7 & 255;
            if (i33 < i9) {
                i10 = i31 + 2;
                i11 = bArr[i33] & 255;
            } else {
                i10 = i33;
                i11 = 0;
            }
            if (i10 < i9) {
                i12 = i10 + 1;
                i13 = bArr[i10] & 255;
            } else {
                i12 = i10;
                i13 = 0;
            }
            int i35 = ((b7 & 3) << 4) | (i11 >>> 4);
            int i36 = ((i11 & 15) << 2) | (i13 >>> 6);
            int i37 = i13 & 63;
            char[] cArr2 = a.f9022a;
            cArr[i32] = cArr2[i34 >>> 2];
            int i38 = i32 + 2;
            cArr[i32 + 1] = cArr2[i35];
            char c7 = '=';
            cArr[i38] = i38 < i30 ? cArr2[i36] : '=';
            int i39 = i32 + 3;
            if (i39 < i30) {
                c7 = cArr2[i37];
            }
            cArr[i39] = c7;
            i32 += 4;
            i31 = i12;
        }
        return "http://mobi.kuwo.cn/mobi.s?f=kuwo&q=".concat(new String(cArr));
    }

    public static String b() {
        String str = f9055c;
        if (str == null || h0.FRAGMENT_ENCODE_SET.equals(str)) {
            StringBuilder sb = new StringBuilder(f9054b);
            sb.append("user=");
            sb.append(k.f9050d);
            sb.append("&prod=");
            sb.append(k.f9051e);
            sb.append("&corp=kuwo&source=");
            f9055c = a.a.i(sb, k.f9052f, "&");
        }
        return f9055c;
    }
}
