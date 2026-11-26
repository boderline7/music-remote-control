package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.widget.EditText;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4993a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4994b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4995c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f4996d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f4997e;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        this.f4993a = i7;
        this.f4994b = obj;
        this.f4995c = obj2;
        this.f4996d = obj3;
        this.f4997e = obj4;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f4993a;
        Object obj = this.f4997e;
        Object obj2 = this.f4996d;
        Object obj3 = this.f4995c;
        Object obj4 = this.f4994b;
        switch (i8) {
            case 0:
                ((AdditionalFeaturesFragment) obj4).lambda$showChangeNameDialog$45((EditText) obj3, (AppInfo) obj2, (PanelAppSettingsAdapter) obj, dialogInterface, i7);
                break;
            case 1:
                ((DesktopAssistantFragment) obj4).lambda$setupWeeklyButton$80((String) obj3, (File[]) obj2, (String) obj, dialogInterface, i7);
                break;
            default:
                ((PermissionManagerActivity) obj4).lambda$showInputMethodPicker$13((String[]) obj3, (int[]) obj2, (String[]) obj, dialogInterface, i7);
                break;
        }
    }
}
