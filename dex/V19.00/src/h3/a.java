package h3;

import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.imageview.ShapeableImageView;
import p3.j;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final Rect f6721a = new Rect();

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ShapeableImageView f6722b;

    public a(ShapeableImageView shapeableImageView) {
        this.f6722b = shapeableImageView;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        ShapeableImageView shapeableImageView = this.f6722b;
        if (shapeableImageView.f3749l == null) {
            return;
        }
        if (shapeableImageView.f3748k == null) {
            shapeableImageView.f3748k = new j(shapeableImageView.f3749l);
        }
        RectF rectF = shapeableImageView.f3742e;
        Rect rect = this.f6721a;
        rectF.round(rect);
        shapeableImageView.f3748k.setBounds(rect);
        shapeableImageView.f3748k.getOutline(outline);
    }
}
