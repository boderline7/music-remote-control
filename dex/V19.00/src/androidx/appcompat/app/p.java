package androidx.appcompat.app;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p implements j1.f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f841a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f842b;

    public p(r rVar) {
        this.f842b = rVar;
    }

    public p(j1.g gVar) {
        i5.a.g("registry", gVar);
        this.f842b = new LinkedHashSet();
        gVar.c(j1.c.COMPONENT_KEY, this);
    }

    @Override // j1.f
    public final Bundle a() {
        int i7 = this.f841a;
        Object obj = this.f842b;
        switch (i7) {
            case 0:
                Bundle bundle = new Bundle();
                ((r) obj).getDelegate().getClass();
                return bundle;
            default:
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList(j1.c.CLASSES_KEY, new ArrayList<>((Set) obj));
                return bundle2;
        }
    }
}
