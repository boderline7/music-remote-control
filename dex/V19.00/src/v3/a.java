package v3;

import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transformation.ExpandableBehavior;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f9389a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f9390b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f3.a f9391c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ExpandableBehavior f9392d;

    public a(ExpandableBehavior expandableBehavior, View view, int i7, f3.a aVar) {
        this.f9392d = expandableBehavior;
        this.f9389a = view;
        this.f9390b = i7;
        this.f9391c = aVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view = this.f9389a;
        view.getViewTreeObserver().removeOnPreDrawListener(this);
        ExpandableBehavior expandableBehavior = this.f9392d;
        if (expandableBehavior.f4469a == this.f9390b) {
            Object obj = this.f9391c;
            expandableBehavior.w((View) obj, view, ((FloatingActionButton) obj).f3736o.f7560a, false);
        }
        return false;
    }
}
