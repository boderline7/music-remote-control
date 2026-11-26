package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.widget.EditText;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5027a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5028b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5029c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5030d;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i7) {
        this.f5027a = i7;
        this.f5028b = obj;
        this.f5029c = obj2;
        this.f5030d = obj3;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5027a;
        Object obj = this.f5030d;
        Object obj2 = this.f5029c;
        Object obj3 = this.f5028b;
        switch (i8) {
            case 0:
                ((AdditionalFeaturesFragment) obj3).lambda$showChangeNameDialog$46((AppInfo) obj2, (PanelAppSettingsAdapter) obj, dialogInterface, i7);
                break;
            case 1:
                ((DesktopAssistantFragment) obj3).lambda$setupWeeklyVideoButton$64((String) obj2, (String) obj, dialogInterface, i7);
                break;
            case 2:
                ((FileManagerActivity) obj3).lambda$showRenameDialog$111((EditText) obj2, (File) obj, dialogInterface, i7);
                break;
            default:
                ((StorageAnalysisActivity) obj3).lambda$showFilterDialog$6((long[]) obj2, (String[]) obj, dialogInterface, i7);
                break;
        }
    }
}
