package com.navi.tool.ui.log;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a0 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5615a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f5616b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f5617c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ List f5618d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ EditText f5619e;

    public /* synthetic */ a0(LinearLayout linearLayout, LinearLayout linearLayout2, List list, EditText editText, int i7) {
        this.f5615a = i7;
        this.f5616b = linearLayout;
        this.f5617c = linearLayout2;
        this.f5618d = list;
        this.f5619e = editText;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5615a;
        LinearLayout linearLayout = this.f5617c;
        LinearLayout linearLayout2 = this.f5616b;
        EditText editText = this.f5619e;
        List list = this.f5618d;
        switch (i7) {
            case 0:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$104(linearLayout2, linearLayout, list, editText, view);
                break;
            case 1:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$75(linearLayout2, linearLayout, list, editText, view);
                break;
            case 2:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$95(linearLayout2, linearLayout, list, editText, view);
                break;
            case 3:
                LogLinkFragment.lambda$showEditProfileDialogInternal$51(linearLayout2, linearLayout, list, editText, view);
                break;
            case 4:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$77(linearLayout2, linearLayout, list, editText, view);
                break;
            default:
                LogLinkFragment.lambda$showEditProfileDialogInternal$59(linearLayout2, linearLayout, list, editText, view);
                break;
        }
    }
}
