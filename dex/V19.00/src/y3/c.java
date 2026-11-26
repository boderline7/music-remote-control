package y3;

import java.nio.charset.Charset;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum c {
    Cp437(new int[]{0, 2}, new String[0]),
    ISO8859_1(new int[]{1, 3}, "ISO-8859-1"),
    ISO8859_2(4, "ISO-8859-2"),
    ISO8859_3(5, "ISO-8859-3"),
    ISO8859_4(6, "ISO-8859-4"),
    ISO8859_5(7, "ISO-8859-5"),
    ISO8859_7(9, "ISO-8859-7"),
    ISO8859_9(11, "ISO-8859-9"),
    ISO8859_13(15, "ISO-8859-13"),
    ISO8859_15(17, "ISO-8859-15"),
    ISO8859_16(18, "ISO-8859-16"),
    SJIS(20, "Shift_JIS"),
    Cp1250(21, "windows-1250"),
    Cp1251(22, "windows-1251"),
    Cp1252(23, "windows-1252"),
    Cp1256(24, "windows-1256"),
    UnicodeBigUnmarked(25, "UTF-16BE", "UnicodeBig"),
    UTF8(26, "UTF-8"),
    ASCII(new int[]{27, z1.c.REM_FLOAT}, "US-ASCII"),
    Big5(new int[]{28}, new String[0]),
    GB18030(29, g.GB2312, "EUC_CN", "GBK"),
    EUC_KR(30, "EUC-KR");


    /* renamed from: c, reason: collision with root package name */
    public static final HashMap f10178c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public static final HashMap f10179d = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    public final int[] f10181a;

    /* renamed from: b, reason: collision with root package name */
    public final String[] f10182b;

    static {
        for (c cVar : values()) {
            for (int i7 : cVar.f10181a) {
                f10178c.put(Integer.valueOf(i7), cVar);
            }
            f10179d.put(cVar.name(), cVar);
            for (String str : cVar.f10182b) {
                f10179d.put(str, cVar);
            }
        }
    }

    c(int i7, String... strArr) {
        this.f10181a = new int[]{i7};
        this.f10182b = strArr;
    }

    c(int[] iArr, String... strArr) {
        this.f10181a = iArr;
        this.f10182b = strArr;
    }

    public static c a(Charset charset) {
        return (c) f10179d.get(charset.name());
    }
}
