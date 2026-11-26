package n0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: e, reason: collision with root package name */
    public static final byte[] f7563e = new byte[1792];

    /* renamed from: a, reason: collision with root package name */
    public final CharSequence f7564a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7565b;

    /* renamed from: c, reason: collision with root package name */
    public int f7566c;

    /* renamed from: d, reason: collision with root package name */
    public char f7567d;

    static {
        for (int i7 = 0; i7 < 1792; i7++) {
            f7563e[i7] = Character.getDirectionality(i7);
        }
    }

    public b(CharSequence charSequence) {
        this.f7564a = charSequence;
        this.f7565b = charSequence.length();
    }

    public final byte a() {
        int i7 = this.f7566c - 1;
        CharSequence charSequence = this.f7564a;
        char charAt = charSequence.charAt(i7);
        this.f7567d = charAt;
        if (Character.isLowSurrogate(charAt)) {
            int codePointBefore = Character.codePointBefore(charSequence, this.f7566c);
            this.f7566c -= Character.charCount(codePointBefore);
            return Character.getDirectionality(codePointBefore);
        }
        this.f7566c--;
        char c7 = this.f7567d;
        return c7 < 1792 ? f7563e[c7] : Character.getDirectionality(c7);
    }
}
