package k2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends b {
    public static final short endArcArchiveDataCrcSize = 4;
    public static final short endArcVolumeNumberSize = 2;

    public f(b bVar, byte[] bArr) {
        super(bVar);
        int i7 = 0;
        if ((this.f7213d & 2) != 0) {
            z.W0(bArr, 0);
            i7 = 4;
        }
        if ((this.f7213d & 8) != 0) {
            z.Y0(bArr, i7);
        }
    }
}
