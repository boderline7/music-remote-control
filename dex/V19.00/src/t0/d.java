package t0;

import android.database.Cursor;
import android.widget.Filter;
import androidx.appcompat.widget.h3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends Filter {

    /* renamed from: a, reason: collision with root package name */
    public c f9021a;

    @Override // android.widget.Filter
    public final CharSequence convertResultToString(Object obj) {
        return ((h3) this.f9021a).c((Cursor) obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
    @Override // android.widget.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.widget.Filter.FilterResults performFiltering(java.lang.CharSequence r5) {
        /*
            r4 = this;
            t0.c r0 = r4.f9021a
            androidx.appcompat.widget.h3 r0 = (androidx.appcompat.widget.h3) r0
            if (r5 != 0) goto L9
            java.lang.String r5 = ""
            goto L10
        L9:
            r0.getClass()
            java.lang.String r5 = r5.toString()
        L10:
            androidx.appcompat.widget.SearchView r1 = r0.f1275k
            int r2 = r1.getVisibility()
            r3 = 0
            if (r2 != 0) goto L2c
            int r1 = r1.getWindowVisibility()
            if (r1 == 0) goto L20
            goto L2c
        L20:
            android.app.SearchableInfo r1 = r0.f1276l     // Catch: java.lang.RuntimeException -> L2c
            android.database.Cursor r5 = r0.g(r1, r5)     // Catch: java.lang.RuntimeException -> L2c
            if (r5 == 0) goto L2c
            r5.getCount()     // Catch: java.lang.RuntimeException -> L2c
            goto L2d
        L2c:
            r5 = r3
        L2d:
            android.widget.Filter$FilterResults r0 = new android.widget.Filter$FilterResults
            r0.<init>()
            if (r5 == 0) goto L3d
            int r1 = r5.getCount()
            r0.count = r1
            r0.values = r5
            goto L42
        L3d:
            r5 = 0
            r0.count = r5
            r0.values = r3
        L42:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: t0.d.performFiltering(java.lang.CharSequence):android.widget.Filter$FilterResults");
    }

    @Override // android.widget.Filter
    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        c cVar = this.f9021a;
        Cursor cursor = ((b) cVar).f9016c;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        ((h3) cVar).b((Cursor) obj);
    }
}
