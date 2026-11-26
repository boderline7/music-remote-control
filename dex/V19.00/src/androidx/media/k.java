package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2260a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2261b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f2262c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f2263d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2264e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2265f;

    public k(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, int i7, String str, int i8, Bundle bundle) {
        this.f2265f = oVar;
        this.f2261b = oVar2;
        this.f2262c = i7;
        this.f2263d = str;
        this.f2264e = i8;
    }

    public k(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, String str, int i7, int i8, Bundle bundle) {
        this.f2265f = oVar;
        this.f2261b = oVar2;
        this.f2263d = str;
        this.f2262c = i7;
        this.f2264e = i8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f2260a;
        androidx.fragment.app.o oVar = this.f2265f;
        androidx.fragment.app.o oVar2 = this.f2261b;
        switch (i7) {
            case 0:
                IBinder a7 = oVar2.a();
                ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.remove(a7);
                e eVar = new e((MediaBrowserServiceCompat) oVar.f2027a, this.f2263d, this.f2262c, this.f2264e, this.f2261b);
                ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                eVar.f2249f = ((MediaBrowserServiceCompat) oVar.f2027a).a();
                ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                if (eVar.f2249f == null) {
                    try {
                        Message obtain = Message.obtain();
                        obtain.what = 2;
                        obtain.arg1 = 2;
                        obtain.setData(null);
                        ((Messenger) oVar2.f2027a).send(obtain);
                        break;
                    } catch (RemoteException unused) {
                        return;
                    }
                } else {
                    try {
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.put(a7, eVar);
                        a7.linkToDeath(eVar, 0);
                        ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                        break;
                    } catch (RemoteException unused2) {
                        ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.remove(a7);
                        return;
                    }
                }
            default:
                IBinder a8 = oVar2.a();
                ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.remove(a8);
                Iterator it = ((MediaBrowserServiceCompat) oVar.f2027a).f2229d.iterator();
                while (true) {
                    if (it.hasNext()) {
                        e eVar2 = (e) it.next();
                        if (eVar2.f2246c == this.f2262c) {
                            r2 = (TextUtils.isEmpty(this.f2263d) || this.f2264e <= 0) ? new e((MediaBrowserServiceCompat) oVar.f2027a, eVar2.f2244a, eVar2.f2245b, eVar2.f2246c, this.f2261b) : null;
                            it.remove();
                        }
                    }
                }
                if (r2 == null) {
                    r2 = new e((MediaBrowserServiceCompat) oVar.f2027a, this.f2263d, this.f2264e, this.f2262c, this.f2261b);
                }
                ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.put(a8, r2);
                try {
                    a8.linkToDeath(r2, 0);
                    break;
                } catch (RemoteException unused3) {
                    return;
                }
        }
    }
}
