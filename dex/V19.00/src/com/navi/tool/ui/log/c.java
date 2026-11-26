package com.navi.tool.ui.log;

import android.view.View;
import androidx.recyclerview.widget.h1;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.ui.log.LogAssistantProfileAdapter;
import com.navi.tool.ui.log.LogLinkFragment;
import com.navi.tool.ui.log.LogMediaProfileAdapter;
import com.navi.tool.ui.log.LogProfileAdapter;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5649a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5650b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5651c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5652d;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, int i7) {
        this.f5649a = i7;
        this.f5650b = obj;
        this.f5651c = obj2;
        this.f5652d = obj3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5649a;
        Object obj = this.f5652d;
        Object obj2 = this.f5651c;
        Object obj3 = this.f5650b;
        switch (i7) {
            case 0:
                ((LogAssistantProfileAdapter.LogAssistantProfileViewHolder) obj3).lambda$bind$2((LogAssistantProfile) obj2, (LogAssistantProfileAdapter.OnProfileEditListener) obj, view);
                break;
            case 1:
                LogLinkFragment.MediaViewHolder.lambda$bind$0((List) obj3, (LogLinkFragment.MediaFileItem) obj2, (h1) obj, view);
                break;
            case 2:
                ((LogMediaProfileAdapter.LogMediaProfileViewHolder) obj3).lambda$bind$2((LogMediaProfile) obj2, (LogMediaProfileAdapter.OnProfileEditListener) obj, view);
                break;
            default:
                ((LogProfileAdapter.LogProfileViewHolder) obj3).lambda$bind$2((LogAppProfile) obj2, (LogProfileAdapter.OnProfileEditListener) obj, view);
                break;
        }
    }
}
