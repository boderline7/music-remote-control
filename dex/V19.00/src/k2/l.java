package k2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f7243a;
    public static final l SUBHEAD_TYPE_CMT = new l(new byte[]{67, 77, 84});
    public static final l SUBHEAD_TYPE_ACL = new l(new byte[]{65, 67, 76});
    public static final l SUBHEAD_TYPE_STREAM = new l(new byte[]{83, 84, 77});
    public static final l SUBHEAD_TYPE_UOWNER = new l(new byte[]{85, 79, 87});
    public static final l SUBHEAD_TYPE_AV = new l(new byte[]{65, 86});
    public static final l SUBHEAD_TYPE_RR = new l(new byte[]{82, 82});
    public static final l SUBHEAD_TYPE_OS2EA = new l(new byte[]{69, 65, 50});
    public static final l SUBHEAD_TYPE_BEOSEA = new l(new byte[]{69, 65, 66, 69});

    public l(byte[] bArr) {
        this.f7243a = bArr;
    }

    public final String toString() {
        return new String(this.f7243a);
    }
}
