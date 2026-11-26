package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2274a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2275b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f2276c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Bundle f2277d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2278e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2279f;

    public /* synthetic */ n(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, String str, Bundle bundle, ResultReceiver resultReceiver, int i7) {
        this.f2274a = i7;
        this.f2279f = oVar;
        this.f2275b = oVar2;
        this.f2276c = str;
        this.f2277d = bundle;
        this.f2278e = resultReceiver;
    }

    public n(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, String str, IBinder iBinder, Bundle bundle) {
        this.f2274a = 2;
        this.f2279f = oVar;
        this.f2275b = oVar2;
        this.f2276c = str;
        this.f2278e = iBinder;
        this.f2277d = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle = this.f2277d;
        int i7 = this.f2274a;
        String str = this.f2276c;
        Object obj = this.f2278e;
        androidx.fragment.app.o oVar = this.f2279f;
        androidx.fragment.app.o oVar2 = this.f2275b;
        switch (i7) {
            case 0:
                if (((e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.getOrDefault(oVar2.a(), null)) == null) {
                    return;
                }
                ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                b bVar = new b(str, (ResultReceiver) obj, 1);
                bVar.f2259c = 4;
                bVar.c();
                if (!bVar.a()) {
                    throw new IllegalStateException(a.a.B("onSearch must call detach() or sendResult() before returning for query=", str));
                }
                return;
            case 1:
                if (((e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.getOrDefault(oVar2.a(), null)) == null) {
                    Objects.toString(bundle);
                    return;
                } else {
                    ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                    ((ResultReceiver) obj).m(-1, null);
                    return;
                }
            default:
                e eVar = (e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.getOrDefault(oVar2.a(), null);
                if (eVar == null) {
                    return;
                }
                MediaBrowserServiceCompat mediaBrowserServiceCompat = (MediaBrowserServiceCompat) oVar.f2027a;
                IBinder iBinder = (IBinder) obj;
                mediaBrowserServiceCompat.getClass();
                HashMap hashMap = eVar.f2248e;
                List<o0.c> list = (List) hashMap.get(str);
                if (list == null) {
                    list = new ArrayList();
                }
                for (o0.c cVar : list) {
                    if (iBinder == cVar.f7788a) {
                        Bundle bundle2 = (Bundle) cVar.f7789b;
                        if (bundle == bundle2) {
                            return;
                        }
                        if (bundle == null) {
                            if (bundle2.getInt(android.support.v4.media.m.EXTRA_PAGE, -1) == -1 && bundle2.getInt(android.support.v4.media.m.EXTRA_PAGE_SIZE, -1) == -1) {
                                return;
                            }
                        } else if (bundle2 == null) {
                            if (bundle.getInt(android.support.v4.media.m.EXTRA_PAGE, -1) == -1 && bundle.getInt(android.support.v4.media.m.EXTRA_PAGE_SIZE, -1) == -1) {
                                return;
                            }
                        } else if (bundle.getInt(android.support.v4.media.m.EXTRA_PAGE, -1) == bundle2.getInt(android.support.v4.media.m.EXTRA_PAGE, -1) && bundle.getInt(android.support.v4.media.m.EXTRA_PAGE_SIZE, -1) == bundle2.getInt(android.support.v4.media.m.EXTRA_PAGE_SIZE, -1)) {
                            return;
                        }
                    }
                }
                list.add(new o0.c(iBinder, bundle));
                hashMap.put(str, list);
                if (bundle == null) {
                    mediaBrowserServiceCompat.b();
                } else {
                    mediaBrowserServiceCompat.b();
                }
                throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + eVar.f2244a + " id=" + str);
        }
    }
}
