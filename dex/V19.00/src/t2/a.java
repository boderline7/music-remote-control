package t2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.media.h;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.a0;
import com.google.android.material.internal.b0;
import com.google.android.material.internal.e0;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import m3.e;
import p3.j;
import p3.p;
import q2.g;
import q2.k;
import q2.l;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends Drawable implements a0 {
    public static final int BADGE_CONTENT_NOT_TRUNCATED = -2;

    @Deprecated
    public static final int BOTTOM_END = 8388693;

    @Deprecated
    public static final int BOTTOM_START = 8388691;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;

    /* renamed from: n, reason: collision with root package name */
    public static final int f9056n = l.Widget_MaterialComponents_Badge;

    /* renamed from: o, reason: collision with root package name */
    public static final int f9057o = q2.c.badgeStyle;

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f9058a;

    /* renamed from: b, reason: collision with root package name */
    public final j f9059b;

    /* renamed from: c, reason: collision with root package name */
    public final b0 f9060c;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f9061d;

    /* renamed from: e, reason: collision with root package name */
    public final b f9062e;

    /* renamed from: f, reason: collision with root package name */
    public float f9063f;

    /* renamed from: g, reason: collision with root package name */
    public float f9064g;

    /* renamed from: h, reason: collision with root package name */
    public final int f9065h;

    /* renamed from: i, reason: collision with root package name */
    public float f9066i;

    /* renamed from: j, reason: collision with root package name */
    public float f9067j;

    /* renamed from: k, reason: collision with root package name */
    public float f9068k;

    /* renamed from: l, reason: collision with root package name */
    public WeakReference f9069l;

    /* renamed from: m, reason: collision with root package name */
    public WeakReference f9070m;

    public a(Context context, BadgeState$State badgeState$State) {
        e eVar;
        WeakReference weakReference = new WeakReference(context);
        this.f9058a = weakReference;
        e0.c(context, e0.f3851b, "Theme.MaterialComponents");
        this.f9061d = new Rect();
        b0 b0Var = new b0(this);
        this.f9060c = b0Var;
        TextPaint textPaint = b0Var.f3796a;
        textPaint.setTextAlign(Paint.Align.CENTER);
        b bVar = new b(context, badgeState$State);
        this.f9062e = bVar;
        boolean f7 = f();
        BadgeState$State badgeState$State2 = bVar.f9072b;
        j jVar = new j(p.a(context, f7 ? badgeState$State2.f3287g.intValue() : badgeState$State2.f3285e.intValue(), f() ? badgeState$State2.f3288h.intValue() : badgeState$State2.f3286f.intValue()).a());
        this.f9059b = jVar;
        h();
        Context context2 = (Context) weakReference.get();
        if (context2 != null && b0Var.f3802g != (eVar = new e(context2, badgeState$State2.f3284d.intValue()))) {
            b0Var.c(eVar, context2);
            textPaint.setColor(badgeState$State2.f3283c.intValue());
            invalidateSelf();
            j();
            invalidateSelf();
        }
        int i7 = badgeState$State2.f3292l;
        if (i7 != -2) {
            this.f9065h = ((int) Math.pow(10.0d, i7 - 1.0d)) - 1;
        } else {
            this.f9065h = badgeState$State2.f3293m;
        }
        b0Var.f3800e = true;
        j();
        invalidateSelf();
        b0Var.f3800e = true;
        h();
        j();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList valueOf = ColorStateList.valueOf(badgeState$State2.f3282b.intValue());
        if (jVar.f8263a.f8243c != valueOf) {
            jVar.o(valueOf);
            invalidateSelf();
        }
        textPaint.setColor(badgeState$State2.f3283c.intValue());
        invalidateSelf();
        WeakReference weakReference2 = this.f9069l;
        if (weakReference2 != null && weakReference2.get() != null) {
            View view = (View) this.f9069l.get();
            WeakReference weakReference3 = this.f9070m;
            i(view, weakReference3 != null ? (FrameLayout) weakReference3.get() : null);
        }
        j();
        boolean booleanValue = badgeState$State2.f3300t.booleanValue();
        setVisible(booleanValue, false);
        if (!c.USE_COMPAT_PARENT || d() == null || booleanValue) {
            return;
        }
        ((ViewGroup) d().getParent()).invalidate();
    }

    @Override // com.google.android.material.internal.a0
    public final void a() {
        invalidateSelf();
    }

    public final String b() {
        b bVar = this.f9062e;
        BadgeState$State badgeState$State = bVar.f9072b;
        String str = badgeState$State.f3290j;
        boolean z6 = str != null;
        WeakReference weakReference = this.f9058a;
        if (z6) {
            int i7 = badgeState$State.f3292l;
            if (i7 == -2 || str == null || str.length() <= i7) {
                return str;
            }
            Context context = (Context) weakReference.get();
            if (context == null) {
                return h0.FRAGMENT_ENCODE_SET;
            }
            return String.format(context.getString(k.m3_exceed_max_badge_text_suffix), str.substring(0, i7 - 1), "…");
        }
        if (!g()) {
            return null;
        }
        int i8 = this.f9065h;
        BadgeState$State badgeState$State2 = bVar.f9072b;
        if (i8 == -2 || e() <= this.f9065h) {
            return NumberFormat.getInstance(badgeState$State2.f3294n).format(e());
        }
        Context context2 = (Context) weakReference.get();
        return context2 == null ? h0.FRAGMENT_ENCODE_SET : String.format(badgeState$State2.f3294n, context2.getString(k.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.f9065h), "+");
    }

    public final CharSequence c() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        b bVar = this.f9062e;
        BadgeState$State badgeState$State = bVar.f9072b;
        String str = badgeState$State.f3290j;
        if (str != null) {
            CharSequence charSequence = badgeState$State.f3295o;
            return charSequence != null ? charSequence : str;
        }
        boolean g7 = g();
        BadgeState$State badgeState$State2 = bVar.f9072b;
        if (!g7) {
            return badgeState$State2.f3296p;
        }
        if (badgeState$State2.f3297q == 0 || (context = (Context) this.f9058a.get()) == null) {
            return null;
        }
        if (this.f9065h != -2) {
            int e7 = e();
            int i7 = this.f9065h;
            if (e7 > i7) {
                return context.getString(badgeState$State2.f3298r, Integer.valueOf(i7));
            }
        }
        return context.getResources().getQuantityString(badgeState$State2.f3297q, e(), Integer.valueOf(e()));
    }

    public final FrameLayout d() {
        WeakReference weakReference = this.f9070m;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        String b7;
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.f9059b.draw(canvas);
        if (!f() || (b7 = b()) == null) {
            return;
        }
        Rect rect = new Rect();
        b0 b0Var = this.f9060c;
        b0Var.f3796a.getTextBounds(b7, 0, b7.length(), rect);
        float exactCenterY = this.f9064g - rect.exactCenterY();
        canvas.drawText(b7, this.f9063f, rect.bottom <= 0 ? (int) exactCenterY : Math.round(exactCenterY), b0Var.f3796a);
    }

    public final int e() {
        int i7 = this.f9062e.f9072b.f3291k;
        if (i7 != -1) {
            return i7;
        }
        return 0;
    }

    public final boolean f() {
        return this.f9062e.f9072b.f3290j != null || g();
    }

    public final boolean g() {
        BadgeState$State badgeState$State = this.f9062e.f9072b;
        return badgeState$State.f3290j == null && badgeState$State.f3291k != -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f9062e.f9072b.f3289i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f9061d.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f9061d.width();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        Context context = (Context) this.f9058a.get();
        if (context == null) {
            return;
        }
        boolean f7 = f();
        b bVar = this.f9062e;
        this.f9059b.setShapeAppearanceModel(p.a(context, f7 ? bVar.f9072b.f3287g.intValue() : bVar.f9072b.f3285e.intValue(), f() ? bVar.f9072b.f3288h.intValue() : bVar.f9072b.f3286f.intValue()).a());
        invalidateSelf();
    }

    public final void i(View view, FrameLayout frameLayout) {
        WeakReference weakReference;
        this.f9069l = new WeakReference(view);
        boolean z6 = c.USE_COMPAT_PARENT;
        if (z6 && frameLayout == null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if ((viewGroup == null || viewGroup.getId() != g.mtrl_anchor_parent) && ((weakReference = this.f9070m) == null || weakReference.get() != viewGroup)) {
                ViewGroup viewGroup2 = (ViewGroup) view.getParent();
                viewGroup2.setClipChildren(false);
                viewGroup2.setClipToPadding(false);
                FrameLayout frameLayout2 = new FrameLayout(view.getContext());
                frameLayout2.setId(g.mtrl_anchor_parent);
                frameLayout2.setClipChildren(false);
                frameLayout2.setClipToPadding(false);
                frameLayout2.setLayoutParams(view.getLayoutParams());
                frameLayout2.setMinimumWidth(view.getWidth());
                frameLayout2.setMinimumHeight(view.getHeight());
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(indexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout2.addView(view);
                viewGroup.addView(frameLayout2, indexOfChild);
                this.f9070m = new WeakReference(frameLayout2);
                frameLayout2.post(new h(this, view, frameLayout2, 5));
            }
        } else {
            this.f9070m = new WeakReference(frameLayout);
        }
        if (!z6) {
            ViewGroup viewGroup3 = (ViewGroup) view.getParent();
            viewGroup3.setClipChildren(false);
            viewGroup3.setClipToPadding(false);
        }
        j();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instructions count: 713
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t2.a.j():void");
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.a0
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        b bVar = this.f9062e;
        bVar.f9071a.f3289i = i7;
        bVar.f9072b.f3289i = i7;
        this.f9060c.f3796a.setAlpha(getAlpha());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
