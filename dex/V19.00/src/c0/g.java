package c0;

import android.view.View;
import java.util.Comparator;
import p0.d1;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3026a;

    public /* synthetic */ g(int i7) {
        this.f3026a = i7;
    }

    public final int a(View view, View view2) {
        switch (this.f3026a) {
            case 0:
                int i7 = d1.OVER_SCROLL_ALWAYS;
                float m6 = r0.m(view);
                float m7 = r0.m(view2);
                if (m6 > m7) {
                    return -1;
                }
                return m6 < m7 ? 1 : 0;
            case 1:
            default:
                return view.getTop() - view2.getTop();
            case 2:
                q1.g gVar = (q1.g) view.getLayoutParams();
                q1.g gVar2 = (q1.g) view2.getLayoutParams();
                boolean z6 = gVar.f8437a;
                if (z6 != gVar2.f8437a) {
                    return z6 ? 1 : -1;
                }
                return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return -1;
     */
    @Override // java.util.Comparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int compare(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r6.f3026a
            switch(r0) {
                case 0: goto L75;
                case 1: goto L6b;
                case 2: goto L62;
                case 3: goto L5;
                case 4: goto L5;
                case 5: goto L5;
                case 6: goto L51;
                case 7: goto L48;
                default: goto L5;
            }
        L5:
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r0 = "a"
            i5.a.g(r0, r7)
            java.lang.String r0 = "b"
            i5.a.g(r0, r8)
            int r0 = r7.length()
            int r1 = r8.length()
            int r0 = java.lang.Math.min(r0, r1)
            r1 = 4
        L20:
            r2 = 1
            r3 = -1
            if (r1 >= r0) goto L39
            char r4 = r7.charAt(r1)
            char r5 = r8.charAt(r1)
            if (r4 == r5) goto L36
            int r7 = i5.a.i(r4, r5)
            if (r7 >= 0) goto L47
        L34:
            r2 = r3
            goto L47
        L36:
            int r1 = r1 + 1
            goto L20
        L39:
            int r7 = r7.length()
            int r8 = r8.length()
            if (r7 == r8) goto L46
            if (r7 >= r8) goto L47
            goto L34
        L46:
            r2 = 0
        L47:
            return r2
        L48:
            android.view.View r7 = (android.view.View) r7
            android.view.View r8 = (android.view.View) r8
            int r7 = r6.a(r7, r8)
            return r7
        L51:
            y1.a0 r7 = (y1.a0) r7
            y1.a0 r8 = (y1.a0) r8
            y1.q r7 = r7.b()
            y1.q r8 = r8.b()
            int r7 = r7.compareTo(r8)
            return r7
        L62:
            android.view.View r7 = (android.view.View) r7
            android.view.View r8 = (android.view.View) r8
            int r7 = r6.a(r7, r8)
            return r7
        L6b:
            q1.f r7 = (q1.f) r7
            q1.f r8 = (q1.f) r8
            int r7 = r7.f8435a
            int r8 = r8.f8435a
            int r7 = r7 - r8
            return r7
        L75:
            android.view.View r7 = (android.view.View) r7
            android.view.View r8 = (android.view.View) r8
            int r7 = r6.a(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.g.compare(java.lang.Object, java.lang.Object):int");
    }
}
