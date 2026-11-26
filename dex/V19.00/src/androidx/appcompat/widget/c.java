package androidx.appcompat.widget;

import android.view.View;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1195a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f1196b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1197c;

    public c(ActionBarContextView actionBarContextView, j.c cVar) {
        this.f1197c = actionBarContextView;
        this.f1196b = cVar;
    }

    public c(w3 w3Var) {
        this.f1197c = w3Var;
        this.f1196b = new k.a(w3Var.f1442a.getContext(), w3Var.f1450i);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f1195a;
        Object obj = this.f1196b;
        switch (i7) {
            case 0:
                ((j.c) obj).a();
                break;
            default:
                w3 w3Var = (w3) this.f1197c;
                Window.Callback callback = w3Var.f1453l;
                if (callback != null && w3Var.f1454m) {
                    callback.onMenuItemSelected(0, (k.a) obj);
                    break;
                }
                break;
        }
    }
}
