package com.navi.tool.ui.log;

import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.ui.adapter.AppAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class s implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5733a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ List f5734b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AppAdapter f5735c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5736d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ LogAppProfile f5737e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f5738f;

    public /* synthetic */ s(LogLinkFragment logLinkFragment, ArrayList arrayList, AppAdapter appAdapter, RecyclerView recyclerView, LogAppProfile logAppProfile) {
        this.f5738f = logLinkFragment;
        this.f5734b = arrayList;
        this.f5735c = appAdapter;
        this.f5736d = recyclerView;
        this.f5737e = logAppProfile;
    }

    public /* synthetic */ s(List list, List list2, AppAdapter appAdapter, RecyclerView recyclerView, LogAppProfile logAppProfile) {
        this.f5734b = list;
        this.f5738f = list2;
        this.f5735c = appAdapter;
        this.f5736d = recyclerView;
        this.f5737e = logAppProfile;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5733a;
        AppAdapter appAdapter = this.f5735c;
        List list = this.f5734b;
        LogAppProfile logAppProfile = this.f5737e;
        RecyclerView recyclerView = this.f5736d;
        Object obj = this.f5738f;
        switch (i7) {
            case 0:
                LogLinkFragment.lambda$showEditProfileDialogInternal$57(list, (List) obj, appAdapter, recyclerView, logAppProfile);
                break;
            default:
                ((LogLinkFragment) obj).lambda$showEditProfileDialogInternal$58(list, appAdapter, recyclerView, logAppProfile);
                break;
        }
    }
}
