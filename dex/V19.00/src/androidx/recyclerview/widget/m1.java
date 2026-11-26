package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class m1 {
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    public static final int FLAG_CHANGED = 2;
    public static final int FLAG_INVALIDATED = 4;
    public static final int FLAG_MOVED = 2048;
    public static final int FLAG_REMOVED = 8;

    /* renamed from: a, reason: collision with root package name */
    public n1 f2538a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f2539b;

    /* renamed from: c, reason: collision with root package name */
    public long f2540c;

    /* renamed from: d, reason: collision with root package name */
    public long f2541d;

    /* renamed from: e, reason: collision with root package name */
    public long f2542e;

    /* renamed from: f, reason: collision with root package name */
    public long f2543f;

    public static void b(j2 j2Var) {
        int i7 = j2Var.mFlags;
        if (!j2Var.isInvalid() && (i7 & 4) == 0) {
            j2Var.getOldPosition();
            j2Var.getAdapterPosition();
        }
    }

    public abstract boolean a(j2 j2Var, j2 j2Var2, androidx.media.a aVar, androidx.media.a aVar2);

    public final void c(j2 j2Var) {
        n1 n1Var = this.f2538a;
        if (n1Var != null) {
            boolean z6 = true;
            j2Var.setIsRecyclable(true);
            if (j2Var.mShadowedHolder != null && j2Var.mShadowingHolder == null) {
                j2Var.mShadowedHolder = null;
            }
            j2Var.mShadowingHolder = null;
            if (j2Var.shouldBeKeptAsChild()) {
                return;
            }
            View view = j2Var.itemView;
            RecyclerView recyclerView = n1Var.f2551a;
            recyclerView.g0();
            j jVar = recyclerView.f2305e;
            n1 n1Var2 = (n1) jVar.f2498b;
            int indexOfChild = n1Var2.f2551a.indexOfChild(view);
            if (indexOfChild == -1) {
                jVar.l(view);
            } else {
                i iVar = (i) jVar.f2499c;
                if (iVar.e(indexOfChild)) {
                    iVar.g(indexOfChild);
                    jVar.l(view);
                    n1Var2.h(indexOfChild);
                } else {
                    z6 = false;
                }
            }
            if (z6) {
                j2 K = RecyclerView.K(view);
                z1 z1Var = recyclerView.f2299b;
                z1Var.j(K);
                z1Var.g(K);
            }
            recyclerView.h0(!z6);
            if (z6 || !j2Var.isTmpDetached()) {
                return;
            }
            recyclerView.removeDetachedView(j2Var.itemView, false);
        }
    }

    public abstract void d(j2 j2Var);

    public abstract void e();

    public abstract boolean f();
}
