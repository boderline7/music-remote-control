package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w f451e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f452f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f453g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(String str, w wVar, int i7, int i8) {
        super(str, true);
        this.f451e = wVar;
        this.f452f = i7;
        this.f453g = i8;
    }

    @Override // w5.a
    public final long a() {
        int i7 = this.f452f;
        int i8 = this.f453g;
        w wVar = this.f451e;
        wVar.getClass();
        try {
            wVar.f501y.E(i7, i8, true);
            return -1L;
        } catch (IOException e7) {
            wVar.B(e7);
            return -1L;
        }
    }
}
