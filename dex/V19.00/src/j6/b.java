package j6;

import a6.k0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.LinkedBlockingQueue;
import l6.c;
import l6.d;
import l6.e;
import l6.f;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static volatile int f7024a;

    /* renamed from: b, reason: collision with root package name */
    public static final d f7025b = new d();

    /* renamed from: c, reason: collision with root package name */
    public static final k0 f7026c = new k0();

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f7027d;

    /* renamed from: e, reason: collision with root package name */
    public static final String[] f7028e;

    /* renamed from: f, reason: collision with root package name */
    public static final String f7029f;

    static {
        String str;
        try {
            str = System.getProperty("slf4j.detectLoggerNameMismatch");
        } catch (SecurityException unused) {
            str = null;
        }
        f7027d = str == null ? false : str.equalsIgnoreCase("true");
        f7028e = new String[]{"1.6", "1.7"};
        f7029f = "org/slf4j/impl/StaticLoggerBinder.class";
    }

    public static final void a() {
        LinkedHashSet linkedHashSet;
        try {
            try {
                try {
                    try {
                        if (f()) {
                            linkedHashSet = null;
                        } else {
                            linkedHashSet = b();
                            i(linkedHashSet);
                        }
                        StaticLoggerBinder.getSingleton();
                        f7024a = 3;
                        h(linkedHashSet);
                    } catch (NoSuchMethodError e7) {
                        String message = e7.getMessage();
                        if (message != null && message.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                            f7024a = 2;
                            f.q0("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                            f.q0("Your binding is version 1.5.5 or earlier.");
                            f.q0("Upgrade your binding to version 1.6.x.");
                        }
                        throw e7;
                    }
                } catch (Exception e8) {
                    f7024a = 2;
                    f.r0("Failed to instantiate SLF4J LoggerFactory", e8);
                    throw new IllegalStateException("Unexpected initialization failure", e8);
                }
            } catch (NoClassDefFoundError e9) {
                String message2 = e9.getMessage();
                if (message2 == null || (!message2.contains("org/slf4j/impl/StaticLoggerBinder") && !message2.contains("org.slf4j.impl.StaticLoggerBinder"))) {
                    f7024a = 2;
                    f.r0("Failed to instantiate SLF4J LoggerFactory", e9);
                    throw e9;
                }
                f7024a = 4;
                f.q0("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                f.q0("Defaulting to no-operation (NOP) logger implementation");
                f.q0("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
            }
            g();
        } catch (Throwable th) {
            g();
            throw th;
        }
    }

    public static LinkedHashSet b() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = b.class.getClassLoader();
            String str = f7029f;
            Enumeration<URL> systemResources = classLoader == null ? ClassLoader.getSystemResources(str) : classLoader.getResources(str);
            while (systemResources.hasMoreElements()) {
                linkedHashSet.add(systemResources.nextElement());
            }
        } catch (IOException e7) {
            f.r0("Error getting resources from path", e7);
        }
        return linkedHashSet;
    }

    public static ILoggerFactory c() {
        if (f7024a == 0) {
            synchronized (b.class) {
                try {
                    if (f7024a == 0) {
                        f7024a = 1;
                        a();
                        if (f7024a == 3) {
                            j();
                        }
                    }
                } finally {
                }
            }
        }
        int i7 = f7024a;
        if (i7 == 1) {
            return f7025b;
        }
        if (i7 == 2) {
            throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        if (i7 == 3) {
            return StaticLoggerBinder.getSingleton().getLoggerFactory();
        }
        if (i7 == 4) {
            return f7026c;
        }
        throw new IllegalStateException("Unreachable code");
    }

    public static a d(Class cls) {
        int i7;
        a e7 = e(cls.getName());
        if (f7027d) {
            e eVar = f.f7468a;
            Class cls2 = null;
            if (eVar == null) {
                if (f.f7469b) {
                    eVar = null;
                } else {
                    try {
                        eVar = new e();
                    } catch (SecurityException unused) {
                        eVar = null;
                    }
                    f.f7468a = eVar;
                    f.f7469b = true;
                }
            }
            if (eVar != null) {
                Class[] classContext = eVar.getClassContext();
                String name = f.class.getName();
                int i8 = 0;
                while (i8 < classContext.length && !name.equals(classContext[i8].getName())) {
                    i8++;
                }
                if (i8 >= classContext.length || (i7 = i8 + 2) >= classContext.length) {
                    throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
                }
                cls2 = classContext[i7];
            }
            if (cls2 != null && (!cls2.isAssignableFrom(cls))) {
                f.q0(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", e7.A(), cls2.getName()));
                f.q0("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
            }
        }
        return e7;
    }

    public static a e(String str) {
        return c().c(str);
    }

    public static boolean f() {
        String str;
        try {
            str = System.getProperty("java.vendor.url");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str == null) {
            return false;
        }
        return str.toLowerCase().contains("android");
    }

    public static void g() {
        d dVar = f7025b;
        synchronized (dVar) {
            try {
                dVar.f7465a = true;
                Iterator it = new ArrayList(dVar.f7466b.values()).iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    cVar.f7459b = e(cVar.f7458a);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        LinkedBlockingQueue linkedBlockingQueue = f7025b.f7467c;
        int size = linkedBlockingQueue.size();
        ArrayList arrayList = new ArrayList(128);
        int i7 = 0;
        while (linkedBlockingQueue.drainTo(arrayList, 128) != 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                k6.a aVar = (k6.a) it2.next();
                if (aVar != null) {
                    c cVar2 = aVar.f7261a;
                    String str = cVar2.f7458a;
                    if (cVar2.f7459b == null) {
                        throw new IllegalStateException("Delegate logger cannot be null at this state.");
                    }
                    if (!(cVar2.f7459b instanceof l6.b)) {
                        if (!cVar2.f()) {
                            f.q0(str);
                        } else if (cVar2.f()) {
                            try {
                                cVar2.f7461d.invoke(cVar2.f7459b, aVar);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                            }
                        }
                    }
                }
                int i8 = i7 + 1;
                if (i7 == 0) {
                    if (aVar.f7261a.f()) {
                        f.q0("A number (" + size + ") of logging calls during the initialization phase have been intercepted and are");
                        f.q0("now being replayed. These are subject to the filtering rules of the underlying logging system.");
                        f.q0("See also http://www.slf4j.org/codes.html#replay");
                    } else if (!(aVar.f7261a.f7459b instanceof l6.b)) {
                        f.q0("The following set of substitute loggers may have been accessed");
                        f.q0("during the initialization phase. Logging calls during this");
                        f.q0("phase were not honored. However, subsequent logging calls to these");
                        f.q0("loggers will work as normally expected.");
                        f.q0("See also http://www.slf4j.org/codes.html#substituteLogger");
                    }
                }
                i7 = i8;
            }
            arrayList.clear();
        }
        d dVar2 = f7025b;
        dVar2.f7466b.clear();
        dVar2.f7467c.clear();
    }

    public static void h(LinkedHashSet linkedHashSet) {
        if (linkedHashSet == null || linkedHashSet.size() <= 1) {
            return;
        }
        f.q0("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
    }

    public static void i(LinkedHashSet linkedHashSet) {
        if (linkedHashSet.size() > 1) {
            f.q0("Class path contains multiple SLF4J bindings.");
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                f.q0("Found binding in [" + ((URL) it.next()) + "]");
            }
            f.q0("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    public static final void j() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z6 = false;
            for (String str2 : f7028e) {
                if (str.startsWith(str2)) {
                    z6 = true;
                }
            }
            if (z6) {
                return;
            }
            f.q0("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(f7028e).toString());
            f.q0("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            f.r0("Unexpected problem occured during version sanity check", th);
        }
    }
}
