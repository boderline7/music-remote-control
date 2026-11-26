package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5457a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ EditText f5458b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f5459c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5460d;

    public /* synthetic */ t(Object obj, EditText editText, boolean z6, int i7) {
        this.f5457a = i7;
        this.f5460d = obj;
        this.f5458b = editText;
        this.f5459c = z6;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5457a;
        boolean z6 = this.f5459c;
        EditText editText = this.f5458b;
        Object obj = this.f5460d;
        switch (i8) {
            case 0:
                ((CarLinkFragment) obj).lambda$showTankCapacityDialog$2(editText, z6, dialogInterface, i7);
                break;
            default:
                ((FileManagerActivity) obj).lambda$showCreateDialog$114(editText, z6, dialogInterface, i7);
                break;
        }
    }
}
