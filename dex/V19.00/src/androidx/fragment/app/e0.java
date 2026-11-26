package androidx.fragment.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1938a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1939b;

    public /* synthetic */ e0(int i7, Object obj) {
        this.f1938a = i7;
        this.f1939b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogInterface.OnDismissListener onDismissListener;
        Dialog dialog;
        int i7 = this.f1938a;
        Object obj = this.f1939b;
        switch (i7) {
            case 0:
                f fVar = (f) obj;
                if (((z) fVar.f1945c).getAnimatingAway() != null) {
                    Object obj2 = fVar.f1945c;
                    ((z) obj2).setAnimatingAway(null);
                    ((p0) fVar.f1946d).c((z) obj2, (l0.d) fVar.f1947e);
                    break;
                }
                break;
            case 1:
                f fVar2 = (f) obj;
                fVar2.f1944b.endViewTransition((View) fVar2.f1945c);
                ((i) fVar2.f1946d).a();
                break;
            case 2:
                q qVar = (q) obj;
                onDismissListener = qVar.mOnDismissListener;
                dialog = qVar.mDialog;
                onDismissListener.onDismiss(dialog);
                break;
            case 3:
                ((x0) obj).s(true);
                break;
            default:
                j1.b(4, (ArrayList) obj);
                break;
        }
    }
}
