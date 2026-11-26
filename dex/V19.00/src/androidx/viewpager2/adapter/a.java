package androidx.viewpager2.adapter;

import android.view.View;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a implements View.OnLayoutChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FrameLayout f2852a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f2853b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g f2854c;

    public a(g gVar, FrameLayout frameLayout, h hVar) {
        this.f2854c = gVar;
        this.f2852a = frameLayout;
        this.f2853b = hVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        FrameLayout frameLayout = this.f2852a;
        if (frameLayout.getParent() != null) {
            frameLayout.removeOnLayoutChangeListener(this);
            this.f2854c.placeFragmentInViewHolder(this.f2853b);
        }
    }
}
