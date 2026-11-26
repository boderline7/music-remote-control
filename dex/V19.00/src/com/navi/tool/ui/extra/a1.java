package com.navi.tool.ui.extra;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.ui.extra.DesktopAssistantActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5001a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f5002b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5003c;

    public /* synthetic */ a1(Object obj, boolean z6, int i7) {
        this.f5001a = i7;
        this.f5003c = obj;
        this.f5002b = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5001a;
        boolean z6 = this.f5002b;
        Object obj = this.f5003c;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity.AnonymousClass12) obj).lambda$onSuccess$0(z6);
                break;
            case 1:
                ((DesktopAssistantFragment) obj).lambda$updatePermissionStatus$7(z6);
                break;
            default:
                ((SwitchMaterial) obj).setChecked(z6);
                break;
        }
    }
}
