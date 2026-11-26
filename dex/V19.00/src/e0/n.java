package e0;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.p0;
import androidx.lifecycle.s0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n extends Activity implements androidx.lifecycle.x, p0.n {
    private q.k mExtraDataMap = new q.k();
    private androidx.lifecycle.a0 mLifecycleRegistry = new androidx.lifecycle.a0(this);

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !n1.z.T(decorView, keyEvent)) {
            return n1.z.U(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !n1.z.T(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Deprecated
    public <T extends m> T getExtraData(Class<T> cls) {
        a.a.u(this.mExtraDataMap.getOrDefault(cls, null));
        return null;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        s0.Companion.getClass();
        p0.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.a0 a0Var = this.mLifecycleRegistry;
        androidx.lifecycle.r rVar = androidx.lifecycle.r.CREATED;
        a0Var.getClass();
        i5.a.g("state", rVar);
        a0Var.d("markState");
        a0Var.g(rVar);
        super.onSaveInstanceState(bundle);
    }

    @Deprecated
    public void putExtraData(m mVar) {
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        if (android.os.Build.VERSION.SDK_INT >= 29) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
    
        if (android.os.Build.VERSION.SDK_INT >= 31) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldDumpInternalState(java.lang.String[] r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L64
            int r2 = r5.length
            if (r2 <= 0) goto L64
            r5 = r5[r0]
            r5.getClass()
            int r2 = r5.hashCode()
            r3 = -1
            switch(r2) {
                case -645125871: goto L41;
                case 100470631: goto L36;
                case 472614934: goto L2b;
                case 1159329357: goto L20;
                case 1455016274: goto L15;
                default: goto L14;
            }
        L14:
            goto L4b
        L15:
            java.lang.String r2 = "--autofill"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L1e
            goto L4b
        L1e:
            r3 = 4
            goto L4b
        L20:
            java.lang.String r2 = "--contentcapture"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L29
            goto L4b
        L29:
            r3 = 3
            goto L4b
        L2b:
            java.lang.String r2 = "--list-dumpables"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L34
            goto L4b
        L34:
            r3 = 2
            goto L4b
        L36:
            java.lang.String r2 = "--dump-dumpable"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L3f
            goto L4b
        L3f:
            r3 = r1
            goto L4b
        L41:
            java.lang.String r2 = "--translation"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L4a
            goto L4b
        L4a:
            r3 = r0
        L4b:
            switch(r3) {
                case 0: goto L5d;
                case 1: goto L58;
                case 2: goto L58;
                case 3: goto L51;
                case 4: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L64
        L4f:
            r0 = r1
            goto L64
        L51:
            int r5 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r5 < r2) goto L64
            goto L4f
        L58:
            boolean r0 = n1.z.B0()
            goto L64
        L5d:
            int r5 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r5 < r2) goto L64
            goto L4f
        L64:
            r5 = r0 ^ 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.n.shouldDumpInternalState(java.lang.String[]):boolean");
    }

    @Override // p0.n
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
