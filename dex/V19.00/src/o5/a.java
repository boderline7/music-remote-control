package o5;

import java.nio.charset.Charset;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a INSTANCE = new a();
    public static final Charset ISO_8859_1;
    public static final Charset US_ASCII;
    public static final Charset UTF_16;
    public static final Charset UTF_16BE;
    public static final Charset UTF_16LE;
    public static final Charset UTF_8;

    /* renamed from: a, reason: collision with root package name */
    public static volatile Charset f8050a;

    /* renamed from: b, reason: collision with root package name */
    public static volatile Charset f8051b;

    static {
        Charset forName = Charset.forName("UTF-8");
        i5.a.f("forName(\"UTF-8\")", forName);
        UTF_8 = forName;
        Charset forName2 = Charset.forName("UTF-16");
        i5.a.f("forName(\"UTF-16\")", forName2);
        UTF_16 = forName2;
        Charset forName3 = Charset.forName("UTF-16BE");
        i5.a.f("forName(\"UTF-16BE\")", forName3);
        UTF_16BE = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        i5.a.f("forName(\"UTF-16LE\")", forName4);
        UTF_16LE = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        i5.a.f("forName(\"US-ASCII\")", forName5);
        US_ASCII = forName5;
        Charset forName6 = Charset.forName("ISO-8859-1");
        i5.a.f("forName(\"ISO-8859-1\")", forName6);
        ISO_8859_1 = forName6;
    }
}
