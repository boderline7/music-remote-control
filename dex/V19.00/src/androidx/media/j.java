package androidx.media;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public final Object f2257a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2258b;

    /* renamed from: c, reason: collision with root package name */
    public int f2259c;

    public j(Object obj) {
        this.f2257a = obj;
    }

    public final boolean a() {
        return this.f2258b;
    }

    public abstract void b();

    public final void c() {
        if (this.f2258b) {
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f2257a);
        }
        this.f2258b = true;
        b();
    }
}
