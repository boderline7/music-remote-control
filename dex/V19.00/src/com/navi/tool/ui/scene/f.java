package com.navi.tool.ui.scene;

import com.navi.tool.model.SceneRule;
import com.navi.tool.ui.scene.SceneRuleAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements SceneRuleAdapter.OnToggleListener, SceneRuleAdapter.OnEditListener, SceneRuleAdapter.OnDeleteListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SceneModeFragment f5800a;

    public /* synthetic */ f(SceneModeFragment sceneModeFragment) {
        this.f5800a = sceneModeFragment;
    }

    @Override // com.navi.tool.ui.scene.SceneRuleAdapter.OnDeleteListener
    public final void onDelete(SceneRule sceneRule) {
        this.f5800a.deleteRule(sceneRule);
    }

    @Override // com.navi.tool.ui.scene.SceneRuleAdapter.OnEditListener
    public final void onEdit(SceneRule sceneRule) {
        this.f5800a.editRule(sceneRule);
    }

    @Override // com.navi.tool.ui.scene.SceneRuleAdapter.OnToggleListener
    public final void onToggle(SceneRule sceneRule) {
        this.f5800a.toggleRule(sceneRule);
    }
}
