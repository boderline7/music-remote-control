package s5;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {
    public static final q Companion = new q();

    /* renamed from: j, reason: collision with root package name */
    public static final Pattern f8873j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: k, reason: collision with root package name */
    public static final Pattern f8874k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: l, reason: collision with root package name */
    public static final Pattern f8875l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: m, reason: collision with root package name */
    public static final Pattern f8876m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: a, reason: collision with root package name */
    public final String f8877a;

    /* renamed from: b, reason: collision with root package name */
    public final String f8878b;

    /* renamed from: c, reason: collision with root package name */
    public final long f8879c;

    /* renamed from: d, reason: collision with root package name */
    public final String f8880d;

    /* renamed from: e, reason: collision with root package name */
    public final String f8881e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f8882f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f8883g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f8884h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f8885i;

    public r(String str, String str2, long j7, String str3, String str4, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.f8877a = str;
        this.f8878b = str2;
        this.f8879c = j7;
        this.f8880d = str3;
        this.f8881e = str4;
        this.f8882f = z6;
        this.f8883g = z7;
        this.f8884h = z8;
        this.f8885i = z9;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r) {
            r rVar = (r) obj;
            if (i5.a.c(rVar.f8877a, this.f8877a) && i5.a.c(rVar.f8878b, this.f8878b) && rVar.f8879c == this.f8879c && i5.a.c(rVar.f8880d, this.f8880d) && i5.a.c(rVar.f8881e, this.f8881e) && rVar.f8882f == this.f8882f && rVar.f8883g == this.f8883g && rVar.f8884h == this.f8884h && rVar.f8885i == this.f8885i) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f8885i) + ((Boolean.hashCode(this.f8884h) + ((Boolean.hashCode(this.f8883g) + ((Boolean.hashCode(this.f8882f) + ((this.f8881e.hashCode() + ((this.f8880d.hashCode() + ((Long.hashCode(this.f8879c) + ((this.f8878b.hashCode() + ((this.f8877a.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8877a);
        sb.append('=');
        sb.append(this.f8878b);
        if (this.f8884h) {
            long j7 = this.f8879c;
            if (j7 == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                String format = ((DateFormat) y5.c.f10212a.get()).format(new Date(j7));
                i5.a.f("STANDARD_DATE_FORMAT.get().format(this)", format);
                sb.append(format);
            }
        }
        if (!this.f8885i) {
            sb.append("; domain=");
            sb.append(this.f8880d);
        }
        sb.append("; path=");
        sb.append(this.f8881e);
        if (this.f8882f) {
            sb.append("; secure");
        }
        if (this.f8883g) {
            sb.append("; httponly");
        }
        String sb2 = sb.toString();
        i5.a.f("toString()", sb2);
        return sb2;
    }
}
