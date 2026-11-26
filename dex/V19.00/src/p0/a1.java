package p0;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a1 implements OnReceiveContentListener {

    /* renamed from: a, reason: collision with root package name */
    public final c0 f8094a;

    public a1(c0 c0Var) {
        this.f8094a = c0Var;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        i iVar = new i(new e.a(contentInfo));
        i a7 = ((androidx.core.widget.x) this.f8094a).a(view, iVar);
        if (a7 == null) {
            return null;
        }
        if (a7 == iVar) {
            return contentInfo;
        }
        ContentInfo m6 = a7.f8143a.m();
        Objects.requireNonNull(m6);
        return e0.w.h(m6);
    }
}
