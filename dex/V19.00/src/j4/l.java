package j4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import com.navi.tool.amapflying.util.TmcBarFloatingView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends View {
    public static final int ORIENTATION_RIGHT = 1;
    public static final int ORIENTATION_UP = 0;

    /* renamed from: a, reason: collision with root package name */
    public final Paint f7012a;

    /* renamed from: b, reason: collision with root package name */
    public final Path f7013b;

    /* renamed from: c, reason: collision with root package name */
    public int f7014c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ TmcBarFloatingView f7015d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(TmcBarFloatingView tmcBarFloatingView, Context context) {
        super(context);
        this.f7015d = tmcBarFloatingView;
        this.f7014c = 0;
        this.f7012a = new Paint(1);
        this.f7013b = new Path();
        setLayerType(1, null);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int i7 = width / 2;
        int i8 = height / 2;
        int i9 = TmcBarFloatingView.POSITION_RIGHT;
        int a7 = this.f7015d.a(2);
        int min = (Math.min(width, height) / 2) - a7;
        this.f7012a.setColor(-1);
        Paint paint = this.f7012a;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        float f7 = a7;
        this.f7012a.setShadowLayer(f7, CropImageView.DEFAULT_ASPECT_RATIO, f7 / 2.0f, Color.parseColor("#40000000"));
        float f8 = i7;
        float f9 = i8;
        float f10 = min;
        canvas.drawCircle(f8, f9, f10, this.f7012a);
        this.f7012a.clearShadowLayer();
        this.f7012a.setStyle(Paint.Style.STROKE);
        this.f7012a.setColor(-3355444);
        this.f7012a.setStrokeWidth(r4.a(1));
        canvas.drawCircle(f8, f9, f10, this.f7012a);
        this.f7012a.setStyle(style);
        this.f7012a.setColor(Color.parseColor("#4081FF"));
        this.f7012a.setPathEffect(new CornerPathEffect(r4.a(3)));
        int i10 = (int) (f10 * 1.0f);
        this.f7013b.reset();
        if (this.f7014c == 1) {
            float f11 = i10 / 2.0f;
            float f12 = f8 + f11;
            float f13 = f8 - f11;
            this.f7013b.moveTo(f12, f9);
            this.f7013b.lineTo(f13, f9 - f11);
            this.f7013b.lineTo(f13, f11 + f9);
        } else {
            float f14 = i10 / 2.0f;
            float f15 = f9 - f14;
            float f16 = f9 + f14;
            this.f7013b.moveTo(f8, f15);
            this.f7013b.lineTo(f8 - f14, f16);
            this.f7013b.lineTo(f14 + f8, f16);
        }
        this.f7013b.close();
        canvas.drawPath(this.f7013b, this.f7012a);
        this.f7012a.setPathEffect(null);
    }
}
