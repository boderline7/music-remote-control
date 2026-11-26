package androidx.fragment.app;

import android.app.Dialog;
import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m implements DialogInterface.OnCancelListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ q f2013a;

    public m(q qVar) {
        this.f2013a = qVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Dialog dialog;
        Dialog dialog2;
        q qVar = this.f2013a;
        dialog = qVar.mDialog;
        if (dialog != null) {
            dialog2 = qVar.mDialog;
            qVar.onCancel(dialog2);
        }
    }
}
