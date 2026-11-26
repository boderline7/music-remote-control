package t4;

import android.widget.TextView;
import com.yalantis.ucrop.UCropActivity;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k implements y4.d, z4.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9086a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ UCropActivity f9087b;

    public /* synthetic */ k(UCropActivity uCropActivity, int i7) {
        this.f9086a = i7;
        this.f9087b = uCropActivity;
    }

    public final void a(float f7) {
        TextView textView = this.f9087b.f6161v;
        if (textView != null) {
            textView.setText(String.format(Locale.getDefault(), "%d%%", Integer.valueOf((int) (f7 * 100.0f))));
        }
    }
}
