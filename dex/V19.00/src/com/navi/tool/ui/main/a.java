package com.navi.tool.ui.main;

import android.view.View;
import com.navi.tool.MainActivity;
import com.navi.tool.model.StoreConfig;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5763a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5764b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5765c;

    public /* synthetic */ a(int i7, Object obj, Object obj2) {
        this.f5763a = i7;
        this.f5764b = obj;
        this.f5765c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5763a;
        Object obj = this.f5765c;
        Object obj2 = this.f5764b;
        switch (i7) {
            case 0:
                ((StoreConfigAdapter) obj2).lambda$onBindViewHolder$0((StoreConfig) obj, view);
                break;
            case 1:
                ((StoreConfigAdapter) obj2).lambda$onBindViewHolder$1((StoreConfig) obj, view);
                break;
            case 2:
                ((StoreConfigAdapter) obj2).lambda$onBindViewHolder$2((StoreConfig) obj, view);
                break;
            default:
                ((MainActivity) obj2).lambda$updateToolbarTitle$24((String) obj, view);
                break;
        }
    }
}
