package com.sagittarius.v6.b;

import android.os.Build;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class b {
    private static int a() {
        try {
            return Build.VERSION.PREVIEW_SDK_INT;
        } catch (Throwable th) {
            return 1;
        }
    }

    public static void a(ClassLoader classLoader, File file) {
        synchronized (b.class) {
            try {
            } catch (Throwable th) {
                throw th;
            }
            if (file.exists()) {
                if ((Build.VERSION.SDK_INT != 25 || a() == 0) && Build.VERSION.SDK_INT <= 25) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        try {
                            d.a(classLoader, file);
                        } catch (Throwable th2) {
                            s.a(s.a(classLoader, c.f6107a[0]).get(classLoader), c.f6107a[1], new File[]{file});
                        }
                    } else if (Build.VERSION.SDK_INT >= 14) {
                        s.a(s.a(classLoader, c.f6107a[0]).get(classLoader), c.f6107a[1], new File[]{file});
                    }
                    return;
                }
                try {
                    e.a(classLoader, file);
                    return;
                } catch (Throwable th3) {
                    d.a(classLoader, file);
                }
                throw th;
            }
        }
    }
}
