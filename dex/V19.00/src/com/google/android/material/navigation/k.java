package com.google.android.material.navigation;

import android.view.KeyEvent;
import android.view.View;
import com.navi.tool.MainActivity;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k extends w0.e {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4015a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f4016b;

    public k(KeyEvent.Callback callback, int i7) {
        this.f4015a = i7;
        this.f4016b = callback;
    }

    @Override // w0.c
    public final void a(View view) {
        int i7 = this.f4015a;
        KeyEvent.Callback callback = this.f4016b;
        switch (i7) {
            case 0:
                NavigationView navigationView = (NavigationView) callback;
                if (view == navigationView) {
                    j3.f fVar = navigationView.f4005t;
                    Objects.requireNonNull(fVar);
                    view.post(new androidx.activity.e(9, fVar));
                    break;
                }
                break;
            default:
                ((MainActivity) callback).recheckLicense();
                break;
        }
    }

    @Override // w0.c
    public final void d(View view) {
        j3.f fVar;
        j3.c cVar;
        switch (this.f4015a) {
            case 0:
                NavigationView navigationView = (NavigationView) this.f4016b;
                if (view == navigationView && (cVar = (fVar = navigationView.f4005t).f6962a) != null) {
                    cVar.c(fVar.f6964c);
                    break;
                }
                break;
        }
    }
}
