package r2;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Property;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import q.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final k f8509a = new k();

    /* renamed from: b, reason: collision with root package name */
    public final k f8510b = new k();

    public static e a(Context context, TypedArray typedArray, int i7) {
        int resourceId;
        if (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0) {
            return null;
        }
        return b(context, resourceId);
    }

    public static e b(Context context, int i7) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i7);
            if (loadAnimator instanceof AnimatorSet) {
                return c(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return c(arrayList);
        } catch (Exception unused) {
            Integer.toHexString(i7);
            return null;
        }
    }

    public static e c(ArrayList arrayList) {
        e eVar = new e();
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Animator animator = (Animator) arrayList.get(i7);
            if (!(animator instanceof ObjectAnimator)) {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            eVar.h(objectAnimator.getPropertyName(), objectAnimator.getValues());
            String propertyName = objectAnimator.getPropertyName();
            long startDelay = objectAnimator.getStartDelay();
            long duration = objectAnimator.getDuration();
            TimeInterpolator interpolator = objectAnimator.getInterpolator();
            if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                interpolator = a.FAST_OUT_SLOW_IN_INTERPOLATOR;
            } else if (interpolator instanceof AccelerateInterpolator) {
                interpolator = a.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            } else if (interpolator instanceof DecelerateInterpolator) {
                interpolator = a.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
            }
            f fVar = new f();
            fVar.f8514d = 0;
            fVar.f8515e = 1;
            fVar.f8511a = startDelay;
            fVar.f8512b = duration;
            fVar.f8513c = interpolator;
            fVar.f8514d = objectAnimator.getRepeatCount();
            fVar.f8515e = objectAnimator.getRepeatMode();
            eVar.f8509a.put(propertyName, fVar);
        }
        return eVar;
    }

    public final ObjectAnimator d(String str, ExtendedFloatingActionButton extendedFloatingActionButton, Property property) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(extendedFloatingActionButton, e(str));
        ofPropertyValuesHolder.setProperty(property);
        f(str).a(ofPropertyValuesHolder);
        return ofPropertyValuesHolder;
    }

    public final PropertyValuesHolder[] e(String str) {
        if (!g(str)) {
            throw new IllegalArgumentException();
        }
        PropertyValuesHolder[] propertyValuesHolderArr = (PropertyValuesHolder[]) this.f8510b.getOrDefault(str, null);
        PropertyValuesHolder[] propertyValuesHolderArr2 = new PropertyValuesHolder[propertyValuesHolderArr.length];
        for (int i7 = 0; i7 < propertyValuesHolderArr.length; i7++) {
            propertyValuesHolderArr2[i7] = propertyValuesHolderArr[i7].clone();
        }
        return propertyValuesHolderArr2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return this.f8509a.equals(((e) obj).f8509a);
        }
        return false;
    }

    public final f f(String str) {
        k kVar = this.f8509a;
        if (kVar.getOrDefault(str, null) != null) {
            return (f) kVar.getOrDefault(str, null);
        }
        throw new IllegalArgumentException();
    }

    public final boolean g(String str) {
        return this.f8510b.getOrDefault(str, null) != null;
    }

    public final void h(String str, PropertyValuesHolder[] propertyValuesHolderArr) {
        this.f8510b.put(str, propertyValuesHolderArr);
    }

    public final int hashCode() {
        return this.f8509a.hashCode();
    }

    public final String toString() {
        return "\n" + e.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f8509a + "}\n";
    }
}
