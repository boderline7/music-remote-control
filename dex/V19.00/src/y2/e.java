package y2;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.o1;
import com.google.android.material.carousel.CarouselLayoutManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends o1 {

    /* renamed from: a, reason: collision with root package name */
    public final Paint f10137a;

    /* renamed from: b, reason: collision with root package name */
    public List f10138b;

    public e() {
        Paint paint = new Paint();
        this.f10137a = paint;
        this.f10138b = Collections.unmodifiableList(new ArrayList());
        paint.setStrokeWidth(5.0f);
        paint.setColor(-65281);
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        int i7;
        super.onDrawOver(canvas, recyclerView, g2Var);
        Paint paint = this.f10137a;
        paint.setStrokeWidth(recyclerView.getResources().getDimension(q2.e.m3_carousel_debug_keyline_width));
        for (h hVar : this.f10138b) {
            paint.setColor(i0.a.b(-65281, hVar.f10152c, -16776961));
            if (((CarouselLayoutManager) recyclerView.getLayoutManager()).U0()) {
                float f7 = hVar.f10151b;
                f fVar = ((CarouselLayoutManager) recyclerView.getLayoutManager()).f3498y;
                switch (fVar.f10139b) {
                    case 0:
                        i7 = 0;
                        break;
                    default:
                        i7 = fVar.f10140c.J();
                        break;
                }
                canvas.drawLine(f7, i7, hVar.f10151b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).f3498y.a(), paint);
            } else {
                canvas.drawLine(((CarouselLayoutManager) recyclerView.getLayoutManager()).f3498y.b(), hVar.f10151b, ((CarouselLayoutManager) recyclerView.getLayoutManager()).f3498y.c(), hVar.f10151b, paint);
            }
        }
    }
}
