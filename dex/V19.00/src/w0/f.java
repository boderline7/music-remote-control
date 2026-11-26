package w0;

import android.view.View;
import androidx.appcompat.app.s0;
import androidx.drawerlayout.widget.DrawerLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final int f9409d;

    /* renamed from: e, reason: collision with root package name */
    public u0.e f9410e;

    /* renamed from: f, reason: collision with root package name */
    public final s0 f9411f = new s0(9, this);

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ DrawerLayout f9412g;

    public f(DrawerLayout drawerLayout, int i7) {
        this.f9412g = drawerLayout;
        this.f9409d = i7;
    }

    @Override // l6.f
    public final boolean E0(int i7, View view) {
        DrawerLayout drawerLayout = this.f9412g;
        drawerLayout.getClass();
        return DrawerLayout.p(view) && drawerLayout.b(this.f9409d, view) && drawerLayout.j(view) == 0;
    }

    @Override // l6.f
    public final int I(View view) {
        this.f9412g.getClass();
        if (DrawerLayout.p(view)) {
            return view.getWidth();
        }
        return 0;
    }

    @Override // l6.f
    public final void W(int i7, int i8) {
        int i9 = i7 & 1;
        DrawerLayout drawerLayout = this.f9412g;
        View f7 = i9 == 1 ? drawerLayout.f(3) : drawerLayout.f(5);
        if (f7 == null || drawerLayout.j(f7) != 0) {
            return;
        }
        this.f9410e.b(i8, f7);
    }

    @Override // l6.f
    public final void X() {
        this.f9412g.postDelayed(this.f9411f, 160L);
    }

    @Override // l6.f
    public final void h0(int i7, View view) {
        ((d) view.getLayoutParams()).f9407c = false;
        int i8 = this.f9409d == 3 ? 5 : 3;
        DrawerLayout drawerLayout = this.f9412g;
        View f7 = drawerLayout.f(i8);
        if (f7 != null) {
            drawerLayout.c(f7, true);
        }
    }

    @Override // l6.f
    public final void i0(int i7) {
        this.f9412g.y(i7, this.f9410e.f9256t);
    }

    @Override // l6.f
    public final void j0(View view, int i7, int i8) {
        int width = view.getWidth();
        DrawerLayout drawerLayout = this.f9412g;
        float width2 = (drawerLayout.b(3, view) ? i7 + width : drawerLayout.getWidth() - i7) / width;
        drawerLayout.v(view, width2);
        view.setVisibility(width2 == CropImageView.DEFAULT_ASPECT_RATIO ? 4 : 0);
        drawerLayout.invalidate();
    }

    @Override // l6.f
    public final void k0(View view, float f7, float f8) {
        int i7;
        DrawerLayout drawerLayout = this.f9412g;
        drawerLayout.getClass();
        float f9 = ((d) view.getLayoutParams()).f9406b;
        int width = view.getWidth();
        if (drawerLayout.b(3, view)) {
            i7 = (f7 > CropImageView.DEFAULT_ASPECT_RATIO || (f7 == CropImageView.DEFAULT_ASPECT_RATIO && f9 > 0.5f)) ? 0 : -width;
        } else {
            int width2 = drawerLayout.getWidth();
            if (f7 < CropImageView.DEFAULT_ASPECT_RATIO || (f7 == CropImageView.DEFAULT_ASPECT_RATIO && f9 > 0.5f)) {
                width2 -= width;
            }
            i7 = width2;
        }
        this.f9410e.p(i7, view.getTop());
        drawerLayout.invalidate();
    }

    @Override // l6.f
    public final int n(View view, int i7) {
        DrawerLayout drawerLayout = this.f9412g;
        if (drawerLayout.b(3, view)) {
            return Math.max(-view.getWidth(), Math.min(i7, 0));
        }
        int width = drawerLayout.getWidth();
        return Math.max(width - view.getWidth(), Math.min(i7, width));
    }

    @Override // l6.f
    public final int o(View view, int i7) {
        return view.getTop();
    }
}
