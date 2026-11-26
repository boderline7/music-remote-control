package androidx.fragment.app;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1966a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1967b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1968c;

    public /* synthetic */ h(int i7, Object obj, Object obj2) {
        this.f1966a = i7;
        this.f1967b = obj;
        this.f1968c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1966a;
        Object obj = this.f1968c;
        switch (i7) {
            case 0:
                j1.b(4, (ArrayList) obj);
                break;
            case 1:
                ((k) obj).a();
                break;
            default:
                ((y1) obj).c();
                break;
        }
    }
}
