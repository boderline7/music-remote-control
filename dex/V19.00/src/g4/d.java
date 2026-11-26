package g4;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RadioGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.navi.tool.MainActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6529a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f6530b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f6531c;

    public /* synthetic */ d(MainActivity mainActivity, View view, int i7) {
        this.f6529a = i7;
        this.f6530b = mainActivity;
        this.f6531c = view;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f6529a;
        MainActivity mainActivity = this.f6530b;
        View view = this.f6531c;
        switch (i8) {
            case 0:
                mainActivity.lambda$showThemeSelectorDialog$40((RadioGroup) view, dialogInterface, i7);
                break;
            default:
                mainActivity.lambda$showOtpDialog$38((TextInputEditText) view, dialogInterface, i7);
                break;
        }
    }
}
