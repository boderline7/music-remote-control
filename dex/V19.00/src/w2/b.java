package w2;

import android.view.View;
import androidx.activity.e;
import androidx.appcompat.app.s0;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9457a;

    /* renamed from: b, reason: collision with root package name */
    public int f9458b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9459c;

    /* renamed from: d, reason: collision with root package name */
    public final Runnable f9460d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ c0.b f9461e;

    public b(BottomSheetBehavior bottomSheetBehavior) {
        this.f9457a = 0;
        this.f9461e = bottomSheetBehavior;
        this.f9460d = new s0(13, this);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(BottomSheetBehavior bottomSheetBehavior, int i7) {
        this(bottomSheetBehavior);
        this.f9457a = 0;
    }

    public b(SideSheetBehavior sideSheetBehavior) {
        this.f9457a = 1;
        this.f9461e = sideSheetBehavior;
        this.f9460d = new e(10, this);
    }

    public final void a(int i7) {
        c0.b bVar = this.f9461e;
        int i8 = this.f9457a;
        Runnable runnable = this.f9460d;
        switch (i8) {
            case 0:
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) bVar;
                WeakReference weakReference = bottomSheetBehavior.U;
                if (weakReference != null && weakReference.get() != null) {
                    this.f9458b = i7;
                    if (!this.f9459c) {
                        View view = (View) bottomSheetBehavior.U.get();
                        int i9 = d1.OVER_SCROLL_ALWAYS;
                        l0.m(view, runnable);
                        this.f9459c = true;
                        break;
                    }
                }
                break;
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) bVar;
                WeakReference weakReference2 = sideSheetBehavior.f4133p;
                if (weakReference2 != null && weakReference2.get() != null) {
                    this.f9458b = i7;
                    if (!this.f9459c) {
                        View view2 = (View) sideSheetBehavior.f4133p.get();
                        int i10 = d1.OVER_SCROLL_ALWAYS;
                        l0.m(view2, runnable);
                        this.f9459c = true;
                        break;
                    }
                }
                break;
        }
    }
}
