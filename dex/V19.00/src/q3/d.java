package q3;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import l6.f;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ SideSheetBehavior f8449d;

    public d(SideSheetBehavior sideSheetBehavior) {
        this.f8449d = sideSheetBehavior;
    }

    @Override // l6.f
    public final boolean E0(int i7, View view) {
        WeakReference weakReference;
        SideSheetBehavior sideSheetBehavior = this.f8449d;
        return (sideSheetBehavior.f4125h == 1 || (weakReference = sideSheetBehavior.f4133p) == null || weakReference.get() != view) ? false : true;
    }

    @Override // l6.f
    public final int I(View view) {
        SideSheetBehavior sideSheetBehavior = this.f8449d;
        return sideSheetBehavior.f4129l + sideSheetBehavior.f4132o;
    }

    @Override // l6.f
    public final void i0(int i7) {
        if (i7 == 1) {
            SideSheetBehavior sideSheetBehavior = this.f8449d;
            if (sideSheetBehavior.f4124g) {
                sideSheetBehavior.x(1);
            }
        }
    }

    @Override // l6.f
    public final void j0(View view, int i7, int i8) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        SideSheetBehavior sideSheetBehavior = this.f8449d;
        WeakReference weakReference = sideSheetBehavior.f4134q;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
            a aVar = sideSheetBehavior.f4118a;
            int left = view.getLeft();
            int right = view.getRight();
            int i9 = aVar.f8441d;
            SideSheetBehavior sideSheetBehavior2 = aVar.f8442e;
            switch (i9) {
                case 0:
                    if (left <= sideSheetBehavior2.f4130m) {
                        marginLayoutParams.leftMargin = right;
                        break;
                    }
                    break;
                default:
                    int i10 = sideSheetBehavior2.f4130m;
                    if (left <= i10) {
                        marginLayoutParams.rightMargin = i10 - left;
                        break;
                    }
                    break;
            }
            view2.setLayoutParams(marginLayoutParams);
        }
        LinkedHashSet linkedHashSet = sideSheetBehavior.f4139v;
        if (linkedHashSet.isEmpty()) {
            return;
        }
        sideSheetBehavior.f4118a.G0(i7);
        Iterator it = linkedHashSet.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0054, code lost:
    
        if (java.lang.Math.abs(r6 - r0.f4118a.H0()) < java.lang.Math.abs(r6 - r0.f4118a.I0())) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
    
        if (r0.f4118a.K0(r5) == false) goto L19;
     */
    @Override // l6.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k0(android.view.View r5, float r6, float r7) {
        /*
            r4 = this;
            com.google.android.material.sidesheet.SideSheetBehavior r0 = r4.f8449d
            q3.a r1 = r0.f4118a
            boolean r1 = r1.J0(r6)
            r2 = 3
            if (r1 == 0) goto Lc
            goto L56
        Lc:
            q3.a r1 = r0.f4118a
            boolean r1 = r1.M0(r5, r6)
            r3 = 5
            if (r1 == 0) goto L27
            q3.a r1 = r0.f4118a
            boolean r6 = r1.L0(r6, r7)
            if (r6 != 0) goto L25
            q3.a r6 = r0.f4118a
            boolean r6 = r6.K0(r5)
            if (r6 == 0) goto L56
        L25:
            r2 = r3
            goto L56
        L27:
            r1 = 0
            int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r1 == 0) goto L39
            float r6 = java.lang.Math.abs(r6)
            float r7 = java.lang.Math.abs(r7)
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L39
            goto L25
        L39:
            int r6 = r5.getLeft()
            q3.a r7 = r0.f4118a
            int r7 = r7.H0()
            int r7 = r6 - r7
            int r7 = java.lang.Math.abs(r7)
            q3.a r1 = r0.f4118a
            int r1 = r1.I0()
            int r6 = r6 - r1
            int r6 = java.lang.Math.abs(r6)
            if (r7 >= r6) goto L25
        L56:
            r6 = 1
            r0.z(r5, r2, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q3.d.k0(android.view.View, float, float):void");
    }

    @Override // l6.f
    public final int n(View view, int i7) {
        int i8;
        int i9;
        SideSheetBehavior sideSheetBehavior = this.f8449d;
        a aVar = sideSheetBehavior.f4118a;
        switch (aVar.f8441d) {
            case 0:
                i8 = -aVar.f8442e.f4129l;
                break;
            default:
                i8 = aVar.H0();
                break;
        }
        a aVar2 = sideSheetBehavior.f4118a;
        int i10 = aVar2.f8441d;
        SideSheetBehavior sideSheetBehavior2 = aVar2.f8442e;
        switch (i10) {
            case 0:
                i9 = sideSheetBehavior2.f4132o;
                break;
            default:
                i9 = sideSheetBehavior2.f4130m;
                break;
        }
        return z.y(i7, i8, i9);
    }

    @Override // l6.f
    public final int o(View view, int i7) {
        return view.getTop();
    }
}
