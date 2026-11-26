package com.navi.tool.ui.log;

import android.view.View;
import androidx.recyclerview.widget.j2;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.ui.log.LogAssistantProfileAdapter;
import com.navi.tool.ui.log.LogMediaProfileAdapter;
import com.navi.tool.ui.log.LogProfileAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5620a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j2 f5621b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5622c;

    public /* synthetic */ b(j2 j2Var, Object obj, int i7) {
        this.f5620a = i7;
        this.f5621b = j2Var;
        this.f5622c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5620a;
        Object obj = this.f5622c;
        j2 j2Var = this.f5621b;
        switch (i7) {
            case 0:
                ((LogAssistantProfileAdapter.LogAssistantProfileViewHolder) j2Var).lambda$bind$1((LogAssistantProfile) obj, view);
                break;
            case 1:
                ((LogMediaProfileAdapter.LogMediaProfileViewHolder) j2Var).lambda$bind$1((LogMediaProfile) obj, view);
                break;
            default:
                ((LogProfileAdapter.LogProfileViewHolder) j2Var).lambda$bind$1((LogAppProfile) obj, view);
                break;
        }
    }
}
