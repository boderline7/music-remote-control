package j3;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements OnBackAnimationCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f6960a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f6961b;

    public d(e eVar, b bVar) {
        this.f6961b = eVar;
        this.f6960a = bVar;
    }

    public final void onBackCancelled() {
        if (this.f6961b.f6959a != null) {
            this.f6960a.d();
        }
    }

    public final void onBackInvoked() {
        this.f6960a.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        if (this.f6961b.f6959a != null) {
            this.f6960a.c(new androidx.activity.c(backEvent));
        }
    }

    public final void onBackStarted(BackEvent backEvent) {
        if (this.f6961b.f6959a != null) {
            this.f6960a.b(new androidx.activity.c(backEvent));
        }
    }
}
