package t4;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9088a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ UCropActivity f9089b;

    public /* synthetic */ l(UCropActivity uCropActivity, int i7) {
        this.f9088a = i7;
        this.f9089b = uCropActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f9088a;
        UCropActivity uCropActivity = this.f9089b;
        switch (i7) {
            case 0:
                GestureCropImageView gestureCropImageView = uCropActivity.f6151l;
                AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) ((ViewGroup) view).getChildAt(0);
                if (view.isSelected()) {
                    if (aspectRatioTextView.f6223k != CropImageView.DEFAULT_ASPECT_RATIO) {
                        float f7 = aspectRatioTextView.f6225m;
                        float f8 = aspectRatioTextView.f6226n;
                        aspectRatioTextView.f6225m = f8;
                        aspectRatioTextView.f6226n = f7;
                        aspectRatioTextView.f6223k = f8 / f7;
                    }
                    aspectRatioTextView.n();
                }
                gestureCropImageView.setTargetAspectRatio(aspectRatioTextView.f6223k);
                uCropActivity.f6151l.setImageToWrapCropBounds();
                if (!view.isSelected()) {
                    Iterator it = uCropActivity.f6159t.iterator();
                    while (it.hasNext()) {
                        ViewGroup viewGroup = (ViewGroup) it.next();
                        viewGroup.setSelected(viewGroup == view);
                    }
                    break;
                }
                break;
            case 1:
                GestureCropImageView gestureCropImageView2 = uCropActivity.f6151l;
                float f9 = -gestureCropImageView2.getCurrentAngle();
                RectF rectF = gestureCropImageView2.f6169u;
                float centerX = rectF.centerX();
                float centerY = rectF.centerY();
                if (f9 != CropImageView.DEFAULT_ASPECT_RATIO) {
                    Matrix matrix = gestureCropImageView2.f6204g;
                    matrix.postRotate(f9, centerX, centerY);
                    gestureCropImageView2.setImageMatrix(matrix);
                    y4.d dVar = gestureCropImageView2.f6207j;
                    if (dVar != null) {
                        float[] fArr = gestureCropImageView2.f6203f;
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
                uCropActivity.f6151l.setImageToWrapCropBounds();
                break;
            case 2:
                GestureCropImageView gestureCropImageView3 = uCropActivity.f6151l;
                float f11 = 90;
                RectF rectF2 = gestureCropImageView3.f6169u;
                float centerX2 = rectF2.centerX();
                float centerY2 = rectF2.centerY();
                if (f11 != CropImageView.DEFAULT_ASPECT_RATIO) {
                    Matrix matrix2 = gestureCropImageView3.f6204g;
                    matrix2.postRotate(f11, centerX2, centerY2);
                    gestureCropImageView3.setImageMatrix(matrix2);
                    y4.d dVar2 = gestureCropImageView3.f6207j;
                    if (dVar2 != null) {
                        float[] fArr2 = gestureCropImageView3.f6203f;
                        matrix2.getValues(fArr2);
                        double d8 = fArr2[1];
                        matrix2.getValues(fArr2);
                        float f12 = (float) (-(Math.atan2(d8, fArr2[0]) * 57.29577951308232d));
                        TextView textView2 = ((k) dVar2).f9087b.f6160u;
                        if (textView2 != null) {
                            textView2.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(f12)));
                        }
                    }
                }
                uCropActivity.f6151l.setImageToWrapCropBounds();
                break;
            default:
                if (!view.isSelected()) {
                    int id = view.getId();
                    int i8 = UCropActivity.DEFAULT_COMPRESS_QUALITY;
                    uCropActivity.g(id);
                    break;
                }
                break;
        }
    }
}
