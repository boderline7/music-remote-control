package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class ViewStubCompat extends View {

    /* renamed from: a, reason: collision with root package name */
    public int f1180a;

    /* renamed from: b, reason: collision with root package name */
    public int f1181b;

    /* renamed from: c, reason: collision with root package name */
    public WeakReference f1182c;

    /* renamed from: d, reason: collision with root package name */
    public LayoutInflater f1183d;

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1180a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ViewStubCompat, i7, 0);
        this.f1181b = obtainStyledAttributes.getResourceId(f.j.ViewStubCompat_android_inflatedId, -1);
        this.f1180a = obtainStyledAttributes.getResourceId(f.j.ViewStubCompat_android_layout, 0);
        setId(obtainStyledAttributes.getResourceId(f.j.ViewStubCompat_android_id, -1));
        obtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public final View a() {
        ViewParent parent = getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if (this.f1180a == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        LayoutInflater layoutInflater = this.f1183d;
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(getContext());
        }
        View inflate = layoutInflater.inflate(this.f1180a, viewGroup, false);
        int i7 = this.f1181b;
        if (i7 != -1) {
            inflate.setId(i7);
        }
        int indexOfChild = viewGroup.indexOfChild(this);
        viewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            viewGroup.addView(inflate, indexOfChild, layoutParams);
        } else {
            viewGroup.addView(inflate, indexOfChild);
        }
        this.f1182c = new WeakReference(inflate);
        return inflate;
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f1181b;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1183d;
    }

    public int getLayoutResource() {
        return this.f1180a;
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i7) {
        this.f1181b = i7;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1183d = layoutInflater;
    }

    public void setLayoutResource(int i7) {
        this.f1180a = i7;
    }

    public void setOnInflateListener(z3 z3Var) {
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        WeakReference weakReference = this.f1182c;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i7);
            return;
        }
        super.setVisibility(i7);
        if (i7 == 0 || i7 == 4) {
            a();
        }
    }
}
