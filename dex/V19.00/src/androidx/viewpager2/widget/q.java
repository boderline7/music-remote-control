package androidx.viewpager2.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.r1;
import com.google.android.material.navigation.NavigationBarItemView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2920a;

    /* renamed from: b, reason: collision with root package name */
    public int f2921b;

    /* renamed from: c, reason: collision with root package name */
    public final View f2922c;

    public q(int i7, o oVar) {
        this.f2920a = 0;
        this.f2921b = i7;
        this.f2922c = oVar;
    }

    public q(NavigationBarItemView navigationBarItemView, int i7) {
        this.f2920a = 1;
        this.f2922c = navigationBarItemView;
        this.f2921b = i7;
    }

    public q(com.google.android.material.slider.c cVar) {
        this.f2920a = 2;
        this.f2922c = cVar;
        this.f2921b = -1;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(com.google.android.material.slider.c cVar, int i7) {
        this(cVar);
        this.f2920a = 2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        r1 r1Var;
        int i7 = this.f2920a;
        View view = this.f2922c;
        switch (i7) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) view;
                int i8 = this.f2921b;
                if (!recyclerView.f2339v && (r1Var = recyclerView.f2321m) != null) {
                    r1Var.C0(recyclerView, i8);
                    break;
                }
                break;
            case 1:
                int i9 = this.f2921b;
                int[] iArr = NavigationBarItemView.G;
                ((NavigationBarItemView) view).i(i9);
                break;
            default:
                ((com.google.android.material.slider.c) view).f4170g.x(this.f2921b, 4);
                break;
        }
    }
}
