package androidx.appcompat.widget;

import android.widget.EditText;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k3 extends y0.j {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1304a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final WeakReference f1305b;

    public k3(EditText editText) {
        this.f1305b = new WeakReference(editText);
    }

    public k3(SwitchCompat switchCompat) {
        this.f1305b = new WeakReference(switchCompat);
    }

    @Override // y0.j
    public final void a() {
        switch (this.f1304a) {
            case 0:
                SwitchCompat switchCompat = (SwitchCompat) this.f1305b.get();
                if (switchCompat != null) {
                    switchCompat.c();
                    break;
                }
                break;
        }
    }

    @Override // y0.j
    public final void b() {
        WeakReference weakReference = this.f1305b;
        switch (this.f1304a) {
            case 0:
                SwitchCompat switchCompat = (SwitchCompat) weakReference.get();
                if (switchCompat != null) {
                    switchCompat.c();
                    break;
                }
                break;
            default:
                a1.j.a((EditText) weakReference.get(), 1);
                break;
        }
    }
}
