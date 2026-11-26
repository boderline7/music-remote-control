package p0;

import android.view.View;
import java.util.Collection;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class u0 {
    public static void a(View view, Collection<View> collection, int i7) {
        view.addKeyboardNavigationClusters(collection, i7);
    }

    public static int b(View view) {
        return view.getImportantForAutofill();
    }

    public static int c(View view) {
        return view.getNextClusterForwardId();
    }

    public static boolean d(View view) {
        return view.hasExplicitFocusable();
    }

    public static boolean e(View view) {
        return view.isFocusedByDefault();
    }

    public static boolean f(View view) {
        return view.isImportantForAutofill();
    }

    public static boolean g(View view) {
        return view.isKeyboardNavigationCluster();
    }

    public static View h(View view, View view2, int i7) {
        return view.keyboardNavigationClusterSearch(view2, i7);
    }

    public static boolean i(View view) {
        return view.restoreDefaultFocus();
    }

    public static void j(View view, String... strArr) {
        view.setAutofillHints(strArr);
    }

    public static void k(View view, boolean z6) {
        view.setFocusedByDefault(z6);
    }

    public static void l(View view, int i7) {
        view.setImportantForAutofill(i7);
    }

    public static void m(View view, boolean z6) {
        view.setKeyboardNavigationCluster(z6);
    }

    public static void n(View view, int i7) {
        view.setNextClusterForwardId(i7);
    }

    public static void o(View view, CharSequence charSequence) {
        view.setTooltipText(charSequence);
    }
}
