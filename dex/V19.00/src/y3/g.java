package y3;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {
    public static final String GB2312 = "GB2312";
    public static final Charset GB2312_CHARSET;

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f10190a = Charset.defaultCharset();
    public static final String SHIFT_JIS = "SJIS";
    public static final Charset SHIFT_JIS_CHARSET = Charset.forName(SHIFT_JIS);

    static {
        Charset charset;
        try {
            charset = Charset.forName(GB2312);
        } catch (UnsupportedCharsetException unused) {
            charset = null;
        }
        GB2312_CHARSET = charset;
        Charset forName = Charset.forName("EUC_JP");
        Charset charset2 = SHIFT_JIS_CHARSET;
        Charset charset3 = f10190a;
        if (charset2.equals(charset3)) {
            return;
        }
        forName.equals(charset3);
    }
}
