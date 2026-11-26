package y4;

import android.graphics.Matrix;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import java.util.Locale;
import l6.f;
import t4.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ GestureCropImageView f10208d;

    public c(GestureCropImageView gestureCropImageView) {
        this.f10208d = gestureCropImageView;
    }

    @Override // l6.f
    public final void f0(x4.c cVar) {
        float f7 = cVar.f9643g;
        GestureCropImageView gestureCropImageView = this.f10208d;
        float f8 = gestureCropImageView.J;
        float f9 = gestureCropImageView.K;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            Matrix matrix = gestureCropImageView.f6204g;
            matrix.postRotate(f7, f8, f9);
            gestureCropImageView.setImageMatrix(matrix);
            d dVar = gestureCropImageView.f6207j;
            if (dVar != null) {
                float[] fArr = gestureCropImageView.f6203f;
                matrix.getValues(fArr);
                double d7 = fArr[1];
                matrix.getValues(fArr);
                float f10 = (float) (-(Math.atan2(d7, fArr[0]) * 57.29577951308232d));
                TextView textView = ((k) dVar).f9087b.f6160u;
                if (textView != null) {
                    textView.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(f10)));
                }
            }
        }
    }
}
