package u0;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import p0.d1;
import p0.l0;
import q0.i;
import q0.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends m {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ b f9217b;

    public a(b bVar) {
        this.f9217b = bVar;
    }

    @Override // q0.m
    public final i a(int i7) {
        return new i(AccessibilityNodeInfo.obtain(this.f9217b.r(i7).f8426a));
    }

    @Override // q0.m
    public final i b(int i7) {
        b bVar = this.f9217b;
        int i8 = i7 == 2 ? bVar.f9228k : bVar.f9229l;
        if (i8 == Integer.MIN_VALUE) {
            return null;
        }
        return a(i8);
    }

    @Override // q0.m
    public final boolean c(int i7, int i8, Bundle bundle) {
        int i9;
        b bVar = this.f9217b;
        View view = bVar.f9226i;
        if (i7 == -1) {
            int i10 = d1.OVER_SCROLL_ALWAYS;
            return l0.j(view, i8, bundle);
        }
        boolean z6 = true;
        if (i8 == 1) {
            return bVar.w(i7);
        }
        if (i8 == 2) {
            return bVar.j(i7);
        }
        if (i8 == 64) {
            AccessibilityManager accessibilityManager = bVar.f9225h;
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled() && (i9 = bVar.f9228k) != i7) {
                if (i9 != Integer.MIN_VALUE) {
                    bVar.f9228k = Integer.MIN_VALUE;
                    bVar.f9226i.invalidate();
                    bVar.x(i9, 65536);
                }
                bVar.f9228k = i7;
                view.invalidate();
                bVar.x(i7, 32768);
            }
            z6 = false;
        } else {
            if (i8 != 128) {
                return bVar.s(i7, i8, bundle);
            }
            if (bVar.f9228k == i7) {
                bVar.f9228k = Integer.MIN_VALUE;
                view.invalidate();
                bVar.x(i7, 65536);
            }
            z6 = false;
        }
        return z6;
    }
}
