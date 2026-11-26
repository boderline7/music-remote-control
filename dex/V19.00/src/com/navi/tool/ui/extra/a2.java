package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.widget.EditText;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a2 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5004a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5005b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ EditText f5006c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AppInfo f5007d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ PanelAppSettingsAdapter f5008e;

    public /* synthetic */ a2(DesktopAssistantFragment desktopAssistantFragment, EditText editText, AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, int i7) {
        this.f5004a = i7;
        this.f5005b = desktopAssistantFragment;
        this.f5006c = editText;
        this.f5007d = appInfo;
        this.f5008e = panelAppSettingsAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        switch (this.f5004a) {
            case 0:
                this.f5005b.lambda$showChangeTopInfoNameDialog$29(this.f5006c, this.f5007d, this.f5008e, dialogInterface, i7);
                break;
            default:
                this.f5005b.lambda$showChangeNameDialog$8(this.f5006c, this.f5007d, this.f5008e, dialogInterface, i7);
                break;
        }
    }
}
