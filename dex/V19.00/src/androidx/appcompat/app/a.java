package androidx.appcompat.app;

import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class a extends ViewGroup.MarginLayoutParams {

    /* renamed from: a, reason: collision with root package name */
    public int f670a;

    public a(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f670a = 0;
    }

    public a(a aVar) {
        super((ViewGroup.MarginLayoutParams) aVar);
        this.f670a = 0;
        this.f670a = aVar.f670a;
    }
}
