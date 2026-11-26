package k2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class b {
    public static final short BaseBlockSize = 7;
    public static final short EARC_DATACRC = 2;
    public static final short EARC_NEXT_VOLUME = 1;
    public static final short EARC_REVSPACE = 4;
    public static final short EARC_VOLNUMBER = 8;
    public static final short LHD_COMMENT = 8;
    public static final short LHD_DIRECTORY = 224;
    public static final short LHD_EXTFLAGS = 8192;
    public static final short LHD_EXTTIME = 4096;
    public static final short LHD_LARGE = 256;
    public static final short LHD_PASSWORD = 4;
    public static final short LHD_SALT = 1024;
    public static final short LHD_SOLID = 16;
    public static final short LHD_SPLIT_AFTER = 2;
    public static final short LHD_SPLIT_BEFORE = 1;
    public static final short LHD_UNICODE = 512;
    public static final short LHD_VERSION = 2048;
    public static final short LHD_WINDOW1024 = 128;
    public static final short LHD_WINDOW128 = 32;
    public static final short LHD_WINDOW2048 = 160;
    public static final short LHD_WINDOW256 = 64;
    public static final short LHD_WINDOW4096 = 192;
    public static final short LHD_WINDOW512 = 96;
    public static final short LHD_WINDOW64 = 0;
    public static final short LHD_WINDOWMASK = 224;
    public static final short LONG_BLOCK = Short.MIN_VALUE;
    public static final short MHD_AV = 32;
    public static final short MHD_COMMENT = 2;
    public static final short MHD_ENCRYPTVER = 512;
    public static final short MHD_FIRSTVOLUME = 256;
    public static final short MHD_LOCK = 4;
    public static final short MHD_NEWNUMBERING = 16;
    public static final short MHD_PACK_COMMENT = 16;
    public static final short MHD_PASSWORD = 128;
    public static final short MHD_PROTECT = 64;
    public static final short MHD_SOLID = 8;
    public static final short MHD_VOLUME = 1;
    public static final short SKIP_IF_UNKNOWN = 16384;

    /* renamed from: f, reason: collision with root package name */
    public static final j6.a f7209f = j6.b.d(b.class);

    /* renamed from: a, reason: collision with root package name */
    public long f7210a;

    /* renamed from: b, reason: collision with root package name */
    public short f7211b;

    /* renamed from: c, reason: collision with root package name */
    public byte f7212c;

    /* renamed from: d, reason: collision with root package name */
    public short f7213d;

    /* renamed from: e, reason: collision with root package name */
    public short f7214e;

    public b(b bVar) {
        this.f7211b = (short) 0;
        this.f7212c = (byte) 0;
        this.f7213d = (short) 0;
        this.f7214e = (short) 0;
        this.f7213d = bVar.f7213d;
        this.f7211b = bVar.f7211b;
        this.f7212c = bVar.b().f7256a;
        this.f7214e = bVar.a(false);
        this.f7210a = bVar.f7210a;
    }

    public final short a(boolean z6) {
        short s6 = this.f7214e;
        return z6 ? (short) (s6 + ((short) (((~s6) + 1) & 15))) : s6;
    }

    public final s b() {
        s sVar = s.MarkHeader;
        byte b7 = this.f7212c;
        if (sVar.a(b7)) {
            return sVar;
        }
        s sVar2 = s.MainHeader;
        if (sVar2.a(b7)) {
            return sVar2;
        }
        s sVar3 = s.FileHeader;
        if (sVar3.a(b7)) {
            return sVar3;
        }
        s sVar4 = s.EndArcHeader;
        if (sVar4.a(b7)) {
            return sVar4;
        }
        s sVar5 = s.NewSubHeader;
        if (sVar5.a(b7)) {
            return sVar5;
        }
        s sVar6 = s.SubHeader;
        if (sVar6.a(b7)) {
            return sVar6;
        }
        s sVar7 = s.SignHeader;
        if (sVar7.a(b7)) {
            return sVar7;
        }
        s sVar8 = s.ProtectHeader;
        if (sVar8.a(b7)) {
            return sVar8;
        }
        s sVar9 = s.CommHeader;
        if (sVar9.a(b7)) {
            return sVar9;
        }
        s sVar10 = s.AvHeader;
        if (sVar10.a(b7)) {
            return sVar10;
        }
        return null;
    }
}
