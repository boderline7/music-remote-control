package com.google.android.material.textfield;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.s0;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.m1;
import androidx.appcompat.widget.r2;
import androidx.appcompat.widget.z;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.k0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import p0.d1;
import p0.l0;
import p0.m0;
import p0.o0;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    public ColorStateList A;
    public boolean A0;
    public ColorStateList B;
    public boolean C;
    public CharSequence D;
    public boolean E;
    public p3.j F;
    public p3.j G;
    public StateListDrawable H;
    public boolean I;
    public p3.j J;
    public p3.j K;
    public p3.p L;
    public boolean M;
    public final int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public final Rect V;
    public final Rect W;

    /* renamed from: a, reason: collision with root package name */
    public final FrameLayout f4269a;

    /* renamed from: a0, reason: collision with root package name */
    public final RectF f4270a0;

    /* renamed from: b, reason: collision with root package name */
    public final r f4271b;

    /* renamed from: b0, reason: collision with root package name */
    public Typeface f4272b0;

    /* renamed from: c, reason: collision with root package name */
    public final l f4273c;

    /* renamed from: c0, reason: collision with root package name */
    public ColorDrawable f4274c0;

    /* renamed from: d, reason: collision with root package name */
    public EditText f4275d;

    /* renamed from: d0, reason: collision with root package name */
    public int f4276d0;

    /* renamed from: e, reason: collision with root package name */
    public CharSequence f4277e;

    /* renamed from: e0, reason: collision with root package name */
    public final LinkedHashSet f4278e0;

    /* renamed from: f, reason: collision with root package name */
    public int f4279f;

    /* renamed from: f0, reason: collision with root package name */
    public ColorDrawable f4280f0;

    /* renamed from: g, reason: collision with root package name */
    public int f4281g;

    /* renamed from: g0, reason: collision with root package name */
    public int f4282g0;

    /* renamed from: h, reason: collision with root package name */
    public int f4283h;

    /* renamed from: h0, reason: collision with root package name */
    public Drawable f4284h0;

    /* renamed from: i, reason: collision with root package name */
    public int f4285i;

    /* renamed from: i0, reason: collision with root package name */
    public ColorStateList f4286i0;

    /* renamed from: j, reason: collision with root package name */
    public final o f4287j;

    /* renamed from: j0, reason: collision with root package name */
    public ColorStateList f4288j0;

    /* renamed from: k, reason: collision with root package name */
    public boolean f4289k;

    /* renamed from: k0, reason: collision with root package name */
    public int f4290k0;

    /* renamed from: l, reason: collision with root package name */
    public int f4291l;

    /* renamed from: l0, reason: collision with root package name */
    public int f4292l0;

    /* renamed from: m, reason: collision with root package name */
    public boolean f4293m;

    /* renamed from: m0, reason: collision with root package name */
    public int f4294m0;

    /* renamed from: n, reason: collision with root package name */
    public t f4295n;

    /* renamed from: n0, reason: collision with root package name */
    public ColorStateList f4296n0;

    /* renamed from: o, reason: collision with root package name */
    public AppCompatTextView f4297o;

    /* renamed from: o0, reason: collision with root package name */
    public int f4298o0;

    /* renamed from: p, reason: collision with root package name */
    public int f4299p;

    /* renamed from: p0, reason: collision with root package name */
    public int f4300p0;

    /* renamed from: q, reason: collision with root package name */
    public int f4301q;

    /* renamed from: q0, reason: collision with root package name */
    public int f4302q0;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f4303r;

    /* renamed from: r0, reason: collision with root package name */
    public int f4304r0;

    /* renamed from: s, reason: collision with root package name */
    public boolean f4305s;

    /* renamed from: s0, reason: collision with root package name */
    public int f4306s0;

    /* renamed from: t, reason: collision with root package name */
    public AppCompatTextView f4307t;

    /* renamed from: t0, reason: collision with root package name */
    public boolean f4308t0;

    /* renamed from: u, reason: collision with root package name */
    public ColorStateList f4309u;

    /* renamed from: u0, reason: collision with root package name */
    public final com.google.android.material.internal.d f4310u0;

    /* renamed from: v, reason: collision with root package name */
    public int f4311v;

    /* renamed from: v0, reason: collision with root package name */
    public boolean f4312v0;

    /* renamed from: w, reason: collision with root package name */
    public n1.h f4313w;

    /* renamed from: w0, reason: collision with root package name */
    public boolean f4314w0;

    /* renamed from: x, reason: collision with root package name */
    public n1.h f4315x;

    /* renamed from: x0, reason: collision with root package name */
    public ValueAnimator f4316x0;

    /* renamed from: y, reason: collision with root package name */
    public ColorStateList f4317y;

    /* renamed from: y0, reason: collision with root package name */
    public boolean f4318y0;

    /* renamed from: z, reason: collision with root package name */
    public ColorStateList f4319z;

    /* renamed from: z0, reason: collision with root package name */
    public boolean f4320z0;
    public static final int B0 = q2.l.Widget_Design_TextInputLayout;
    public static final int[][] C0 = {new int[]{R.attr.state_pressed}, new int[0]};

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new u();

        /* renamed from: b, reason: collision with root package name */
        public CharSequence f4321b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f4322c;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4321b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f4322c = parcel.readInt() == 1;
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f4321b) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            TextUtils.writeToParcel(this.f4321b, parcel, i7);
            parcel.writeInt(this.f4322c ? 1 : 0);
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.textInputStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextInputLayout(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 925
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private Drawable getEditTextBoxBackground() {
        EditText editText = this.f4275d;
        if (!(editText instanceof AutoCompleteTextView) || editText.getInputType() != 0) {
            return this.F;
        }
        int b7 = b3.a.b(q2.c.colorControlHighlight, this.f4275d);
        int i7 = this.O;
        int[][] iArr = C0;
        if (i7 != 2) {
            if (i7 != 1) {
                return null;
            }
            p3.j jVar = this.F;
            int i8 = this.U;
            return new RippleDrawable(new ColorStateList(iArr, new int[]{b3.a.f(b7, 0.1f, i8), i8}), jVar, jVar);
        }
        Context context = getContext();
        p3.j jVar2 = this.F;
        int d7 = b3.a.d(context, q2.c.colorSurface, "TextInputLayout");
        p3.j jVar3 = new p3.j(jVar2.f8263a.f8241a);
        int f7 = b3.a.f(b7, 0.1f, d7);
        jVar3.o(new ColorStateList(iArr, new int[]{f7, 0}));
        jVar3.setTint(d7);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{f7, d7});
        p3.j jVar4 = new p3.j(jVar2.f8263a.f8241a);
        jVar4.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, jVar3, jVar4), jVar2});
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.H == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.H = stateListDrawable;
            stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.H.addState(new int[0], f(false));
        }
        return this.H;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.G == null) {
            this.G = f(true);
        }
        return this.G;
    }

    public static void k(ViewGroup viewGroup, boolean z6) {
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            childAt.setEnabled(z6);
            if (childAt instanceof ViewGroup) {
                k((ViewGroup) childAt, z6);
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.f4275d != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3) {
            boolean z6 = editText instanceof TextInputEditText;
        }
        this.f4275d = editText;
        int i7 = this.f4279f;
        if (i7 != -1) {
            setMinEms(i7);
        } else {
            setMinWidth(this.f4283h);
        }
        int i8 = this.f4281g;
        if (i8 != -1) {
            setMaxEms(i8);
        } else {
            setMaxWidth(this.f4285i);
        }
        this.I = false;
        i();
        setTextInputAccessibilityDelegate(new s(this));
        Typeface typeface = this.f4275d.getTypeface();
        com.google.android.material.internal.d dVar = this.f4310u0;
        boolean m6 = dVar.m(typeface);
        boolean o6 = dVar.o(typeface);
        if (m6 || o6) {
            dVar.i(false);
        }
        float textSize = this.f4275d.getTextSize();
        if (dVar.f3827l != textSize) {
            dVar.f3827l = textSize;
            dVar.i(false);
        }
        int i9 = Build.VERSION.SDK_INT;
        float letterSpacing = this.f4275d.getLetterSpacing();
        if (dVar.f3818g0 != letterSpacing) {
            dVar.f3818g0 = letterSpacing;
            dVar.i(false);
        }
        int gravity = this.f4275d.getGravity();
        dVar.l((gravity & (-113)) | 48);
        if (dVar.f3823j != gravity) {
            dVar.f3823j = gravity;
            dVar.i(false);
        }
        this.f4275d.addTextChangedListener(new r2(this, 2));
        if (this.f4286i0 == null) {
            this.f4286i0 = this.f4275d.getHintTextColors();
        }
        if (this.C) {
            if (TextUtils.isEmpty(this.D)) {
                CharSequence hint = this.f4275d.getHint();
                this.f4277e = hint;
                setHint(hint);
                this.f4275d.setHint((CharSequence) null);
            }
            this.E = true;
        }
        if (i9 >= 29) {
            p();
        }
        if (this.f4297o != null) {
            n(this.f4275d.getText());
        }
        r();
        this.f4287j.b();
        this.f4271b.bringToFront();
        l lVar = this.f4273c;
        lVar.bringToFront();
        Iterator it = this.f4278e0.iterator();
        while (it.hasNext()) {
            ((k) it.next()).a(this);
        }
        lVar.m();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        u(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.D)) {
            return;
        }
        this.D = charSequence;
        com.google.android.material.internal.d dVar = this.f4310u0;
        if (charSequence == null || !TextUtils.equals(dVar.G, charSequence)) {
            dVar.G = charSequence;
            dVar.H = null;
            Bitmap bitmap = dVar.K;
            if (bitmap != null) {
                bitmap.recycle();
                dVar.K = null;
            }
            dVar.i(false);
        }
        if (this.f4308t0) {
            return;
        }
        j();
    }

    private void setPlaceholderTextEnabled(boolean z6) {
        if (this.f4305s == z6) {
            return;
        }
        if (z6) {
            AppCompatTextView appCompatTextView = this.f4307t;
            if (appCompatTextView != null) {
                this.f4269a.addView(appCompatTextView);
                this.f4307t.setVisibility(0);
            }
        } else {
            AppCompatTextView appCompatTextView2 = this.f4307t;
            if (appCompatTextView2 != null) {
                appCompatTextView2.setVisibility(8);
            }
            this.f4307t = null;
        }
        this.f4305s = z6;
    }

    public final void a(float f7) {
        com.google.android.material.internal.d dVar = this.f4310u0;
        if (dVar.f3807b == f7) {
            return;
        }
        if (this.f4316x0 == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f4316x0 = valueAnimator;
            valueAnimator.setInterpolator(l6.f.v0(getContext(), q2.c.motionEasingEmphasizedInterpolator, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
            this.f4316x0.setDuration(l6.f.u0(getContext(), q2.c.motionDurationMedium4, z1.c.SUB_FLOAT));
            this.f4316x0.addUpdateListener(new s2.d(4, this));
        }
        this.f4316x0.setFloatValues(dVar.f3807b, f7);
        this.f4316x0.start();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i7, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        FrameLayout frameLayout = this.f4269a;
        frameLayout.addView(view, layoutParams2);
        frameLayout.setLayoutParams(layoutParams);
        t();
        setEditText((EditText) view);
    }

    public final void b() {
        int i7;
        int i8;
        p3.j jVar = this.F;
        if (jVar == null) {
            return;
        }
        p3.p pVar = jVar.f8263a.f8241a;
        p3.p pVar2 = this.L;
        if (pVar != pVar2) {
            jVar.setShapeAppearanceModel(pVar2);
        }
        if (this.O == 2 && (i7 = this.Q) > -1 && (i8 = this.T) != 0) {
            p3.j jVar2 = this.F;
            jVar2.u(i7);
            jVar2.t(ColorStateList.valueOf(i8));
        }
        int i9 = this.U;
        if (this.O == 1) {
            i9 = i0.a.c(this.U, b3.a.c(getContext(), q2.c.colorSurface, 0));
        }
        this.U = i9;
        this.F.o(ColorStateList.valueOf(i9));
        p3.j jVar3 = this.J;
        if (jVar3 != null && this.K != null) {
            if (this.Q > -1 && this.T != 0) {
                jVar3.o(this.f4275d.isFocused() ? ColorStateList.valueOf(this.f4290k0) : ColorStateList.valueOf(this.T));
                this.K.o(ColorStateList.valueOf(this.T));
            }
            invalidate();
        }
        s();
    }

    public final int c() {
        float e7;
        if (!this.C) {
            return 0;
        }
        int i7 = this.O;
        com.google.android.material.internal.d dVar = this.f4310u0;
        if (i7 == 0) {
            e7 = dVar.e();
        } else {
            if (i7 != 2) {
                return 0;
            }
            e7 = dVar.e() / 2.0f;
        }
        return (int) e7;
    }

    public final n1.h d() {
        n1.h hVar = new n1.h();
        hVar.f7655c = l6.f.u0(getContext(), q2.c.motionDurationShort2, 87);
        hVar.f7656d = l6.f.v0(getContext(), q2.c.motionEasingLinearInterpolator, r2.a.LINEAR_INTERPOLATOR);
        return hVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i7) {
        EditText editText = this.f4275d;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i7);
            return;
        }
        if (this.f4277e != null) {
            boolean z6 = this.E;
            this.E = false;
            CharSequence hint = editText.getHint();
            this.f4275d.setHint(this.f4277e);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i7);
                return;
            } finally {
                this.f4275d.setHint(hint);
                this.E = z6;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i7);
        onProvideAutofillVirtualStructure(viewStructure, i7);
        FrameLayout frameLayout = this.f4269a;
        viewStructure.setChildCount(frameLayout.getChildCount());
        for (int i8 = 0; i8 < frameLayout.getChildCount(); i8++) {
            View childAt = frameLayout.getChildAt(i8);
            ViewStructure newChild = viewStructure.newChild(i8);
            childAt.dispatchProvideAutofillStructure(newChild, i7);
            if (childAt == this.f4275d) {
                newChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.f4320z0 = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.f4320z0 = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        p3.j jVar;
        super.draw(canvas);
        boolean z6 = this.C;
        com.google.android.material.internal.d dVar = this.f4310u0;
        if (z6) {
            dVar.d(canvas);
        }
        if (this.K == null || (jVar = this.J) == null) {
            return;
        }
        jVar.draw(canvas);
        if (this.f4275d.isFocused()) {
            Rect bounds = this.K.getBounds();
            Rect bounds2 = this.J.getBounds();
            float f7 = dVar.f3807b;
            int centerX = bounds2.centerX();
            bounds.left = r2.a.c(centerX, f7, bounds2.left);
            bounds.right = r2.a.c(centerX, f7, bounds2.right);
            this.K.draw(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.f4318y0
            if (r0 == 0) goto L5
            return
        L5:
            r0 = 1
            r4.f4318y0 = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            r2 = 0
            com.google.android.material.internal.d r3 = r4.f4310u0
            if (r3 == 0) goto L2f
            r3.R = r1
            android.content.res.ColorStateList r1 = r3.f3833o
            if (r1 == 0) goto L20
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L2a
        L20:
            android.content.res.ColorStateList r1 = r3.f3831n
            if (r1 == 0) goto L2f
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L2f
        L2a:
            r3.i(r2)
            r1 = r0
            goto L30
        L2f:
            r1 = r2
        L30:
            android.widget.EditText r3 = r4.f4275d
            if (r3 == 0) goto L47
            int r3 = p0.d1.OVER_SCROLL_ALWAYS
            boolean r3 = p0.o0.c(r4)
            if (r3 == 0) goto L43
            boolean r3 = r4.isEnabled()
            if (r3 == 0) goto L43
            goto L44
        L43:
            r0 = r2
        L44:
            r4.u(r0, r2)
        L47:
            r4.r()
            r4.x()
            if (r1 == 0) goto L52
            r4.invalidate()
        L52:
            r4.f4318y0 = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final boolean e() {
        return this.C && !TextUtils.isEmpty(this.D) && (this.F instanceof g);
    }

    public final p3.j f(boolean z6) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(q2.e.mtrl_shape_corner_size_small_component);
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (z6) {
            f7 = dimensionPixelOffset;
        }
        EditText editText = this.f4275d;
        float popupElevation = editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText).getPopupElevation() : getResources().getDimensionPixelOffset(q2.e.m3_comp_outlined_autocomplete_menu_container_elevation);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(q2.e.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        p3.m mVar = new p3.m();
        p3.m mVar2 = new p3.m();
        p3.m mVar3 = new p3.m();
        p3.m mVar4 = new p3.m();
        p3.f s6 = l6.f.s();
        p3.f s7 = l6.f.s();
        p3.f s8 = l6.f.s();
        p3.f s9 = l6.f.s();
        p3.a aVar = new p3.a(f7);
        p3.a aVar2 = new p3.a(f7);
        p3.a aVar3 = new p3.a(dimensionPixelOffset);
        p3.a aVar4 = new p3.a(dimensionPixelOffset);
        p3.p pVar = new p3.p();
        pVar.f8301a = mVar;
        pVar.f8302b = mVar2;
        pVar.f8303c = mVar3;
        pVar.f8304d = mVar4;
        pVar.f8305e = aVar;
        pVar.f8306f = aVar2;
        pVar.f8307g = aVar4;
        pVar.f8308h = aVar3;
        pVar.f8309i = s6;
        pVar.f8310j = s7;
        pVar.f8311k = s8;
        pVar.f8312l = s9;
        EditText editText2 = this.f4275d;
        ColorStateList dropDownBackgroundTintList = editText2 instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText2).getDropDownBackgroundTintList() : null;
        Context context = getContext();
        if (dropDownBackgroundTintList == null) {
            int i7 = p3.j.SHADOW_COMPAT_MODE_DEFAULT;
            dropDownBackgroundTintList = ColorStateList.valueOf(b3.a.d(context, q2.c.colorSurface, p3.j.class.getSimpleName()));
        }
        p3.j jVar = new p3.j();
        jVar.l(context);
        jVar.o(dropDownBackgroundTintList);
        jVar.n(popupElevation);
        jVar.setShapeAppearanceModel(pVar);
        p3.i iVar = jVar.f8263a;
        if (iVar.f8248h == null) {
            iVar.f8248h = new Rect();
        }
        jVar.f8263a.f8248h.set(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        jVar.invalidateSelf();
        return jVar;
    }

    public final int g(int i7, boolean z6) {
        return ((z6 || getPrefixText() == null) ? (!z6 || getSuffixText() == null) ? this.f4275d.getCompoundPaddingLeft() : this.f4273c.c() : this.f4271b.a()) + i7;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.f4275d;
        if (editText == null) {
            return super.getBaseline();
        }
        return c() + getPaddingTop() + editText.getBaseline();
    }

    public p3.j getBoxBackground() {
        int i7 = this.O;
        if (i7 == 1 || i7 == 2) {
            return this.F;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.U;
    }

    public int getBoxBackgroundMode() {
        return this.O;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.P;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean f7 = k0.f(this);
        RectF rectF = this.f4270a0;
        return f7 ? this.L.f8308h.a(rectF) : this.L.f8307g.a(rectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean f7 = k0.f(this);
        RectF rectF = this.f4270a0;
        return f7 ? this.L.f8307g.a(rectF) : this.L.f8308h.a(rectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean f7 = k0.f(this);
        RectF rectF = this.f4270a0;
        return f7 ? this.L.f8305e.a(rectF) : this.L.f8306f.a(rectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean f7 = k0.f(this);
        RectF rectF = this.f4270a0;
        return f7 ? this.L.f8306f.a(rectF) : this.L.f8305e.a(rectF);
    }

    public int getBoxStrokeColor() {
        return this.f4294m0;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.f4296n0;
    }

    public int getBoxStrokeWidth() {
        return this.R;
    }

    public int getBoxStrokeWidthFocused() {
        return this.S;
    }

    public int getCounterMaxLength() {
        return this.f4291l;
    }

    public CharSequence getCounterOverflowDescription() {
        AppCompatTextView appCompatTextView;
        if (this.f4289k && this.f4293m && (appCompatTextView = this.f4297o) != null) {
            return appCompatTextView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.f4319z;
    }

    public ColorStateList getCounterTextColor() {
        return this.f4317y;
    }

    public ColorStateList getCursorColor() {
        return this.A;
    }

    public ColorStateList getCursorErrorColor() {
        return this.B;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.f4286i0;
    }

    public EditText getEditText() {
        return this.f4275d;
    }

    public CharSequence getEndIconContentDescription() {
        return this.f4273c.f4364g.getContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.f4273c.f4364g.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.f4273c.f4370m;
    }

    public int getEndIconMode() {
        return this.f4273c.f4366i;
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.f4273c.f4371n;
    }

    public CheckableImageButton getEndIconView() {
        return this.f4273c.f4364g;
    }

    public CharSequence getError() {
        o oVar = this.f4287j;
        if (oVar.f4405q) {
            return oVar.f4404p;
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.f4287j.f4408t;
    }

    public CharSequence getErrorContentDescription() {
        return this.f4287j.f4407s;
    }

    public int getErrorCurrentTextColors() {
        AppCompatTextView appCompatTextView = this.f4287j.f4406r;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public Drawable getErrorIconDrawable() {
        return this.f4273c.f4360c.getDrawable();
    }

    public CharSequence getHelperText() {
        o oVar = this.f4287j;
        if (oVar.f4412x) {
            return oVar.f4411w;
        }
        return null;
    }

    public int getHelperTextCurrentTextColor() {
        AppCompatTextView appCompatTextView = this.f4287j.f4413y;
        if (appCompatTextView != null) {
            return appCompatTextView.getCurrentTextColor();
        }
        return -1;
    }

    public CharSequence getHint() {
        if (this.C) {
            return this.D;
        }
        return null;
    }

    public final float getHintCollapsedTextHeight() {
        return this.f4310u0.e();
    }

    public final int getHintCurrentCollapsedTextColor() {
        com.google.android.material.internal.d dVar = this.f4310u0;
        return dVar.f(dVar.f3833o);
    }

    public ColorStateList getHintTextColor() {
        return this.f4288j0;
    }

    public t getLengthCounter() {
        return this.f4295n;
    }

    public int getMaxEms() {
        return this.f4281g;
    }

    public int getMaxWidth() {
        return this.f4285i;
    }

    public int getMinEms() {
        return this.f4279f;
    }

    public int getMinWidth() {
        return this.f4283h;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.f4273c.f4364g.getContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.f4273c.f4364g.getDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.f4305s) {
            return this.f4303r;
        }
        return null;
    }

    public int getPlaceholderTextAppearance() {
        return this.f4311v;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.f4309u;
    }

    public CharSequence getPrefixText() {
        return this.f4271b.f4423c;
    }

    public ColorStateList getPrefixTextColor() {
        return this.f4271b.f4422b.getTextColors();
    }

    public TextView getPrefixTextView() {
        return this.f4271b.f4422b;
    }

    public p3.p getShapeAppearanceModel() {
        return this.L;
    }

    public CharSequence getStartIconContentDescription() {
        return this.f4271b.f4424d.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.f4271b.f4424d.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.f4271b.f4427g;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.f4271b.f4428h;
    }

    public CharSequence getSuffixText() {
        return this.f4273c.f4373p;
    }

    public ColorStateList getSuffixTextColor() {
        return this.f4273c.f4374q.getTextColors();
    }

    public TextView getSuffixTextView() {
        return this.f4273c.f4374q;
    }

    public Typeface getTypeface() {
        return this.f4272b0;
    }

    public final int h(int i7, boolean z6) {
        return i7 - ((z6 || getSuffixText() == null) ? (!z6 || getPrefixText() == null) ? this.f4275d.getCompoundPaddingRight() : this.f4271b.a() : this.f4273c.c());
    }

    public final void i() {
        int i7 = this.O;
        if (i7 == 0) {
            this.F = null;
            this.J = null;
            this.K = null;
        } else if (i7 == 1) {
            this.F = new p3.j(this.L);
            this.J = new p3.j();
            this.K = new p3.j();
        } else {
            if (i7 != 2) {
                throw new IllegalArgumentException(this.O + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.C || (this.F instanceof g)) {
                this.F = new p3.j(this.L);
            } else {
                p3.p pVar = this.L;
                int i8 = g.f4338z;
                if (pVar == null) {
                    pVar = new p3.p();
                }
                this.F = new f(new e(pVar, new RectF()));
            }
            this.J = null;
            this.K = null;
        }
        s();
        x();
        if (this.O == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                this.P = getResources().getDimensionPixelSize(q2.e.material_font_2_0_box_collapsed_padding_top);
            } else if (l6.f.N(getContext())) {
                this.P = getResources().getDimensionPixelSize(q2.e.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.f4275d != null && this.O == 1) {
            if (getContext().getResources().getConfiguration().fontScale >= 2.0f) {
                EditText editText = this.f4275d;
                int i9 = d1.OVER_SCROLL_ALWAYS;
                m0.k(editText, m0.f(editText), getResources().getDimensionPixelSize(q2.e.material_filled_edittext_font_2_0_padding_top), m0.e(this.f4275d), getResources().getDimensionPixelSize(q2.e.material_filled_edittext_font_2_0_padding_bottom));
            } else if (l6.f.N(getContext())) {
                EditText editText2 = this.f4275d;
                int i10 = d1.OVER_SCROLL_ALWAYS;
                m0.k(editText2, m0.f(editText2), getResources().getDimensionPixelSize(q2.e.material_filled_edittext_font_1_3_padding_top), m0.e(this.f4275d), getResources().getDimensionPixelSize(q2.e.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.O != 0) {
            t();
        }
        EditText editText3 = this.f4275d;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i11 = this.O;
                if (i11 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i11 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    public final void j() {
        float f7;
        float f8;
        float f9;
        RectF rectF;
        float f10;
        int i7;
        int i8;
        if (e()) {
            int width = this.f4275d.getWidth();
            int gravity = this.f4275d.getGravity();
            com.google.android.material.internal.d dVar = this.f4310u0;
            boolean b7 = dVar.b(dVar.G);
            dVar.I = b7;
            Rect rect = dVar.f3819h;
            if (gravity != 17 && (gravity & 7) != 1) {
                if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                    if (b7) {
                        i8 = rect.left;
                        f9 = i8;
                    } else {
                        f7 = rect.right;
                        f8 = dVar.f3824j0;
                    }
                } else if (b7) {
                    f7 = rect.right;
                    f8 = dVar.f3824j0;
                } else {
                    i8 = rect.left;
                    f9 = i8;
                }
                float max = Math.max(f9, rect.left);
                rectF = this.f4270a0;
                rectF.left = max;
                rectF.top = rect.top;
                if (gravity != 17 || (gravity & 7) == 1) {
                    f10 = (width / 2.0f) + (dVar.f3824j0 / 2.0f);
                } else if ((gravity & 8388613) == 8388613 || (gravity & 5) == 5) {
                    if (dVar.I) {
                        f10 = max + dVar.f3824j0;
                    } else {
                        i7 = rect.right;
                        f10 = i7;
                    }
                } else if (dVar.I) {
                    i7 = rect.right;
                    f10 = i7;
                } else {
                    f10 = dVar.f3824j0 + max;
                }
                rectF.right = Math.min(f10, rect.right);
                rectF.bottom = dVar.e() + rect.top;
                if (rectF.width() > CropImageView.DEFAULT_ASPECT_RATIO || rectF.height() <= CropImageView.DEFAULT_ASPECT_RATIO) {
                }
                float f11 = rectF.left;
                float f12 = this.N;
                rectF.left = f11 - f12;
                rectF.right += f12;
                rectF.offset(-getPaddingLeft(), ((-getPaddingTop()) - (rectF.height() / 2.0f)) + this.Q);
                g gVar = (g) this.F;
                gVar.getClass();
                gVar.y(rectF.left, rectF.top, rectF.right, rectF.bottom);
                return;
            }
            f7 = width / 2.0f;
            f8 = dVar.f3824j0 / 2.0f;
            f9 = f7 - f8;
            float max2 = Math.max(f9, rect.left);
            rectF = this.f4270a0;
            rectF.left = max2;
            rectF.top = rect.top;
            if (gravity != 17) {
            }
            f10 = (width / 2.0f) + (dVar.f3824j0 / 2.0f);
            rectF.right = Math.min(f10, rect.right);
            rectF.bottom = dVar.e() + rect.top;
            if (rectF.width() > CropImageView.DEFAULT_ASPECT_RATIO) {
            }
        }
    }

    public final void l(TextView textView, int i7) {
        try {
            textView.setTextAppearance(i7);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        textView.setTextAppearance(q2.l.TextAppearance_AppCompat_Caption);
        Context context = getContext();
        int i8 = q2.d.design_error;
        Object obj = g0.g.f6423a;
        textView.setTextColor(g0.d.a(context, i8));
    }

    public final boolean m() {
        o oVar = this.f4287j;
        return (oVar.f4403o != 1 || oVar.f4406r == null || TextUtils.isEmpty(oVar.f4404p)) ? false : true;
    }

    public final void n(Editable editable) {
        ((y2.l) this.f4295n).getClass();
        int length = editable != null ? editable.length() : 0;
        boolean z6 = this.f4293m;
        int i7 = this.f4291l;
        String str = null;
        if (i7 == -1) {
            this.f4297o.setText(String.valueOf(length));
            this.f4297o.setContentDescription(null);
            this.f4293m = false;
        } else {
            this.f4293m = length > i7;
            Context context = getContext();
            this.f4297o.setContentDescription(context.getString(this.f4293m ? q2.k.character_counter_overflowed_content_description : q2.k.character_counter_content_description, Integer.valueOf(length), Integer.valueOf(this.f4291l)));
            if (z6 != this.f4293m) {
                o();
            }
            n0.c c7 = n0.c.c();
            AppCompatTextView appCompatTextView = this.f4297o;
            String string = getContext().getString(q2.k.character_counter_pattern, Integer.valueOf(length), Integer.valueOf(this.f4291l));
            if (string == null) {
                c7.getClass();
            } else {
                str = c7.d(string, c7.f7575c).toString();
            }
            appCompatTextView.setText(str);
        }
        if (this.f4275d == null || z6 == this.f4293m) {
            return;
        }
        u(false, false);
        x();
        r();
    }

    public final void o() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AppCompatTextView appCompatTextView = this.f4297o;
        if (appCompatTextView != null) {
            l(appCompatTextView, this.f4293m ? this.f4299p : this.f4301q);
            if (!this.f4293m && (colorStateList2 = this.f4317y) != null) {
                this.f4297o.setTextColor(colorStateList2);
            }
            if (!this.f4293m || (colorStateList = this.f4319z) == null) {
                return;
            }
            this.f4297o.setTextColor(colorStateList);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f4310u0.h(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int max;
        l lVar = this.f4273c;
        lVar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        boolean z6 = false;
        this.A0 = false;
        if (this.f4275d != null && this.f4275d.getMeasuredHeight() < (max = Math.max(lVar.getMeasuredHeight(), this.f4271b.getMeasuredHeight()))) {
            this.f4275d.setMinimumHeight(max);
            z6 = true;
        }
        boolean q6 = q();
        if (z6 || q6) {
            this.f4275d.post(new androidx.activity.e(13, this));
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        EditText editText = this.f4275d;
        if (editText != null) {
            Rect rect = this.V;
            com.google.android.material.internal.e.a(this, editText, rect);
            p3.j jVar = this.J;
            if (jVar != null) {
                int i11 = rect.bottom;
                jVar.setBounds(rect.left, i11 - this.R, rect.right, i11);
            }
            p3.j jVar2 = this.K;
            if (jVar2 != null) {
                int i12 = rect.bottom;
                jVar2.setBounds(rect.left, i12 - this.S, rect.right, i12);
            }
            if (this.C) {
                float textSize = this.f4275d.getTextSize();
                com.google.android.material.internal.d dVar = this.f4310u0;
                if (dVar.f3827l != textSize) {
                    dVar.f3827l = textSize;
                    dVar.i(false);
                }
                int gravity = this.f4275d.getGravity();
                dVar.l((gravity & (-113)) | 48);
                if (dVar.f3823j != gravity) {
                    dVar.f3823j = gravity;
                    dVar.i(false);
                }
                if (this.f4275d == null) {
                    throw new IllegalStateException();
                }
                boolean f7 = k0.f(this);
                int i13 = rect.bottom;
                Rect rect2 = this.W;
                rect2.bottom = i13;
                int i14 = this.O;
                if (i14 == 1) {
                    rect2.left = g(rect.left, f7);
                    rect2.top = rect.top + this.P;
                    rect2.right = h(rect.right, f7);
                } else if (i14 != 2) {
                    rect2.left = g(rect.left, f7);
                    rect2.top = getPaddingTop();
                    rect2.right = h(rect.right, f7);
                } else {
                    rect2.left = this.f4275d.getPaddingLeft() + rect.left;
                    rect2.top = rect.top - c();
                    rect2.right = rect.right - this.f4275d.getPaddingRight();
                }
                int i15 = rect2.left;
                int i16 = rect2.top;
                int i17 = rect2.right;
                int i18 = rect2.bottom;
                Rect rect3 = dVar.f3819h;
                if (rect3.left != i15 || rect3.top != i16 || rect3.right != i17 || rect3.bottom != i18) {
                    rect3.set(i15, i16, i17, i18);
                    dVar.S = true;
                }
                if (this.f4275d == null) {
                    throw new IllegalStateException();
                }
                TextPaint textPaint = dVar.U;
                textPaint.setTextSize(dVar.f3827l);
                textPaint.setTypeface(dVar.f3847z);
                textPaint.setLetterSpacing(dVar.f3818g0);
                float f8 = -textPaint.ascent();
                rect2.left = this.f4275d.getCompoundPaddingLeft() + rect.left;
                rect2.top = (this.O != 1 || this.f4275d.getMinLines() > 1) ? rect.top + this.f4275d.getCompoundPaddingTop() : (int) (rect.centerY() - (f8 / 2.0f));
                rect2.right = rect.right - this.f4275d.getCompoundPaddingRight();
                int compoundPaddingBottom = (this.O != 1 || this.f4275d.getMinLines() > 1) ? rect.bottom - this.f4275d.getCompoundPaddingBottom() : (int) (rect2.top + f8);
                rect2.bottom = compoundPaddingBottom;
                int i19 = rect2.left;
                int i20 = rect2.top;
                int i21 = rect2.right;
                Rect rect4 = dVar.f3817g;
                if (rect4.left != i19 || rect4.top != i20 || rect4.right != i21 || rect4.bottom != compoundPaddingBottom) {
                    rect4.set(i19, i20, i21, compoundPaddingBottom);
                    dVar.S = true;
                }
                dVar.i(false);
                if (!e() || this.f4308t0) {
                    return;
                }
                j();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        EditText editText;
        super.onMeasure(i7, i8);
        boolean z6 = this.A0;
        l lVar = this.f4273c;
        if (!z6) {
            lVar.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.A0 = true;
        }
        if (this.f4307t != null && (editText = this.f4275d) != null) {
            this.f4307t.setGravity(editText.getGravity());
            this.f4307t.setPadding(this.f4275d.getCompoundPaddingLeft(), this.f4275d.getCompoundPaddingTop(), this.f4275d.getCompoundPaddingRight(), this.f4275d.getCompoundPaddingBottom());
        }
        lVar.m();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        setError(savedState.f4321b);
        if (savedState.f4322c) {
            post(new s0(14, this));
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        boolean z6 = i7 == 1;
        if (z6 != this.M) {
            p3.d dVar = this.L.f8305e;
            RectF rectF = this.f4270a0;
            float a7 = dVar.a(rectF);
            float a8 = this.L.f8306f.a(rectF);
            float a9 = this.L.f8308h.a(rectF);
            float a10 = this.L.f8307g.a(rectF);
            p3.p pVar = this.L;
            l6.f fVar = pVar.f8301a;
            l6.f fVar2 = pVar.f8302b;
            l6.f fVar3 = pVar.f8304d;
            l6.f fVar4 = pVar.f8303c;
            p3.f s6 = l6.f.s();
            p3.f s7 = l6.f.s();
            p3.f s8 = l6.f.s();
            p3.f s9 = l6.f.s();
            p3.n.b(fVar2);
            p3.n.b(fVar);
            p3.n.b(fVar4);
            p3.n.b(fVar3);
            p3.a aVar = new p3.a(a8);
            p3.a aVar2 = new p3.a(a7);
            p3.a aVar3 = new p3.a(a10);
            p3.a aVar4 = new p3.a(a9);
            p3.p pVar2 = new p3.p();
            pVar2.f8301a = fVar2;
            pVar2.f8302b = fVar;
            pVar2.f8303c = fVar3;
            pVar2.f8304d = fVar4;
            pVar2.f8305e = aVar;
            pVar2.f8306f = aVar2;
            pVar2.f8307g = aVar4;
            pVar2.f8308h = aVar3;
            pVar2.f8309i = s6;
            pVar2.f8310j = s7;
            pVar2.f8311k = s8;
            pVar2.f8312l = s9;
            this.M = z6;
            setShapeAppearanceModel(pVar2);
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (m()) {
            savedState.f4321b = getError();
        }
        l lVar = this.f4273c;
        savedState.f4322c = lVar.f4366i != 0 && lVar.f4364g.isChecked();
        return savedState;
    }

    public final void p() {
        Drawable textCursorDrawable;
        Drawable textCursorDrawable2;
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.A;
        if (colorStateList2 == null) {
            Context context = getContext();
            TypedValue s02 = l6.f.s0(context, q2.c.colorControlActivated);
            if (s02 != null) {
                int i7 = s02.resourceId;
                if (i7 != 0) {
                    colorStateList2 = g0.g.b(context, i7);
                } else {
                    int i8 = s02.data;
                    if (i8 != 0) {
                        colorStateList2 = ColorStateList.valueOf(i8);
                    }
                }
            }
            colorStateList2 = null;
        }
        EditText editText = this.f4275d;
        if (editText != null) {
            textCursorDrawable = editText.getTextCursorDrawable();
            if (textCursorDrawable == null) {
                return;
            }
            textCursorDrawable2 = this.f4275d.getTextCursorDrawable();
            Drawable mutate = textCursorDrawable2.mutate();
            if ((m() || (this.f4297o != null && this.f4293m)) && (colorStateList = this.B) != null) {
                colorStateList2 = colorStateList;
            }
            j0.b.h(mutate, colorStateList2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q() {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.q():boolean");
    }

    public final void r() {
        Drawable background;
        AppCompatTextView appCompatTextView;
        EditText editText = this.f4275d;
        if (editText == null || this.O != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        int[] iArr = m1.f1350a;
        Drawable mutate = background.mutate();
        if (m()) {
            mutate.setColorFilter(z.c(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.f4293m && (appCompatTextView = this.f4297o) != null) {
            mutate.setColorFilter(z.c(appCompatTextView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            mutate.clearColorFilter();
            this.f4275d.refreshDrawableState();
        }
    }

    public final void s() {
        EditText editText = this.f4275d;
        if (editText == null || this.F == null) {
            return;
        }
        if ((this.I || editText.getBackground() == null) && this.O != 0) {
            Drawable editTextBoxBackground = getEditTextBoxBackground();
            EditText editText2 = this.f4275d;
            int i7 = d1.OVER_SCROLL_ALWAYS;
            l0.q(editText2, editTextBoxBackground);
            this.I = true;
        }
    }

    public void setBoxBackgroundColor(int i7) {
        if (this.U != i7) {
            this.U = i7;
            this.f4298o0 = i7;
            this.f4302q0 = i7;
            this.f4304r0 = i7;
            b();
        }
    }

    public void setBoxBackgroundColorResource(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setBoxBackgroundColor(g0.d.a(context, i7));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.f4298o0 = defaultColor;
        this.U = defaultColor;
        this.f4300p0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.f4302q0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        this.f4304r0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
        b();
    }

    public void setBoxBackgroundMode(int i7) {
        if (i7 == this.O) {
            return;
        }
        this.O = i7;
        if (this.f4275d != null) {
            i();
        }
    }

    public void setBoxCollapsedPaddingTop(int i7) {
        this.P = i7;
    }

    public void setBoxCornerFamily(int i7) {
        p3.n g7 = this.L.g();
        p3.d dVar = this.L.f8305e;
        l6.f r6 = l6.f.r(i7);
        g7.f8289a = r6;
        p3.n.b(r6);
        g7.f8293e = dVar;
        p3.d dVar2 = this.L.f8306f;
        l6.f r7 = l6.f.r(i7);
        g7.f8290b = r7;
        p3.n.b(r7);
        g7.f8294f = dVar2;
        p3.d dVar3 = this.L.f8308h;
        l6.f r8 = l6.f.r(i7);
        g7.f8292d = r8;
        p3.n.b(r8);
        g7.f8296h = dVar3;
        p3.d dVar4 = this.L.f8307g;
        l6.f r9 = l6.f.r(i7);
        g7.f8291c = r9;
        p3.n.b(r9);
        g7.f8295g = dVar4;
        this.L = g7.a();
        b();
    }

    public void setBoxCornerRadii(float f7, float f8, float f9, float f10) {
        boolean f11 = k0.f(this);
        this.M = f11;
        float f12 = f11 ? f8 : f7;
        if (!f11) {
            f7 = f8;
        }
        float f13 = f11 ? f10 : f9;
        if (!f11) {
            f9 = f10;
        }
        p3.j jVar = this.F;
        if (jVar != null && jVar.j() == f12) {
            p3.j jVar2 = this.F;
            if (jVar2.f8263a.f8241a.f8306f.a(jVar2.h()) == f7) {
                p3.j jVar3 = this.F;
                if (jVar3.f8263a.f8241a.f8308h.a(jVar3.h()) == f13) {
                    p3.j jVar4 = this.F;
                    if (jVar4.f8263a.f8241a.f8307g.a(jVar4.h()) == f9) {
                        return;
                    }
                }
            }
        }
        p3.n g7 = this.L.g();
        g7.f8293e = new p3.a(f12);
        g7.f8294f = new p3.a(f7);
        g7.f8296h = new p3.a(f13);
        g7.f8295g = new p3.a(f9);
        this.L = g7.a();
        b();
    }

    public void setBoxCornerRadiiResources(int i7, int i8, int i9, int i10) {
        setBoxCornerRadii(getContext().getResources().getDimension(i7), getContext().getResources().getDimension(i8), getContext().getResources().getDimension(i10), getContext().getResources().getDimension(i9));
    }

    public void setBoxStrokeColor(int i7) {
        if (this.f4294m0 != i7) {
            this.f4294m0 = i7;
            x();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.f4290k0 = colorStateList.getDefaultColor();
            this.f4306s0 = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.f4292l0 = colorStateList.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, -1);
            this.f4294m0 = colorStateList.getColorForState(new int[]{R.attr.state_focused, R.attr.state_enabled}, -1);
        } else if (this.f4294m0 != colorStateList.getDefaultColor()) {
            this.f4294m0 = colorStateList.getDefaultColor();
        }
        x();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList) {
        if (this.f4296n0 != colorStateList) {
            this.f4296n0 = colorStateList;
            x();
        }
    }

    public void setBoxStrokeWidth(int i7) {
        this.R = i7;
        x();
    }

    public void setBoxStrokeWidthFocused(int i7) {
        this.S = i7;
        x();
    }

    public void setBoxStrokeWidthFocusedResource(int i7) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i7));
    }

    public void setBoxStrokeWidthResource(int i7) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i7));
    }

    public void setCounterEnabled(boolean z6) {
        if (this.f4289k != z6) {
            o oVar = this.f4287j;
            if (z6) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.f4297o = appCompatTextView;
                appCompatTextView.setId(q2.g.textinput_counter);
                Typeface typeface = this.f4272b0;
                if (typeface != null) {
                    this.f4297o.setTypeface(typeface);
                }
                this.f4297o.setMaxLines(1);
                oVar.a(this.f4297o, 2);
                p0.o.h((ViewGroup.MarginLayoutParams) this.f4297o.getLayoutParams(), getResources().getDimensionPixelOffset(q2.e.mtrl_textinput_counter_margin_start));
                o();
                if (this.f4297o != null) {
                    EditText editText = this.f4275d;
                    n(editText != null ? editText.getText() : null);
                }
            } else {
                oVar.g(this.f4297o, 2);
                this.f4297o = null;
            }
            this.f4289k = z6;
        }
    }

    public void setCounterMaxLength(int i7) {
        if (this.f4291l != i7) {
            if (i7 > 0) {
                this.f4291l = i7;
            } else {
                this.f4291l = -1;
            }
            if (!this.f4289k || this.f4297o == null) {
                return;
            }
            EditText editText = this.f4275d;
            n(editText == null ? null : editText.getText());
        }
    }

    public void setCounterOverflowTextAppearance(int i7) {
        if (this.f4299p != i7) {
            this.f4299p = i7;
            o();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.f4319z != colorStateList) {
            this.f4319z = colorStateList;
            o();
        }
    }

    public void setCounterTextAppearance(int i7) {
        if (this.f4301q != i7) {
            this.f4301q = i7;
            o();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.f4317y != colorStateList) {
            this.f4317y = colorStateList;
            o();
        }
    }

    public void setCursorColor(ColorStateList colorStateList) {
        if (this.A != colorStateList) {
            this.A = colorStateList;
            p();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            if (m() || (this.f4297o != null && this.f4293m)) {
                p();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList) {
        this.f4286i0 = colorStateList;
        this.f4288j0 = colorStateList;
        if (this.f4275d != null) {
            u(false, false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        k(this, z6);
        super.setEnabled(z6);
    }

    public void setEndIconActivated(boolean z6) {
        this.f4273c.f4364g.setActivated(z6);
    }

    public void setEndIconCheckable(boolean z6) {
        this.f4273c.f4364g.setCheckable(z6);
    }

    public void setEndIconContentDescription(int i7) {
        l lVar = this.f4273c;
        CharSequence text = i7 != 0 ? lVar.getResources().getText(i7) : null;
        CheckableImageButton checkableImageButton = lVar.f4364g;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.f4273c.f4364g;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(int i7) {
        l lVar = this.f4273c;
        Drawable A = i7 != 0 ? l6.f.A(lVar.getContext(), i7) : null;
        CheckableImageButton checkableImageButton = lVar.f4364g;
        checkableImageButton.setImageDrawable(A);
        if (A != null) {
            ColorStateList colorStateList = lVar.f4368k;
            PorterDuff.Mode mode = lVar.f4369l;
            TextInputLayout textInputLayout = lVar.f4358a;
            l6.f.e(textInputLayout, checkableImageButton, colorStateList, mode);
            l6.f.p0(textInputLayout, checkableImageButton, lVar.f4368k);
        }
    }

    public void setEndIconDrawable(Drawable drawable) {
        l lVar = this.f4273c;
        CheckableImageButton checkableImageButton = lVar.f4364g;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = lVar.f4368k;
            PorterDuff.Mode mode = lVar.f4369l;
            TextInputLayout textInputLayout = lVar.f4358a;
            l6.f.e(textInputLayout, checkableImageButton, colorStateList, mode);
            l6.f.p0(textInputLayout, checkableImageButton, lVar.f4368k);
        }
    }

    public void setEndIconMinSize(int i7) {
        l lVar = this.f4273c;
        if (i7 < 0) {
            lVar.getClass();
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (i7 != lVar.f4370m) {
            lVar.f4370m = i7;
            CheckableImageButton checkableImageButton = lVar.f4364g;
            checkableImageButton.setMinimumWidth(i7);
            checkableImageButton.setMinimumHeight(i7);
            CheckableImageButton checkableImageButton2 = lVar.f4360c;
            checkableImageButton2.setMinimumWidth(i7);
            checkableImageButton2.setMinimumHeight(i7);
        }
    }

    public void setEndIconMode(int i7) {
        this.f4273c.g(i7);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        l lVar = this.f4273c;
        View.OnLongClickListener onLongClickListener = lVar.f4372o;
        CheckableImageButton checkableImageButton = lVar.f4364g;
        checkableImageButton.setOnClickListener(onClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        l lVar = this.f4273c;
        lVar.f4372o = onLongClickListener;
        CheckableImageButton checkableImageButton = lVar.f4364g;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setEndIconScaleType(ImageView.ScaleType scaleType) {
        l lVar = this.f4273c;
        lVar.f4371n = scaleType;
        lVar.f4364g.setScaleType(scaleType);
        lVar.f4360c.setScaleType(scaleType);
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        l lVar = this.f4273c;
        if (lVar.f4368k != colorStateList) {
            lVar.f4368k = colorStateList;
            l6.f.e(lVar.f4358a, lVar.f4364g, colorStateList, lVar.f4369l);
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        l lVar = this.f4273c;
        if (lVar.f4369l != mode) {
            lVar.f4369l = mode;
            l6.f.e(lVar.f4358a, lVar.f4364g, lVar.f4368k, mode);
        }
    }

    public void setEndIconVisible(boolean z6) {
        this.f4273c.h(z6);
    }

    public void setError(CharSequence charSequence) {
        o oVar = this.f4287j;
        if (!oVar.f4405q) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            oVar.f();
            return;
        }
        oVar.c();
        oVar.f4404p = charSequence;
        oVar.f4406r.setText(charSequence);
        int i7 = oVar.f4402n;
        if (i7 != 1) {
            oVar.f4403o = 1;
        }
        oVar.i(i7, oVar.f4403o, oVar.h(oVar.f4406r, charSequence));
    }

    public void setErrorAccessibilityLiveRegion(int i7) {
        o oVar = this.f4287j;
        oVar.f4408t = i7;
        AppCompatTextView appCompatTextView = oVar.f4406r;
        if (appCompatTextView != null) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            o0.f(appCompatTextView, i7);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        o oVar = this.f4287j;
        oVar.f4407s = charSequence;
        AppCompatTextView appCompatTextView = oVar.f4406r;
        if (appCompatTextView != null) {
            appCompatTextView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z6) {
        o oVar = this.f4287j;
        if (oVar.f4405q == z6) {
            return;
        }
        oVar.c();
        TextInputLayout textInputLayout = oVar.f4396h;
        if (z6) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(oVar.f4395g);
            oVar.f4406r = appCompatTextView;
            appCompatTextView.setId(q2.g.textinput_error);
            oVar.f4406r.setTextAlignment(5);
            Typeface typeface = oVar.B;
            if (typeface != null) {
                oVar.f4406r.setTypeface(typeface);
            }
            int i7 = oVar.f4409u;
            oVar.f4409u = i7;
            AppCompatTextView appCompatTextView2 = oVar.f4406r;
            if (appCompatTextView2 != null) {
                textInputLayout.l(appCompatTextView2, i7);
            }
            ColorStateList colorStateList = oVar.f4410v;
            oVar.f4410v = colorStateList;
            AppCompatTextView appCompatTextView3 = oVar.f4406r;
            if (appCompatTextView3 != null && colorStateList != null) {
                appCompatTextView3.setTextColor(colorStateList);
            }
            CharSequence charSequence = oVar.f4407s;
            oVar.f4407s = charSequence;
            AppCompatTextView appCompatTextView4 = oVar.f4406r;
            if (appCompatTextView4 != null) {
                appCompatTextView4.setContentDescription(charSequence);
            }
            int i8 = oVar.f4408t;
            oVar.f4408t = i8;
            AppCompatTextView appCompatTextView5 = oVar.f4406r;
            if (appCompatTextView5 != null) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                o0.f(appCompatTextView5, i8);
            }
            oVar.f4406r.setVisibility(4);
            oVar.a(oVar.f4406r, 0);
        } else {
            oVar.f();
            oVar.g(oVar.f4406r, 0);
            oVar.f4406r = null;
            textInputLayout.r();
            textInputLayout.x();
        }
        oVar.f4405q = z6;
    }

    public void setErrorIconDrawable(int i7) {
        l lVar = this.f4273c;
        lVar.i(i7 != 0 ? l6.f.A(lVar.getContext(), i7) : null);
        l6.f.p0(lVar.f4358a, lVar.f4360c, lVar.f4361d);
    }

    public void setErrorIconDrawable(Drawable drawable) {
        this.f4273c.i(drawable);
    }

    public void setErrorIconOnClickListener(View.OnClickListener onClickListener) {
        l lVar = this.f4273c;
        CheckableImageButton checkableImageButton = lVar.f4360c;
        View.OnLongClickListener onLongClickListener = lVar.f4363f;
        checkableImageButton.setOnClickListener(onClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        l lVar = this.f4273c;
        lVar.f4363f = onLongClickListener;
        CheckableImageButton checkableImageButton = lVar.f4360c;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setErrorIconTintList(ColorStateList colorStateList) {
        l lVar = this.f4273c;
        if (lVar.f4361d != colorStateList) {
            lVar.f4361d = colorStateList;
            l6.f.e(lVar.f4358a, lVar.f4360c, colorStateList, lVar.f4362e);
        }
    }

    public void setErrorIconTintMode(PorterDuff.Mode mode) {
        l lVar = this.f4273c;
        if (lVar.f4362e != mode) {
            lVar.f4362e = mode;
            l6.f.e(lVar.f4358a, lVar.f4360c, lVar.f4361d, mode);
        }
    }

    public void setErrorTextAppearance(int i7) {
        o oVar = this.f4287j;
        oVar.f4409u = i7;
        AppCompatTextView appCompatTextView = oVar.f4406r;
        if (appCompatTextView != null) {
            oVar.f4396h.l(appCompatTextView, i7);
        }
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        o oVar = this.f4287j;
        oVar.f4410v = colorStateList;
        AppCompatTextView appCompatTextView = oVar.f4406r;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z6) {
        if (this.f4312v0 != z6) {
            this.f4312v0 = z6;
            u(false, false);
        }
    }

    public void setHelperText(CharSequence charSequence) {
        boolean isEmpty = TextUtils.isEmpty(charSequence);
        o oVar = this.f4287j;
        if (isEmpty) {
            if (oVar.f4412x) {
                setHelperTextEnabled(false);
                return;
            }
            return;
        }
        if (!oVar.f4412x) {
            setHelperTextEnabled(true);
        }
        oVar.c();
        oVar.f4411w = charSequence;
        oVar.f4413y.setText(charSequence);
        int i7 = oVar.f4402n;
        if (i7 != 2) {
            oVar.f4403o = 2;
        }
        oVar.i(i7, oVar.f4403o, oVar.h(oVar.f4413y, charSequence));
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        o oVar = this.f4287j;
        oVar.A = colorStateList;
        AppCompatTextView appCompatTextView = oVar.f4413y;
        if (appCompatTextView == null || colorStateList == null) {
            return;
        }
        appCompatTextView.setTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z6) {
        o oVar = this.f4287j;
        if (oVar.f4412x == z6) {
            return;
        }
        oVar.c();
        int i7 = 1;
        if (z6) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(oVar.f4395g);
            oVar.f4413y = appCompatTextView;
            appCompatTextView.setId(q2.g.textinput_helper_text);
            oVar.f4413y.setTextAlignment(5);
            Typeface typeface = oVar.B;
            if (typeface != null) {
                oVar.f4413y.setTypeface(typeface);
            }
            oVar.f4413y.setVisibility(4);
            AppCompatTextView appCompatTextView2 = oVar.f4413y;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            o0.f(appCompatTextView2, 1);
            int i9 = oVar.f4414z;
            oVar.f4414z = i9;
            AppCompatTextView appCompatTextView3 = oVar.f4413y;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTextAppearance(i9);
            }
            ColorStateList colorStateList = oVar.A;
            oVar.A = colorStateList;
            AppCompatTextView appCompatTextView4 = oVar.f4413y;
            if (appCompatTextView4 != null && colorStateList != null) {
                appCompatTextView4.setTextColor(colorStateList);
            }
            oVar.a(oVar.f4413y, 1);
            oVar.f4413y.setAccessibilityDelegate(new androidx.appcompat.widget.t(i7, oVar));
        } else {
            oVar.c();
            int i10 = oVar.f4402n;
            if (i10 == 2) {
                oVar.f4403o = 0;
            }
            oVar.i(i10, oVar.f4403o, oVar.h(oVar.f4413y, h0.FRAGMENT_ENCODE_SET));
            oVar.g(oVar.f4413y, 1);
            oVar.f4413y = null;
            TextInputLayout textInputLayout = oVar.f4396h;
            textInputLayout.r();
            textInputLayout.x();
        }
        oVar.f4412x = z6;
    }

    public void setHelperTextTextAppearance(int i7) {
        o oVar = this.f4287j;
        oVar.f4414z = i7;
        AppCompatTextView appCompatTextView = oVar.f4413y;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i7);
        }
    }

    public void setHint(int i7) {
        setHint(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setHint(CharSequence charSequence) {
        if (this.C) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z6) {
        this.f4314w0 = z6;
    }

    public void setHintEnabled(boolean z6) {
        if (z6 != this.C) {
            this.C = z6;
            if (z6) {
                CharSequence hint = this.f4275d.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.D)) {
                        setHint(hint);
                    }
                    this.f4275d.setHint((CharSequence) null);
                }
                this.E = true;
            } else {
                this.E = false;
                if (!TextUtils.isEmpty(this.D) && TextUtils.isEmpty(this.f4275d.getHint())) {
                    this.f4275d.setHint(this.D);
                }
                setHintInternal(null);
            }
            if (this.f4275d != null) {
                t();
            }
        }
    }

    public void setHintTextAppearance(int i7) {
        com.google.android.material.internal.d dVar = this.f4310u0;
        dVar.k(i7);
        this.f4288j0 = dVar.f3833o;
        if (this.f4275d != null) {
            u(false, false);
            t();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.f4288j0 != colorStateList) {
            if (this.f4286i0 == null) {
                com.google.android.material.internal.d dVar = this.f4310u0;
                if (dVar.f3833o != colorStateList) {
                    dVar.f3833o = colorStateList;
                    dVar.i(false);
                }
            }
            this.f4288j0 = colorStateList;
            if (this.f4275d != null) {
                u(false, false);
            }
        }
    }

    public void setLengthCounter(t tVar) {
        this.f4295n = tVar;
    }

    public void setMaxEms(int i7) {
        this.f4281g = i7;
        EditText editText = this.f4275d;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMaxEms(i7);
    }

    public void setMaxWidth(int i7) {
        this.f4285i = i7;
        EditText editText = this.f4275d;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMaxWidth(i7);
    }

    public void setMaxWidthResource(int i7) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i7));
    }

    public void setMinEms(int i7) {
        this.f4279f = i7;
        EditText editText = this.f4275d;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMinEms(i7);
    }

    public void setMinWidth(int i7) {
        this.f4283h = i7;
        EditText editText = this.f4275d;
        if (editText == null || i7 == -1) {
            return;
        }
        editText.setMinWidth(i7);
    }

    public void setMinWidthResource(int i7) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i7));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int i7) {
        l lVar = this.f4273c;
        lVar.f4364g.setContentDescription(i7 != 0 ? lVar.getResources().getText(i7) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence) {
        this.f4273c.f4364g.setContentDescription(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int i7) {
        l lVar = this.f4273c;
        lVar.f4364g.setImageDrawable(i7 != 0 ? l6.f.A(lVar.getContext(), i7) : null);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable) {
        this.f4273c.f4364g.setImageDrawable(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z6) {
        l lVar = this.f4273c;
        if (z6 && lVar.f4366i != 1) {
            lVar.g(1);
        } else if (z6) {
            lVar.getClass();
        } else {
            lVar.g(0);
        }
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList) {
        l lVar = this.f4273c;
        lVar.f4368k = colorStateList;
        l6.f.e(lVar.f4358a, lVar.f4364g, colorStateList, lVar.f4369l);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode mode) {
        l lVar = this.f4273c;
        lVar.f4369l = mode;
        l6.f.e(lVar.f4358a, lVar.f4364g, lVar.f4368k, mode);
    }

    public void setPlaceholderText(CharSequence charSequence) {
        if (this.f4307t == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.f4307t = appCompatTextView;
            appCompatTextView.setId(q2.g.textinput_placeholder);
            AppCompatTextView appCompatTextView2 = this.f4307t;
            int i7 = d1.OVER_SCROLL_ALWAYS;
            l0.s(appCompatTextView2, 2);
            n1.h d7 = d();
            this.f4313w = d7;
            d7.f7654b = 67L;
            this.f4315x = d();
            setPlaceholderTextAppearance(this.f4311v);
            setPlaceholderTextColor(this.f4309u);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.f4305s) {
                setPlaceholderTextEnabled(true);
            }
            this.f4303r = charSequence;
        }
        EditText editText = this.f4275d;
        v(editText == null ? null : editText.getText());
    }

    public void setPlaceholderTextAppearance(int i7) {
        this.f4311v = i7;
        AppCompatTextView appCompatTextView = this.f4307t;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(i7);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.f4309u != colorStateList) {
            this.f4309u = colorStateList;
            AppCompatTextView appCompatTextView = this.f4307t;
            if (appCompatTextView == null || colorStateList == null) {
                return;
            }
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(CharSequence charSequence) {
        r rVar = this.f4271b;
        rVar.getClass();
        rVar.f4423c = TextUtils.isEmpty(charSequence) ? null : charSequence;
        rVar.f4422b.setText(charSequence);
        rVar.e();
    }

    public void setPrefixTextAppearance(int i7) {
        this.f4271b.f4422b.setTextAppearance(i7);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.f4271b.f4422b.setTextColor(colorStateList);
    }

    public void setShapeAppearanceModel(p3.p pVar) {
        p3.j jVar = this.F;
        if (jVar == null || jVar.f8263a.f8241a == pVar) {
            return;
        }
        this.L = pVar;
        b();
    }

    public void setStartIconCheckable(boolean z6) {
        this.f4271b.f4424d.setCheckable(z6);
    }

    public void setStartIconContentDescription(int i7) {
        setStartIconContentDescription(i7 != 0 ? getResources().getText(i7) : null);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        CheckableImageButton checkableImageButton = this.f4271b.f4424d;
        if (checkableImageButton.getContentDescription() != charSequence) {
            checkableImageButton.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(int i7) {
        setStartIconDrawable(i7 != 0 ? l6.f.A(getContext(), i7) : null);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.f4271b.b(drawable);
    }

    public void setStartIconMinSize(int i7) {
        r rVar = this.f4271b;
        if (i7 < 0) {
            rVar.getClass();
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i7 != rVar.f4427g) {
            rVar.f4427g = i7;
            CheckableImageButton checkableImageButton = rVar.f4424d;
            checkableImageButton.setMinimumWidth(i7);
            checkableImageButton.setMinimumHeight(i7);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        r rVar = this.f4271b;
        View.OnLongClickListener onLongClickListener = rVar.f4429i;
        CheckableImageButton checkableImageButton = rVar.f4424d;
        checkableImageButton.setOnClickListener(onClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        r rVar = this.f4271b;
        rVar.f4429i = onLongClickListener;
        CheckableImageButton checkableImageButton = rVar.f4424d;
        checkableImageButton.setOnLongClickListener(onLongClickListener);
        l6.f.y0(checkableImageButton, onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        r rVar = this.f4271b;
        rVar.f4428h = scaleType;
        rVar.f4424d.setScaleType(scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        r rVar = this.f4271b;
        if (rVar.f4425e != colorStateList) {
            rVar.f4425e = colorStateList;
            l6.f.e(rVar.f4421a, rVar.f4424d, colorStateList, rVar.f4426f);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        r rVar = this.f4271b;
        if (rVar.f4426f != mode) {
            rVar.f4426f = mode;
            l6.f.e(rVar.f4421a, rVar.f4424d, rVar.f4425e, mode);
        }
    }

    public void setStartIconVisible(boolean z6) {
        this.f4271b.c(z6);
    }

    public void setSuffixText(CharSequence charSequence) {
        l lVar = this.f4273c;
        lVar.getClass();
        lVar.f4373p = TextUtils.isEmpty(charSequence) ? null : charSequence;
        lVar.f4374q.setText(charSequence);
        lVar.n();
    }

    public void setSuffixTextAppearance(int i7) {
        this.f4273c.f4374q.setTextAppearance(i7);
    }

    public void setSuffixTextColor(ColorStateList colorStateList) {
        this.f4273c.f4374q.setTextColor(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(s sVar) {
        EditText editText = this.f4275d;
        if (editText != null) {
            d1.o(editText, sVar);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != this.f4272b0) {
            this.f4272b0 = typeface;
            com.google.android.material.internal.d dVar = this.f4310u0;
            boolean m6 = dVar.m(typeface);
            boolean o6 = dVar.o(typeface);
            if (m6 || o6) {
                dVar.i(false);
            }
            o oVar = this.f4287j;
            if (typeface != oVar.B) {
                oVar.B = typeface;
                AppCompatTextView appCompatTextView = oVar.f4406r;
                if (appCompatTextView != null) {
                    appCompatTextView.setTypeface(typeface);
                }
                AppCompatTextView appCompatTextView2 = oVar.f4413y;
                if (appCompatTextView2 != null) {
                    appCompatTextView2.setTypeface(typeface);
                }
            }
            AppCompatTextView appCompatTextView3 = this.f4297o;
            if (appCompatTextView3 != null) {
                appCompatTextView3.setTypeface(typeface);
            }
        }
    }

    public final void t() {
        if (this.O != 1) {
            FrameLayout frameLayout = this.f4269a;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            int c7 = c();
            if (c7 != layoutParams.topMargin) {
                layoutParams.topMargin = c7;
                frameLayout.requestLayout();
            }
        }
    }

    public final void u(boolean z6, boolean z7) {
        ColorStateList colorStateList;
        AppCompatTextView appCompatTextView;
        boolean isEnabled = isEnabled();
        EditText editText = this.f4275d;
        boolean z8 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.f4275d;
        boolean z9 = editText2 != null && editText2.hasFocus();
        ColorStateList colorStateList2 = this.f4286i0;
        com.google.android.material.internal.d dVar = this.f4310u0;
        if (colorStateList2 != null) {
            dVar.j(colorStateList2);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.f4286i0;
            dVar.j(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.f4306s0) : this.f4306s0));
        } else if (m()) {
            AppCompatTextView appCompatTextView2 = this.f4287j.f4406r;
            dVar.j(appCompatTextView2 != null ? appCompatTextView2.getTextColors() : null);
        } else if (this.f4293m && (appCompatTextView = this.f4297o) != null) {
            dVar.j(appCompatTextView.getTextColors());
        } else if (z9 && (colorStateList = this.f4288j0) != null && dVar.f3833o != colorStateList) {
            dVar.f3833o = colorStateList;
            dVar.i(false);
        }
        l lVar = this.f4273c;
        r rVar = this.f4271b;
        if (z8 || !this.f4312v0 || (isEnabled() && z9)) {
            if (z7 || this.f4308t0) {
                ValueAnimator valueAnimator = this.f4316x0;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.f4316x0.cancel();
                }
                if (z6 && this.f4314w0) {
                    a(1.0f);
                } else {
                    dVar.p(1.0f);
                }
                this.f4308t0 = false;
                if (e()) {
                    j();
                }
                EditText editText3 = this.f4275d;
                v(editText3 != null ? editText3.getText() : null);
                rVar.f4430j = false;
                rVar.e();
                lVar.f4375r = false;
                lVar.n();
                return;
            }
            return;
        }
        if (z7 || !this.f4308t0) {
            ValueAnimator valueAnimator2 = this.f4316x0;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.f4316x0.cancel();
            }
            if (z6 && this.f4314w0) {
                a(CropImageView.DEFAULT_ASPECT_RATIO);
            } else {
                dVar.p(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            if (e() && (!((g) this.F).f4339y.f4337v.isEmpty()) && e()) {
                ((g) this.F).y(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            }
            this.f4308t0 = true;
            AppCompatTextView appCompatTextView3 = this.f4307t;
            if (appCompatTextView3 != null && this.f4305s) {
                appCompatTextView3.setText((CharSequence) null);
                n1.u.a(this.f4269a, this.f4315x);
                this.f4307t.setVisibility(4);
            }
            rVar.f4430j = true;
            rVar.e();
            lVar.f4375r = true;
            lVar.n();
        }
    }

    public final void v(Editable editable) {
        ((y2.l) this.f4295n).getClass();
        FrameLayout frameLayout = this.f4269a;
        if ((editable != null && editable.length() != 0) || this.f4308t0) {
            AppCompatTextView appCompatTextView = this.f4307t;
            if (appCompatTextView == null || !this.f4305s) {
                return;
            }
            appCompatTextView.setText((CharSequence) null);
            n1.u.a(frameLayout, this.f4315x);
            this.f4307t.setVisibility(4);
            return;
        }
        if (this.f4307t == null || !this.f4305s || TextUtils.isEmpty(this.f4303r)) {
            return;
        }
        this.f4307t.setText(this.f4303r);
        n1.u.a(frameLayout, this.f4313w);
        this.f4307t.setVisibility(0);
        this.f4307t.bringToFront();
        announceForAccessibility(this.f4303r);
    }

    public final void w(boolean z6, boolean z7) {
        int defaultColor = this.f4296n0.getDefaultColor();
        int colorForState = this.f4296n0.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.f4296n0.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z6) {
            this.T = colorForState2;
        } else if (z7) {
            this.T = colorForState;
        } else {
            this.T = defaultColor;
        }
    }

    public final void x() {
        AppCompatTextView appCompatTextView;
        EditText editText;
        EditText editText2;
        if (this.F == null || this.O == 0) {
            return;
        }
        boolean z6 = false;
        boolean z7 = isFocused() || ((editText2 = this.f4275d) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.f4275d) != null && editText.isHovered())) {
            z6 = true;
        }
        if (!isEnabled()) {
            this.T = this.f4306s0;
        } else if (m()) {
            if (this.f4296n0 != null) {
                w(z7, z6);
            } else {
                this.T = getErrorCurrentTextColors();
            }
        } else if (!this.f4293m || (appCompatTextView = this.f4297o) == null) {
            if (z7) {
                this.T = this.f4294m0;
            } else if (z6) {
                this.T = this.f4292l0;
            } else {
                this.T = this.f4290k0;
            }
        } else if (this.f4296n0 != null) {
            w(z7, z6);
        } else {
            this.T = appCompatTextView.getCurrentTextColor();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            p();
        }
        l lVar = this.f4273c;
        lVar.l();
        CheckableImageButton checkableImageButton = lVar.f4360c;
        ColorStateList colorStateList = lVar.f4361d;
        TextInputLayout textInputLayout = lVar.f4358a;
        l6.f.p0(textInputLayout, checkableImageButton, colorStateList);
        ColorStateList colorStateList2 = lVar.f4368k;
        CheckableImageButton checkableImageButton2 = lVar.f4364g;
        l6.f.p0(textInputLayout, checkableImageButton2, colorStateList2);
        if (lVar.b() instanceof i) {
            if (!textInputLayout.m() || checkableImageButton2.getDrawable() == null) {
                l6.f.e(textInputLayout, checkableImageButton2, lVar.f4368k, lVar.f4369l);
            } else {
                Drawable mutate = checkableImageButton2.getDrawable().mutate();
                j0.b.g(mutate, textInputLayout.getErrorCurrentTextColors());
                checkableImageButton2.setImageDrawable(mutate);
            }
        }
        r rVar = this.f4271b;
        l6.f.p0(rVar.f4421a, rVar.f4424d, rVar.f4425e);
        if (this.O == 2) {
            int i7 = this.Q;
            if (z7 && isEnabled()) {
                this.Q = this.S;
            } else {
                this.Q = this.R;
            }
            if (this.Q != i7 && e() && !this.f4308t0) {
                if (e()) {
                    ((g) this.F).y(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                }
                j();
            }
        }
        if (this.O == 1) {
            if (!isEnabled()) {
                this.U = this.f4300p0;
            } else if (z6 && !z7) {
                this.U = this.f4304r0;
            } else if (z7) {
                this.U = this.f4302q0;
            } else {
                this.U = this.f4298o0;
            }
        }
        b();
    }
}
