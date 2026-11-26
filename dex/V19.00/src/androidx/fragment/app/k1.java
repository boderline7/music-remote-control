package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k1 extends Transition.EpicenterCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2005a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Rect f2006b;

    public /* synthetic */ k1(Rect rect, int i7) {
        this.f2005a = i7;
        this.f2006b = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public final Rect onGetEpicenter(Transition transition) {
        int i7 = this.f2005a;
        Rect rect = this.f2006b;
        switch (i7) {
            case 0:
                break;
            default:
                if (rect == null || rect.isEmpty()) {
                }
                break;
        }
        return rect;
    }
}
