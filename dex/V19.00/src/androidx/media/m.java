package androidx.media;

import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2269a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2270b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f2271c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.o f2272d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2273e;

    public /* synthetic */ m(androidx.fragment.app.o oVar, androidx.fragment.app.o oVar2, String str, Object obj, int i7) {
        this.f2269a = i7;
        this.f2272d = oVar;
        this.f2270b = oVar2;
        this.f2271c = str;
        this.f2273e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f2269a;
        String str = this.f2271c;
        Object obj = this.f2273e;
        androidx.fragment.app.o oVar = this.f2272d;
        androidx.fragment.app.o oVar2 = this.f2270b;
        switch (i7) {
            case 0:
                e eVar = (e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.getOrDefault(oVar2.a(), null);
                if (eVar == null) {
                    return;
                }
                IBinder iBinder = (IBinder) obj;
                ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                HashMap hashMap = eVar.f2248e;
                if (iBinder == null) {
                    hashMap.remove(str);
                    return;
                }
                List list = (List) hashMap.get(str);
                if (list != null) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (iBinder == ((o0.c) it.next()).f7788a) {
                            it.remove();
                        }
                    }
                    if (list.size() == 0) {
                        hashMap.remove(str);
                        return;
                    }
                    return;
                }
                return;
            default:
                if (((e) ((MediaBrowserServiceCompat) oVar.f2027a).f2230e.getOrDefault(oVar2.a(), null)) == null) {
                    return;
                }
                ((MediaBrowserServiceCompat) oVar.f2027a).getClass();
                b bVar = new b(str, (ResultReceiver) obj, 0);
                bVar.f2259c = 2;
                bVar.c();
                if (!bVar.a()) {
                    throw new IllegalStateException(a.a.B("onLoadItem must call detach() or sendResult() before returning for id=", str));
                }
                return;
        }
    }
}
