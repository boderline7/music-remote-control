package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n0 implements LayoutInflater.Factory2 {

    /* renamed from: a, reason: collision with root package name */
    public final x0 f2025a;

    public n0(x0 x0Var) {
        this.f2025a = x0Var;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        d1 f7;
        View view2;
        boolean equals = FragmentContainerView.class.getName().equals(str);
        x0 x0Var = this.f2025a;
        if (equals) {
            FragmentContainerView fragmentContainerView = new FragmentContainerView(context, attributeSet);
            fragmentContainerView.f1880d = true;
            String classAttribute = attributeSet.getClassAttribute();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b1.c.FragmentContainerView);
            if (classAttribute == null) {
                classAttribute = obtainStyledAttributes.getString(b1.c.FragmentContainerView_android_name);
            }
            String string = obtainStyledAttributes.getString(b1.c.FragmentContainerView_android_tag);
            obtainStyledAttributes.recycle();
            int id = fragmentContainerView.getId();
            z w6 = x0Var.w(id);
            if (classAttribute != null && w6 == null) {
                if (id <= 0) {
                    throw new IllegalStateException(a.a.h("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : s5.h0.FRAGMENT_ENCODE_SET));
                }
                r0 y6 = x0Var.y();
                context.getClassLoader();
                z instantiate = z.instantiate(y6.f2046a.f2117p.f2008b, classAttribute, null);
                instantiate.onInflate(context, attributeSet, (Bundle) null);
                a aVar = new a(x0Var);
                aVar.f1989p = true;
                instantiate.mContainer = fragmentContainerView;
                aVar.c(fragmentContainerView.getId(), instantiate, string, 1);
                if (aVar.f1980g) {
                    throw new IllegalStateException("This transaction is already being added to the back stack");
                }
                aVar.f1981h = false;
                aVar.f1910q.t(aVar, true);
            }
            Iterator it = x0Var.f2104c.d().iterator();
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                z zVar = d1Var.f1933c;
                if (zVar.mContainerId == fragmentContainerView.getId() && (view2 = zVar.mView) != null && view2.getParent() == null) {
                    zVar.mContainer = fragmentContainerView;
                    d1Var.a();
                }
            }
            return fragmentContainerView;
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, b1.c.Fragment);
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes2.getString(b1.c.Fragment_android_name);
        }
        int resourceId = obtainStyledAttributes2.getResourceId(b1.c.Fragment_android_id, -1);
        String string2 = obtainStyledAttributes2.getString(b1.c.Fragment_android_tag);
        obtainStyledAttributes2.recycle();
        if (attributeValue != null) {
            try {
                if (z.class.isAssignableFrom(r0.a(context.getClassLoader(), attributeValue))) {
                    int id2 = view != null ? view.getId() : 0;
                    if (id2 == -1 && resourceId == -1 && string2 == null) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                    }
                    z w7 = resourceId != -1 ? x0Var.w(resourceId) : null;
                    if (w7 == null && string2 != null) {
                        e1 e1Var = x0Var.f2104c;
                        ArrayList arrayList = e1Var.f1940a;
                        int size = arrayList.size() - 1;
                        while (true) {
                            if (size >= 0) {
                                z zVar2 = (z) arrayList.get(size);
                                if (zVar2 != null && string2.equals(zVar2.mTag)) {
                                    w7 = zVar2;
                                    break;
                                }
                                size--;
                            } else {
                                Iterator it2 = e1Var.f1941b.values().iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        w7 = null;
                                        break;
                                    }
                                    d1 d1Var2 = (d1) it2.next();
                                    if (d1Var2 != null) {
                                        z zVar3 = d1Var2.f1933c;
                                        if (string2.equals(zVar3.mTag)) {
                                            w7 = zVar3;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (w7 == null && id2 != -1) {
                        w7 = x0Var.w(id2);
                    }
                    if (w7 == null) {
                        r0 y7 = x0Var.y();
                        context.getClassLoader();
                        w7 = z.instantiate(y7.f2046a.f2117p.f2008b, attributeValue, null);
                        w7.mFromLayout = true;
                        w7.mFragmentId = resourceId != 0 ? resourceId : id2;
                        w7.mContainerId = id2;
                        w7.mTag = string2;
                        w7.mInLayout = true;
                        w7.mFragmentManager = x0Var;
                        l0 l0Var = x0Var.f2117p;
                        w7.mHost = l0Var;
                        w7.onInflate(l0Var.f2008b, attributeSet, w7.mSavedFragmentState);
                        f7 = x0Var.a(w7);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            w7.toString();
                            Integer.toHexString(resourceId);
                        }
                    } else {
                        if (w7.mInLayout) {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id2) + " with another fragment for " + attributeValue);
                        }
                        w7.mInLayout = true;
                        w7.mFragmentManager = x0Var;
                        l0 l0Var2 = x0Var.f2117p;
                        w7.mHost = l0Var2;
                        w7.onInflate(l0Var2.f2008b, attributeSet, w7.mSavedFragmentState);
                        f7 = x0Var.f(w7);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            w7.toString();
                            Integer.toHexString(resourceId);
                        }
                    }
                    w7.mContainer = (ViewGroup) view;
                    f7.j();
                    f7.i();
                    View view3 = w7.mView;
                    if (view3 == null) {
                        throw new IllegalStateException(a.a.h("Fragment ", attributeValue, " did not create a view."));
                    }
                    if (resourceId != 0) {
                        view3.setId(resourceId);
                    }
                    if (w7.mView.getTag() == null) {
                        w7.mView.setTag(string2);
                    }
                    w7.mView.addOnAttachStateChangeListener(new m0(this, f7));
                    return w7.mView;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
