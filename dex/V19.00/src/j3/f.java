package j3;

import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final c f6962a;

    /* renamed from: b, reason: collision with root package name */
    public final b f6963b;

    /* renamed from: c, reason: collision with root package name */
    public final View f6964c;

    /* JADX WARN: Multi-variable type inference failed */
    public f(View view) {
        b bVar = (b) view;
        int i7 = Build.VERSION.SDK_INT;
        this.f6962a = i7 >= 34 ? new e() : i7 >= 33 ? new c() : null;
        this.f6963b = bVar;
        this.f6964c = view;
    }
}
