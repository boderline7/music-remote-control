package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActivityChooserView extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public final u f992a;

    /* renamed from: b, reason: collision with root package name */
    public final v f993b;

    /* renamed from: c, reason: collision with root package name */
    public final View f994c;

    /* renamed from: d, reason: collision with root package name */
    public final FrameLayout f995d;

    /* renamed from: e, reason: collision with root package name */
    public final ImageView f996e;

    /* renamed from: f, reason: collision with root package name */
    public final FrameLayout f997f;

    /* renamed from: g, reason: collision with root package name */
    public p0.d f998g;

    /* renamed from: h, reason: collision with root package name */
    public final k.e f999h;

    /* renamed from: i, reason: collision with root package name */
    public e2 f1000i;

    /* renamed from: j, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1001j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f1002k;

    public static class InnerLayout extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        public static final int[] f1003a = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            int resourceId;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1003a);
            setBackgroundDrawable((!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) ? obtainStyledAttributes.getDrawable(0) : l6.f.A(context, resourceId));
            obtainStyledAttributes.recycle();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        int i8 = 0;
        new s(this, i8);
        this.f999h = new k.e(2, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ActivityChooserView, i7, 0);
        p0.d1.n(this, context, f.j.ActivityChooserView, attributeSet, obtainStyledAttributes, i7);
        obtainStyledAttributes.getInt(f.j.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = obtainStyledAttributes.getDrawable(f.j.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(f.g.abc_activity_chooser_view, (ViewGroup) this, true);
        v vVar = new v(this);
        this.f993b = vVar;
        View findViewById = findViewById(f.f.activity_chooser_view_content);
        this.f994c = findViewById;
        findViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(f.f.default_activity_button);
        this.f997f = frameLayout;
        frameLayout.setOnClickListener(vVar);
        frameLayout.setOnLongClickListener(vVar);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(f.f.expand_activities_button);
        frameLayout2.setOnClickListener(vVar);
        frameLayout2.setAccessibilityDelegate(new t(i8, this));
        frameLayout2.setOnTouchListener(new k.b(this, frameLayout2));
        this.f995d = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(f.f.image);
        this.f996e = imageView;
        imageView.setImageDrawable(drawable);
        u uVar = new u(this);
        this.f992a = uVar;
        uVar.registerDataSetObserver(new s(this, 1));
        Resources resources = context.getResources();
        Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(f.d.abc_config_prefDialogWidth));
    }

    public final void a() {
        if (b()) {
            getListPopupWindow().dismiss();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.f999h);
            }
        }
    }

    public final boolean b() {
        return getListPopupWindow().f1237z.isShowing();
    }

    public r getDataModel() {
        this.f992a.getClass();
        return null;
    }

    public e2 getListPopupWindow() {
        if (this.f1000i == null) {
            e2 e2Var = new e2(getContext());
            this.f1000i = e2Var;
            e2Var.p(this.f992a);
            e2 e2Var2 = this.f1000i;
            e2Var2.f1226o = this;
            e2Var2.f1236y = true;
            e2Var2.f1237z.setFocusable(true);
            e2 e2Var3 = this.f1000i;
            v vVar = this.f993b;
            e2Var3.f1227p = vVar;
            e2Var3.f1237z.setOnDismissListener(vVar);
        }
        return this.f1000i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f992a.getClass();
        this.f1002k = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f992a.getClass();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f999h);
        }
        if (b()) {
            a();
        }
        this.f1002k = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        this.f994c.layout(0, 0, i9 - i7, i10 - i8);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        if (this.f997f.getVisibility() != 0) {
            i8 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i8), v.c.EXACTLY);
        }
        View view = this.f994c;
        measureChild(view, i7, i8);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(r rVar) {
        u uVar = this.f992a;
        uVar.f1418a.f992a.getClass();
        uVar.notifyDataSetChanged();
        if (b()) {
            a();
            if (b() || !this.f1002k) {
                return;
            }
            uVar.getClass();
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    public void setDefaultActionButtonContentDescription(int i7) {
    }

    public void setExpandActivityOverflowButtonContentDescription(int i7) {
        this.f996e.setContentDescription(getContext().getString(i7));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f996e.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i7) {
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f1001j = onDismissListener;
    }

    public void setProvider(p0.d dVar) {
        this.f998g = dVar;
    }
}
