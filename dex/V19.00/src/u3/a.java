package u3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import androidx.appcompat.widget.u2;
import com.google.android.material.internal.a0;
import com.google.android.material.internal.b0;
import com.yalantis.ucrop.view.CropImageView;
import m3.e;
import p3.g;
import p3.j;
import p3.k;
import p3.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends j implements a0 {
    public static final /* synthetic */ int O = 0;
    public final Paint.FontMetrics A;
    public final b0 B;
    public final u2 C;
    public final Rect D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public float K;
    public float L;
    public float M;
    public float N;

    /* renamed from: y, reason: collision with root package name */
    public CharSequence f9312y;

    /* renamed from: z, reason: collision with root package name */
    public final Context f9313z;

    public a(Context context, int i7) {
        super(context, null, 0, i7);
        this.A = new Paint.FontMetrics();
        b0 b0Var = new b0(this);
        this.B = b0Var;
        this.C = new u2(2, this);
        this.D = new Rect();
        this.K = 1.0f;
        this.L = 1.0f;
        this.M = 0.5f;
        this.N = 1.0f;
        this.f9313z = context;
        TextPaint textPaint = b0Var.f3796a;
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.save();
        float y6 = y();
        float f7 = (float) (-((Math.sqrt(2.0d) * this.I) - this.I));
        canvas.scale(this.K, this.L, (getBounds().width() * 0.5f) + getBounds().left, (getBounds().height() * this.M) + getBounds().top);
        canvas.translate(y6, f7);
        super.draw(canvas);
        if (this.f9312y != null) {
            float centerY = getBounds().centerY();
            b0 b0Var = this.B;
            TextPaint textPaint = b0Var.f3796a;
            Paint.FontMetrics fontMetrics = this.A;
            textPaint.getFontMetrics(fontMetrics);
            int i7 = (int) (centerY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f));
            e eVar = b0Var.f3802g;
            TextPaint textPaint2 = b0Var.f3796a;
            if (eVar != null) {
                textPaint2.drawableState = getState();
                b0Var.f3802g.e(this.f9313z, textPaint2, b0Var.f3797b);
                textPaint2.setAlpha((int) (this.N * 255.0f));
            }
            CharSequence charSequence = this.f9312y;
            canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), i7, textPaint2);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) Math.max(this.B.f3796a.getTextSize(), this.G);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float f7 = this.E * 2;
        CharSequence charSequence = this.f9312y;
        return (int) Math.max(f7 + (charSequence == null ? CropImageView.DEFAULT_ASPECT_RATIO : this.B.a(charSequence.toString())), this.F);
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        n g7 = this.f8263a.f8241a.g();
        g7.f8299k = z();
        setShapeAppearanceModel(g7.a());
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final float y() {
        int i7;
        Rect rect = this.D;
        if (((rect.right - getBounds().right) - this.J) - this.H < 0) {
            i7 = ((rect.right - getBounds().right) - this.J) - this.H;
        } else {
            if (((rect.left - getBounds().left) - this.J) + this.H <= 0) {
                return CropImageView.DEFAULT_ASPECT_RATIO;
            }
            i7 = ((rect.left - getBounds().left) - this.J) + this.H;
        }
        return i7;
    }

    public final k z() {
        float f7 = -y();
        float width = ((float) (getBounds().width() - (Math.sqrt(2.0d) * this.I))) / 2.0f;
        return new k(new g(this.I), Math.min(Math.max(f7, -width), width));
    }
}
