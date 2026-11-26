package androidx.appcompat.app;

import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u0 implements k.b0, com.google.android.material.internal.j0 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f856a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f857b;

    public u0(v0 v0Var) {
        this.f857b = v0Var;
    }

    public u0(BottomSheetBehavior bottomSheetBehavior, boolean z6) {
        this.f857b = bottomSheetBehavior;
        this.f856a = z6;
    }

    @Override // k.b0
    public final void a(k.o oVar, boolean z6) {
        androidx.appcompat.widget.m mVar;
        if (this.f856a) {
            return;
        }
        this.f856a = true;
        v0 v0Var = (v0) this.f857b;
        ActionMenuView actionMenuView = v0Var.f858a.f1442a.f1152a;
        if (actionMenuView != null && (mVar = actionMenuView.f985t) != null) {
            mVar.g();
            androidx.appcompat.widget.h hVar = mVar.f1343u;
            if (hVar != null && hVar.b()) {
                hVar.f7055j.dismiss();
            }
        }
        v0Var.f859b.onPanelClosed(108, oVar);
        this.f856a = false;
    }

    @Override // k.b0
    public final boolean c(k.o oVar) {
        ((v0) this.f857b).f859b.onMenuOpened(108, oVar);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
    
        if (r7 != false) goto L35;
     */
    @Override // com.google.android.material.internal.j0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final p0.l2 d(android.view.View r11, p0.l2 r12, androidx.media.a r13) {
        /*
            r10 = this;
            p0.j2 r0 = r12.f8161a
            r1 = 7
            i0.c r1 = r0.f(r1)
            r2 = 32
            i0.c r0 = r0.f(r2)
            java.lang.Object r2 = r10.f857b
            com.google.android.material.bottomsheet.BottomSheetBehavior r2 = (com.google.android.material.bottomsheet.BottomSheetBehavior) r2
            int r3 = r1.f6774b
            r2.f3408w = r3
            boolean r3 = com.google.android.material.internal.k0.f(r11)
            int r4 = r11.getPaddingBottom()
            int r5 = r11.getPaddingLeft()
            int r6 = r11.getPaddingRight()
            boolean r7 = r2.f3400o
            if (r7 == 0) goto L32
            int r4 = r12.a()
            r2.f3407v = r4
            int r7 = r13.f2238d
            int r4 = r4 + r7
        L32:
            boolean r7 = r2.f3401p
            int r8 = r1.f6773a
            if (r7 == 0) goto L40
            if (r3 == 0) goto L3d
            int r5 = r13.f2237c
            goto L3f
        L3d:
            int r5 = r13.f2235a
        L3f:
            int r5 = r5 + r8
        L40:
            boolean r7 = r2.f3402q
            int r9 = r1.f6775c
            if (r7 == 0) goto L4f
            if (r3 == 0) goto L4b
            int r13 = r13.f2235a
            goto L4d
        L4b:
            int r13 = r13.f2237c
        L4d:
            int r6 = r13 + r9
        L4f:
            android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
            boolean r3 = r2.f3404s
            r7 = 1
            if (r3 == 0) goto L62
            int r3 = r13.leftMargin
            if (r3 == r8) goto L62
            r13.leftMargin = r8
            r3 = r7
            goto L63
        L62:
            r3 = 0
        L63:
            boolean r8 = r2.f3405t
            if (r8 == 0) goto L6e
            int r8 = r13.rightMargin
            if (r8 == r9) goto L6e
            r13.rightMargin = r9
            goto L6f
        L6e:
            r7 = r3
        L6f:
            boolean r3 = r2.f3406u
            if (r3 == 0) goto L7c
            int r3 = r13.topMargin
            int r1 = r1.f6774b
            if (r3 == r1) goto L7c
            r13.topMargin = r1
            goto L7e
        L7c:
            if (r7 == 0) goto L81
        L7e:
            r11.setLayoutParams(r13)
        L81:
            int r13 = r11.getPaddingTop()
            r11.setPadding(r5, r13, r6, r4)
            boolean r11 = r10.f856a
            if (r11 == 0) goto L90
            int r13 = r0.f6776d
            r2.f3398m = r13
        L90:
            boolean r13 = r2.f3400o
            if (r13 != 0) goto L96
            if (r11 == 0) goto L99
        L96:
            r2.Q()
        L99:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.u0.d(android.view.View, p0.l2, androidx.media.a):p0.l2");
    }
}
