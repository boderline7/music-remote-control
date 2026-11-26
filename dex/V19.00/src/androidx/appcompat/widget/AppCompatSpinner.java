package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatSpinner extends Spinner {

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f1041i = {R.attr.spinnerMode};

    /* renamed from: a, reason: collision with root package name */
    public final w f1042a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f1043b;

    /* renamed from: c, reason: collision with root package name */
    public final j f1044c;

    /* renamed from: d, reason: collision with root package name */
    public SpinnerAdapter f1045d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f1046e;

    /* renamed from: f, reason: collision with root package name */
    public final p0 f1047f;

    /* renamed from: g, reason: collision with root package name */
    public int f1048g;

    /* renamed from: h, reason: collision with root package name */
    public final Rect f1049h;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new o0();

        /* renamed from: a, reason: collision with root package name */
        public boolean f1050a;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeByte(this.f1050a ? (byte) 1 : (byte) 0);
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.spinnerStyle);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
    
        if (r4 == null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppCompatSpinner(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            r8.<init>(r9, r10, r11)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r8.f1049h = r0
            android.content.Context r0 = r8.getContext()
            androidx.appcompat.widget.l3.a(r8, r0)
            int[] r0 = f.j.Spinner
            r1 = 0
            android.content.res.TypedArray r0 = r9.obtainStyledAttributes(r10, r0, r11, r1)
            androidx.appcompat.widget.w r2 = new androidx.appcompat.widget.w
            r2.<init>(r8)
            r8.f1042a = r2
            int r2 = f.j.Spinner_popupTheme
            int r2 = r0.getResourceId(r2, r1)
            if (r2 == 0) goto L2f
            j.f r3 = new j.f
            r3.<init>(r9, r2)
            r8.f1043b = r3
            goto L31
        L2f:
            r8.f1043b = r9
        L31:
            r2 = -1
            r3 = 0
            int[] r4 = androidx.appcompat.widget.AppCompatSpinner.f1041i     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            android.content.res.TypedArray r4 = r9.obtainStyledAttributes(r10, r4, r11, r1)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            boolean r5 = r4.hasValue(r1)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L55
            if (r5 == 0) goto L47
            int r2 = r4.getInt(r1, r1)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L55
            goto L47
        L44:
            r9 = move-exception
            r3 = r4
            goto L4f
        L47:
            r4.recycle()
            goto L58
        L4b:
            r9 = move-exception
            goto L4f
        L4d:
            r4 = r3
            goto L55
        L4f:
            if (r3 == 0) goto L54
            r3.recycle()
        L54:
            throw r9
        L55:
            if (r4 == 0) goto L58
            goto L47
        L58:
            r1 = 1
            if (r2 == 0) goto L98
            if (r2 == r1) goto L5e
            goto La7
        L5e:
            androidx.appcompat.widget.n0 r2 = new androidx.appcompat.widget.n0
            android.content.Context r4 = r8.f1043b
            r2.<init>(r8, r4, r10, r11)
            android.content.Context r4 = r8.f1043b
            int[] r5 = f.j.Spinner
            androidx.appcompat.app.g r4 = androidx.appcompat.app.g.S(r4, r10, r5, r11)
            int r5 = f.j.Spinner_android_dropDownWidth
            java.lang.Object r6 = r4.f715c
            android.content.res.TypedArray r6 = (android.content.res.TypedArray) r6
            r7 = -2
            int r5 = r6.getLayoutDimension(r5, r7)
            r8.f1048g = r5
            int r5 = f.j.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r5 = r4.I(r5)
            r2.n(r5)
            int r5 = f.j.Spinner_android_prompt
            java.lang.String r5 = r0.getString(r5)
            r2.C = r5
            r4.V()
            r8.f1047f = r2
            androidx.appcompat.widget.j r4 = new androidx.appcompat.widget.j
            r4.<init>(r8, r8, r2, r1)
            r8.f1044c = r4
            goto La7
        L98:
            androidx.appcompat.widget.k0 r2 = new androidx.appcompat.widget.k0
            r2.<init>(r8)
            r8.f1047f = r2
            int r4 = f.j.Spinner_android_prompt
            java.lang.String r4 = r0.getString(r4)
            r2.f1296c = r4
        La7:
            int r2 = f.j.Spinner_android_entries
            java.lang.CharSequence[] r2 = r0.getTextArray(r2)
            if (r2 == 0) goto Lbf
            android.widget.ArrayAdapter r4 = new android.widget.ArrayAdapter
            r5 = 17367048(0x1090008, float:2.5162948E-38)
            r4.<init>(r9, r5, r2)
            int r9 = f.g.support_simple_spinner_dropdown_item
            r4.setDropDownViewResource(r9)
            r8.setAdapter(r4)
        Lbf:
            r0.recycle()
            r8.f1046e = r1
            android.widget.SpinnerAdapter r9 = r8.f1045d
            if (r9 == 0) goto Lcd
            r8.setAdapter(r9)
            r8.f1045d = r3
        Lcd:
            androidx.appcompat.widget.w r9 = r8.f1042a
            r9.g(r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i7 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i8 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i7) {
                view = null;
                i7 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i8 = Math.max(i8, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i8;
        }
        Rect rect = this.f1049h;
        drawable.getPadding(rect);
        return i8 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1042a;
        if (wVar != null) {
            wVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        p0 p0Var = this.f1047f;
        return p0Var != null ? p0Var.e() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        p0 p0Var = this.f1047f;
        return p0Var != null ? p0Var.h() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f1047f != null ? this.f1048g : super.getDropDownWidth();
    }

    public final p0 getInternalPopup() {
        return this.f1047f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        p0 p0Var = this.f1047f;
        return p0Var != null ? p0Var.i() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1043b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        p0 p0Var = this.f1047f;
        return p0Var != null ? p0Var.j() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1042a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1042a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p0 p0Var = this.f1047f;
        if (p0Var == null || !p0Var.b()) {
            return;
        }
        p0Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.f1047f == null || View.MeasureSpec.getMode(i7) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i7)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.f1050a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new k.e(3, this));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        p0 p0Var = this.f1047f;
        savedState.f1050a = p0Var != null && p0Var.b();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        j jVar = this.f1044c;
        if (jVar == null || !jVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        p0 p0Var = this.f1047f;
        if (p0Var == null) {
            return super.performClick();
        }
        if (p0Var.b()) {
            return true;
        }
        this.f1047f.f(i0.b(this), i0.a(this));
        return true;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1046e) {
            this.f1045d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        p0 p0Var = this.f1047f;
        if (p0Var != null) {
            Context context = this.f1043b;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            l0 l0Var = new l0();
            l0Var.f1306a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                l0Var.f1307b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                j0.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            p0Var.p(l0Var);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1042a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1042a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i7) {
        p0 p0Var = this.f1047f;
        if (p0Var == null) {
            super.setDropDownHorizontalOffset(i7);
        } else {
            p0Var.q(i7);
            p0Var.c(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i7) {
        p0 p0Var = this.f1047f;
        if (p0Var != null) {
            p0Var.o(i7);
        } else {
            super.setDropDownVerticalOffset(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i7) {
        if (this.f1047f != null) {
            this.f1048g = i7;
        } else {
            super.setDropDownWidth(i7);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        p0 p0Var = this.f1047f;
        if (p0Var != null) {
            p0Var.n(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i7) {
        setPopupBackgroundDrawable(l6.f.A(getPopupContext(), i7));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        p0 p0Var = this.f1047f;
        if (p0Var != null) {
            p0Var.m(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1042a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1042a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }
}
