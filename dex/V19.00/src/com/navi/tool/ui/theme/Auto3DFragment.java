package com.navi.tool.ui.theme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.fragment.app.z;
import androidx.lifecycle.h0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.NaviApplication;
import com.navi.tool.databinding.FragmentAuto3dBinding;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import g4.p;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class Auto3DFragment extends z {
    private AppAdapter appAdapter;
    private FragmentAuto3dBinding binding;
    private ThemeViewModel themeViewModel;

    private boolean hasAnyAppEnabled() {
        List list = (List) this.themeViewModel.getAutoNaviAppsLiveData().d();
        if (list != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((AppInfo) it.next()).isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(AppInfo appInfo) {
        boolean z6 = !appInfo.isEnabled();
        this.themeViewModel.set3DAppEnabled(appInfo.getPackageName(), z6);
        appInfo.setEnabled(z6);
        updateNavigation3DVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setAuto3DEnabled(z6);
        updateAppVisibility(z6);
        updateNavigation3DVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$10(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateNavigationFixedScaleVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$11(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateCruiseFixedScaleVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$12(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateNavigationScaleVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$13(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateCruiseScaleVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewCreated$14(RadioGroup radioGroup, Integer num) {
        if (num != null) {
            int i7 = p.radio_navigation_10m;
            if (num.intValue() == 25) {
                i7 = p.radio_navigation_25m;
            } else if (num.intValue() == 50) {
                i7 = p.radio_navigation_50m;
            }
            radioGroup.check(i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewCreated$15(RadioGroup radioGroup, Integer num) {
        if (num != null) {
            int i7 = p.radio_cruise_10m;
            if (num.intValue() == 25) {
                i7 = p.radio_cruise_25m;
            } else if (num.intValue() == 50) {
                i7 = p.radio_cruise_50m;
            }
            radioGroup.check(i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setNavigation3DEnabled(z6);
        updateNavigationFixedScaleVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setCruise3DEnabled(z6);
        updateCruiseFixedScaleVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$4(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setNavigationFixedScaleEnabled(z6);
        updateNavigationScaleVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$5(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setCruiseFixedScaleEnabled(z6);
        updateCruiseScaleVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$6(RadioGroup radioGroup, int i7) {
        this.themeViewModel.setNavigationScale(i7 == p.radio_navigation_25m ? 25 : i7 == p.radio_navigation_50m ? 50 : 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$7(RadioGroup radioGroup, int i7) {
        this.themeViewModel.setCruiseScale(i7 == p.radio_cruise_25m ? 25 : i7 == p.radio_cruise_50m ? 50 : 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$8(List list) {
        this.appAdapter.submitList(list);
        Boolean bool = (Boolean) this.themeViewModel.getAuto3DEnabled().d();
        if (bool != null) {
            updateAppVisibility(bool.booleanValue());
        }
        updateNavigation3DVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$9(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateAppVisibility(bool.booleanValue());
        updateNavigation3DVisibility();
    }

    private void syncCruiseScaleSelectionToUI() {
        ThemeViewModel themeViewModel = this.themeViewModel;
        Integer num = themeViewModel != null ? (Integer) themeViewModel.getCruiseScale().d() : null;
        if (num == null || this.binding == null) {
            return;
        }
        int i7 = p.radio_cruise_10m;
        if (num.intValue() == 25) {
            i7 = p.radio_cruise_25m;
        } else if (num.intValue() == 50) {
            i7 = p.radio_cruise_50m;
        }
        this.binding.f4754g.check(i7);
        this.binding.f4754g.jumpDrawablesToCurrentState();
        this.binding.f4754g.invalidate();
    }

    private void syncNavigationScaleSelectionToUI() {
        ThemeViewModel themeViewModel = this.themeViewModel;
        Integer num = themeViewModel != null ? (Integer) themeViewModel.getNavigationScale().d() : null;
        if (num == null || this.binding == null) {
            return;
        }
        int i7 = p.radio_navigation_10m;
        if (num.intValue() == 25) {
            i7 = p.radio_navigation_25m;
        } else if (num.intValue() == 50) {
            i7 = p.radio_navigation_50m;
        }
        this.binding.f4755h.check(i7);
        this.binding.f4755h.jumpDrawablesToCurrentState();
        this.binding.f4755h.invalidate();
    }

    private void updateAppVisibility(boolean z6) {
        if (!z6) {
            this.binding.f4756i.setVisibility(8);
            this.binding.f4762o.setVisibility(8);
            return;
        }
        this.binding.f4756i.setVisibility(0);
        this.binding.f4762o.setVisibility(8);
        List list = (List) this.themeViewModel.getAutoNaviAppsLiveData().d();
        if (list == null || list.isEmpty()) {
            this.binding.f4756i.setVisibility(8);
            this.binding.f4762o.setVisibility(0);
        }
    }

    private void updateCruiseFixedScaleVisibility(boolean z6) {
        if (z6) {
            this.binding.f4750c.setVisibility(0);
        } else {
            this.binding.f4750c.setVisibility(8);
        }
    }

    private void updateCruiseScaleVisibility(boolean z6) {
        if (!z6) {
            this.binding.f4751d.setVisibility(8);
        } else {
            this.binding.f4751d.setVisibility(0);
            syncCruiseScaleSelectionToUI();
        }
    }

    private void updateNavigation3DVisibility() {
        Boolean bool = (Boolean) this.themeViewModel.getAuto3DEnabled().d();
        if (bool == null || !bool.booleanValue()) {
            this.binding.f4749b.setVisibility(8);
        } else if (hasAnyAppEnabled()) {
            this.binding.f4749b.setVisibility(0);
        } else {
            this.binding.f4749b.setVisibility(8);
        }
    }

    private void updateNavigationFixedScaleVisibility(boolean z6) {
        if (z6) {
            this.binding.f4752e.setVisibility(0);
        } else {
            this.binding.f4752e.setVisibility(8);
        }
    }

    private void updateNavigationScaleVisibility(boolean z6) {
        if (!z6) {
            this.binding.f4753f.setVisibility(8);
        } else {
            this.binding.f4753f.setVisibility(0);
            syncNavigationScaleSelectionToUI();
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentAuto3dBinding inflate = FragmentAuto3dBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.f4748a;
    }

    @Override // androidx.fragment.app.z
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        ThemeViewModel themeViewModel = this.themeViewModel;
        if (themeViewModel != null) {
            themeViewModel.refreshApps();
        }
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.themeViewModel = ((NaviApplication) requireActivity().getApplication()).getThemeViewModel();
        RecyclerView recyclerView = this.binding.f4756i;
        getContext();
        final int i7 = 1;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        AppAdapter appAdapter = new AppAdapter(new a(this));
        this.appAdapter = appAdapter;
        recyclerView.setAdapter(appAdapter);
        final SwitchMaterial switchMaterial = this.binding.f4757j;
        final int i8 = 0;
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5824b;

            {
                this.f5824b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i9 = i8;
                Auto3DFragment auto3DFragment = this.f5824b;
                switch (i9) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$5(compoundButton, z6);
                        break;
                }
            }
        });
        final SwitchMaterial switchMaterial2 = this.binding.f4760m;
        switchMaterial2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5824b;

            {
                this.f5824b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i9 = i7;
                Auto3DFragment auto3DFragment = this.f5824b;
                switch (i9) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$5(compoundButton, z6);
                        break;
                }
            }
        });
        final SwitchMaterial switchMaterial3 = this.binding.f4758k;
        final int i9 = 2;
        switchMaterial3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5824b;

            {
                this.f5824b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i92 = i9;
                Auto3DFragment auto3DFragment = this.f5824b;
                switch (i92) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$5(compoundButton, z6);
                        break;
                }
            }
        });
        final SwitchMaterial switchMaterial4 = this.binding.f4761n;
        final int i10 = 3;
        switchMaterial4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5824b;

            {
                this.f5824b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i92 = i10;
                Auto3DFragment auto3DFragment = this.f5824b;
                switch (i92) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$5(compoundButton, z6);
                        break;
                }
            }
        });
        final SwitchMaterial switchMaterial5 = this.binding.f4759l;
        final int i11 = 4;
        switchMaterial5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5824b;

            {
                this.f5824b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i92 = i11;
                Auto3DFragment auto3DFragment = this.f5824b;
                switch (i92) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$5(compoundButton, z6);
                        break;
                }
            }
        });
        final RadioGroup radioGroup = this.binding.f4755h;
        radioGroup.setOnCheckedChangeListener(new e(this, i8));
        final RadioGroup radioGroup2 = this.binding.f4754g;
        radioGroup2.setOnCheckedChangeListener(new e(this, i7));
        this.themeViewModel.getAutoNaviAppsLiveData().e(getViewLifecycleOwner(), new a(this));
        this.themeViewModel.getAuto3DEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5819b;

            {
                this.f5819b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i11;
                Auto3DFragment auto3DFragment = this.f5819b;
                SwitchMaterial switchMaterial6 = switchMaterial;
                Boolean bool = (Boolean) obj;
                switch (i12) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$10(switchMaterial6, bool);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$11(switchMaterial6, bool);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$12(switchMaterial6, bool);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$13(switchMaterial6, bool);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$9(switchMaterial6, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getNavigation3DEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5819b;

            {
                this.f5819b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i8;
                Auto3DFragment auto3DFragment = this.f5819b;
                SwitchMaterial switchMaterial6 = switchMaterial2;
                Boolean bool = (Boolean) obj;
                switch (i12) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$10(switchMaterial6, bool);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$11(switchMaterial6, bool);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$12(switchMaterial6, bool);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$13(switchMaterial6, bool);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$9(switchMaterial6, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getCruise3DEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5819b;

            {
                this.f5819b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i7;
                Auto3DFragment auto3DFragment = this.f5819b;
                SwitchMaterial switchMaterial6 = switchMaterial3;
                Boolean bool = (Boolean) obj;
                switch (i12) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$10(switchMaterial6, bool);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$11(switchMaterial6, bool);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$12(switchMaterial6, bool);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$13(switchMaterial6, bool);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$9(switchMaterial6, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getNavigationFixedScaleEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5819b;

            {
                this.f5819b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i9;
                Auto3DFragment auto3DFragment = this.f5819b;
                SwitchMaterial switchMaterial6 = switchMaterial4;
                Boolean bool = (Boolean) obj;
                switch (i12) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$10(switchMaterial6, bool);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$11(switchMaterial6, bool);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$12(switchMaterial6, bool);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$13(switchMaterial6, bool);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$9(switchMaterial6, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getCruiseFixedScaleEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Auto3DFragment f5819b;

            {
                this.f5819b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i10;
                Auto3DFragment auto3DFragment = this.f5819b;
                SwitchMaterial switchMaterial6 = switchMaterial5;
                Boolean bool = (Boolean) obj;
                switch (i12) {
                    case 0:
                        auto3DFragment.lambda$onViewCreated$10(switchMaterial6, bool);
                        break;
                    case 1:
                        auto3DFragment.lambda$onViewCreated$11(switchMaterial6, bool);
                        break;
                    case 2:
                        auto3DFragment.lambda$onViewCreated$12(switchMaterial6, bool);
                        break;
                    case 3:
                        auto3DFragment.lambda$onViewCreated$13(switchMaterial6, bool);
                        break;
                    default:
                        auto3DFragment.lambda$onViewCreated$9(switchMaterial6, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getNavigationScale().e(getViewLifecycleOwner(), new h0() { // from class: com.navi.tool.ui.theme.c
            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i8;
                RadioGroup radioGroup3 = radioGroup;
                Integer num = (Integer) obj;
                switch (i12) {
                    case 0:
                        Auto3DFragment.lambda$onViewCreated$14(radioGroup3, num);
                        break;
                    default:
                        Auto3DFragment.lambda$onViewCreated$15(radioGroup3, num);
                        break;
                }
            }
        });
        this.themeViewModel.getCruiseScale().e(getViewLifecycleOwner(), new h0() { // from class: com.navi.tool.ui.theme.c
            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i12 = i7;
                RadioGroup radioGroup3 = radioGroup2;
                Integer num = (Integer) obj;
                switch (i12) {
                    case 0:
                        Auto3DFragment.lambda$onViewCreated$14(radioGroup3, num);
                        break;
                    default:
                        Auto3DFragment.lambda$onViewCreated$15(radioGroup3, num);
                        break;
                }
            }
        });
    }
}
