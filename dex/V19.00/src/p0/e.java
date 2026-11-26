package p0;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements f {

    /* renamed from: a, reason: collision with root package name */
    public final ContentInfo.Builder f8118a;

    public e(ClipData clipData, int i7) {
        this.f8118a = e0.w.f(clipData, i7);
    }

    @Override // p0.f
    public final i a() {
        ContentInfo build;
        build = this.f8118a.build();
        return new i(new e.a(build));
    }

    @Override // p0.f
    public final void c(Bundle bundle) {
        this.f8118a.setExtras(bundle);
    }

    @Override // p0.f
    public final void d(Uri uri) {
        this.f8118a.setLinkUri(uri);
    }

    @Override // p0.f
    public final void e(int i7) {
        this.f8118a.setFlags(i7);
    }
}
