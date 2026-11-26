package androidx.media;

import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e implements IBinder.DeathRecipient {

    /* renamed from: a, reason: collision with root package name */
    public final String f2244a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2245b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2246c;

    /* renamed from: d, reason: collision with root package name */
    public final androidx.fragment.app.o f2247d;

    /* renamed from: e, reason: collision with root package name */
    public final HashMap f2248e = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    public c f2249f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserServiceCompat f2250g;

    public e(MediaBrowserServiceCompat mediaBrowserServiceCompat, String str, int i7, int i8, androidx.fragment.app.o oVar) {
        this.f2250g = mediaBrowserServiceCompat;
        this.f2244a = str;
        this.f2245b = i7;
        this.f2246c = i8;
        if (str == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            o.u(str, i7, i8);
        }
        this.f2247d = oVar;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.f2250g.f2231f.post(new d(this));
    }
}
