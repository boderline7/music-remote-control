package k;

import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.q1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7068a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7069b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f7070c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f7071d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f7072e;

    public /* synthetic */ g(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        this.f7068a = i7;
        this.f7072e = obj;
        this.f7069b = obj2;
        this.f7070c = obj3;
        this.f7071d = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7068a;
        Object obj = this.f7071d;
        Object obj2 = this.f7070c;
        Object obj3 = this.f7069b;
        switch (i7) {
            case 0:
                h hVar = (h) obj3;
                if (hVar != null) {
                    e.a aVar = (e.a) this.f7072e;
                    ((i) aVar.f6290b).A = true;
                    hVar.f7074b.c(false);
                    ((i) aVar.f6290b).A = false;
                }
                MenuItem menuItem = (MenuItem) obj2;
                if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                    ((o) obj).q(menuItem, null, 4);
                    break;
                }
                break;
            default:
                ((q1) obj3).getClass();
                q1.g((View) obj2, (Rect) obj);
                break;
        }
    }
}
