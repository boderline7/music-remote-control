package l3;

import android.os.SystemClock;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7339a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ BaseProgressIndicator f7340b;

    public /* synthetic */ b(BaseProgressIndicator baseProgressIndicator, int i7) {
        this.f7339a = i7;
        this.f7340b = baseProgressIndicator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7339a;
        BaseProgressIndicator baseProgressIndicator = this.f7340b;
        switch (i7) {
            case 0:
                int i8 = BaseProgressIndicator.SHOW_NONE;
                if (baseProgressIndicator.f4027e > 0) {
                    SystemClock.uptimeMillis();
                }
                baseProgressIndicator.setVisibility(0);
                break;
            default:
                int i9 = BaseProgressIndicator.SHOW_NONE;
                ((l) baseProgressIndicator.getCurrentDrawable()).e(false, false, true);
                if (baseProgressIndicator.getProgressDrawable() == null || !baseProgressIndicator.getProgressDrawable().isVisible()) {
                    if (baseProgressIndicator.getIndeterminateDrawable() == null || !baseProgressIndicator.getIndeterminateDrawable().isVisible()) {
                        baseProgressIndicator.setVisibility(4);
                        break;
                    }
                }
                break;
        }
    }
}
