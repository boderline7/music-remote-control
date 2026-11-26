package g4;

import android.app.ProgressDialog;
import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6540a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6541b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f6542c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ProgressDialog f6543d;

    public /* synthetic */ i(MainActivity mainActivity, ProgressDialog progressDialog, String str) {
        this.f6541b = mainActivity;
        this.f6543d = progressDialog;
        this.f6542c = str;
    }

    public /* synthetic */ i(MainActivity mainActivity, String str, ProgressDialog progressDialog) {
        this.f6541b = mainActivity;
        this.f6542c = str;
        this.f6543d = progressDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f6540a;
        MainActivity mainActivity = this.f6541b;
        ProgressDialog progressDialog = this.f6543d;
        String str = this.f6542c;
        switch (i7) {
            case 0:
                mainActivity.lambda$handleStartTrial$27(progressDialog, str);
                break;
            default:
                mainActivity.lambda$handleStartTrial$29(str, progressDialog);
                break;
        }
    }
}
