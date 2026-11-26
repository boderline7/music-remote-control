package j4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends View {

    /* renamed from: a, reason: collision with root package name */
    public final Paint f7018a;

    /* renamed from: b, reason: collision with root package name */
    public final RectF f7019b;

    /* renamed from: c, reason: collision with root package name */
    public List f7020c;

    /* renamed from: d, reason: collision with root package name */
    public int f7021d;

    public n(Context context) {
        super(context);
        this.f7018a = new Paint(1);
        this.f7019b = new RectF();
        this.f7020c = Collections.emptyList();
        this.f7021d = 0;
    }

    public final void a(List list) {
        this.f7020c = list != null ? new ArrayList(list) : Collections.emptyList();
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f7020c.isEmpty()) {
            return;
        }
        Iterator it = this.f7020c.iterator();
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            i8 += Math.max(0, ((m) it.next()).f7016a);
        }
        if (i8 <= 0) {
            return;
        }
        float paddingLeft = getPaddingLeft();
        float paddingTop = getPaddingTop();
        float width = getWidth() - getPaddingRight();
        float height = getHeight() - getPaddingBottom();
        int i9 = this.f7021d;
        Paint paint = this.f7018a;
        RectF rectF = this.f7019b;
        if (i9 == 0) {
            float f7 = height - paddingTop;
            while (i7 < this.f7020c.size()) {
                m mVar = (m) this.f7020c.get(i7);
                float f8 = i7 == this.f7020c.size() + (-1) ? height : ((mVar.f7016a * f7) / i8) + paddingTop;
                rectF.set(paddingLeft, paddingTop, width, f8);
                paint.setColor(mVar.f7017b);
                canvas.drawRect(rectF, paint);
                i7++;
                paddingTop = f8;
            }
            return;
        }
        float f9 = width - paddingLeft;
        while (i7 < this.f7020c.size()) {
            m mVar2 = (m) this.f7020c.get(i7);
            float f10 = i7 == this.f7020c.size() + (-1) ? width : ((mVar2.f7016a * f9) / i8) + paddingLeft;
            rectF.set(paddingLeft, paddingTop, f10, height);
            paint.setColor(mVar2.f7017b);
            canvas.drawRect(rectF, paint);
            i7++;
            paddingLeft = f10;
        }
    }
}
