package a5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: e, reason: collision with root package name */
    public static final Pattern f309e = Pattern.compile("[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)", 2);

    /* renamed from: f, reason: collision with root package name */
    public static final Pattern f310f = Pattern.compile("[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);

    /* renamed from: g, reason: collision with root package name */
    public static final Pattern f311g = Pattern.compile("[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);

    /* renamed from: a, reason: collision with root package name */
    public final String f312a;

    /* renamed from: b, reason: collision with root package name */
    public final String f313b;

    /* renamed from: c, reason: collision with root package name */
    public final String f314c;

    /* renamed from: d, reason: collision with root package name */
    public final String f315d;

    public b(String str) {
        this.f312a = str;
        String str2 = h0.FRAGMENT_ENCODE_SET;
        if (str != null) {
            Matcher matcher = f309e.matcher(str);
            this.f313b = matcher.find() ? matcher.group(1) : str2;
            Matcher matcher2 = f310f.matcher(str);
            this.f314c = matcher2.find() ? matcher2.group(2) : null;
        } else {
            this.f313b = h0.FRAGMENT_ENCODE_SET;
            this.f314c = "UTF-8";
        }
        if (!"multipart/form-data".equalsIgnoreCase(this.f313b)) {
            this.f315d = null;
        } else {
            Matcher matcher3 = f311g.matcher(str);
            this.f315d = matcher3.find() ? matcher3.group(2) : null;
        }
    }

    public final String a() {
        String str = this.f314c;
        return str == null ? "US-ASCII" : str;
    }
}
