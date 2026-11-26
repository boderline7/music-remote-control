package y3;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {

    /* renamed from: c, reason: collision with root package name */
    public static final ArrayList f10183c = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    public final CharsetEncoder[] f10184a;

    /* renamed from: b, reason: collision with root package name */
    public final int f10185b;

    static {
        String[] strArr = {"IBM437", "ISO-8859-2", "ISO-8859-3", "ISO-8859-4", "ISO-8859-5", "ISO-8859-6", "ISO-8859-7", "ISO-8859-8", "ISO-8859-9", "ISO-8859-10", "ISO-8859-11", "ISO-8859-13", "ISO-8859-14", "ISO-8859-15", "ISO-8859-16", "windows-1250", "windows-1251", "windows-1252", "windows-1256", "Shift_JIS"};
        for (int i7 = 0; i7 < 20; i7++) {
            String str = strArr[i7];
            if (((c) c.f10179d.get(str)) != null) {
                try {
                    f10183c.add(Charset.forName(str).newEncoder());
                } catch (UnsupportedCharsetException unused) {
                }
            }
        }
    }

    public d(String str, Charset charset, int i7) {
        boolean z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(StandardCharsets.ISO_8859_1.newEncoder());
        int i8 = 0;
        boolean z7 = charset != null && charset.name().startsWith("UTF");
        for (int i9 = 0; i9 < str.length(); i9++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                CharsetEncoder charsetEncoder = (CharsetEncoder) it.next();
                char charAt = str.charAt(i9);
                if (charAt == i7 || charsetEncoder.canEncode(charAt)) {
                    z6 = true;
                    break;
                }
            }
            z6 = false;
            if (!z6) {
                Iterator it2 = f10183c.iterator();
                while (it2.hasNext()) {
                    CharsetEncoder charsetEncoder2 = (CharsetEncoder) it2.next();
                    if (charsetEncoder2.canEncode(str.charAt(i9))) {
                        arrayList.add(charsetEncoder2);
                        break;
                    }
                }
            }
            if (!z6) {
                z7 = true;
            }
        }
        if (arrayList.size() != 1 || z7) {
            this.f10184a = new CharsetEncoder[arrayList.size() + 2];
            Iterator it3 = arrayList.iterator();
            int i10 = 0;
            while (it3.hasNext()) {
                this.f10184a[i10] = (CharsetEncoder) it3.next();
                i10++;
            }
            this.f10184a[i10] = StandardCharsets.UTF_8.newEncoder();
            this.f10184a[i10 + 1] = StandardCharsets.UTF_16BE.newEncoder();
        } else {
            this.f10184a = new CharsetEncoder[]{(CharsetEncoder) arrayList.get(0)};
        }
        if (charset != null) {
            while (true) {
                CharsetEncoder[] charsetEncoderArr = this.f10184a;
                if (i8 >= charsetEncoderArr.length) {
                    break;
                }
                if (charsetEncoderArr[i8] != null && charset.name().equals(this.f10184a[i8].charset().name())) {
                    break;
                } else {
                    i8++;
                }
            }
            this.f10185b = i8;
        }
        i8 = -1;
        this.f10185b = i8;
    }

    public final boolean a(char c7, int i7) {
        return this.f10184a[i7].canEncode(h0.FRAGMENT_ENCODE_SET + c7);
    }

    public final byte[] b(char c7, int i7) {
        return (h0.FRAGMENT_ENCODE_SET + c7).getBytes(this.f10184a[i7].charset());
    }
}
