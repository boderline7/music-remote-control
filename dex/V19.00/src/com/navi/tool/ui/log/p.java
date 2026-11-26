package com.navi.tool.ui.log;

import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.ui.log.LogAssistantProfileAdapter;
import com.navi.tool.ui.log.LogMediaProfileAdapter;
import com.navi.tool.ui.log.LogProfileAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p implements LogMediaProfileAdapter.OnProfileToggleListener, LogMediaProfileAdapter.OnProfileEditListener, LogMediaProfileAdapter.OnProfileDeleteListener, LogAssistantProfileAdapter.OnProfileToggleListener, LogAssistantProfileAdapter.OnProfileEditListener, LogAssistantProfileAdapter.OnProfileDeleteListener, LogProfileAdapter.OnProfileToggleListener, LogProfileAdapter.OnProfileEditListener, LogProfileAdapter.OnProfileDeleteListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ LogLinkFragment f5713a;

    public /* synthetic */ p(LogLinkFragment logLinkFragment) {
        this.f5713a = logLinkFragment;
    }

    @Override // com.navi.tool.ui.log.LogProfileAdapter.OnProfileDeleteListener
    public final void onProfileDelete(LogAppProfile logAppProfile) {
        this.f5713a.lambda$initAppProfilesUi$8(logAppProfile);
    }

    @Override // com.navi.tool.ui.log.LogAssistantProfileAdapter.OnProfileDeleteListener
    public final void onProfileDelete(LogAssistantProfile logAssistantProfile) {
        this.f5713a.lambda$initAssistantProfilesUi$26(logAssistantProfile);
    }

    @Override // com.navi.tool.ui.log.LogMediaProfileAdapter.OnProfileDeleteListener
    public final void onProfileDelete(LogMediaProfile logMediaProfile) {
        this.f5713a.lambda$initMediaProfilesUi$17(logMediaProfile);
    }

    @Override // com.navi.tool.ui.log.LogProfileAdapter.OnProfileEditListener
    public final void onProfileEdit(LogAppProfile logAppProfile) {
        this.f5713a.lambda$initAppProfilesUi$5(logAppProfile);
    }

    @Override // com.navi.tool.ui.log.LogAssistantProfileAdapter.OnProfileEditListener
    public final void onProfileEdit(LogAssistantProfile logAssistantProfile) {
        this.f5713a.lambda$initAssistantProfilesUi$23(logAssistantProfile);
    }

    @Override // com.navi.tool.ui.log.LogMediaProfileAdapter.OnProfileEditListener
    public final void onProfileEdit(LogMediaProfile logMediaProfile) {
        this.f5713a.lambda$initMediaProfilesUi$14(logMediaProfile);
    }

    @Override // com.navi.tool.ui.log.LogProfileAdapter.OnProfileToggleListener
    public final void onProfileToggle(LogAppProfile logAppProfile) {
        this.f5713a.lambda$initAppProfilesUi$4(logAppProfile);
    }

    @Override // com.navi.tool.ui.log.LogAssistantProfileAdapter.OnProfileToggleListener
    public final void onProfileToggle(LogAssistantProfile logAssistantProfile) {
        this.f5713a.lambda$initAssistantProfilesUi$22(logAssistantProfile);
    }

    @Override // com.navi.tool.ui.log.LogMediaProfileAdapter.OnProfileToggleListener
    public final void onProfileToggle(LogMediaProfile logMediaProfile) {
        this.f5713a.lambda$initMediaProfilesUi$13(logMediaProfile);
    }
}
