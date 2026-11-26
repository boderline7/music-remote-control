package o2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class a {
    public static final int MAX_SIZE = 32768;

    /* renamed from: a, reason: collision with root package name */
    public int f7876a;

    /* renamed from: b, reason: collision with root package name */
    public int f7877b;

    /* renamed from: c, reason: collision with root package name */
    public final byte[] f7878c = new byte[32768];

    public final void a(int i7) {
        int i8 = i7 + this.f7877b;
        this.f7876a += i8 >>> 3;
        this.f7877b = i8 & 7;
    }

    public final int b() {
        int i7 = this.f7876a;
        byte[] bArr = this.f7878c;
        return (((((bArr[i7] & 255) << 16) + ((bArr[i7 + 1] & 255) << 8)) + (bArr[i7 + 2] & 255)) >>> (8 - this.f7877b)) & 65535;
    }
}
