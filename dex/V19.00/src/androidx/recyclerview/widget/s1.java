package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class s1 extends ViewGroup.MarginLayoutParams {

    /* renamed from: a, reason: collision with root package name */
    public j2 f2652a;

    /* renamed from: b, reason: collision with root package name */
    public final Rect f2653b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2654c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2655d;

    public s1(int i7, int i8) {
        super(i7, i8);
        this.f2653b = new Rect();
        this.f2654c = true;
        this.f2655d = false;
    }

    public s1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2653b = new Rect();
        this.f2654c = true;
        this.f2655d = false;
    }

    public s1(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f2653b = new Rect();
        this.f2654c = true;
        this.f2655d = false;
    }

    public s1(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f2653b = new Rect();
        this.f2654c = true;
        this.f2655d = false;
    }

    public s1(s1 s1Var) {
        super((ViewGroup.LayoutParams) s1Var);
        this.f2653b = new Rect();
        this.f2654c = true;
        this.f2655d = false;
    }
}
