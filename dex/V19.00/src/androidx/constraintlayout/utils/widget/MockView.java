package androidx.constraintlayout.utils.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MockView extends View {

    /* renamed from: a, reason: collision with root package name */
    public final Paint f1625a;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f1626b;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f1627c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1628d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1629e;

    /* renamed from: f, reason: collision with root package name */
    public String f1630f;

    /* renamed from: g, reason: collision with root package name */
    public final Rect f1631g;

    /* renamed from: h, reason: collision with root package name */
    public int f1632h;

    /* renamed from: i, reason: collision with root package name */
    public int f1633i;

    /* renamed from: j, reason: collision with root package name */
    public int f1634j;

    /* renamed from: k, reason: collision with root package name */
    public int f1635k;

    public MockView(Context context) {
        super(context);
        this.f1625a = new Paint();
        this.f1626b = new Paint();
        this.f1627c = new Paint();
        this.f1628d = true;
        this.f1629e = true;
        this.f1630f = null;
        this.f1631g = new Rect();
        this.f1632h = Color.argb(255, 0, 0, 0);
        this.f1633i = Color.argb(255, 200, 200, 200);
        this.f1634j = Color.argb(255, 50, 50, 50);
        this.f1635k = 4;
        a(context, null);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1625a = new Paint();
        this.f1626b = new Paint();
        this.f1627c = new Paint();
        this.f1628d = true;
        this.f1629e = true;
        this.f1630f = null;
        this.f1631g = new Rect();
        this.f1632h = Color.argb(255, 0, 0, 0);
        this.f1633i = Color.argb(255, 200, 200, 200);
        this.f1634j = Color.argb(255, 50, 50, 50);
        this.f1635k = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1625a = new Paint();
        this.f1626b = new Paint();
        this.f1627c = new Paint();
        this.f1628d = true;
        this.f1629e = true;
        this.f1630f = null;
        this.f1631g = new Rect();
        this.f1632h = Color.argb(255, 0, 0, 0);
        this.f1633i = Color.argb(255, 200, 200, 200);
        this.f1634j = Color.argb(255, 50, 50, 50);
        this.f1635k = 4;
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.MockView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.MockView_mock_label) {
                    this.f1630f = obtainStyledAttributes.getString(index);
                } else if (index == t.MockView_mock_showDiagonals) {
                    this.f1628d = obtainStyledAttributes.getBoolean(index, this.f1628d);
                } else if (index == t.MockView_mock_diagonalsColor) {
                    this.f1632h = obtainStyledAttributes.getColor(index, this.f1632h);
                } else if (index == t.MockView_mock_labelBackgroundColor) {
                    this.f1634j = obtainStyledAttributes.getColor(index, this.f1634j);
                } else if (index == t.MockView_mock_labelColor) {
                    this.f1633i = obtainStyledAttributes.getColor(index, this.f1633i);
                } else if (index == t.MockView_mock_showLabel) {
                    this.f1629e = obtainStyledAttributes.getBoolean(index, this.f1629e);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.f1630f == null) {
            try {
                this.f1630f = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        int i8 = this.f1632h;
        Paint paint = this.f1625a;
        paint.setColor(i8);
        paint.setAntiAlias(true);
        int i9 = this.f1633i;
        Paint paint2 = this.f1626b;
        paint2.setColor(i9);
        paint2.setAntiAlias(true);
        this.f1627c.setColor(this.f1634j);
        this.f1635k = Math.round((getResources().getDisplayMetrics().xdpi / 160.0f) * this.f1635k);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f1628d) {
            width--;
            height--;
            float f7 = width;
            float f8 = height;
            canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f7, f8, this.f1625a);
            canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, f8, f7, CropImageView.DEFAULT_ASPECT_RATIO, this.f1625a);
            canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f7, CropImageView.DEFAULT_ASPECT_RATIO, this.f1625a);
            canvas.drawLine(f7, CropImageView.DEFAULT_ASPECT_RATIO, f7, f8, this.f1625a);
            canvas.drawLine(f7, f8, CropImageView.DEFAULT_ASPECT_RATIO, f8, this.f1625a);
            canvas.drawLine(CropImageView.DEFAULT_ASPECT_RATIO, f8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f1625a);
        }
        String str = this.f1630f;
        if (str == null || !this.f1629e) {
            return;
        }
        int length = str.length();
        Paint paint = this.f1626b;
        Rect rect = this.f1631g;
        paint.getTextBounds(str, 0, length, rect);
        float width2 = (width - rect.width()) / 2.0f;
        float height2 = ((height - rect.height()) / 2.0f) + rect.height();
        rect.offset((int) width2, (int) height2);
        int i7 = rect.left;
        int i8 = this.f1635k;
        rect.set(i7 - i8, rect.top - i8, rect.right + i8, rect.bottom + i8);
        canvas.drawRect(rect, this.f1627c);
        canvas.drawText(this.f1630f, width2, height2, paint);
    }
}
