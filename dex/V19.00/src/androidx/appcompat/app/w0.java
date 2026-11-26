package androidx.appcompat.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w0 {
    public static final int DAY = 0;
    public static final int NIGHT = 1;

    /* renamed from: d, reason: collision with root package name */
    public static w0 f875d;

    /* renamed from: a, reason: collision with root package name */
    public long f876a;

    /* renamed from: b, reason: collision with root package name */
    public long f877b;

    /* renamed from: c, reason: collision with root package name */
    public int f878c;

    public final void a(double d7, double d8, long j7) {
        double d9 = (0.01720197f * ((j7 - 946728000000L) / 8.64E7f)) + 6.24006f;
        double sin = (Math.sin(r4 * 3.0f) * 5.236000106378924E-6d) + (Math.sin(2.0f * r4) * 3.4906598739326E-4d) + (Math.sin(d9) * 0.03341960161924362d) + d9 + 1.796593063d + 3.141592653589793d;
        double sin2 = (Math.sin(2.0d * sin) * (-0.0069d)) + (Math.sin(d9) * 0.0053d) + Math.round((r3 - 9.0E-4f) - r7) + 9.0E-4f + ((-d8) / 360.0d);
        double asin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(sin));
        double d10 = 0.01745329238474369d * d7;
        double sin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(asin) * Math.sin(d10))) / (Math.cos(asin) * Math.cos(d10));
        if (sin3 >= 1.0d) {
            this.f878c = 1;
            this.f876a = -1L;
            this.f877b = -1L;
        } else {
            if (sin3 <= -1.0d) {
                this.f878c = 0;
                this.f876a = -1L;
                this.f877b = -1L;
                return;
            }
            double acos = (float) (Math.acos(sin3) / 6.283185307179586d);
            this.f876a = Math.round((sin2 + acos) * 8.64E7d) + 946728000000L;
            long round = Math.round((sin2 - acos) * 8.64E7d) + 946728000000L;
            this.f877b = round;
            if (round >= j7 || this.f876a <= j7) {
                this.f878c = 1;
            } else {
                this.f878c = 0;
            }
        }
    }
}
