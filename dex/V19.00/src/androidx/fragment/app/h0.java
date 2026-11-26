package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h0 extends AnimationSet implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final ViewGroup f1969a;

    /* renamed from: b, reason: collision with root package name */
    public final View f1970b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1971c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1972d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1973e;

    public h0(Animation animation, ViewGroup viewGroup, View view) {
        super(false);
        this.f1973e = true;
        this.f1969a = viewGroup;
        this.f1970b = view;
        addAnimation(animation);
        viewGroup.post(this);
    }

    @Override // android.view.animation.AnimationSet, android.view.animation.Animation
    public final boolean getTransformation(long j7, Transformation transformation) {
        this.f1973e = true;
        if (this.f1971c) {
            return !this.f1972d;
        }
        if (!super.getTransformation(j7, transformation)) {
            this.f1971c = true;
            p0.e0.a(this.f1969a, this);
        }
        return true;
    }

    @Override // android.view.animation.Animation
    public final boolean getTransformation(long j7, Transformation transformation, float f7) {
        this.f1973e = true;
        if (this.f1971c) {
            return !this.f1972d;
        }
        if (!super.getTransformation(j7, transformation, f7)) {
            this.f1971c = true;
            p0.e0.a(this.f1969a, this);
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z6 = this.f1971c;
        ViewGroup viewGroup = this.f1969a;
        if (z6 || !this.f1973e) {
            viewGroup.endViewTransition(this.f1970b);
            this.f1972d = true;
        } else {
            this.f1973e = false;
            viewGroup.post(this);
        }
    }
}
