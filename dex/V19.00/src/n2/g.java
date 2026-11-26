package n2;

import android.support.v4.media.session.PlaybackStateCompat;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final int BOT = 32768;
    public static final int TOP = 16777216;

    /* renamed from: a, reason: collision with root package name */
    public long f7739a;

    /* renamed from: b, reason: collision with root package name */
    public long f7740b;

    /* renamed from: c, reason: collision with root package name */
    public long f7741c;

    /* renamed from: d, reason: collision with root package name */
    public final f f7742d = new f();

    /* renamed from: e, reason: collision with root package name */
    public l2.d f7743e;

    public final void a() {
        boolean z6 = false;
        while (true) {
            long j7 = this.f7739a;
            long j8 = this.f7741c;
            if (((j7 + j8) ^ j7) >= 16777216) {
                z6 = j8 < PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                if (!z6) {
                    return;
                }
            }
            if (z6) {
                this.f7741c = (-j7) & 32767;
                z6 = false;
            }
            this.f7740b = ((this.f7740b << 8) | this.f7743e.u()) & 4294967295L;
            this.f7741c = (this.f7741c << 8) & 4294967295L;
            this.f7739a = (this.f7739a << 8) & 4294967295L;
        }
    }

    public final void b() {
        long j7 = this.f7739a;
        long j8 = this.f7741c;
        f fVar = this.f7742d;
        long j9 = fVar.f7736a;
        this.f7739a = (((j9 & 4294967295L) * j8) + j7) & 4294967295L;
        this.f7741c = ((fVar.f7737b - (j9 & 4294967295L)) * j8) & 4294967295L;
    }

    public final String toString() {
        return "RangeCoder[\n  low=" + this.f7739a + "\n  code=" + this.f7740b + "\n  range=" + this.f7741c + "\n  subrange=" + this.f7742d + "]";
    }
}
