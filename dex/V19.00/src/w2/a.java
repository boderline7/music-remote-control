package w2;

import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.lang.ref.WeakReference;
import l6.f;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ BottomSheetBehavior f9456d;

    public a(BottomSheetBehavior bottomSheetBehavior) {
        this.f9456d = bottomSheetBehavior;
    }

    @Override // l6.f
    public final boolean E0(int i7, View view) {
        BottomSheetBehavior bottomSheetBehavior = this.f9456d;
        int i8 = bottomSheetBehavior.L;
        if (i8 == 1 || bottomSheetBehavior.f3385c0) {
            return false;
        }
        if (i8 == 3 && bottomSheetBehavior.f3381a0 == i7) {
            WeakReference weakReference = bottomSheetBehavior.W;
            View view2 = weakReference != null ? (View) weakReference.get() : null;
            if (view2 != null && view2.canScrollVertically(-1)) {
                return false;
            }
        }
        System.currentTimeMillis();
        WeakReference weakReference2 = bottomSheetBehavior.U;
        return weakReference2 != null && weakReference2.get() == view;
    }

    @Override // l6.f
    public final int J() {
        BottomSheetBehavior bottomSheetBehavior = this.f9456d;
        return bottomSheetBehavior.I ? bottomSheetBehavior.T : bottomSheetBehavior.G;
    }

    @Override // l6.f
    public final void i0(int i7) {
        if (i7 == 1) {
            BottomSheetBehavior bottomSheetBehavior = this.f9456d;
            if (bottomSheetBehavior.K) {
                bottomSheetBehavior.J(1);
            }
        }
    }

    @Override // l6.f
    public final void j0(View view, int i7, int i8) {
        this.f9456d.A(i8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        if (r7 > r4.E) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        if (java.lang.Math.abs(r6.getTop() - r4.D()) < java.lang.Math.abs(r6.getTop() - r4.E)) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0097, code lost:
    
        if (java.lang.Math.abs(r7 - r4.E) < java.lang.Math.abs(r7 - r4.G)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b1, code lost:
    
        if (java.lang.Math.abs(r7 - r4.D) < java.lang.Math.abs(r7 - r4.G)) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c1, code lost:
    
        if (r7 < java.lang.Math.abs(r7 - r4.G)) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d2, code lost:
    
        if (java.lang.Math.abs(r7 - r8) < java.lang.Math.abs(r7 - r4.G)) goto L50;
     */
    @Override // l6.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k0(android.view.View r6, float r7, float r8) {
        /*
            r5 = this;
            r0 = 0
            int r1 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r2 = 6
            r3 = 3
            com.google.android.material.bottomsheet.BottomSheetBehavior r4 = r5.f9456d
            if (r1 >= 0) goto L1d
            boolean r7 = r4.f3382b
            if (r7 == 0) goto L10
        Ld:
            r2 = r3
            goto Ld4
        L10:
            int r7 = r6.getTop()
            java.lang.System.currentTimeMillis()
            int r8 = r4.E
            if (r7 <= r8) goto Ld
            goto Ld4
        L1d:
            boolean r1 = r4.I
            if (r1 == 0) goto L6c
            boolean r1 = r4.K(r6, r8)
            if (r1 == 0) goto L6c
            float r7 = java.lang.Math.abs(r7)
            float r0 = java.lang.Math.abs(r8)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L3a
            int r7 = r4.f3386d
            float r7 = (float) r7
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 > 0) goto L49
        L3a:
            int r7 = r6.getTop()
            int r8 = r4.T
            int r0 = r4.D()
            int r0 = r0 + r8
            int r0 = r0 / 2
            if (r7 <= r0) goto L4c
        L49:
            r2 = 5
            goto Ld4
        L4c:
            boolean r7 = r4.f3382b
            if (r7 == 0) goto L51
            goto Ld
        L51:
            int r7 = r6.getTop()
            int r8 = r4.D()
            int r7 = r7 - r8
            int r7 = java.lang.Math.abs(r7)
            int r8 = r6.getTop()
            int r0 = r4.E
            int r8 = r8 - r0
            int r8 = java.lang.Math.abs(r8)
            if (r7 >= r8) goto Ld4
            goto Ld
        L6c:
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r1 = 4
            if (r0 == 0) goto L9a
            float r7 = java.lang.Math.abs(r7)
            float r8 = java.lang.Math.abs(r8)
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 <= 0) goto L7e
            goto L9a
        L7e:
            boolean r7 = r4.f3382b
            if (r7 == 0) goto L84
        L82:
            r2 = r1
            goto Ld4
        L84:
            int r7 = r6.getTop()
            int r8 = r4.E
            int r8 = r7 - r8
            int r8 = java.lang.Math.abs(r8)
            int r0 = r4.G
            int r7 = r7 - r0
            int r7 = java.lang.Math.abs(r7)
            if (r8 >= r7) goto L82
            goto Ld4
        L9a:
            int r7 = r6.getTop()
            boolean r8 = r4.f3382b
            if (r8 == 0) goto Lb5
            int r8 = r4.D
            int r8 = r7 - r8
            int r8 = java.lang.Math.abs(r8)
            int r0 = r4.G
            int r7 = r7 - r0
            int r7 = java.lang.Math.abs(r7)
            if (r8 >= r7) goto L82
            goto Ld
        Lb5:
            int r8 = r4.E
            if (r7 >= r8) goto Lc5
            int r8 = r4.G
            int r8 = r7 - r8
            int r8 = java.lang.Math.abs(r8)
            if (r7 >= r8) goto Ld4
            goto Ld
        Lc5:
            int r8 = r7 - r8
            int r8 = java.lang.Math.abs(r8)
            int r0 = r4.G
            int r7 = r7 - r0
            int r7 = java.lang.Math.abs(r7)
            if (r8 >= r7) goto L82
        Ld4:
            r4.getClass()
            r7 = 1
            r4.L(r6, r2, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: w2.a.k0(android.view.View, float, float):void");
    }

    @Override // l6.f
    public final int n(View view, int i7) {
        return view.getLeft();
    }

    @Override // l6.f
    public final int o(View view, int i7) {
        return z.y(i7, this.f9456d.D(), J());
    }
}
