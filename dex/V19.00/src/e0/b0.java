package e0;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b0 implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f6291a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final Context f6292b;

    public b0(Context context) {
        this.f6292b = context;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f6291a.iterator();
    }
}
