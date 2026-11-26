package com.navi.tool.ui.extra;

import android.view.View;
import com.navi.tool.ui.extra.CarControlAdapter;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.StorageAnalysisActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e1 implements View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5126a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.recyclerview.widget.h1 f5127b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5128c;

    public /* synthetic */ e1(androidx.recyclerview.widget.h1 h1Var, Object obj, int i7) {
        this.f5126a = i7;
        this.f5127b = h1Var;
        this.f5128c = obj;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        boolean lambda$onBindViewHolder$2;
        boolean lambda$onBindViewHolder$4;
        boolean lambda$onBindViewHolder$0;
        int i7 = this.f5126a;
        Object obj = this.f5128c;
        androidx.recyclerview.widget.h1 h1Var = this.f5127b;
        switch (i7) {
            case 0:
                lambda$onBindViewHolder$2 = ((DesktopAssistantActivity.DesktopAppAdapter) h1Var).lambda$onBindViewHolder$2((DesktopAssistantActivity.AppItem) obj, view);
                return lambda$onBindViewHolder$2;
            case 1:
                lambda$onBindViewHolder$4 = ((StorageAnalysisActivity.LargeFileAdapter) h1Var).lambda$onBindViewHolder$4((StorageAnalysisActivity.FileItem) obj, view);
                return lambda$onBindViewHolder$4;
            default:
                lambda$onBindViewHolder$0 = ((CarControlAdapter) h1Var).lambda$onBindViewHolder$0((CarControlAdapter.CardItem) obj, view);
                return lambda$onBindViewHolder$0;
        }
    }
}
