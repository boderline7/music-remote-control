package androidx.fragment.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2043a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ z f2044b;

    public /* synthetic */ r(z zVar, int i7) {
        this.f2043a = i7;
        this.f2044b = zVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f2043a;
        z zVar = this.f2044b;
        switch (i7) {
            case 0:
                zVar.startPostponedEnterTransition();
                break;
            default:
                zVar.callStartTransitionListener(false);
                break;
        }
    }
}
