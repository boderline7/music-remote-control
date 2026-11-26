package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class w {
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;

    @Deprecated
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_AUTO_BATTERY = 3;

    @Deprecated
    public static final int MODE_NIGHT_AUTO_TIME = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;

    /* renamed from: a, reason: collision with root package name */
    public static final q0 f866a = new q0(new r0());

    /* renamed from: b, reason: collision with root package name */
    public static int f867b = -100;

    /* renamed from: c, reason: collision with root package name */
    public static l0.g f868c = null;

    /* renamed from: d, reason: collision with root package name */
    public static l0.g f869d = null;

    /* renamed from: e, reason: collision with root package name */
    public static Boolean f870e = null;

    /* renamed from: f, reason: collision with root package name */
    public static boolean f871f = false;

    /* renamed from: g, reason: collision with root package name */
    public static final q.c f872g = new q.c(0);

    /* renamed from: h, reason: collision with root package name */
    public static final Object f873h = new Object();

    /* renamed from: i, reason: collision with root package name */
    public static final Object f874i = new Object();

    public static boolean b(Context context) {
        if (f870e == null) {
            try {
                int i7 = AppLocalesMetadataHolderService.f669a;
                Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class<?>) AppLocalesMetadataHolderService.class), o0.a() | 128).metaData;
                if (bundle != null) {
                    f870e = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                f870e = Boolean.FALSE;
            }
        }
        return f870e.booleanValue();
    }

    public static void e(w wVar) {
        synchronized (f873h) {
            try {
                Iterator it = f872g.iterator();
                while (it.hasNext()) {
                    w wVar2 = (w) ((WeakReference) it.next()).get();
                    if (wVar2 == wVar || wVar2 == null) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void j(int i7) {
        if ((i7 == -1 || i7 == 0 || i7 == 1 || i7 == 2 || i7 == 3) && f867b != i7) {
            f867b = i7;
            synchronized (f873h) {
                try {
                    Iterator it = f872g.iterator();
                    while (it.hasNext()) {
                        w wVar = (w) ((WeakReference) it.next()).get();
                        if (wVar != null) {
                            ((j0) wVar).n(true, true);
                        }
                    }
                } finally {
                }
            }
        }
    }

    public abstract void a();

    public abstract void c();

    public abstract void d();

    public abstract boolean f(int i7);

    public abstract void g(int i7);

    public abstract void h(View view);

    public abstract void i(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void k(CharSequence charSequence);

    public abstract j.c m(j.b bVar);
}
