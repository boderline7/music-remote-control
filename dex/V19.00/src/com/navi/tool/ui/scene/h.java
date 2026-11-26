package com.navi.tool.ui.scene;

import android.content.DialogInterface;
import com.navi.tool.model.SceneRule;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5803a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SceneModeFragment f5804b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5805c;

    public /* synthetic */ h(SceneModeFragment sceneModeFragment, Object obj, int i7) {
        this.f5803a = i7;
        this.f5804b = sceneModeFragment;
        this.f5805c = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5803a;
        SceneModeFragment sceneModeFragment = this.f5804b;
        Object obj = this.f5805c;
        switch (i8) {
            case 0:
                sceneModeFragment.lambda$deleteSelectedRules$10((List) obj, dialogInterface, i7);
                break;
            default:
                sceneModeFragment.lambda$deleteRule$7((SceneRule) obj, dialogInterface, i7);
                break;
        }
    }
}
