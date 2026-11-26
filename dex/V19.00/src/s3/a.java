package s3;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends com.google.android.material.tabs.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8693a;

    public /* synthetic */ a(int i7) {
        this.f8693a = i7;
    }

    @Override // com.google.android.material.tabs.a
    public final void b(TabLayout tabLayout, View view, View view2, float f7, Drawable drawable) {
        float cos;
        float f8;
        switch (this.f8693a) {
            case 0:
                RectF a7 = com.google.android.material.tabs.a.a(tabLayout, view);
                RectF a8 = com.google.android.material.tabs.a.a(tabLayout, view2);
                if (a7.left < a8.left) {
                    double d7 = (f7 * 3.141592653589793d) / 2.0d;
                    f8 = (float) (1.0d - Math.cos(d7));
                    cos = (float) Math.sin(d7);
                } else {
                    double d8 = (f7 * 3.141592653589793d) / 2.0d;
                    float sin = (float) Math.sin(d8);
                    cos = (float) (1.0d - Math.cos(d8));
                    f8 = sin;
                }
                drawable.setBounds(r2.a.c((int) a7.left, f8, (int) a8.left), drawable.getBounds().top, r2.a.c((int) a7.right, cos, (int) a8.right), drawable.getBounds().bottom);
                break;
            default:
                if (f7 >= 0.5f) {
                    view = view2;
                }
                RectF a9 = com.google.android.material.tabs.a.a(tabLayout, view);
                float b7 = f7 < 0.5f ? r2.a.b(1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, f7) : r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.5f, 1.0f, f7);
                drawable.setBounds((int) a9.left, drawable.getBounds().top, (int) a9.right, drawable.getBounds().bottom);
                drawable.setAlpha((int) (b7 * 255.0f));
                break;
        }
    }
}
