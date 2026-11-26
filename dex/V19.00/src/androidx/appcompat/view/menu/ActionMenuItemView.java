package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.n;
import androidx.appcompat.widget.x3;
import f.j;
import k.b;
import k.c;
import k.d0;
import k.o;
import k.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActionMenuItemView extends AppCompatTextView implements d0, View.OnClickListener, n {

    /* renamed from: h, reason: collision with root package name */
    public q f894h;

    /* renamed from: i, reason: collision with root package name */
    public CharSequence f895i;

    /* renamed from: j, reason: collision with root package name */
    public Drawable f896j;

    /* renamed from: k, reason: collision with root package name */
    public k.n f897k;

    /* renamed from: l, reason: collision with root package name */
    public b f898l;

    /* renamed from: m, reason: collision with root package name */
    public c f899m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f900n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f901o;

    /* renamed from: p, reason: collision with root package name */
    public final int f902p;

    /* renamed from: q, reason: collision with root package name */
    public int f903q;

    /* renamed from: r, reason: collision with root package name */
    public final int f904r;

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        Resources resources = context.getResources();
        this.f900n = m();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ActionMenuItemView, i7, 0);
        this.f902p = obtainStyledAttributes.getDimensionPixelSize(j.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.f904r = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f903q = -1;
        setSaveEnabled(false);
    }

    @Override // androidx.appcompat.widget.n
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.widget.n
    public final boolean b() {
        return (TextUtils.isEmpty(getText()) ^ true) && this.f894h.getIcon() == null;
    }

    @Override // k.d0
    public final void c(q qVar) {
        this.f894h = qVar;
        setIcon(qVar.getIcon());
        setTitle(qVar.getTitleCondensed());
        setId(qVar.f7166a);
        setVisibility(qVar.isVisible() ? 0 : 8);
        setEnabled(qVar.isEnabled());
        if (qVar.hasSubMenu() && this.f898l == null) {
            this.f898l = new b(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // k.d0
    public q getItemData() {
        return this.f894h;
    }

    public final boolean m() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        return i7 >= 480 || (i7 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void n() {
        boolean z6 = true;
        boolean z7 = !TextUtils.isEmpty(this.f895i);
        if (this.f896j != null && ((this.f894h.f7190y & 4) != 4 || (!this.f900n && !this.f901o))) {
            z6 = false;
        }
        boolean z8 = z7 & z6;
        setText(z8 ? this.f895i : null);
        CharSequence charSequence = this.f894h.f7182q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z8 ? null : this.f894h.f7170e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f894h.f7183r;
        if (TextUtils.isEmpty(charSequence2)) {
            x3.a(this, z8 ? null : this.f894h.f7170e);
        } else {
            x3.a(this, charSequence2);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        k.n nVar = this.f897k;
        if (nVar != null) {
            nVar.a(this.f894h);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f900n = m();
        n();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void onMeasure(int i7, int i8) {
        int i9;
        boolean z6 = !TextUtils.isEmpty(getText());
        if (z6 && (i9 = this.f903q) >= 0) {
            super.setPadding(i9, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i7, i8);
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int measuredWidth = getMeasuredWidth();
        int i10 = this.f902p;
        int min = mode == Integer.MIN_VALUE ? Math.min(size, i10) : i10;
        if (mode != 1073741824 && i10 > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, v.c.EXACTLY), i8);
        }
        if (z6 || this.f896j == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f896j.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        b bVar;
        if (this.f894h.hasSubMenu() && (bVar = this.f898l) != null && bVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z6) {
    }

    public void setChecked(boolean z6) {
    }

    public void setExpandedFormat(boolean z6) {
        if (this.f901o != z6) {
            this.f901o = z6;
            q qVar = this.f894h;
            if (qVar != null) {
                o oVar = qVar.f7179n;
                oVar.f7149k = true;
                oVar.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f896j = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i7 = this.f904r;
            if (intrinsicWidth > i7) {
                intrinsicHeight = (int) (intrinsicHeight * (i7 / intrinsicWidth));
                intrinsicWidth = i7;
            }
            if (intrinsicHeight > i7) {
                intrinsicWidth = (int) (intrinsicWidth * (i7 / intrinsicHeight));
            } else {
                i7 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i7);
        }
        setCompoundDrawables(drawable, null, null, null);
        n();
    }

    public void setItemInvoker(k.n nVar) {
        this.f897k = nVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        this.f903q = i7;
        super.setPadding(i7, i8, i9, i10);
    }

    public void setPopupCallback(c cVar) {
        this.f899m = cVar;
    }

    public void setShortcut(boolean z6, char c7) {
    }

    public void setTitle(CharSequence charSequence) {
        this.f895i = charSequence;
        n();
    }
}
