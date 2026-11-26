package androidx.appcompat.widget;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class e0 {

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f1209c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};

    /* renamed from: a, reason: collision with root package name */
    public final ProgressBar f1210a;

    /* renamed from: b, reason: collision with root package name */
    public Bitmap f1211b;

    public e0(ProgressBar progressBar) {
        this.f1210a = progressBar;
    }

    public void a(AttributeSet attributeSet, int i7) {
        ProgressBar progressBar = this.f1210a;
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(progressBar.getContext(), attributeSet, f1209c, i7);
        Drawable J = S.J(0);
        if (J != null) {
            if (J instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) J;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i8 = 0; i8 < numberOfFrames; i8++) {
                    Drawable b7 = b(animationDrawable.getFrame(i8), true);
                    b7.setLevel(10000);
                    animationDrawable2.addFrame(b7, animationDrawable.getDuration(i8));
                }
                animationDrawable2.setLevel(10000);
                J = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(J);
        }
        Drawable J2 = S.J(1);
        if (J2 != null) {
            progressBar.setProgressDrawable(b(J2, false));
        }
        S.V();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Drawable b(Drawable drawable, boolean z6) {
        if (drawable instanceof j0.h) {
            ((j0.i) ((j0.h) drawable)).getClass();
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i7 = 0; i7 < numberOfLayers; i7++) {
                    int id = layerDrawable.getId(i7);
                    drawableArr[i7] = b(layerDrawable.getDrawable(i7), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i8 = 0; i8 < numberOfLayers; i8++) {
                    layerDrawable2.setId(i8, layerDrawable.getId(i8));
                    layerDrawable2.setLayerGravity(i8, layerDrawable.getLayerGravity(i8));
                    layerDrawable2.setLayerWidth(i8, layerDrawable.getLayerWidth(i8));
                    layerDrawable2.setLayerHeight(i8, layerDrawable.getLayerHeight(i8));
                    layerDrawable2.setLayerInsetLeft(i8, layerDrawable.getLayerInsetLeft(i8));
                    layerDrawable2.setLayerInsetRight(i8, layerDrawable.getLayerInsetRight(i8));
                    layerDrawable2.setLayerInsetTop(i8, layerDrawable.getLayerInsetTop(i8));
                    layerDrawable2.setLayerInsetBottom(i8, layerDrawable.getLayerInsetBottom(i8));
                    layerDrawable2.setLayerInsetStart(i8, layerDrawable.getLayerInsetStart(i8));
                    layerDrawable2.setLayerInsetEnd(i8, layerDrawable.getLayerInsetEnd(i8));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.f1211b == null) {
                    this.f1211b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z6 ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }
}
