package g4;

import android.content.DialogInterface;
import com.navi.tool.MainActivity;
import com.navi.tool.ui.cast.ScreenCastingFragment;
import com.navi.tool.ui.log.LogLinkFragment;
import com.navi.tool.ui.scene.SceneModeFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6536a;

    public /* synthetic */ g(int i7) {
        this.f6536a = i7;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        switch (this.f6536a) {
            case 0:
                MainActivity.lambda$handleStartTrial$26(dialogInterface, i7);
                break;
            case 1:
                ScreenCastingFragment.lambda$initSwitch$5(dialogInterface, i7);
                break;
            case 2:
                LogLinkFragment.lambda$showEditMediaProfileDialogInternal$106(dialogInterface, i7);
                break;
            case 3:
                LogLinkFragment.lambda$initAssistantProfilesUi$24(dialogInterface, i7);
                break;
            case 4:
                LogLinkFragment.lambda$initAppProfilesUi$6(dialogInterface, i7);
                break;
            case 5:
                LogLinkFragment.lambda$deleteSelectedAssistantProfiles$35(dialogInterface, i7);
                break;
            case 6:
                LogLinkFragment.lambda$showCreateOrEditAssistantProfileDialog$64(dialogInterface, i7);
                break;
            case 7:
                LogLinkFragment.lambda$showCreateOrEditAssistantProfileDialog$66(dialogInterface, i7);
                break;
            case 8:
                LogLinkFragment.lambda$showCreateOrEditProfileDialog$40(dialogInterface, i7);
                break;
            case 9:
                LogLinkFragment.lambda$deleteSelectedMediaProfiles$33(dialogInterface, i7);
                break;
            case 10:
                LogLinkFragment.lambda$deleteSelectedAppProfiles$31(dialogInterface, i7);
                break;
            case 11:
                LogLinkFragment.lambda$initMediaProfilesUi$15(dialogInterface, i7);
                break;
            case 12:
                LogLinkFragment.lambda$showEditAssistantProfileDialogInternal$79(dialogInterface, i7);
                break;
            case 13:
                LogLinkFragment.lambda$showEditProfileDialogInternal$61(dialogInterface, i7);
                break;
            case 14:
                LogLinkFragment.lambda$showCreateOrEditMediaProfileDialog$86(dialogInterface, i7);
                break;
            case 15:
                SceneModeFragment.lambda$showRuleEditorDialog$14(dialogInterface, i7);
                break;
            case 16:
                SceneModeFragment.lambda$deleteSelectedRules$8(dialogInterface, i7);
                break;
            case 17:
                SceneModeFragment.lambda$deleteRule$6(dialogInterface, i7);
                break;
            default:
                dialogInterface.dismiss();
                break;
        }
    }
}
