package g4;

import android.content.DialogInterface;
import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6532a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6533b;

    public /* synthetic */ e(MainActivity mainActivity, int i7) {
        this.f6532a = i7;
        this.f6533b = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f6532a;
        MainActivity mainActivity = this.f6533b;
        switch (i8) {
            case 0:
                mainActivity.lambda$showSwitchVersionDialog$30(dialogInterface, i7);
                break;
            case 1:
                mainActivity.lambda$showUsageInstructionDialog$16(dialogInterface, i7);
                break;
            case 2:
                mainActivity.lambda$showUsageInstructionDialog$17(dialogInterface, i7);
                break;
            case 3:
                mainActivity.lambda$showOtpDialog$39(dialogInterface, i7);
                break;
            default:
                mainActivity.lambda$onNavigationItemSelected$41(dialogInterface, i7);
                break;
        }
    }
}
