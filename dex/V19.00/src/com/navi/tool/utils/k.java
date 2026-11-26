package com.navi.tool.utils;

import p0.s2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5867a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5868b;

    public /* synthetic */ k(int i7, Object obj) {
        this.f5867a = i7;
        this.f5868b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5867a;
        Object obj = this.f5868b;
        switch (i7) {
            case 0:
                DialogImeNavBarHelper.lambda$hideNavigationBar$3((s2) obj);
                break;
            default:
                ((UidSyncManager) obj).lambda$triggerSync$0();
                break;
        }
    }
}
