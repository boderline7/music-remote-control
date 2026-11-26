package u2;

import android.view.View;
import android.view.ViewParent;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends f {

    /* renamed from: d, reason: collision with root package name */
    public int f9307d;

    /* renamed from: e, reason: collision with root package name */
    public int f9308e = -1;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SwipeDismissBehavior f9309f;

    public a(SwipeDismissBehavior swipeDismissBehavior) {
        this.f9309f = swipeDismissBehavior;
    }

    @Override // l6.f
    public final boolean E0(int i7, View view) {
        int i8 = this.f9308e;
        return (i8 == -1 || i8 == i7) && this.f9309f.w(view);
    }

    @Override // l6.f
    public final int I(View view) {
        return view.getWidth();
    }

    @Override // l6.f
    public final void h0(int i7, View view) {
        this.f9308e = i7;
        this.f9307d = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            SwipeDismissBehavior swipeDismissBehavior = this.f9309f;
            swipeDismissBehavior.f3321c = true;
            parent.requestDisallowInterceptTouchEvent(true);
            swipeDismissBehavior.f3321c = false;
        }
    }

    @Override // l6.f
    public final void i0(int i7) {
        this.f9309f.getClass();
    }

    @Override // l6.f
    public final void j0(View view, int i7, int i8) {
        float width = view.getWidth();
        SwipeDismissBehavior swipeDismissBehavior = this.f9309f;
        float f7 = width * swipeDismissBehavior.f3324f;
        float width2 = view.getWidth() * swipeDismissBehavior.f3325g;
        float abs = Math.abs(i7 - this.f9307d);
        if (abs <= f7) {
            view.setAlpha(1.0f);
        } else if (abs >= width2) {
            view.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            view.setAlpha(Math.min(Math.max(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f - ((abs - f7) / (width2 - f7))), 1.0f));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
    
        if (java.lang.Math.abs(r9.getLeft() - r8.f9307d) >= java.lang.Math.round(r9.getWidth() * r3.f3323e)) goto L27;
     */
    @Override // l6.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k0(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            r11 = -1
            r8.f9308e = r11
            int r11 = r9.getWidth()
            r0 = 0
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r2 = 1
            com.google.android.material.behavior.SwipeDismissBehavior r3 = r8.f9309f
            r4 = 0
            if (r1 == 0) goto L39
            int r5 = p0.d1.OVER_SCROLL_ALWAYS
            int r5 = p0.m0.d(r9)
            if (r5 != r2) goto L1a
            r5 = r2
            goto L1b
        L1a:
            r5 = r4
        L1b:
            int r6 = r3.f3322d
            r7 = 2
            if (r6 != r7) goto L21
            goto L52
        L21:
            if (r6 != 0) goto L2d
            if (r5 == 0) goto L2a
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L66
            goto L52
        L2a:
            if (r1 <= 0) goto L66
            goto L52
        L2d:
            if (r6 != r2) goto L66
            if (r5 == 0) goto L34
            if (r1 <= 0) goto L66
            goto L52
        L34:
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 >= 0) goto L66
            goto L52
        L39:
            int r1 = r9.getLeft()
            int r5 = r8.f9307d
            int r1 = r1 - r5
            int r5 = r9.getWidth()
            float r5 = (float) r5
            float r6 = r3.f3323e
            float r5 = r5 * r6
            int r5 = java.lang.Math.round(r5)
            int r1 = java.lang.Math.abs(r1)
            if (r1 < r5) goto L66
        L52:
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 < 0) goto L61
            int r10 = r9.getLeft()
            int r0 = r8.f9307d
            if (r10 >= r0) goto L5f
            goto L61
        L5f:
            int r0 = r0 + r11
            goto L69
        L61:
            int r10 = r8.f9307d
            int r0 = r10 - r11
            goto L69
        L66:
            int r0 = r8.f9307d
            r2 = r4
        L69:
            u0.e r10 = r3.f3319a
            int r11 = r9.getTop()
            boolean r10 = r10.p(r0, r11)
            if (r10 == 0) goto L7f
            u2.b r10 = new u2.b
            r10.<init>(r3, r9, r2)
            int r11 = p0.d1.OVER_SCROLL_ALWAYS
            p0.l0.m(r9, r10)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.a.k0(android.view.View, float, float):void");
    }

    @Override // l6.f
    public final int n(View view, int i7) {
        int width;
        int width2;
        int width3;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        boolean z6 = m0.d(view) == 1;
        int i9 = this.f9309f.f3322d;
        if (i9 == 0) {
            if (z6) {
                width = this.f9307d - view.getWidth();
                width2 = this.f9307d;
            } else {
                width = this.f9307d;
                width3 = view.getWidth();
                width2 = width3 + width;
            }
        } else if (i9 != 1) {
            width = this.f9307d - view.getWidth();
            width2 = view.getWidth() + this.f9307d;
        } else if (z6) {
            width = this.f9307d;
            width3 = view.getWidth();
            width2 = width3 + width;
        } else {
            width = this.f9307d - view.getWidth();
            width2 = this.f9307d;
        }
        return Math.min(Math.max(width, i7), width2);
    }

    @Override // l6.f
    public final int o(View view, int i7) {
        return view.getTop();
    }
}
