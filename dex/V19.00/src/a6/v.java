package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w f474e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f475f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f476g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(String str, w wVar, int i7, long j7) {
        super(str, true);
        this.f474e = wVar;
        this.f475f = i7;
        this.f476g = j7;
    }

    @Override // w5.a
    public final long a() {
        w wVar = this.f474e;
        try {
            wVar.f501y.G(this.f475f, this.f476g);
            return -1L;
        } catch (IOException e7) {
            wVar.B(e7);
            return -1L;
        }
    }
}
