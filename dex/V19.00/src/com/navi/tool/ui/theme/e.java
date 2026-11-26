package com.navi.tool.ui.theme;

import android.widget.RadioGroup;
import androidx.fragment.app.z;
import com.navi.tool.ui.log.LogLinkFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5825a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ z f5826b;

    public /* synthetic */ e(z zVar, int i7) {
        this.f5825a = i7;
        this.f5826b = zVar;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i7) {
        int i8 = this.f5825a;
        z zVar = this.f5826b;
        switch (i8) {
            case 0:
                ((Auto3DFragment) zVar).lambda$onViewCreated$6(radioGroup, i7);
                break;
            case 1:
                ((Auto3DFragment) zVar).lambda$onViewCreated$7(radioGroup, i7);
                break;
            default:
                ((LogLinkFragment) zVar).lambda$onCreateView$0(radioGroup, i7);
                break;
        }
    }
}
