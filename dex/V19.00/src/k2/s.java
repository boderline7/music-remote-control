package k2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum s {
    MainHeader((byte) 115),
    MarkHeader((byte) 114),
    FileHeader((byte) 116),
    CommHeader((byte) 117),
    AvHeader((byte) 118),
    SubHeader((byte) 119),
    ProtectHeader((byte) 120),
    SignHeader((byte) 121),
    NewSubHeader((byte) 122),
    EndArcHeader((byte) 123);


    /* renamed from: a, reason: collision with root package name */
    public final byte f7256a;

    s(byte b7) {
        this.f7256a = b7;
    }

    public final boolean a(byte b7) {
        return this.f7256a == b7;
    }
}
