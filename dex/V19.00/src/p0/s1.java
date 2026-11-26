package p0;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f8186a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y1 f8187b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i6.i f8188c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ValueAnimator f8189d;

    public s1(View view, y1 y1Var, i6.i iVar, ValueAnimator valueAnimator) {
        this.f8186a = view;
        this.f8187b = y1Var;
        this.f8188c = iVar;
        this.f8189d = valueAnimator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        u1.h(this.f8186a, this.f8187b, this.f8188c);
        this.f8189d.start();
    }
}
