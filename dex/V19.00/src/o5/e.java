package o5;

import java.io.Serializable;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements Serializable {
    public static final d Companion = new d();

    /* renamed from: a, reason: collision with root package name */
    public final Pattern f8062a;

    public e() {
        Pattern compile = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        i5.a.f("compile(pattern)", compile);
        this.f8062a = compile;
    }

    public final String toString() {
        String pattern = this.f8062a.toString();
        i5.a.f("nativePattern.toString()", pattern);
        return pattern;
    }
}
