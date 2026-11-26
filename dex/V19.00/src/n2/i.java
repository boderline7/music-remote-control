package n2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends e {
    public static final int size = 4;

    /* renamed from: c, reason: collision with root package name */
    public int f7748c;

    public final int a() {
        byte[] bArr = this.f7734a;
        if (bArr != null) {
            this.f7748c = z.W0(bArr, this.f7735b);
        }
        return this.f7748c;
    }

    public final String toString() {
        return "State[\n  pos=" + this.f7735b + "\n  size=4\n  next=" + a() + "\n]";
    }
}
