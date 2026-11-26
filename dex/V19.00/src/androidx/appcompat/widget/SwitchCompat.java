package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SwitchCompat extends CompoundButton {
    public static final i3 R = new i3(Float.class, "thumbPos", 0);
    public static final int[] S = {R.attr.state_checked};
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public boolean H;
    public final TextPaint I;
    public ColorStateList J;
    public StaticLayout K;
    public StaticLayout L;
    public i.a M;
    public ObjectAnimator N;
    public b0 O;
    public k3 P;
    public final Rect Q;

    /* renamed from: a, reason: collision with root package name */
    public Drawable f1126a;

    /* renamed from: b, reason: collision with root package name */
    public ColorStateList f1127b;

    /* renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1128c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1129d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1130e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f1131f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f1132g;

    /* renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f1133h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1134i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1135j;

    /* renamed from: k, reason: collision with root package name */
    public int f1136k;

    /* renamed from: l, reason: collision with root package name */
    public int f1137l;

    /* renamed from: m, reason: collision with root package name */
    public int f1138m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1139n;

    /* renamed from: o, reason: collision with root package name */
    public CharSequence f1140o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f1141p;

    /* renamed from: q, reason: collision with root package name */
    public CharSequence f1142q;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f1143r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f1144s;

    /* renamed from: t, reason: collision with root package name */
    public int f1145t;

    /* renamed from: u, reason: collision with root package name */
    public final int f1146u;

    /* renamed from: v, reason: collision with root package name */
    public float f1147v;

    /* renamed from: w, reason: collision with root package name */
    public float f1148w;

    /* renamed from: x, reason: collision with root package name */
    public final VelocityTracker f1149x;

    /* renamed from: y, reason: collision with root package name */
    public final int f1150y;

    /* renamed from: z, reason: collision with root package name */
    public float f1151z;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1127b = null;
        this.f1128c = null;
        this.f1129d = false;
        this.f1130e = false;
        this.f1132g = null;
        this.f1133h = null;
        this.f1134i = false;
        this.f1135j = false;
        this.f1149x = VelocityTracker.obtain();
        this.H = true;
        this.Q = new Rect();
        l3.a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.I = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.SwitchCompat, i7, 0);
        androidx.appcompat.app.g gVar = new androidx.appcompat.app.g(context, obtainStyledAttributes);
        p0.d1.n(this, context, f.j.SwitchCompat, attributeSet, obtainStyledAttributes, i7);
        Drawable I = gVar.I(f.j.SwitchCompat_android_thumb);
        this.f1126a = I;
        if (I != null) {
            I.setCallback(this);
        }
        Drawable I2 = gVar.I(f.j.SwitchCompat_track);
        this.f1131f = I2;
        if (I2 != null) {
            I2.setCallback(this);
        }
        setTextOnInternal(gVar.Q(f.j.SwitchCompat_android_textOn));
        setTextOffInternal(gVar.Q(f.j.SwitchCompat_android_textOff));
        this.f1144s = gVar.E(f.j.SwitchCompat_showText, true);
        this.f1136k = gVar.H(f.j.SwitchCompat_thumbTextPadding, 0);
        this.f1137l = gVar.H(f.j.SwitchCompat_switchMinWidth, 0);
        this.f1138m = gVar.H(f.j.SwitchCompat_switchPadding, 0);
        this.f1139n = gVar.E(f.j.SwitchCompat_splitTrack, false);
        ColorStateList F = gVar.F(f.j.SwitchCompat_thumbTint);
        if (F != null) {
            this.f1127b = F;
            this.f1129d = true;
        }
        PorterDuff.Mode c7 = m1.c(gVar.L(f.j.SwitchCompat_thumbTintMode, -1), null);
        if (this.f1128c != c7) {
            this.f1128c = c7;
            this.f1130e = true;
        }
        if (this.f1129d || this.f1130e) {
            a();
        }
        ColorStateList F2 = gVar.F(f.j.SwitchCompat_trackTint);
        if (F2 != null) {
            this.f1132g = F2;
            this.f1134i = true;
        }
        PorterDuff.Mode c8 = m1.c(gVar.L(f.j.SwitchCompat_trackTintMode, -1), null);
        if (this.f1133h != c8) {
            this.f1133h = c8;
            this.f1135j = true;
        }
        if (this.f1134i || this.f1135j) {
            b();
        }
        int N = gVar.N(f.j.SwitchCompat_switchTextAppearance, 0);
        if (N != 0) {
            setSwitchTextAppearance(context, N);
        }
        new x0(this).f(attributeSet, i7);
        gVar.V();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1146u = viewConfiguration.getScaledTouchSlop();
        this.f1150y = viewConfiguration.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().b(attributeSet, i7);
        refreshDrawableState();
        setChecked(isChecked());
    }

    private b0 getEmojiTextViewHelper() {
        if (this.O == null) {
            this.O = new b0(this);
        }
        return this.O;
    }

    private boolean getTargetCheckedState() {
        return this.f1151z > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((a4.a(this) ? 1.0f - this.f1151z : this.f1151z) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f1131f;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.Q;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f1126a;
        Rect b7 = drawable2 != null ? m1.b(drawable2) : m1.INSETS_NONE;
        return ((((this.A - this.C) - rect.left) - rect.right) - b7.left) - b7.right;
    }

    private void setTextOffInternal(CharSequence charSequence) {
        this.f1142q = charSequence;
        b0 emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod L = ((p3.f) emojiTextViewHelper.f1192b.f6290b).L(this.M);
        if (L != null) {
            charSequence = L.getTransformation(charSequence, this);
        }
        this.f1143r = charSequence;
        this.L = null;
        if (this.f1144s) {
            d();
        }
    }

    private void setTextOnInternal(CharSequence charSequence) {
        this.f1140o = charSequence;
        b0 emojiTextViewHelper = getEmojiTextViewHelper();
        TransformationMethod L = ((p3.f) emojiTextViewHelper.f1192b.f6290b).L(this.M);
        if (L != null) {
            charSequence = L.getTransformation(charSequence, this);
        }
        this.f1141p = charSequence;
        this.K = null;
        if (this.f1144s) {
            d();
        }
    }

    public final void a() {
        Drawable drawable = this.f1126a;
        if (drawable != null) {
            if (this.f1129d || this.f1130e) {
                Drawable mutate = drawable.mutate();
                this.f1126a = mutate;
                if (this.f1129d) {
                    j0.b.h(mutate, this.f1127b);
                }
                if (this.f1130e) {
                    j0.b.i(this.f1126a, this.f1128c);
                }
                if (this.f1126a.isStateful()) {
                    this.f1126a.setState(getDrawableState());
                }
            }
        }
    }

    public final void b() {
        Drawable drawable = this.f1131f;
        if (drawable != null) {
            if (this.f1134i || this.f1135j) {
                Drawable mutate = drawable.mutate();
                this.f1131f = mutate;
                if (this.f1134i) {
                    j0.b.h(mutate, this.f1132g);
                }
                if (this.f1135j) {
                    j0.b.i(this.f1131f, this.f1133h);
                }
                if (this.f1131f.isStateful()) {
                    this.f1131f.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        setTextOnInternal(this.f1140o);
        setTextOffInternal(this.f1142q);
        requestLayout();
    }

    public final void d() {
        if (this.P == null && ((p3.f) this.O.f1192b.f6290b).z() && y0.l.f9977j != null) {
            y0.l a7 = y0.l.a();
            int b7 = a7.b();
            if (b7 == 3 || b7 == 0) {
                k3 k3Var = new k3(this);
                this.P = k3Var;
                a7.g(k3Var);
            }
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int i7;
        int i8;
        int i9 = this.D;
        int i10 = this.E;
        int i11 = this.F;
        int i12 = this.G;
        int thumbOffset = getThumbOffset() + i9;
        Drawable drawable = this.f1126a;
        Rect b7 = drawable != null ? m1.b(drawable) : m1.INSETS_NONE;
        Drawable drawable2 = this.f1131f;
        Rect rect = this.Q;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i13 = rect.left;
            thumbOffset += i13;
            if (b7 != null) {
                int i14 = b7.left;
                if (i14 > i13) {
                    i9 += i14 - i13;
                }
                int i15 = b7.top;
                int i16 = rect.top;
                i7 = i15 > i16 ? (i15 - i16) + i10 : i10;
                int i17 = b7.right;
                int i18 = rect.right;
                if (i17 > i18) {
                    i11 -= i17 - i18;
                }
                int i19 = b7.bottom;
                int i20 = rect.bottom;
                if (i19 > i20) {
                    i8 = i12 - (i19 - i20);
                    this.f1131f.setBounds(i9, i7, i11, i8);
                }
            } else {
                i7 = i10;
            }
            i8 = i12;
            this.f1131f.setBounds(i9, i7, i11, i8);
        }
        Drawable drawable3 = this.f1126a;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i21 = thumbOffset - rect.left;
            int i22 = thumbOffset + this.C + rect.right;
            this.f1126a.setBounds(i21, i10, i22, i12);
            Drawable background = getBackground();
            if (background != null) {
                j0.b.f(background, i21, i10, i22, i12);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableHotspotChanged(float f7, float f8) {
        super.drawableHotspotChanged(f7, f8);
        Drawable drawable = this.f1126a;
        if (drawable != null) {
            j0.b.e(drawable, f7, f8);
        }
        Drawable drawable2 = this.f1131f;
        if (drawable2 != null) {
            j0.b.e(drawable2, f7, f8);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1126a;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.f1131f;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!a4.a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f1138m : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (a4.a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f1138m : compoundPaddingRight;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.w.d(super.getCustomSelectionActionModeCallback());
    }

    public boolean getShowText() {
        return this.f1144s;
    }

    public boolean getSplitTrack() {
        return this.f1139n;
    }

    public int getSwitchMinWidth() {
        return this.f1137l;
    }

    public int getSwitchPadding() {
        return this.f1138m;
    }

    public CharSequence getTextOff() {
        return this.f1142q;
    }

    public CharSequence getTextOn() {
        return this.f1140o;
    }

    public Drawable getThumbDrawable() {
        return this.f1126a;
    }

    public final float getThumbPosition() {
        return this.f1151z;
    }

    public int getThumbTextPadding() {
        return this.f1136k;
    }

    public ColorStateList getThumbTintList() {
        return this.f1127b;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f1128c;
    }

    public Drawable getTrackDrawable() {
        return this.f1131f;
    }

    public ColorStateList getTrackTintList() {
        return this.f1132g;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.f1133h;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1126a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f1131f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.N;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.N.end();
        this.N = null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, S);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Drawable drawable = this.f1131f;
        Rect rect = this.Q;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i7 = this.E;
        int i8 = this.G;
        int i9 = i7 + rect.top;
        int i10 = i8 - rect.bottom;
        Drawable drawable2 = this.f1126a;
        if (drawable != null) {
            if (!this.f1139n || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect b7 = m1.b(drawable2);
                drawable2.copyBounds(rect);
                rect.left += b7.left;
                rect.right -= b7.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        StaticLayout staticLayout = getTargetCheckedState() ? this.K : this.L;
        if (staticLayout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.J;
            TextPaint textPaint = this.I;
            if (colorStateList != null) {
                textPaint.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            textPaint.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (staticLayout.getWidth() / 2), ((i9 + i10) / 2) - (staticLayout.getHeight() / 2));
            staticLayout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        if (Build.VERSION.SDK_INT < 30) {
            CharSequence charSequence = isChecked() ? this.f1140o : this.f1142q;
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int i11;
        int width;
        int i12;
        int i13;
        int i14;
        super.onLayout(z6, i7, i8, i9, i10);
        int i15 = 0;
        if (this.f1126a != null) {
            Drawable drawable = this.f1131f;
            Rect rect = this.Q;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect b7 = m1.b(this.f1126a);
            i11 = Math.max(0, b7.left - rect.left);
            i15 = Math.max(0, b7.right - rect.right);
        } else {
            i11 = 0;
        }
        if (a4.a(this)) {
            i12 = getPaddingLeft() + i11;
            width = ((this.A + i12) - i11) - i15;
        } else {
            width = (getWidth() - getPaddingRight()) - i15;
            i12 = (width - this.A) + i11 + i15;
        }
        int gravity = getGravity() & z1.c.INVOKE_DIRECT;
        if (gravity == 16) {
            int height = ((getHeight() + getPaddingTop()) - getPaddingBottom()) / 2;
            int i16 = this.B;
            int i17 = height - (i16 / 2);
            i13 = i16 + i17;
            i14 = i17;
        } else if (gravity != 80) {
            i14 = getPaddingTop();
            i13 = this.B + i14;
        } else {
            i13 = getHeight() - getPaddingBottom();
            i14 = i13 - this.B;
        }
        this.D = i12;
        this.E = i14;
        this.G = i13;
        this.F = width;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int i11 = 0;
        if (this.f1144s) {
            StaticLayout staticLayout = this.K;
            TextPaint textPaint = this.I;
            if (staticLayout == null) {
                CharSequence charSequence = this.f1141p;
                this.K = new StaticLayout(charSequence, textPaint, charSequence != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, true);
            }
            if (this.L == null) {
                CharSequence charSequence2 = this.f1143r;
                this.L = new StaticLayout(charSequence2, textPaint, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, true);
            }
        }
        Drawable drawable = this.f1126a;
        Rect rect = this.Q;
        if (drawable != null) {
            drawable.getPadding(rect);
            i9 = (this.f1126a.getIntrinsicWidth() - rect.left) - rect.right;
            i10 = this.f1126a.getIntrinsicHeight();
        } else {
            i9 = 0;
            i10 = 0;
        }
        this.C = Math.max(this.f1144s ? (this.f1136k * 2) + Math.max(this.K.getWidth(), this.L.getWidth()) : 0, i9);
        Drawable drawable2 = this.f1131f;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i11 = this.f1131f.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i12 = rect.left;
        int i13 = rect.right;
        Drawable drawable3 = this.f1126a;
        if (drawable3 != null) {
            Rect b7 = m1.b(drawable3);
            i12 = Math.max(i12, b7.left);
            i13 = Math.max(i13, b7.right);
        }
        int max = this.H ? Math.max(this.f1137l, (this.C * 2) + i12 + i13) : this.f1137l;
        int max2 = Math.max(i11, i10);
        this.A = max;
        this.B = max2;
        super.onMeasure(i7, i8);
        if (getMeasuredHeight() < max2) {
            setMeasuredDimension(getMeasuredWidthAndState(), max2);
        }
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.f1140o : this.f1142q;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
    
        if (r1 != 3) goto L82;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        getEmojiTextViewHelper().c(z6);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z6) {
        super.setChecked(z6);
        boolean isChecked = isChecked();
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= 30) {
                Object obj = this.f1140o;
                if (obj == null) {
                    obj = getResources().getString(f.h.abc_capital_on);
                }
                int i7 = p0.d1.OVER_SCROLL_ALWAYS;
                new p0.h0(d0.c.tag_state_description, 64, 30, 2).b(this, obj);
            }
        } else if (Build.VERSION.SDK_INT >= 30) {
            Object obj2 = this.f1142q;
            if (obj2 == null) {
                obj2 = getResources().getString(f.h.abc_capital_off);
            }
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            new p0.h0(d0.c.tag_state_description, 64, 30, 2).b(this, obj2);
        }
        IBinder windowToken = getWindowToken();
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (windowToken != null) {
            int i9 = p0.d1.OVER_SCROLL_ALWAYS;
            if (p0.o0.c(this)) {
                if (isChecked) {
                    f7 = 1.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, R, f7);
                this.N = ofFloat;
                ofFloat.setDuration(250L);
                j3.a(this.N, true);
                this.N.start();
                return;
            }
        }
        ObjectAnimator objectAnimator = this.N;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        if (isChecked) {
            f7 = 1.0f;
        }
        setThumbPosition(f7);
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.w.e(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        getEmojiTextViewHelper().d(z6);
        setTextOnInternal(this.f1140o);
        setTextOffInternal(this.f1142q);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean z6) {
        this.H = z6;
        invalidate();
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setShowText(boolean z6) {
        if (this.f1144s != z6) {
            this.f1144s = z6;
            requestLayout();
            if (z6) {
                d();
            }
        }
    }

    public void setSplitTrack(boolean z6) {
        this.f1139n = z6;
        invalidate();
    }

    public void setSwitchMinWidth(int i7) {
        this.f1137l = i7;
        requestLayout();
    }

    public void setSwitchPadding(int i7) {
        this.f1138m = i7;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context context, int i7) {
        ColorStateList colorStateList;
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i7, f.j.TextAppearance);
        int i8 = f.j.TextAppearance_android_textColor;
        if (!obtainStyledAttributes.hasValue(i8) || (resourceId = obtainStyledAttributes.getResourceId(i8, 0)) == 0 || (colorStateList = g0.g.b(context, resourceId)) == null) {
            colorStateList = obtainStyledAttributes.getColorStateList(i8);
        }
        if (colorStateList != null) {
            this.J = colorStateList;
        } else {
            this.J = getTextColors();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(f.j.TextAppearance_android_textSize, 0);
        if (dimensionPixelSize != 0) {
            float f7 = dimensionPixelSize;
            TextPaint textPaint = this.I;
            if (f7 != textPaint.getTextSize()) {
                textPaint.setTextSize(f7);
                requestLayout();
            }
        }
        int i9 = obtainStyledAttributes.getInt(f.j.TextAppearance_android_typeface, -1);
        setSwitchTypeface(i9 != 1 ? i9 != 2 ? i9 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, obtainStyledAttributes.getInt(f.j.TextAppearance_android_textStyle, -1));
        if (obtainStyledAttributes.getBoolean(f.j.TextAppearance_textAllCaps, false)) {
            Context context2 = getContext();
            i.a aVar = new i.a();
            aVar.f6771a = context2.getResources().getConfiguration().locale;
            this.M = aVar;
        } else {
            this.M = null;
        }
        setTextOnInternal(this.f1140o);
        setTextOffInternal(this.f1142q);
        obtainStyledAttributes.recycle();
    }

    public void setSwitchTypeface(Typeface typeface) {
        TextPaint textPaint = this.I;
        if ((textPaint.getTypeface() == null || textPaint.getTypeface().equals(typeface)) && (textPaint.getTypeface() != null || typeface == null)) {
            return;
        }
        textPaint.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setSwitchTypeface(Typeface typeface, int i7) {
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        TextPaint textPaint = this.I;
        if (i7 <= 0) {
            textPaint.setFakeBoldText(false);
            textPaint.setTextSkewX(CropImageView.DEFAULT_ASPECT_RATIO);
            setSwitchTypeface(typeface);
            return;
        }
        Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i7) : Typeface.create(typeface, i7);
        setSwitchTypeface(defaultFromStyle);
        int i8 = (~(defaultFromStyle != null ? defaultFromStyle.getStyle() : 0)) & i7;
        textPaint.setFakeBoldText((i8 & 1) != 0);
        if ((i8 & 2) != 0) {
            f7 = -0.25f;
        }
        textPaint.setTextSkewX(f7);
    }

    public void setTextOff(CharSequence charSequence) {
        setTextOffInternal(charSequence);
        requestLayout();
        if (isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object obj = this.f1142q;
        if (obj == null) {
            obj = getResources().getString(f.h.abc_capital_off);
        }
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        new p0.h0(d0.c.tag_state_description, 64, 30, 2).b(this, obj);
    }

    public void setTextOn(CharSequence charSequence) {
        setTextOnInternal(charSequence);
        requestLayout();
        if (!isChecked() || Build.VERSION.SDK_INT < 30) {
            return;
        }
        Object obj = this.f1140o;
        if (obj == null) {
            obj = getResources().getString(f.h.abc_capital_on);
        }
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        new p0.h0(d0.c.tag_state_description, 64, 30, 2).b(this, obj);
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1126a;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1126a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f7) {
        this.f1151z = f7;
        invalidate();
    }

    public void setThumbResource(int i7) {
        setThumbDrawable(l6.f.A(getContext(), i7));
    }

    public void setThumbTextPadding(int i7) {
        this.f1136k = i7;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f1127b = colorStateList;
        this.f1129d = true;
        a();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f1128c = mode;
        this.f1130e = true;
        a();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1131f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1131f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i7) {
        setTrackDrawable(l6.f.A(getContext(), i7));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.f1132g = colorStateList;
        this.f1134i = true;
        b();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.f1133h = mode;
        this.f1135j = true;
        b();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1126a || drawable == this.f1131f;
    }
}
