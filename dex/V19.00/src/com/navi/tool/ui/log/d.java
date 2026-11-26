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
public final /* synthetic */ class d implements View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5655a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j2 f5656b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5657c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5658d;

    public /* synthetic */ d(j2 j2Var, Object obj, Object obj2, int i7) {
        this.f5655a = i7;
        this.f5656b = j2Var;
        this.f5657c = obj;
        this.f5658d = obj2;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        boolean lambda$bind$3;
        boolean lambda$bind$32;
        boolean lambda$bind$33;
        int i7 = this.f5655a;
        Object obj = this.f5658d;
        Object obj2 = this.f5657c;
        j2 j2Var = this.f5656b;
        switch (i7) {
            case 0:
                lambda$bind$3 = ((LogAssistantProfileAdapter.LogAssistantProfileViewHolder) j2Var).lambda$bind$3((LogAssistantProfileAdapter.OnProfileDeleteListener) obj2, (LogAssistantProfile) obj, view);
                return lambda$bind$3;
            case 1:
                lambda$bind$32 = ((LogMediaProfileAdapter.LogMediaProfileViewHolder) j2Var).lambda$bind$3((LogMediaProfileAdapter.OnProfileDeleteListener) obj2, (LogMediaProfile) obj, view);
                return lambda$bind$32;
            default:
                lambda$bind$33 = ((LogProfileAdapter.LogProfileViewHolder) j2Var).lambda$bind$3((LogProfileAdapter.OnProfileDeleteListener) obj2, (LogAppProfile) obj, view);
                return lambda$bind$33;
        }
    }
}
