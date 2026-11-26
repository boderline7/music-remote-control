package u0;

import android.graphics.Rect;
import java.util.Comparator;
import p3.f;
import q0.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final Rect f9231a = new Rect();

    /* renamed from: b, reason: collision with root package name */
    public final Rect f9232b = new Rect();

    /* renamed from: c, reason: collision with root package name */
    public final boolean f9233c;

    /* renamed from: d, reason: collision with root package name */
    public final f f9234d;

    public c(boolean z6, f fVar) {
        this.f9233c = z6;
        this.f9234d = fVar;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        this.f9234d.getClass();
        Rect rect = this.f9231a;
        ((i) obj).d(rect);
        Rect rect2 = this.f9232b;
        ((i) obj2).d(rect2);
        int i7 = rect.top;
        int i8 = rect2.top;
        if (i7 < i8) {
            return -1;
        }
        if (i7 > i8) {
            return 1;
        }
        int i9 = rect.left;
        int i10 = rect2.left;
        boolean z6 = this.f9233c;
        if (i9 < i10) {
            return z6 ? 1 : -1;
        }
        if (i9 > i10) {
            return z6 ? -1 : 1;
        }
        int i11 = rect.bottom;
        int i12 = rect2.bottom;
        if (i11 < i12) {
            return -1;
        }
        if (i11 > i12) {
            return 1;
        }
        int i13 = rect.right;
        int i14 = rect2.right;
        if (i13 < i14) {
            return z6 ? 1 : -1;
        }
        if (i13 > i14) {
            return z6 ? -1 : 1;
        }
        return 0;
    }
}
