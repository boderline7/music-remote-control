package n1;

import android.view.View;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l0 extends r {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;

    /* renamed from: y, reason: collision with root package name */
    public static final String[] f7637y = {"android:visibility:visibility", "android:visibility:parent"};

    /* renamed from: x, reason: collision with root package name */
    public int f7638x = 3;

    public static void H(y yVar) {
        int visibility = yVar.f7686b.getVisibility();
        HashMap hashMap = yVar.f7685a;
        hashMap.put("android:visibility:visibility", Integer.valueOf(visibility));
        View view = yVar.f7686b;
        hashMap.put("android:visibility:parent", view.getParent());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        hashMap.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0059 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static n1.k0 I(n1.y r8, n1.y r9) {
        /*
            n1.k0 r0 = new n1.k0
            r0.<init>()
            r1 = 0
            r0.f7631a = r1
            r0.f7632b = r1
            r2 = 0
            r3 = -1
            java.lang.String r4 = "android:visibility:parent"
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L2f
            java.util.HashMap r6 = r8.f7685a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L2f
            java.lang.Object r7 = r6.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r0.f7633c = r7
            java.lang.Object r6 = r6.get(r4)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.f7635e = r6
            goto L33
        L2f:
            r0.f7633c = r3
            r0.f7635e = r2
        L33:
            if (r9 == 0) goto L52
            java.util.HashMap r6 = r9.f7685a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L52
            java.lang.Object r2 = r6.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.f7634d = r2
            java.lang.Object r2 = r6.get(r4)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f7636f = r2
            goto L56
        L52:
            r0.f7634d = r3
            r0.f7636f = r2
        L56:
            r2 = 1
            if (r8 == 0) goto L8a
            if (r9 == 0) goto L8a
            int r8 = r0.f7633c
            int r9 = r0.f7634d
            if (r8 != r9) goto L68
            android.view.ViewGroup r3 = r0.f7635e
            android.view.ViewGroup r4 = r0.f7636f
            if (r3 != r4) goto L68
            return r0
        L68:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L71
            r0.f7632b = r1
            r0.f7631a = r2
            goto L9f
        L71:
            if (r9 != 0) goto L9f
            r0.f7632b = r2
            r0.f7631a = r2
            goto L9f
        L78:
            android.view.ViewGroup r8 = r0.f7636f
            if (r8 != 0) goto L81
            r0.f7632b = r1
            r0.f7631a = r2
            goto L9f
        L81:
            android.view.ViewGroup r8 = r0.f7635e
            if (r8 != 0) goto L9f
            r0.f7632b = r2
            r0.f7631a = r2
            goto L9f
        L8a:
            if (r8 != 0) goto L95
            int r8 = r0.f7634d
            if (r8 != 0) goto L95
            r0.f7632b = r2
            r0.f7631a = r2
            goto L9f
        L95:
            if (r9 != 0) goto L9f
            int r8 = r0.f7633c
            if (r8 != 0) goto L9f
            r0.f7632b = r1
            r0.f7631a = r2
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.l0.I(n1.y, n1.y):n1.k0");
    }

    @Override // n1.r
    public final void d(y yVar) {
        H(yVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        if (I(n(r3, false), q(r3, false)).f7631a != false) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f1  */
    @Override // n1.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.animation.Animator k(android.view.ViewGroup r21, n1.y r22, n1.y r23) {
        /*
            Method dump skipped, instructions count: 744
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.l0.k(android.view.ViewGroup, n1.y, n1.y):android.animation.Animator");
    }

    @Override // n1.r
    public final String[] p() {
        return f7637y;
    }

    @Override // n1.r
    public final boolean r(y yVar, y yVar2) {
        if (yVar == null && yVar2 == null) {
            return false;
        }
        if (yVar != null && yVar2 != null && yVar2.f7685a.containsKey("android:visibility:visibility") != yVar.f7685a.containsKey("android:visibility:visibility")) {
            return false;
        }
        k0 I = I(yVar, yVar2);
        if (I.f7631a) {
            return I.f7633c == 0 || I.f7634d == 0;
        }
        return false;
    }
}
