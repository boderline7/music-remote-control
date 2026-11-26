package androidx.activity;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 implements OnBackAnimationCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h5.l f604a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h5.l f605b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h5.a f606c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ h5.a f607d;

    public b0(h5.l lVar, h5.l lVar2, h5.a aVar, h5.a aVar2) {
        this.f604a = lVar;
        this.f605b = lVar2;
        this.f606c = aVar;
        this.f607d = aVar2;
    }

    public final void onBackCancelled() {
        this.f607d.a();
    }

    public final void onBackInvoked() {
        this.f606c.a();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        this.f605b.b(new c(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        this.f604a.b(new c(backEvent));
    }
}
