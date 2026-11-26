package androidx.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import androidx.lifecycle.o0;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.amapflying.util.TireFloatingView;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.ui.theme.AutoBrightnessFragment;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.view.SpectrumView;
import com.navi.tool.view.ZoomableImageView;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f616a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f617b;

    public /* synthetic */ e(int i7, Object obj) {
        this.f616a = i7;
        this.f617b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        JSONObject t6;
        int i7 = 2;
        boolean z6 = false;
        switch (this.f616a) {
            case 0:
                ((o) this.f617b).invalidateMenu();
                break;
            case 1:
                n nVar = (n) this.f617b;
                Runnable runnable = nVar.f641b;
                if (runnable != null) {
                    runnable.run();
                    nVar.f641b = null;
                    break;
                }
                break;
            case 2:
                q.a((q) this.f617b);
                break;
            case 3:
            case 4:
            case 8:
            default:
                ZoomableImageView zoomableImageView = (ZoomableImageView) this.f617b;
                int i8 = ZoomableImageView.f6088r;
                zoomableImageView.c();
                break;
            case 5:
                Activity activity = (Activity) this.f617b;
                int i9 = e0.g.f6295b;
                if (!activity.isFinishing()) {
                    int i10 = Build.VERSION.SDK_INT;
                    if (i10 < 28) {
                        Class cls = e0.i.f6302a;
                        boolean z7 = i10 == 26 || i10 == 27;
                        Method method = e0.i.f6307f;
                        if ((!z7 || method != null) && (e0.i.f6306e != null || e0.i.f6305d != null)) {
                            try {
                                Object obj2 = e0.i.f6304c.get(activity);
                                if (obj2 != null && (obj = e0.i.f6303b.get(activity)) != null) {
                                    Application application = activity.getApplication();
                                    e0.h hVar = new e0.h(activity);
                                    application.registerActivityLifecycleCallbacks(hVar);
                                    Handler handler = e0.i.f6308g;
                                    handler.post(new android.support.v4.media.i(hVar, obj2, 3));
                                    int i11 = 4;
                                    try {
                                        if (i10 == 26 || i10 == 27) {
                                            Boolean bool = Boolean.FALSE;
                                            method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                                        } else {
                                            activity.recreate();
                                        }
                                        handler.post(new android.support.v4.media.i(application, hVar, i11));
                                        break;
                                    } catch (Throwable th) {
                                        handler.post(new android.support.v4.media.i(application, hVar, i11));
                                        throw th;
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                        activity.recreate();
                        break;
                    } else {
                        Class cls2 = e0.i.f6302a;
                        activity.recreate();
                        break;
                    }
                }
                break;
            case 6:
                o0 o0Var = (o0) this.f617b;
                androidx.lifecycle.k0 k0Var = o0.Companion;
                i5.a.g("this$0", o0Var);
                if (o0Var.f2193b == 0) {
                    o0Var.f2194c = true;
                    o0Var.f2197f.e(androidx.lifecycle.q.ON_PAUSE);
                }
                if (o0Var.f2192a == 0 && o0Var.f2194c) {
                    o0Var.f2197f.e(androidx.lifecycle.q.ON_STOP);
                    o0Var.f2195d = true;
                    break;
                }
                break;
            case 7:
                ((CarouselLayoutManager) this.f617b).b1();
                break;
            case 9:
                j3.f fVar = (j3.f) this.f617b;
                j3.c cVar = fVar.f6962a;
                if (cVar != null) {
                    cVar.b(fVar.f6963b, fVar.f6964c, true);
                    break;
                }
                break;
            case 10:
                w2.b bVar = (w2.b) this.f617b;
                bVar.f9459c = false;
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) bVar.f9461e;
                u0.e eVar = sideSheetBehavior.f4126i;
                if (eVar != null && eVar.g()) {
                    bVar.a(bVar.f9458b);
                    break;
                } else if (sideSheetBehavior.f4125h == 2) {
                    sideSheetBehavior.x(bVar.f9458b);
                    break;
                }
                break;
            case 11:
                ((com.google.android.material.textfield.c) this.f617b).t(true);
                break;
            case 12:
                com.google.android.material.textfield.i iVar = (com.google.android.material.textfield.i) this.f617b;
                boolean isPopupShowing = iVar.f4344h.isPopupShowing();
                iVar.t(isPopupShowing);
                iVar.f4349m = isPopupShowing;
                break;
            case 13:
                ((TextInputLayout) this.f617b).f4275d.requestLayout();
                break;
            case 14:
                FloatingWindowService floatingWindowService = (FloatingWindowService) this.f617b;
                boolean z8 = FloatingWindowService.f4529p;
                floatingWindowService.l();
                break;
            case 15:
                TireFloatingView tireFloatingView = (TireFloatingView) this.f617b;
                int i12 = TireFloatingView.f4596q;
                tireFloatingView.setVisibility(8);
                break;
            case 16:
                com.navi.tool.baidu.e eVar2 = (com.navi.tool.baidu.e) this.f617b;
                com.navi.tool.baidu.e eVar3 = com.navi.tool.baidu.e.f4632p;
                eVar2.getClass();
                try {
                    if (eVar2.n() && (t6 = eVar2.t()) != null) {
                        String optString = t6.optString("result", s5.h0.FRAGMENT_ENCODE_SET);
                        String str = "1";
                        if (!"true".equalsIgnoreCase(optString) && !"1".equals(optString)) {
                            str = "0";
                        }
                        com.navi.tool.baidu.e.p(str);
                    }
                } catch (Exception unused2) {
                    return;
                }
                break;
            case 17:
                TirePollingService tirePollingService = (TirePollingService) this.f617b;
                AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                tirePollingService.getClass();
                try {
                    if (NativeBridge.isFeatureEnabled(tirePollingService.getApplicationContext(), 4096)) {
                        if (o4.z.e(tirePollingService.getApplicationContext())) {
                            z6 = true;
                        }
                    }
                } catch (Throwable unused3) {
                }
                tirePollingService.f4730a.post(new com.google.android.material.internal.g0(tirePollingService, z6, i7));
                break;
            case 18:
                ((AutoBrightnessFragment) this.f617b).lambda$onViewCreated$7();
                break;
            case 19:
                ((ThemeViewModel) this.f617b).lambda$refreshApps$1();
                break;
            case 20:
                SpectrumView spectrumView = (SpectrumView) this.f617b;
                r4.i iVar2 = spectrumView.f6040o;
                if (iVar2 != null) {
                    ((com.navi.tool.ui.extra.z) iVar2).f5594b.lambda$initViews$61(spectrumView.f6027b);
                    break;
                }
                break;
        }
    }
}
