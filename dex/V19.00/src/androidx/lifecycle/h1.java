package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h1 extends m1 {
    public static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";

    /* renamed from: c, reason: collision with root package name */
    public static h1 f2186c;

    /* renamed from: b, reason: collision with root package name */
    public final Application f2187b;
    public static final g1 Companion = new g1();
    public static final e1.b APPLICATION_KEY = f1.INSTANCE;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public h1(Application application) {
        this(application, 0);
        i5.a.g("application", application);
    }

    public h1(Application application, int i7) {
        this.f2187b = application;
    }

    @Override // androidx.lifecycle.m1, androidx.lifecycle.j1
    public final e1 a(Class cls) {
        Application application = this.f2187b;
        if (application != null) {
            return c(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }

    @Override // androidx.lifecycle.j1
    public final e1 b(Class cls, e1.d dVar) {
        if (this.f2187b != null) {
            return a(cls);
        }
        Application application = (Application) dVar.a(APPLICATION_KEY);
        if (application != null) {
            return c(cls, application);
        }
        if (a.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }
        return super.a(cls);
    }

    public final e1 c(Class cls, Application application) {
        if (!a.class.isAssignableFrom(cls)) {
            return super.a(cls);
        }
        try {
            e1 e1Var = (e1) cls.getConstructor(Application.class).newInstance(application);
            i5.a.f("{\n                try {\n…          }\n            }", e1Var);
            return e1Var;
        } catch (IllegalAccessException e7) {
            throw new RuntimeException("Cannot create an instance of " + cls, e7);
        } catch (InstantiationException e8) {
            throw new RuntimeException("Cannot create an instance of " + cls, e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("Cannot create an instance of " + cls, e9);
        } catch (InvocationTargetException e10) {
            throw new RuntimeException("Cannot create an instance of " + cls, e10);
        }
    }
}
