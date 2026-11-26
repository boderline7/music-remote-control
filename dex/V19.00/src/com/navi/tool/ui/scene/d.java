package com.navi.tool.ui.scene;

import android.view.View;
import android.widget.LinearLayout;
import com.navi.tool.ui.scene.SceneModeFragment;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5785a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f5786b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5787c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5788d;

    public /* synthetic */ d(LinearLayout linearLayout, Object obj, List list, int i7) {
        this.f5785a = i7;
        this.f5786b = linearLayout;
        this.f5788d = obj;
        this.f5787c = list;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5785a;
        LinearLayout linearLayout = this.f5786b;
        List list = this.f5787c;
        Object obj = this.f5788d;
        switch (i7) {
            case 0:
                SceneModeFragment.lambda$addConditionRow$17(linearLayout, (SceneModeFragment.ConditionRowHolder) obj, list, view);
                break;
            default:
                SceneModeFragment.lambda$addActionRow$18(linearLayout, (SceneModeFragment.ActionRowHolder) obj, list, view);
                break;
        }
    }
}
