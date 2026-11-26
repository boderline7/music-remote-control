package e0;

import android.view.View;
import androidx.lifecycle.h0;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.util.TireFloatingView;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.main.SectionsPagerAdapterFiltered;
import com.navi.tool.ui.theme.TTSFragment;
import com.navi.tool.ui.theme.TtsAppAdapter;
import com.navi.tool.ui.voice.VoiceNavigationFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements q0.w, q0.d, s3.h, h0, TtsAppAdapter.OnAppToggleListener, AppAdapter.OnAppToggleListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6293a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f6294b;

    public /* synthetic */ f(int i7, Object obj) {
        this.f6293a = i7;
        this.f6294b = obj;
    }

    @Override // s3.h
    public final void b(com.google.android.material.tabs.b bVar, int i7) {
        MainActivity.lambda$setupTabsAndPager$22((SectionsPagerAdapterFiltered) this.f6294b, bVar, i7);
    }

    @Override // q0.w
    public final boolean g(View view) {
        BottomSheetDragHandleView bottomSheetDragHandleView = (BottomSheetDragHandleView) this.f6294b;
        int i7 = BottomSheetDragHandleView.f3417m;
        return bottomSheetDragHandleView.c();
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        ((VoiceNavigationFragment) this.f6294b).lambda$onCreateView$0(appInfo);
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        int i7 = this.f6293a;
        Object obj2 = this.f6294b;
        switch (i7) {
            case 8:
                TireFloatingView tireFloatingView = (TireFloatingView) obj2;
                o4.s sVar = (o4.s) obj;
                int i8 = TireFloatingView.f4596q;
                tireFloatingView.d(sVar);
                if (sVar != null) {
                    if (!tireFloatingView.f4609m) {
                        tireFloatingView.f4609m = true;
                        tireFloatingView.f4608l = sVar.f8030l;
                        break;
                    } else {
                        long j7 = sVar.f8030l;
                        if (j7 > 0 && j7 > tireFloatingView.f4608l) {
                            tireFloatingView.f4608l = j7;
                            if (sVar.b()) {
                                tireFloatingView.c();
                                break;
                            }
                        }
                    }
                }
                break;
            default:
                o4.b bVar = (o4.b) obj2;
                String str = (String) obj;
                if (bVar.f7926e && bVar.f7925d && bVar.f7924c != null) {
                    bVar.c(str);
                    break;
                }
                break;
        }
    }

    @Override // com.navi.tool.ui.theme.TtsAppAdapter.OnAppToggleListener
    public final void onToggle(AppInfo appInfo, boolean z6) {
        ((TTSFragment) this.f6294b).lambda$setupRecyclerView$5(appInfo, z6);
    }
}
