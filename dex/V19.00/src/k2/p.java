package k2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class p extends c {
    public static final short SubBlockHeaderSize = 3;

    /* renamed from: l, reason: collision with root package name */
    public static final j6.a f7245l = j6.b.d(p.class);

    /* renamed from: j, reason: collision with root package name */
    public final short f7246j;

    /* renamed from: k, reason: collision with root package name */
    public final byte f7247k;

    public p(c cVar, byte[] bArr) {
        super(cVar);
        this.f7246j = z.Y0(bArr, 0);
        this.f7247k = (byte) (this.f7247k | (bArr[2] & 255));
    }

    public p(p pVar) {
        super(pVar);
        this.f7246j = pVar.c().f7249a;
        this.f7247k = pVar.f7247k;
    }

    public final q c() {
        q qVar = q.EA_HEAD;
        short s6 = this.f7246j;
        if (qVar.a(s6)) {
            return qVar;
        }
        q qVar2 = q.UO_HEAD;
        if (qVar2.a(s6)) {
            return qVar2;
        }
        q qVar3 = q.MAC_HEAD;
        if (qVar3.a(s6)) {
            return qVar3;
        }
        q qVar4 = q.BEEA_HEAD;
        if (qVar4.a(s6)) {
            return qVar4;
        }
        q qVar5 = q.NTACL_HEAD;
        if (qVar5.a(s6)) {
            return qVar5;
        }
        q qVar6 = q.STREAM_HEAD;
        if (qVar6.a(s6)) {
            return qVar6;
        }
        return null;
    }

    public void d() {
        j6.a aVar = b.f7209f;
        if (aVar.c()) {
            aVar.t("HeaderType: " + b() + "\nHeadCRC: " + Integer.toHexString(this.f7211b) + "\nFlags: " + Integer.toHexString(this.f7213d) + "\nHeaderSize: " + ((int) a(false)) + "\nPosition in file: " + this.f7210a);
        }
        j6.a aVar2 = c.f7215i;
        if (aVar2.c()) {
            aVar2.a(Long.valueOf(this.f7216g), Long.valueOf(this.f7217h));
        }
        j6.a aVar3 = f7245l;
        if (aVar3.c()) {
            aVar3.d("subtype: {}", c());
            aVar3.d("level: {}", Byte.valueOf(this.f7247k));
        }
    }
}
