package androidx.appcompat.app;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h implements w0.c {

    /* renamed from: a, reason: collision with root package name */
    public final d f718a;

    /* renamed from: b, reason: collision with root package name */
    public final DrawerLayout f719b;

    /* renamed from: c, reason: collision with root package name */
    public final g.d f720c;

    /* renamed from: d, reason: collision with root package name */
    public final Drawable f721d;

    /* renamed from: f, reason: collision with root package name */
    public final int f723f;

    /* renamed from: g, reason: collision with root package name */
    public final int f724g;

    /* renamed from: e, reason: collision with root package name */
    public boolean f722e = true;

    /* renamed from: h, reason: collision with root package name */
    public boolean f725h = false;

    /* JADX WARN: Multi-variable type inference failed */
    public h(Activity activity, DrawerLayout drawerLayout, MaterialToolbar materialToolbar, int i7, int i8) {
        int i9 = 0;
        if (materialToolbar != null) {
            this.f718a = new g(materialToolbar);
            materialToolbar.setNavigationOnClickListener(new c(i9, this));
        } else if (activity instanceof e) {
            this.f718a = ((e) activity).getDrawerToggleDelegate();
        } else {
            this.f718a = new i6.i(activity);
        }
        this.f719b = drawerLayout;
        this.f723f = i7;
        this.f724g = i8;
        this.f720c = new g.d(this.f718a.m());
        this.f721d = this.f718a.B();
    }

    @Override // w0.c
    public final void a(View view) {
        e(1.0f);
        if (this.f722e) {
            this.f718a.b(this.f724g);
        }
    }

    @Override // w0.c
    public final void b() {
    }

    @Override // w0.c
    public final void c(float f7) {
        e(Math.min(1.0f, Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f7)));
    }

    @Override // w0.c
    public final void d(View view) {
        e(CropImageView.DEFAULT_ASPECT_RATIO);
        if (this.f722e) {
            this.f718a.b(this.f723f);
        }
    }

    public final void e(float f7) {
        g.d dVar = this.f720c;
        if (f7 == 1.0f) {
            if (!dVar.f6419i) {
                dVar.f6419i = true;
                dVar.invalidateSelf();
            }
        } else if (f7 == CropImageView.DEFAULT_ASPECT_RATIO && dVar.f6419i) {
            dVar.f6419i = false;
            dVar.invalidateSelf();
        }
        dVar.b(f7);
    }
}
