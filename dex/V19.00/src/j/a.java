package j;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements y0.k {

    /* renamed from: a, reason: collision with root package name */
    public final Context f6874a;

    public a(Context context, int i7) {
        if (i7 != 1) {
            this.f6874a = context;
        } else {
            this.f6874a = context.getApplicationContext();
        }
    }

    public static a b(Context context) {
        return new a(context, 0);
    }

    @Override // y0.k
    public final void a(l6.f fVar) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new y0.a("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new y0.n(this, fVar, threadPoolExecutor, 0));
    }

    public final int c() {
        Configuration configuration = this.f6874a.getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        int i8 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i7 > 600) {
            return 5;
        }
        if (i7 > 960 && i8 > 720) {
            return 5;
        }
        if (i7 > 720 && i8 > 960) {
            return 5;
        }
        if (i7 >= 500) {
            return 4;
        }
        if (i7 > 640 && i8 > 480) {
            return 4;
        }
        if (i7 <= 480 || i8 <= 640) {
            return i7 >= 360 ? 3 : 2;
        }
        return 4;
    }

    public final int d() {
        TypedArray obtainStyledAttributes = this.f6874a.obtainStyledAttributes(null, f.j.ActionBar, f.a.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(f.j.ActionBar_height, 0);
        Resources resources = this.f6874a.getResources();
        if (!this.f6874a.getResources().getBoolean(f.b.abc_action_bar_embed_tabs)) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(f.d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }
}
