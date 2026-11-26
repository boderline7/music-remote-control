package com.google.android.material.bottomappbar;

import android.animation.Animator;
import android.view.View;
import com.google.android.material.internal.j0;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements j0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BottomAppBar f3359a;

    public /* synthetic */ b(BottomAppBar bottomAppBar) {
        this.f3359a = bottomAppBar;
    }

    @Override // com.google.android.material.internal.j0
    public final l2 d(View view, l2 l2Var, androidx.media.a aVar) {
        boolean z6;
        BottomAppBar bottomAppBar = this.f3359a;
        if (bottomAppBar.f3339j0) {
            bottomAppBar.f3346q0 = l2Var.a();
        }
        boolean z7 = false;
        if (bottomAppBar.f3340k0) {
            z6 = bottomAppBar.f3348s0 != l2Var.b();
            bottomAppBar.f3348s0 = l2Var.b();
        } else {
            z6 = false;
        }
        if (bottomAppBar.f3341l0) {
            boolean z8 = bottomAppBar.f3347r0 != l2Var.c();
            bottomAppBar.f3347r0 = l2Var.c();
            z7 = z8;
        }
        if (z6 || z7) {
            Animator animator = bottomAppBar.f3330a0;
            if (animator != null) {
                animator.cancel();
            }
            Animator animator2 = bottomAppBar.W;
            if (animator2 != null) {
                animator2.cancel();
            }
            bottomAppBar.K();
            bottomAppBar.J();
        }
        return l2Var;
    }
}
