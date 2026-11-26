package androidx.appcompat.widget;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a implements p0.m1 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1184a = false;

    /* renamed from: b, reason: collision with root package name */
    public int f1185b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ActionBarContextView f1186c;

    public a(ActionBarContextView actionBarContextView) {
        this.f1186c = actionBarContextView;
    }

    @Override // p0.m1
    public final void a() {
        if (this.f1184a) {
            return;
        }
        ActionBarContextView actionBarContextView = this.f1186c;
        actionBarContextView.f939f = null;
        super/*android.view.View*/.setVisibility(this.f1185b);
    }

    @Override // p0.m1
    public final void b(View view) {
        this.f1184a = true;
    }

    @Override // p0.m1
    public final void c() {
        super/*android.view.View*/.setVisibility(0);
        this.f1184a = false;
    }
}
