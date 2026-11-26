package n1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.i3;
import java.util.HashMap;
import p0.d1;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends r {
    public static final i3 A;
    public static final i3 B;
    public static final i3 C;

    /* renamed from: x, reason: collision with root package name */
    public static final String[] f7604x = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};

    /* renamed from: y, reason: collision with root package name */
    public static final i3 f7605y;

    /* renamed from: z, reason: collision with root package name */
    public static final i3 f7606z;

    static {
        new b(0);
        f7605y = new i3(PointF.class, "topLeft", 1);
        f7606z = new i3(PointF.class, "bottomRight", 2);
        A = new i3(PointF.class, "bottomRight", 3);
        B = new i3(PointF.class, "topLeft", 4);
        C = new i3(PointF.class, "position", 5);
    }

    public static void H(y yVar) {
        View view = yVar.f7686b;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (!o0.c(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap hashMap = yVar.f7685a;
        hashMap.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        hashMap.put("android:changeBounds:parent", yVar.f7686b.getParent());
    }

    @Override // n1.r
    public final void d(y yVar) {
        H(yVar);
    }

    @Override // n1.r
    public final void g(y yVar) {
        H(yVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // n1.r
    public final Animator k(ViewGroup viewGroup, y yVar, y yVar2) {
        int i7;
        f fVar;
        ObjectAnimator ofObject;
        if (yVar == null || yVar2 == null) {
            return null;
        }
        HashMap hashMap = yVar.f7685a;
        HashMap hashMap2 = yVar2.f7685a;
        ViewGroup viewGroup2 = (ViewGroup) hashMap.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) hashMap2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        Rect rect = (Rect) hashMap.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) hashMap2.get("android:changeBounds:bounds");
        int i8 = rect.left;
        int i9 = rect2.left;
        int i10 = rect.top;
        int i11 = rect2.top;
        int i12 = rect.right;
        int i13 = rect2.right;
        int i14 = rect.bottom;
        int i15 = rect2.bottom;
        int i16 = i12 - i8;
        int i17 = i14 - i10;
        int i18 = i13 - i9;
        int i19 = i15 - i11;
        Rect rect3 = (Rect) hashMap.get("android:changeBounds:clip");
        Rect rect4 = (Rect) hashMap2.get("android:changeBounds:clip");
        if ((i16 == 0 || i17 == 0) && (i18 == 0 || i19 == 0)) {
            i7 = 0;
        } else {
            i7 = (i8 == i9 && i10 == i11) ? 0 : 1;
            if (i12 != i13 || i14 != i15) {
                i7++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i7++;
        }
        int i20 = i7;
        if (i20 <= 0) {
            return null;
        }
        View view = yVar2.f7686b;
        b0.a(view, i8, i10, i12, i14);
        if (i20 != 2) {
            fVar = this;
            if (i8 == i9 && i10 == i11) {
                fVar.f7672t.getClass();
                ofObject = ObjectAnimator.ofObject(view, A, (TypeConverter) null, p3.f.t(i12, i14, i13, i15));
            } else {
                fVar.f7672t.getClass();
                ofObject = ObjectAnimator.ofObject(view, B, (TypeConverter) null, p3.f.t(i8, i10, i9, i11));
            }
        } else if (i16 == i18 && i17 == i19) {
            fVar = this;
            fVar.f7672t.getClass();
            ofObject = ObjectAnimator.ofObject(view, C, (TypeConverter) null, p3.f.t(i8, i10, i9, i11));
        } else {
            fVar = this;
            e eVar = new e();
            eVar.f7599e = view;
            fVar.f7672t.getClass();
            ObjectAnimator ofObject2 = ObjectAnimator.ofObject(eVar, f7605y, (TypeConverter) null, p3.f.t(i8, i10, i9, i11));
            fVar.f7672t.getClass();
            ObjectAnimator ofObject3 = ObjectAnimator.ofObject(eVar, f7606z, (TypeConverter) null, p3.f.t(i12, i14, i13, i15));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofObject2, ofObject3);
            animatorSet.addListener(new c(eVar));
            ofObject = animatorSet;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            l6.f.D0(viewGroup4, true);
            fVar.a(new d(viewGroup4));
        }
        return ofObject;
    }

    @Override // n1.r
    public final String[] p() {
        return f7604x;
    }
}
