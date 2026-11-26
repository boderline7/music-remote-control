package com.navi.tool.utils;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import p0.b0;
import p0.d1;
import p0.l2;
import p0.r0;
import p0.s2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogImeNavBarHelper {
    private static final long HIDE_DELAY_MS = 80;
    private static final long RETRY_HIDE_DELAY_MS = 220;
    private static final long SHOW_HIDE_DELAY_MS = 3;

    private DialogImeNavBarHelper() {
    }

    public static void hideAfterShow(Dialog dialog) {
        hideAfterShow(dialog, 3L);
    }

    public static void hideAfterShow(Dialog dialog, long j7) {
        Window window;
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        View decorView = window.getDecorView();
        s2 s2Var = new s2(window, decorView);
        s2Var.f8190a.H();
        i iVar = new i(s2Var, decorView, 1);
        decorView.removeCallbacks(iVar);
        decorView.postDelayed(iVar, Math.max(0L, j7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hideNavigationBar(s2 s2Var, View view) {
        s2Var.f8190a.y(2);
        view.postDelayed(new k(0, s2Var), RETRY_HIDE_DELAY_MS);
    }

    public static void install(Dialog dialog, View... viewArr) {
        Window window;
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        View decorView = window.getDecorView();
        s2 s2Var = new s2(window, decorView);
        s2Var.f8190a.H();
        int i7 = 0;
        final i iVar = new i(s2Var, decorView, i7);
        b0 b0Var = new b0() { // from class: com.navi.tool.utils.j
            @Override // p0.b0
            public final l2 e(View view, l2 l2Var) {
                l2 lambda$install$1;
                lambda$install$1 = DialogImeNavBarHelper.lambda$install$1(iVar, view, l2Var);
                return lambda$install$1;
            }
        };
        int i8 = d1.OVER_SCROLL_ALWAYS;
        r0.u(decorView, b0Var);
        decorView.postDelayed(iVar, HIDE_DELAY_MS);
        if (viewArr == null) {
            return;
        }
        int length = viewArr.length;
        while (i7 < length) {
            View view = viewArr[i7];
            if (view != null) {
                view.postDelayed(iVar, HIDE_DELAY_MS);
            }
            i7++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$hideNavigationBar$3(s2 s2Var) {
        s2Var.f8190a.y(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static l2 lambda$install$1(Runnable runnable, View view, l2 l2Var) {
        if (l2Var.f8161a.o(8)) {
            view.removeCallbacks(runnable);
            view.postDelayed(runnable, HIDE_DELAY_MS);
        }
        return l2Var;
    }
}
