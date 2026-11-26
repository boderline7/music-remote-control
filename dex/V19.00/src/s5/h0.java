package s5;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h0 {
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";

    /* renamed from: a, reason: collision with root package name */
    public final String f8792a;

    /* renamed from: b, reason: collision with root package name */
    public final String f8793b;

    /* renamed from: c, reason: collision with root package name */
    public final String f8794c;

    /* renamed from: d, reason: collision with root package name */
    public final String f8795d;

    /* renamed from: e, reason: collision with root package name */
    public final int f8796e;

    /* renamed from: f, reason: collision with root package name */
    public final List f8797f;

    /* renamed from: g, reason: collision with root package name */
    public final String f8798g;

    /* renamed from: h, reason: collision with root package name */
    public final String f8799h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f8800i;
    public static final g0 Companion = new g0();

    /* renamed from: j, reason: collision with root package name */
    public static final char[] f8791j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public h0(String str, String str2, String str3, String str4, int i7, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        this.f8792a = str;
        this.f8793b = str2;
        this.f8794c = str3;
        this.f8795d = str4;
        this.f8796e = i7;
        this.f8797f = arrayList2;
        this.f8798g = str5;
        this.f8799h = str6;
        this.f8800i = i5.a.c(str, "https");
    }

    public final String a() {
        if (this.f8794c.length() == 0) {
            return FRAGMENT_ENCODE_SET;
        }
        int length = this.f8792a.length() + 3;
        String str = this.f8799h;
        String substring = str.substring(o5.k.F(str, ':', length, false, 4) + 1, o5.k.F(str, '@', 0, false, 6));
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public final String b() {
        int length = this.f8792a.length() + 3;
        String str = this.f8799h;
        int F = o5.k.F(str, '/', length, false, 4);
        String substring = str.substring(F, t5.b.e(F, str.length(), str, "?#"));
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public final ArrayList c() {
        int length = this.f8792a.length() + 3;
        String str = this.f8799h;
        int F = o5.k.F(str, '/', length, false, 4);
        int e7 = t5.b.e(F, str.length(), str, "?#");
        ArrayList arrayList = new ArrayList();
        while (F < e7) {
            int i7 = F + 1;
            int f7 = t5.b.f(str, '/', i7, e7);
            String substring = str.substring(i7, f7);
            i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
            arrayList.add(substring);
            F = f7;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f8797f == null) {
            return null;
        }
        String str = this.f8799h;
        int F = o5.k.F(str, '?', 0, false, 6) + 1;
        String substring = str.substring(F, t5.b.f(str, '#', F, str.length()));
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public final String e() {
        if (this.f8793b.length() == 0) {
            return FRAGMENT_ENCODE_SET;
        }
        int length = this.f8792a.length() + 3;
        String str = this.f8799h;
        String substring = str.substring(length, t5.b.e(length, str.length(), str, ":@"));
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof h0) && i5.a.c(((h0) obj).f8799h, this.f8799h);
    }

    public final String f() {
        f0 f0Var;
        try {
            f0Var = new f0();
            f0Var.b(this, "/...");
        } catch (IllegalArgumentException unused) {
            f0Var = null;
        }
        i5.a.d(f0Var);
        g0 g0Var = Companion;
        f0Var.f8784b = g0.a(g0Var, FRAGMENT_ENCODE_SET, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, z1.c.INVOKE_POLYMORPHIC_RANGE);
        f0Var.f8785c = g0.a(g0Var, FRAGMENT_ENCODE_SET, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, z1.c.INVOKE_POLYMORPHIC_RANGE);
        return f0Var.a().f8799h;
    }

    public final URI g() {
        String substring;
        String str;
        f0 f0Var = new f0();
        String str2 = this.f8792a;
        f0Var.f8783a = str2;
        f0Var.f8784b = e();
        f0Var.f8785c = a();
        f0Var.f8786d = this.f8795d;
        g0 g0Var = Companion;
        g0Var.getClass();
        int b7 = g0.b(str2);
        int i7 = this.f8796e;
        if (i7 == b7) {
            i7 = -1;
        }
        f0Var.f8787e = i7;
        ArrayList arrayList = f0Var.f8788f;
        arrayList.clear();
        arrayList.addAll(c());
        String d7 = d();
        f0Var.f8789g = d7 != null ? g0.e(g0.a(g0Var, d7, 0, 0, QUERY_ENCODE_SET, true, false, true, false, z1.c.DIV_INT_LIT16)) : null;
        if (this.f8798g == null) {
            substring = null;
        } else {
            String str3 = this.f8799h;
            substring = str3.substring(o5.k.F(str3, '#', 0, false, 6) + 1);
            i5.a.f("this as java.lang.String).substring(startIndex)", substring);
        }
        f0Var.f8790h = substring;
        String str4 = f0Var.f8786d;
        if (str4 != null) {
            Pattern compile = Pattern.compile("[\"<>^`{|}]");
            i5.a.f("compile(pattern)", compile);
            str = compile.matcher(str4).replaceAll(FRAGMENT_ENCODE_SET);
            i5.a.f("nativePattern.matcher(in…).replaceAll(replacement)", str);
        } else {
            str = null;
        }
        f0Var.f8786d = str;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            arrayList.set(i8, g0.a(Companion, (String) arrayList.get(i8), 0, 0, PATH_SEGMENT_ENCODE_SET_URI, true, true, false, false, 227));
        }
        ArrayList arrayList2 = f0Var.f8789g;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i9 = 0; i9 < size2; i9++) {
                String str5 = (String) arrayList2.get(i9);
                arrayList2.set(i9, str5 != null ? g0.a(Companion, str5, 0, 0, QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, false, z1.c.SHL_LONG_2ADDR) : null);
            }
        }
        String str6 = f0Var.f8790h;
        f0Var.f8790h = str6 != null ? g0.a(Companion, str6, 0, 0, FRAGMENT_ENCODE_SET_URI, true, true, false, true, z1.c.SHL_LONG) : null;
        String f0Var2 = f0Var.toString();
        try {
            return new URI(f0Var2);
        } catch (URISyntaxException e7) {
            try {
                Pattern compile2 = Pattern.compile("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
                i5.a.f("compile(pattern)", compile2);
                String replaceAll = compile2.matcher(f0Var2).replaceAll(FRAGMENT_ENCODE_SET);
                i5.a.f("nativePattern.matcher(in…).replaceAll(replacement)", replaceAll);
                URI create = URI.create(replaceAll);
                i5.a.f("{\n      // Unlikely edge…Unexpected!\n      }\n    }", create);
                return create;
            } catch (Exception unused) {
                throw new RuntimeException(e7);
            }
        }
    }

    public final int hashCode() {
        return this.f8799h.hashCode();
    }

    public final String toString() {
        return this.f8799h;
    }
}
