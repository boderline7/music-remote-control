package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public TypedValue f1064a;

    /* renamed from: b, reason: collision with root package name */
    public TypedValue f1065b;

    /* renamed from: c, reason: collision with root package name */
    public TypedValue f1066c;

    /* renamed from: d, reason: collision with root package name */
    public TypedValue f1067d;

    /* renamed from: e, reason: collision with root package name */
    public TypedValue f1068e;

    /* renamed from: f, reason: collision with root package name */
    public TypedValue f1069f;

    /* renamed from: g, reason: collision with root package name */
    public final Rect f1070g;

    /* renamed from: h, reason: collision with root package name */
    public h1 f1071h;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1070g = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f1068e == null) {
            this.f1068e = new TypedValue();
        }
        return this.f1068e;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f1069f == null) {
            this.f1069f = new TypedValue();
        }
        return this.f1069f;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1066c == null) {
            this.f1066c = new TypedValue();
        }
        return this.f1066c;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1067d == null) {
            this.f1067d = new TypedValue();
        }
        return this.f1067d;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1064a == null) {
            this.f1064a = new TypedValue();
        }
        return this.f1064a;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1065b == null) {
            this.f1065b = new TypedValue();
        }
        return this.f1065b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        h1 h1Var = this.f1071h;
        if (h1Var != null) {
            h1Var.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        m mVar;
        super.onDetachedFromWindow();
        h1 h1Var = this.f1071h;
        if (h1Var != null) {
            androidx.appcompat.app.j0 j0Var = ((androidx.appcompat.app.y) h1Var).f884b;
            i1 i1Var = j0Var.f785r;
            if (i1Var != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) i1Var;
                actionBarOverlayLayout.k();
                ActionMenuView actionMenuView = ((w3) actionBarOverlayLayout.f958e).f1442a.f1152a;
                if (actionMenuView != null && (mVar = actionMenuView.f985t) != null) {
                    mVar.g();
                    h hVar = mVar.f1343u;
                    if (hVar != null && hVar.b()) {
                        hVar.f7055j.dismiss();
                    }
                }
            }
            if (j0Var.f790w != null) {
                j0Var.f779l.getDecorView().removeCallbacks(j0Var.f791x);
                if (j0Var.f790w.isShowing()) {
                    try {
                        j0Var.f790w.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                j0Var.f790w = null;
            }
            p0.l1 l1Var = j0Var.f792y;
            if (l1Var != null) {
                l1Var.b();
            }
            k.o oVar = j0Var.B(0).f736h;
            if (oVar != null) {
                oVar.c(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b3  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(h1 h1Var) {
        this.f1071h = h1Var;
    }

    public void setDecorPadding(int i7, int i8, int i9, int i10) {
        this.f1070g.set(i7, i8, i9, i10);
        int i11 = p0.d1.OVER_SCROLL_ALWAYS;
        if (p0.o0.c(this)) {
            requestLayout();
        }
    }
}
