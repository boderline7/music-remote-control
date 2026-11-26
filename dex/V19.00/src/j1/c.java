package j1;

import android.os.Bundle;
import androidx.lifecycle.e1;
import androidx.lifecycle.m;
import androidx.lifecycle.o1;
import androidx.lifecycle.p1;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements v {
    public static final String CLASSES_KEY = "classes_to_restore";
    public static final String COMPONENT_KEY = "androidx.savedstate.Restarter";
    public static final b Companion = new b();

    /* renamed from: a, reason: collision with root package name */
    public final j f6941a;

    public c(j jVar) {
        i5.a.g("owner", jVar);
        this.f6941a = jVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        if (qVar != q.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        xVar.getLifecycle().b(this);
        j jVar = this.f6941a;
        Bundle a7 = jVar.getSavedStateRegistry().a(COMPONENT_KEY);
        if (a7 == null) {
            return;
        }
        ArrayList<String> stringArrayList = a7.getStringArrayList(CLASSES_KEY);
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> asSubclass = Class.forName(str, false, c.class.getClassLoader()).asSubclass(e.class);
                i5.a.f("{\n                Class.…class.java)\n            }", asSubclass);
                try {
                    Constructor declaredConstructor = asSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object newInstance = declaredConstructor.newInstance(new Object[0]);
                        i5.a.f("{\n                constr…wInstance()\n            }", newInstance);
                        if (!(jVar instanceof p1)) {
                            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
                        }
                        o1 viewModelStore = ((p1) jVar).getViewModelStore();
                        g savedStateRegistry = jVar.getSavedStateRegistry();
                        viewModelStore.getClass();
                        LinkedHashMap linkedHashMap = viewModelStore.f2200a;
                        Iterator it = new HashSet(linkedHashMap.keySet()).iterator();
                        while (it.hasNext()) {
                            String str2 = (String) it.next();
                            i5.a.g("key", str2);
                            e1 e1Var = (e1) linkedHashMap.get(str2);
                            i5.a.d(e1Var);
                            m.a(e1Var, savedStateRegistry, jVar.getLifecycle());
                        }
                        if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                            savedStateRegistry.d();
                        }
                    } catch (Exception e7) {
                        throw new RuntimeException(a.a.B("Failed to instantiate ", str), e7);
                    }
                } catch (NoSuchMethodException e8) {
                    throw new IllegalStateException("Class " + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e8);
                }
            } catch (ClassNotFoundException e9) {
                throw new RuntimeException(a.a.h("Class ", str, " wasn't found"), e9);
            }
        }
    }
}
