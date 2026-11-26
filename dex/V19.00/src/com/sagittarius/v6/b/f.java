package com.sagittarius.v6.b;

import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f implements a {

    /* renamed from: a, reason: collision with root package name */
    private static f f6110a;

    /* renamed from: b, reason: collision with root package name */
    private static List f6111b;

    /* renamed from: c, reason: collision with root package name */
    private static List f6112c;

    /* renamed from: d, reason: collision with root package name */
    private static final String[] f6113d = null;

    static {
        b();
        f6111b = new ArrayList();
        f6112c = new ArrayList();
        f6110a = null;
    }

    public f() {
        f6111b.add(f6113d[0]);
    }

    public static f a() {
        if (f6110a == null) {
            f6110a = new f();
        }
        return f6110a;
    }

    private static void b() {
        String[] strArr = new String[1];
        f6113d = strArr;
        byte[] a7 = com.sagittarius.v6.a.a.a("JxYafTwlHh4nOyULHiY8ahoFY35qOA==");
        StringBuilder sb = new StringBuilder();
        char[] charArray = "DywSO".toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        strArr[0] = sb.toString();
    }

    @Override // com.sagittarius.v6.b.a
    public final void attachBaseContext(Context context, Application application, Application application2) {
        Iterator it = f6112c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).attachBaseContext(context, application, application2);
        }
    }

    @Override // com.sagittarius.v6.b.a
    public final void init() {
        Iterator it = f6111b.iterator();
        while (it.hasNext()) {
            try {
                f6112c.add((a) Class.forName((String) it.next()).newInstance());
            } catch (Throwable th) {
            }
        }
        Iterator it2 = f6112c.iterator();
        while (it2.hasNext()) {
            ((a) it2.next()).init();
        }
    }

    @Override // com.sagittarius.v6.b.a
    public final void onCreate(Application application, Application application2) {
        Iterator it = f6112c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).onCreate(application, application2);
        }
    }

    @Override // com.sagittarius.v6.b.a
    public final void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks, Application application, Application application2) {
        Iterator it = f6112c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).registerActivityLifecycleCallbacks(activityLifecycleCallbacks, application, application2);
        }
    }
}
