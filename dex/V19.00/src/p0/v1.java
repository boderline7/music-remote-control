package p0;

import android.graphics.Insets;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v1 extends WindowInsetsAnimation$Callback {

    /* renamed from: a, reason: collision with root package name */
    public final q1 f8193a;

    /* renamed from: b, reason: collision with root package name */
    public List f8194b;

    /* renamed from: c, reason: collision with root package name */
    public ArrayList f8195c;

    /* renamed from: d, reason: collision with root package name */
    public final HashMap f8196d;

    public v1(w2.d dVar) {
        super(dVar.f8173b);
        this.f8196d = new HashMap();
        this.f8193a = dVar;
    }

    public final y1 a(WindowInsetsAnimation windowInsetsAnimation) {
        y1 y1Var = (y1) this.f8196d.get(windowInsetsAnimation);
        if (y1Var == null) {
            y1Var = new y1(0, null, 0L);
            if (Build.VERSION.SDK_INT >= 30) {
                y1Var.f8207a = new w1(windowInsetsAnimation);
            }
            this.f8196d.put(windowInsetsAnimation, y1Var);
        }
        return y1Var;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        q1 q1Var = this.f8193a;
        a(windowInsetsAnimation);
        ((w2.d) q1Var).f9463c.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
        this.f8196d.remove(windowInsetsAnimation);
    }

    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        q1 q1Var = this.f8193a;
        a(windowInsetsAnimation);
        w2.d dVar = (w2.d) q1Var;
        View view = dVar.f9463c;
        int[] iArr = dVar.f9466f;
        view.getLocationOnScreen(iArr);
        dVar.f9464d = iArr[1];
    }

    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
        float fraction;
        ArrayList arrayList = this.f8195c;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList(list.size());
            this.f8195c = arrayList2;
            this.f8194b = Collections.unmodifiableList(arrayList2);
        } else {
            arrayList.clear();
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            WindowInsetsAnimation l7 = com.google.android.material.internal.l0.l(list.get(size));
            y1 a7 = a(l7);
            fraction = l7.getFraction();
            a7.f8207a.d(fraction);
            this.f8195c.add(a7);
        }
        q1 q1Var = this.f8193a;
        l2 h7 = l2.h(null, windowInsets);
        q1Var.a(h7, this.f8194b);
        return h7.g();
    }

    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        Insets lowerBound;
        Insets upperBound;
        q1 q1Var = this.f8193a;
        a(windowInsetsAnimation);
        lowerBound = bounds.getLowerBound();
        i0.c c7 = i0.c.c(lowerBound);
        upperBound = bounds.getUpperBound();
        i0.c c8 = i0.c.c(upperBound);
        w2.d dVar = (w2.d) q1Var;
        View view = dVar.f9463c;
        int[] iArr = dVar.f9466f;
        view.getLocationOnScreen(iArr);
        int i7 = dVar.f9464d - iArr[1];
        dVar.f9465e = i7;
        view.setTranslationY(i7);
        com.google.android.material.internal.l0.o();
        return com.google.android.material.internal.l0.j(c7.d(), c8.d());
    }
}
