package g4;

import android.view.View;
import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6527a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6528b;

    public /* synthetic */ c(MainActivity mainActivity, int i7) {
        this.f6527a = i7;
        this.f6528b = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f6527a;
        MainActivity mainActivity = this.f6528b;
        switch (i7) {
            case 0:
                mainActivity.lambda$updateToolbarTitle$23(view);
                break;
            case 1:
                mainActivity.lambda$installTireEntry$25(view);
                break;
            case 2:
                mainActivity.lambda$onCreate$0(view);
                break;
            case 3:
                mainActivity.lambda$onCreate$15(view);
                break;
            case 4:
                mainActivity.lambda$onCreate$9(view);
                break;
            case 5:
                mainActivity.lambda$onCreate$10(view);
                break;
            case 6:
                mainActivity.lambda$onCreate$11(view);
                break;
            case 7:
                mainActivity.lambda$onCreate$12(view);
                break;
            default:
                mainActivity.lambda$onCreate$13(view);
                break;
        }
    }
}
