package g0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Process;
import android.text.TextUtils;
import e0.y;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {
    public static final int RECEIVER_EXPORTED = 2;
    public static final int RECEIVER_NOT_EXPORTED = 4;
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;

    /* renamed from: a, reason: collision with root package name */
    public static final Object f6423a = new Object();

    public static int a(Context context, String str) {
        if (str != null) {
            return (z.B0() || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : new y(context).f6344a.areNotificationsEnabled() ? 0 : -1;
        }
        throw new NullPointerException("permission must be non-null");
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0047, code lost:
    
        if (r5.f6633c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList b(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            h0.m r1 = new h0.m
            r1.<init>(r0, r8)
            java.lang.Object r2 = h0.q.f6644c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = h0.q.f6643b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L50
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L50
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            h0.l r5 = (h0.l) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L50
            android.content.res.Configuration r6 = r5.f6632b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4d
            if (r8 != 0) goto L3f
            int r6 = r5.f6633c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L49
            goto L3f
        L3c:
            r8 = move-exception
            goto Lb0
        L3f:
            if (r8 == 0) goto L4d
            int r6 = r5.f6633c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4d
        L49:
            android.content.res.ColorStateList r3 = r5.f6631a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L52
        L4d:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L50:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L52:
            if (r3 == 0) goto L55
            goto Laf
        L55:
            java.lang.ThreadLocal r2 = h0.q.f6642a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L67
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L67:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L76
            r3 = 31
            if (r2 > r3) goto L76
            goto L7e
        L76:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = h0.c.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7e
        L7e:
            if (r4 == 0) goto Lab
            java.lang.Object r2 = h0.q.f6644c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = h0.q.f6643b     // Catch: java.lang.Throwable -> L96
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L96
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L96
            if (r3 != 0) goto L98
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L96
            r3.<init>()     // Catch: java.lang.Throwable -> L96
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L96
            goto L98
        L96:
            r8 = move-exception
            goto La9
        L98:
            h0.l r0 = new h0.l     // Catch: java.lang.Throwable -> L96
            android.content.res.Resources r1 = r1.f6634a     // Catch: java.lang.Throwable -> L96
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L96
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L96
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L96
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
            r3 = r4
            goto Laf
        La9:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
            throw r8
        Lab:
            android.content.res.ColorStateList r3 = h0.k.b(r0, r9, r8)
        Laf:
            return r3
        Lb0:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g0.g.b(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static void c(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (z.B0()) {
            f.a(context, broadcastReceiver, intentFilter, null, null, 4);
        } else {
            e.a(context, broadcastReceiver, intentFilter, null, null, 4);
        }
    }
}
