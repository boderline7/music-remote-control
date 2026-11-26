package androidx.emoji2.text;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.lifecycle.s;
import androidx.lifecycle.x;
import j.a;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import k1.b;
import y0.l;
import y0.m;
import y0.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class EmojiCompatInitializer implements b {
    @Override // k1.b
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // k1.b
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Boolean b(Context context) {
        Object obj;
        v vVar = new v(new a(context, 1));
        vVar.f9974b = 1;
        if (l.f9977j == null) {
            synchronized (l.f9976i) {
                try {
                    if (l.f9977j == null) {
                        l.f9977j = new l(vVar);
                    }
                } finally {
                }
            }
        }
        k1.a c7 = k1.a.c(context);
        c7.getClass();
        synchronized (k1.a.f7205e) {
            try {
                obj = c7.f7206a.get(ProcessLifecycleInitializer.class);
                if (obj == null) {
                    obj = c7.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        s lifecycle = ((x) obj).getLifecycle();
        lifecycle.a(new m(this, lifecycle));
        return Boolean.TRUE;
    }
}
