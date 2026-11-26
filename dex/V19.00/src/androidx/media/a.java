package androidx.media;

import android.view.View;
import androidx.recyclerview.widget.j2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public int f2235a;

    /* renamed from: b, reason: collision with root package name */
    public int f2236b;

    /* renamed from: c, reason: collision with root package name */
    public int f2237c;

    /* renamed from: d, reason: collision with root package name */
    public int f2238d;

    public final void a(j2 j2Var) {
        View view = j2Var.itemView;
        this.f2235a = view.getLeft();
        this.f2236b = view.getTop();
        this.f2237c = view.getRight();
        this.f2238d = view.getBottom();
    }
}
