package p0;

import android.view.View;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class w0 {
    public static void a(View view, b1 b1Var) {
        q.k kVar = (q.k) view.getTag(d0.c.tag_unhandled_key_listeners);
        if (kVar == null) {
            kVar = new q.k();
            view.setTag(d0.c.tag_unhandled_key_listeners, kVar);
        }
        Objects.requireNonNull(b1Var);
        View.OnUnhandledKeyEventListener v0Var = new v0();
        kVar.put(b1Var, v0Var);
        view.addOnUnhandledKeyEventListener(v0Var);
    }

    public static CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean c(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean d(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void e(View view, b1 b1Var) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        q.k kVar = (q.k) view.getTag(d0.c.tag_unhandled_key_listeners);
        if (kVar == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) kVar.getOrDefault(b1Var, null)) == null) {
            return;
        }
        view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static <T> T f(View view, int i7) {
        return (T) view.requireViewById(i7);
    }

    public static void g(View view, boolean z6) {
        view.setAccessibilityHeading(z6);
    }

    public static void h(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    public static void i(View view, boolean z6) {
        view.setScreenReaderFocusable(z6);
    }
}
