package n1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends Property {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7588a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7589b;

    public b() {
        super(Matrix.class, "imageMatrixProperty");
        this.f7589b = new Matrix();
    }

    public b(int i7) {
        super(PointF.class, "boundsOrigin");
        this.f7589b = new Rect();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(g3.a aVar) {
        super(Float.class, "LABEL_OPACITY_PROPERTY");
        this.f7589b = aVar;
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.f7588a) {
            case 0:
                ((Drawable) obj).copyBounds((Rect) this.f7589b);
                Rect rect = (Rect) this.f7589b;
                return new PointF(rect.left, rect.top);
            case 1:
                ((Matrix) this.f7589b).set(((ImageView) obj).getImageMatrix());
                return (Matrix) this.f7589b;
            default:
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                return Float.valueOf(r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.G.getColorForState(extendedFloatingActionButton.getDrawableState(), ((g3.a) this.f7589b).f6434b.G.getDefaultColor()))));
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.f7588a) {
            case 0:
                Drawable drawable = (Drawable) obj;
                PointF pointF = (PointF) obj2;
                drawable.copyBounds((Rect) this.f7589b);
                ((Rect) this.f7589b).offsetTo(Math.round(pointF.x), Math.round(pointF.y));
                drawable.setBounds((Rect) this.f7589b);
                break;
            case 1:
                ((ImageView) obj).setImageMatrix((Matrix) obj2);
                break;
            default:
                ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                Float f7 = (Float) obj2;
                int colorForState = extendedFloatingActionButton.G.getColorForState(extendedFloatingActionButton.getDrawableState(), ((g3.a) this.f7589b).f6434b.G.getDefaultColor());
                ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (r2.a.a(CropImageView.DEFAULT_ASPECT_RATIO, Color.alpha(colorForState) / 255.0f, f7.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                if (f7.floatValue() != 1.0f) {
                    extendedFloatingActionButton.g(valueOf);
                    break;
                } else {
                    extendedFloatingActionButton.g(extendedFloatingActionButton.G);
                    break;
                }
        }
    }
}
