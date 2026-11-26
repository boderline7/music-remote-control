package x0;

import android.os.Looper;
import android.util.AndroidRuntimeException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends h {

    /* renamed from: l, reason: collision with root package name */
    public j f9570l;

    /* renamed from: m, reason: collision with root package name */
    public float f9571m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f9572n;

    public final void b() {
        if (this.f9570l.f9574b <= 0.0d) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f9564f) {
            this.f9572n = true;
        }
    }
}
