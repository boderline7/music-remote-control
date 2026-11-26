package a1;

import android.text.Editable;
import y0.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends Editable.Factory {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f201a = new Object();

    /* renamed from: b, reason: collision with root package name */
    public static volatile c f202b;

    /* renamed from: c, reason: collision with root package name */
    public static Class f203c;

    @Override // android.text.Editable.Factory
    public final Editable newEditable(CharSequence charSequence) {
        Class cls = f203c;
        return cls != null ? new y(cls, charSequence) : super.newEditable(charSequence);
    }
}
