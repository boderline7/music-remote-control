package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class m3 extends ContextWrapper {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f1352a = new Object();

    public static void a(Context context) {
        if (context.getResources() instanceof o3) {
            return;
        }
        context.getResources();
        int i7 = y3.MAX_SDK_WHERE_REQUIRED;
    }
}
