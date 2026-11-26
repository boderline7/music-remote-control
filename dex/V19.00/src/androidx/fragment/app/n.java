package androidx.fragment.app;

import android.app.Dialog;
import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n implements DialogInterface.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ q f2024a;

    public n(q qVar) {
        this.f2024a = qVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Dialog dialog;
        Dialog dialog2;
        q qVar = this.f2024a;
        dialog = qVar.mDialog;
        if (dialog != null) {
            dialog2 = qVar.mDialog;
            qVar.onDismiss(dialog2);
        }
    }
}
