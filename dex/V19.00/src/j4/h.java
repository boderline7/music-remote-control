package j4;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.navi.tool.amapflying.util.FloatingWindowService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WindowManager f6995a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FloatingWindowService f6996b;

    public h(FloatingWindowService floatingWindowService, WindowManager windowManager) {
        this.f6996b = floatingWindowService;
        this.f6995a = windowManager;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        FloatingWindowService floatingWindowService = this.f6996b;
        try {
            int width = floatingWindowService.f4535d.getWidth();
            int height = floatingWindowService.f4535d.getHeight();
            DisplayMetrics displayMetrics = floatingWindowService.f4542k.getResources().getDisplayMetrics();
            int i7 = displayMetrics.widthPixels / 2;
            int i8 = displayMetrics.heightPixels;
            float f7 = z1.c.INVOKE_VIRTUAL;
            Context context = floatingWindowService.f4542k;
            int round = (i8 - Math.round(f7 * (context != null ? context.getResources() : floatingWindowService.getResources()).getDisplayMetrics().density)) - (height / 2);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) floatingWindowService.f4535d.getLayoutParams();
            layoutParams.x = i7 - (width / 2);
            layoutParams.y = round;
            this.f6995a.updateViewLayout(floatingWindowService.f4535d, layoutParams);
            floatingWindowService.f4535d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } catch (Throwable unused) {
        }
    }
}
