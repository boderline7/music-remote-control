package a3;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY = 2;

    /* renamed from: a, reason: collision with root package name */
    public final a f254a;

    /* renamed from: b, reason: collision with root package name */
    public final View f255b;

    /* renamed from: c, reason: collision with root package name */
    public final Path f256c;

    /* renamed from: d, reason: collision with root package name */
    public final Paint f257d;

    /* renamed from: e, reason: collision with root package name */
    public final Paint f258e;

    /* renamed from: f, reason: collision with root package name */
    public f f259f;

    /* renamed from: g, reason: collision with root package name */
    public Drawable f260g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f261h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f262i;

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.f254a = aVar;
        View view = (View) aVar;
        this.f255b = view;
        view.setWillNotDraw(false);
        this.f256c = new Path();
        this.f257d = new Paint(7);
        Paint paint = new Paint(1);
        this.f258e = paint;
        paint.setColor(0);
    }

    public final void a() {
        if (STRATEGY == 0) {
            this.f261h = true;
            this.f262i = false;
            View view = this.f255b;
            view.buildDrawingCache();
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache == null && view.getWidth() != 0 && view.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                view.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f257d;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f261h = false;
            this.f262i = true;
        }
    }

    public final void b() {
        if (STRATEGY == 0) {
            this.f262i = false;
            View view = this.f255b;
            view.destroyDrawingCache();
            this.f257d.setShader(null);
            view.invalidate();
        }
    }

    public final void c(Canvas canvas) {
        Drawable drawable;
        boolean i7 = i();
        Paint paint = this.f258e;
        a aVar = this.f254a;
        View view = this.f255b;
        if (i7) {
            int i8 = STRATEGY;
            if (i8 == 0) {
                f fVar = this.f259f;
                canvas.drawCircle(fVar.f264a, fVar.f265b, fVar.f266c, this.f257d);
                if (j()) {
                    f fVar2 = this.f259f;
                    canvas.drawCircle(fVar2.f264a, fVar2.f265b, fVar2.f266c, paint);
                }
            } else if (i8 == 1) {
                int save = canvas.save();
                canvas.clipPath(this.f256c);
                aVar.a(canvas);
                if (j()) {
                    canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, view.getWidth(), view.getHeight(), paint);
                }
                canvas.restoreToCount(save);
            } else {
                if (i8 != 2) {
                    throw new IllegalStateException(a.a.d("Unsupported strategy ", i8));
                }
                aVar.a(canvas);
                if (j()) {
                    canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, view.getWidth(), view.getHeight(), paint);
                }
            }
        } else {
            aVar.a(canvas);
            if (j()) {
                canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, view.getWidth(), view.getHeight(), paint);
            }
        }
        if (this.f261h || (drawable = this.f260g) == null || this.f259f == null) {
            return;
        }
        Rect bounds = drawable.getBounds();
        float width = this.f259f.f264a - (bounds.width() / 2.0f);
        float height = this.f259f.f265b - (bounds.height() / 2.0f);
        canvas.translate(width, height);
        this.f260g.draw(canvas);
        canvas.translate(-width, -height);
    }

    public final f d() {
        f fVar = this.f259f;
        if (fVar == null) {
            return null;
        }
        f fVar2 = new f(fVar);
        if (fVar2.f266c == Float.MAX_VALUE) {
            float f7 = fVar2.f264a;
            float f8 = fVar2.f265b;
            View view = this.f255b;
            fVar2.f266c = i3.a.b(f7, f8, view.getWidth(), view.getHeight());
        }
        return fVar2;
    }

    public final boolean e() {
        return this.f254a.h() && !i();
    }

    public final void f(Drawable drawable) {
        this.f260g = drawable;
        this.f255b.invalidate();
    }

    public final void g(int i7) {
        this.f258e.setColor(i7);
        this.f255b.invalidate();
    }

    public final void h(f fVar) {
        View view = this.f255b;
        if (fVar == null) {
            this.f259f = null;
        } else {
            f fVar2 = this.f259f;
            if (fVar2 == null) {
                this.f259f = new f(fVar);
            } else {
                float f7 = fVar.f264a;
                float f8 = fVar.f265b;
                float f9 = fVar.f266c;
                fVar2.f264a = f7;
                fVar2.f265b = f8;
                fVar2.f266c = f9;
            }
            if (fVar.f266c + 1.0E-4f >= i3.a.b(fVar.f264a, fVar.f265b, view.getWidth(), view.getHeight())) {
                this.f259f.f266c = Float.MAX_VALUE;
            }
        }
        if (STRATEGY == 1) {
            Path path = this.f256c;
            path.rewind();
            f fVar3 = this.f259f;
            if (fVar3 != null) {
                path.addCircle(fVar3.f264a, fVar3.f265b, fVar3.f266c, Path.Direction.CW);
            }
        }
        view.invalidate();
    }

    public final boolean i() {
        f fVar = this.f259f;
        boolean z6 = fVar == null || fVar.f266c == Float.MAX_VALUE;
        return STRATEGY == 0 ? !z6 && this.f262i : !z6;
    }

    public final boolean j() {
        return (this.f261h || Color.alpha(this.f258e.getColor()) == 0) ? false : true;
    }
}
