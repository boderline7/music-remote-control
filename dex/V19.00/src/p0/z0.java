package p0;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class z0 {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static i b(View view, i iVar) {
        ContentInfo m6 = iVar.f8143a.m();
        Objects.requireNonNull(m6);
        ContentInfo h7 = e0.w.h(m6);
        ContentInfo performReceiveContent = view.performReceiveContent(h7);
        if (performReceiveContent == null) {
            return null;
        }
        return performReceiveContent == h7 ? iVar : new i(new e.a(performReceiveContent));
    }

    public static void c(View view, String[] strArr, c0 c0Var) {
        if (c0Var == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new a1(c0Var));
        }
    }
}
