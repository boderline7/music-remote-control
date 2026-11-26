package androidx.fragment.app;

import android.transition.Transition;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m1 implements Transition.TransitionListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f2017a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2018b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2019c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2020d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2021e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2022f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ o1 f2023g;

    public m1(o1 o1Var, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.f2023g = o1Var;
        this.f2017a = obj;
        this.f2018b = arrayList;
        this.f2019c = obj2;
        this.f2020d = arrayList2;
        this.f2021e = obj3;
        this.f2022f = arrayList3;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
        o1 o1Var = this.f2023g;
        Object obj = this.f2017a;
        if (obj != null) {
            o1Var.v(obj, this.f2018b, null);
        }
        Object obj2 = this.f2019c;
        if (obj2 != null) {
            o1Var.v(obj2, this.f2020d, null);
        }
        Object obj3 = this.f2021e;
        if (obj3 != null) {
            o1Var.v(obj3, this.f2022f, null);
        }
    }
}
