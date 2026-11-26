package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q extends m1 {

    /* renamed from: s, reason: collision with root package name */
    public static TimeInterpolator f2599s;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2600g;

    /* renamed from: h, reason: collision with root package name */
    public ArrayList f2601h;

    /* renamed from: i, reason: collision with root package name */
    public ArrayList f2602i;

    /* renamed from: j, reason: collision with root package name */
    public ArrayList f2603j;

    /* renamed from: k, reason: collision with root package name */
    public ArrayList f2604k;

    /* renamed from: l, reason: collision with root package name */
    public ArrayList f2605l;

    /* renamed from: m, reason: collision with root package name */
    public ArrayList f2606m;

    /* renamed from: n, reason: collision with root package name */
    public ArrayList f2607n;

    /* renamed from: o, reason: collision with root package name */
    public ArrayList f2608o;

    /* renamed from: p, reason: collision with root package name */
    public ArrayList f2609p;

    /* renamed from: q, reason: collision with root package name */
    public ArrayList f2610q;

    /* renamed from: r, reason: collision with root package name */
    public ArrayList f2611r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((j2) arrayList.get(size)).itemView.animate().cancel();
        }
    }

    @Override // androidx.recyclerview.widget.m1
    public final boolean a(j2 j2Var, j2 j2Var2, androidx.media.a aVar, androidx.media.a aVar2) {
        int i7;
        int i8;
        int i9 = aVar.f2235a;
        int i10 = aVar.f2236b;
        if (j2Var2.shouldIgnore()) {
            int i11 = aVar.f2235a;
            i8 = aVar.f2236b;
            i7 = i11;
        } else {
            i7 = aVar2.f2235a;
            i8 = aVar2.f2236b;
        }
        if (j2Var == j2Var2) {
            return g(j2Var, i9, i10, i7, i8);
        }
        float translationX = j2Var.itemView.getTranslationX();
        float translationY = j2Var.itemView.getTranslationY();
        float alpha = j2Var.itemView.getAlpha();
        l(j2Var);
        j2Var.itemView.setTranslationX(translationX);
        j2Var.itemView.setTranslationY(translationY);
        j2Var.itemView.setAlpha(alpha);
        l(j2Var2);
        j2Var2.itemView.setTranslationX(-((int) ((i7 - i9) - translationX)));
        j2Var2.itemView.setTranslationY(-((int) ((i8 - i10) - translationY)));
        j2Var2.itemView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        ArrayList arrayList = this.f2604k;
        o oVar = new o();
        oVar.f2559a = j2Var;
        oVar.f2560b = j2Var2;
        oVar.f2561c = i9;
        oVar.f2562d = i10;
        oVar.f2563e = i7;
        oVar.f2564f = i8;
        arrayList.add(oVar);
        return true;
    }

    @Override // androidx.recyclerview.widget.m1
    public final void d(j2 j2Var) {
        View view = j2Var.itemView;
        view.animate().cancel();
        ArrayList arrayList = this.f2603j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((p) arrayList.get(size)).f2566a == j2Var) {
                view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                c(j2Var);
                arrayList.remove(size);
            }
        }
        j(j2Var, this.f2604k);
        if (this.f2601h.remove(j2Var)) {
            view.setAlpha(1.0f);
            c(j2Var);
        }
        if (this.f2602i.remove(j2Var)) {
            view.setAlpha(1.0f);
            c(j2Var);
        }
        ArrayList arrayList2 = this.f2607n;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList3 = (ArrayList) arrayList2.get(size2);
            j(j2Var, arrayList3);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList arrayList4 = this.f2606m;
        for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList5 = (ArrayList) arrayList4.get(size3);
            int size4 = arrayList5.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((p) arrayList5.get(size4)).f2566a == j2Var) {
                    view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                    c(j2Var);
                    arrayList5.remove(size4);
                    if (arrayList5.isEmpty()) {
                        arrayList4.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        ArrayList arrayList6 = this.f2605l;
        for (int size5 = arrayList6.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList6.get(size5);
            if (arrayList7.remove(j2Var)) {
                view.setAlpha(1.0f);
                c(j2Var);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
        this.f2610q.remove(j2Var);
        this.f2608o.remove(j2Var);
        this.f2611r.remove(j2Var);
        this.f2609p.remove(j2Var);
        i();
    }

    @Override // androidx.recyclerview.widget.m1
    public final void e() {
        ArrayList arrayList = this.f2603j;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            p pVar = (p) arrayList.get(size);
            View view = pVar.f2566a.itemView;
            view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
            c(pVar.f2566a);
            arrayList.remove(size);
        }
        ArrayList arrayList2 = this.f2601h;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            c((j2) arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList arrayList3 = this.f2602i;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            j2 j2Var = (j2) arrayList3.get(size3);
            j2Var.itemView.setAlpha(1.0f);
            c(j2Var);
            arrayList3.remove(size3);
        }
        ArrayList arrayList4 = this.f2604k;
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            o oVar = (o) arrayList4.get(size4);
            j2 j2Var2 = oVar.f2559a;
            if (j2Var2 != null) {
                k(oVar, j2Var2);
            }
            j2 j2Var3 = oVar.f2560b;
            if (j2Var3 != null) {
                k(oVar, j2Var3);
            }
        }
        arrayList4.clear();
        if (f()) {
            ArrayList arrayList5 = this.f2606m;
            for (int size5 = arrayList5.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList6 = (ArrayList) arrayList5.get(size5);
                for (int size6 = arrayList6.size() - 1; size6 >= 0; size6--) {
                    p pVar2 = (p) arrayList6.get(size6);
                    View view2 = pVar2.f2566a.itemView;
                    view2.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    view2.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                    c(pVar2.f2566a);
                    arrayList6.remove(size6);
                    if (arrayList6.isEmpty()) {
                        arrayList5.remove(arrayList6);
                    }
                }
            }
            ArrayList arrayList7 = this.f2605l;
            for (int size7 = arrayList7.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList8 = (ArrayList) arrayList7.get(size7);
                for (int size8 = arrayList8.size() - 1; size8 >= 0; size8--) {
                    j2 j2Var4 = (j2) arrayList8.get(size8);
                    j2Var4.itemView.setAlpha(1.0f);
                    c(j2Var4);
                    arrayList8.remove(size8);
                    if (arrayList8.isEmpty()) {
                        arrayList7.remove(arrayList8);
                    }
                }
            }
            ArrayList arrayList9 = this.f2607n;
            for (int size9 = arrayList9.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList9.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    o oVar2 = (o) arrayList10.get(size10);
                    j2 j2Var5 = oVar2.f2559a;
                    if (j2Var5 != null) {
                        k(oVar2, j2Var5);
                    }
                    j2 j2Var6 = oVar2.f2560b;
                    if (j2Var6 != null) {
                        k(oVar2, j2Var6);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList9.remove(arrayList10);
                    }
                }
            }
            h(this.f2610q);
            h(this.f2609p);
            h(this.f2608o);
            h(this.f2611r);
            ArrayList arrayList11 = this.f2539b;
            if (arrayList11.size() > 0) {
                a.a.u(arrayList11.get(0));
                throw null;
            }
            arrayList11.clear();
        }
    }

    @Override // androidx.recyclerview.widget.m1
    public final boolean f() {
        return (this.f2602i.isEmpty() && this.f2604k.isEmpty() && this.f2603j.isEmpty() && this.f2601h.isEmpty() && this.f2609p.isEmpty() && this.f2610q.isEmpty() && this.f2608o.isEmpty() && this.f2611r.isEmpty() && this.f2606m.isEmpty() && this.f2605l.isEmpty() && this.f2607n.isEmpty()) ? false : true;
    }

    public final boolean g(j2 j2Var, int i7, int i8, int i9, int i10) {
        View view = j2Var.itemView;
        int translationX = i7 + ((int) view.getTranslationX());
        int translationY = i8 + ((int) j2Var.itemView.getTranslationY());
        l(j2Var);
        int i11 = i9 - translationX;
        int i12 = i10 - translationY;
        if (i11 == 0 && i12 == 0) {
            c(j2Var);
            return false;
        }
        if (i11 != 0) {
            view.setTranslationX(-i11);
        }
        if (i12 != 0) {
            view.setTranslationY(-i12);
        }
        ArrayList arrayList = this.f2603j;
        p pVar = new p();
        pVar.f2566a = j2Var;
        pVar.f2567b = translationX;
        pVar.f2568c = translationY;
        pVar.f2569d = i9;
        pVar.f2570e = i10;
        arrayList.add(pVar);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.f2539b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            a.a.u(arrayList.get(0));
            throw null;
        }
    }

    public final void j(j2 j2Var, ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            o oVar = (o) arrayList.get(size);
            if (k(oVar, j2Var) && oVar.f2559a == null && oVar.f2560b == null) {
                arrayList.remove(oVar);
            }
        }
    }

    public final boolean k(o oVar, j2 j2Var) {
        if (oVar.f2560b == j2Var) {
            oVar.f2560b = null;
        } else {
            if (oVar.f2559a != j2Var) {
                return false;
            }
            oVar.f2559a = null;
        }
        j2Var.itemView.setAlpha(1.0f);
        j2Var.itemView.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
        j2Var.itemView.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
        c(j2Var);
        return true;
    }

    public final void l(j2 j2Var) {
        if (f2599s == null) {
            f2599s = new ValueAnimator().getInterpolator();
        }
        j2Var.itemView.animate().setInterpolator(f2599s);
        d(j2Var);
    }
}
