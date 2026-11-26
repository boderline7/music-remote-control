package i6;

import a6.k0;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.android.AndroidComponentsImpl;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: q, reason: collision with root package name */
    public static volatile e f6822q;

    /* renamed from: r, reason: collision with root package name */
    public static final f f6823r;

    /* renamed from: s, reason: collision with root package name */
    public static final HashMap f6824s;

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f6825a;

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f6826b;

    /* renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f6827c;

    /* renamed from: d, reason: collision with root package name */
    public final b f6828d = new b();

    /* renamed from: e, reason: collision with root package name */
    public final k0 f6829e;

    /* renamed from: f, reason: collision with root package name */
    public final g f6830f;

    /* renamed from: g, reason: collision with root package name */
    public final a f6831g;

    /* renamed from: h, reason: collision with root package name */
    public final android.support.v4.media.i f6832h;

    /* renamed from: i, reason: collision with root package name */
    public final o f6833i;

    /* renamed from: j, reason: collision with root package name */
    public final ExecutorService f6834j;

    /* renamed from: k, reason: collision with root package name */
    public final boolean f6835k;

    /* renamed from: l, reason: collision with root package name */
    public final boolean f6836l;

    /* renamed from: m, reason: collision with root package name */
    public final boolean f6837m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f6838n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f6839o;

    /* renamed from: p, reason: collision with root package name */
    public final h f6840p;

    static {
        f fVar = new f();
        fVar.f6842a = f.f6841b;
        f6823r = fVar;
        f6824s = new HashMap();
    }

    public e() {
        f fVar = f6823r;
        fVar.getClass();
        AndroidComponentsImpl androidComponentsImpl = AndroidComponentsImpl.f8076c;
        this.f6840p = androidComponentsImpl != null ? androidComponentsImpl.f8077a : new k0();
        this.f6825a = new HashMap();
        this.f6826b = new HashMap();
        this.f6827c = new ConcurrentHashMap();
        k0 k0Var = androidComponentsImpl != null ? androidComponentsImpl.f8078b : null;
        this.f6829e = k0Var;
        this.f6830f = k0Var != null ? new g(this, Looper.getMainLooper()) : null;
        this.f6831g = new a(this);
        this.f6832h = new android.support.v4.media.i(this);
        this.f6833i = new o();
        this.f6835k = true;
        this.f6836l = true;
        this.f6837m = true;
        this.f6838n = true;
        this.f6839o = true;
        this.f6834j = fVar.f6842a;
    }

    public static void a(ArrayList arrayList, Class[] clsArr) {
        for (Class cls : clsArr) {
            if (!arrayList.contains(cls)) {
                arrayList.add(cls);
                a(arrayList, cls.getInterfaces());
            }
        }
    }

    public static e b() {
        e eVar = f6822q;
        if (eVar == null) {
            synchronized (e.class) {
                try {
                    eVar = f6822q;
                    if (eVar == null) {
                        eVar = new e();
                        f6822q = eVar;
                    }
                } finally {
                }
            }
        }
        return eVar;
    }

    public final void c(j jVar) {
        Object obj = jVar.f6851a;
        p pVar = jVar.f6852b;
        jVar.f6851a = null;
        jVar.f6852b = null;
        jVar.f6853c = null;
        ArrayList arrayList = j.f6850d;
        synchronized (arrayList) {
            if (arrayList.size() < 10000) {
                arrayList.add(jVar);
            }
        }
        if (pVar.f6873c) {
            d(obj, pVar);
        }
    }

    public final void d(Object obj, p pVar) {
        try {
            pVar.f6872b.f6857a.invoke(pVar.f6871a, obj);
        } catch (IllegalAccessException e7) {
            throw new IllegalStateException("Unexpected exception", e7);
        } catch (InvocationTargetException e8) {
            Throwable cause = e8.getCause();
            boolean z6 = obj instanceof l;
            boolean z7 = this.f6835k;
            h hVar = this.f6840p;
            if (!z6) {
                if (z7) {
                    hVar.f(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + pVar.f6871a.getClass(), cause);
                }
                if (this.f6837m) {
                    f(new l(cause, obj, pVar.f6871a));
                    return;
                }
                return;
            }
            if (z7) {
                Level level = Level.SEVERE;
                hVar.f(level, "SubscriberExceptionEvent subscriber " + pVar.f6871a.getClass() + " threw an exception", cause);
                l lVar = (l) obj;
                hVar.f(level, "Initial event " + lVar.f6855b + " caused exception in " + lVar.f6856c, lVar.f6854a);
            }
        }
    }

    public final synchronized boolean e(Object obj) {
        return this.f6826b.containsKey(obj);
    }

    public final void f(Object obj) {
        d dVar = (d) this.f6828d.get();
        ArrayList arrayList = dVar.f6818a;
        arrayList.add(obj);
        if (dVar.f6819b) {
            return;
        }
        dVar.f6820c = this.f6829e == null || Looper.getMainLooper() == Looper.myLooper();
        dVar.f6819b = true;
        while (!arrayList.isEmpty()) {
            try {
                g(arrayList.remove(0), dVar);
            } finally {
                dVar.f6819b = false;
                dVar.f6820c = false;
            }
        }
    }

    public final void g(Object obj, d dVar) {
        boolean h7;
        List list;
        Class<?> cls = obj.getClass();
        int i7 = 0;
        if (this.f6839o) {
            HashMap hashMap = f6824s;
            synchronized (hashMap) {
                try {
                    List list2 = (List) hashMap.get(cls);
                    list = list2;
                    if (list2 == null) {
                        ArrayList arrayList = new ArrayList();
                        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                            arrayList.add(cls2);
                            a(arrayList, cls2.getInterfaces());
                        }
                        f6824s.put(cls, arrayList);
                        list = arrayList;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            int size = list.size();
            h7 = false;
            for (int i8 = 0; i8 < size; i8++) {
                h7 |= h(obj, dVar, (Class) list.get(i8));
            }
        } else {
            h7 = h(obj, dVar, cls);
        }
        if (h7) {
            return;
        }
        if (this.f6836l) {
            this.f6840p.b(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.f6838n || cls == i.class || cls == l.class) {
            return;
        }
        f(new i(i7, this, obj));
    }

    public final boolean h(Object obj, d dVar, Class cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) this.f6825a.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            p pVar = (p) it.next();
            dVar.f6821d = obj;
            i(pVar, obj, dVar.f6820c);
        }
        return true;
    }

    public final void i(p pVar, Object obj, boolean z6) {
        int i7 = c.f6817a[pVar.f6872b.f6858b.ordinal()];
        if (i7 == 1) {
            d(obj, pVar);
            return;
        }
        g gVar = this.f6830f;
        if (i7 == 2) {
            if (z6) {
                d(obj, pVar);
                return;
            } else {
                gVar.a(obj, pVar);
                return;
            }
        }
        if (i7 == 3) {
            if (gVar != null) {
                gVar.a(obj, pVar);
                return;
            } else {
                d(obj, pVar);
                return;
            }
        }
        if (i7 != 4) {
            if (i7 != 5) {
                throw new IllegalStateException("Unknown thread mode: " + pVar.f6872b.f6858b);
            }
            android.support.v4.media.i iVar = this.f6832h;
            iVar.getClass();
            ((i) iVar.f545b).f(j.a(obj, pVar));
            ((e) iVar.f546c).f6834j.execute(iVar);
            return;
        }
        if (!z6) {
            d(obj, pVar);
            return;
        }
        a aVar = this.f6831g;
        aVar.getClass();
        j a7 = j.a(obj, pVar);
        synchronized (aVar) {
            try {
                aVar.f6814a.f(a7);
                if (!aVar.f6816c) {
                    aVar.f6816c = true;
                    aVar.f6815b.f6834j.execute(aVar);
                }
            } finally {
            }
        }
    }

    public final void j(Object obj) {
        int i7;
        n nVar;
        Method[] methods;
        k kVar;
        if (i5.a.n()) {
            try {
                AndroidComponentsImpl androidComponentsImpl = AndroidComponentsImpl.f8076c;
            } catch (ClassNotFoundException unused) {
                throw new RuntimeException("It looks like you are using EventBus on Android, make sure to add the \"eventbus\" Android library to your dependencies.");
            }
        }
        Class<?> cls = obj.getClass();
        this.f6833i.getClass();
        ConcurrentHashMap concurrentHashMap = o.f6869a;
        List list = (List) concurrentHashMap.get(cls);
        List list2 = list;
        if (list == null) {
            synchronized (o.f6870b) {
                i7 = 0;
                int i8 = 0;
                while (true) {
                    if (i8 >= 4) {
                        nVar = new n();
                        break;
                    }
                    try {
                        n[] nVarArr = o.f6870b;
                        nVar = nVarArr[i8];
                        if (nVar != null) {
                            nVarArr[i8] = null;
                        } else {
                            i8++;
                        }
                    } finally {
                    }
                }
            }
            nVar.f6867e = cls;
            nVar.f6868f = false;
            while (true) {
                Class cls2 = nVar.f6867e;
                if (cls2 != null) {
                    int i9 = 1;
                    try {
                        try {
                            methods = cls2.getDeclaredMethods();
                        } catch (LinkageError e7) {
                            throw new EventBusException(a.a.g("Could not inspect methods of ".concat(nVar.f6867e.getName()), ". Please make this class visible to EventBus annotation processor to avoid reflection."), e7);
                        }
                    } catch (Throwable unused2) {
                        methods = nVar.f6867e.getMethods();
                        nVar.f6868f = true;
                    }
                    int length = methods.length;
                    int i10 = 0;
                    while (i10 < length) {
                        Method method = methods[i10];
                        int modifiers = method.getModifiers();
                        if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length == i9 && (kVar = (k) method.getAnnotation(k.class)) != null) {
                                Class<?> cls3 = parameterTypes[0];
                                HashMap hashMap = nVar.f6864b;
                                Object put = hashMap.put(cls3, method);
                                if (put != null) {
                                    if (put instanceof Method) {
                                        if (!nVar.a((Method) put, cls3)) {
                                            throw new IllegalStateException();
                                        }
                                        hashMap.put(cls3, nVar);
                                    }
                                    if (!nVar.a(method, cls3)) {
                                    }
                                }
                                nVar.f6863a.add(new m(method, cls3, kVar.threadMode(), kVar.priority(), kVar.sticky()));
                            }
                        }
                        i10++;
                        i9 = 1;
                    }
                    if (nVar.f6868f) {
                        nVar.f6867e = null;
                    } else {
                        Class superclass = nVar.f6867e.getSuperclass();
                        nVar.f6867e = superclass;
                        String name = superclass.getName();
                        if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                            nVar.f6867e = null;
                        }
                    }
                } else {
                    ArrayList arrayList = new ArrayList(nVar.f6863a);
                    nVar.f6863a.clear();
                    nVar.f6864b.clear();
                    nVar.f6865c.clear();
                    nVar.f6866d.setLength(0);
                    nVar.f6867e = null;
                    nVar.f6868f = false;
                    synchronized (o.f6870b) {
                        while (true) {
                            if (i7 >= 4) {
                                break;
                            }
                            try {
                                n[] nVarArr2 = o.f6870b;
                                if (nVarArr2[i7] == null) {
                                    nVarArr2[i7] = nVar;
                                    break;
                                }
                                i7++;
                            } finally {
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
                    }
                    concurrentHashMap.put(cls, arrayList);
                    list2 = arrayList;
                }
            }
        }
        synchronized (this) {
            try {
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    k(obj, (m) it.next());
                }
            } finally {
            }
        }
    }

    public final void k(Object obj, m mVar) {
        Object value;
        Class cls = mVar.f6859c;
        p pVar = new p(obj, mVar);
        HashMap hashMap = this.f6825a;
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) hashMap.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            hashMap.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(pVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i7 = 0; i7 <= size; i7++) {
            if (i7 != size) {
                if (mVar.f6860d <= ((p) copyOnWriteArrayList.get(i7)).f6872b.f6860d) {
                }
            }
            copyOnWriteArrayList.add(i7, pVar);
            break;
        }
        HashMap hashMap2 = this.f6826b;
        List list = (List) hashMap2.get(obj);
        if (list == null) {
            list = new ArrayList();
            hashMap2.put(obj, list);
        }
        list.add(cls);
        if (mVar.f6861e) {
            ConcurrentHashMap concurrentHashMap = this.f6827c;
            k0 k0Var = this.f6829e;
            if (!this.f6839o) {
                Object obj2 = concurrentHashMap.get(cls);
                if (obj2 != null) {
                    i(pVar, obj2, k0Var == null || Looper.getMainLooper() == Looper.myLooper());
                    return;
                }
                return;
            }
            for (Map.Entry entry : concurrentHashMap.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey()) && (value = entry.getValue()) != null) {
                    i(pVar, value, k0Var == null || Looper.getMainLooper() == Looper.myLooper());
                }
            }
        }
    }

    public final synchronized void l(Object obj) {
        try {
            List list = (List) this.f6826b.get(obj);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    List list2 = (List) this.f6825a.get((Class) it.next());
                    if (list2 != null) {
                        int size = list2.size();
                        int i7 = 0;
                        while (i7 < size) {
                            p pVar = (p) list2.get(i7);
                            if (pVar.f6871a == obj) {
                                pVar.f6873c = false;
                                list2.remove(i7);
                                i7--;
                                size--;
                            }
                            i7++;
                        }
                    }
                }
                this.f6826b.remove(obj);
            } else {
                this.f6840p.b(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final String toString() {
        return "EventBus[indexCount=0, eventInheritance=" + this.f6839o + "]";
    }
}
