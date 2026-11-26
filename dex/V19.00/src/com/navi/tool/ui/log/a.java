package com.navi.tool.ui.log;

import android.widget.CompoundButton;
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
public final /* synthetic */ class a implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5611a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5612b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5613c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5614d;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i7) {
        this.f5611a = i7;
        this.f5612b = obj;
        this.f5613c = obj2;
        this.f5614d = obj3;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f5611a;
        Object obj = this.f5614d;
        Object obj2 = this.f5613c;
        Object obj3 = this.f5612b;
        switch (i7) {
            case 0:
                ((LogAssistantProfileAdapter.LogAssistantProfileViewHolder) obj3).lambda$bind$0((LogAssistantProfile) obj2, (LogAssistantProfileAdapter.OnProfileToggleListener) obj, compoundButton, z6);
                break;
            case 1:
                LogLinkFragment.MediaViewHolder.lambda$bind$1((List) obj3, (LogLinkFragment.MediaFileItem) obj2, (h1) obj, compoundButton, z6);
                break;
            case 2:
                ((LogMediaProfileAdapter.LogMediaProfileViewHolder) obj3).lambda$bind$0((LogMediaProfile) obj2, (LogMediaProfileAdapter.OnProfileToggleListener) obj, compoundButton, z6);
                break;
            default:
                ((LogProfileAdapter.LogProfileViewHolder) obj3).lambda$bind$0((LogAppProfile) obj2, (LogProfileAdapter.OnProfileToggleListener) obj, compoundButton, z6);
                break;
        }
    }
}
