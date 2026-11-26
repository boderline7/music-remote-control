package com.navi.tool.ui.theme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.z;
import androidx.lifecycle.h0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.databinding.FragmentAutoThemeBinding;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AutoThemeFragment extends z {
    private AppAdapter appAdapter;
    private FragmentAutoThemeBinding binding;
    private ThemeViewModel themeViewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(AppInfo appInfo) {
        this.themeViewModel.setThemeAppEnabled(appInfo.getPackageName(), !appInfo.isEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(CompoundButton compoundButton, boolean z6) {
        this.themeViewModel.setAutoThemeEnabled(z6);
        updateAppVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(List list) {
        this.appAdapter.submitList(list);
        Boolean bool = (Boolean) this.themeViewModel.getAutoThemeEnabled().d();
        if (bool != null) {
            updateAppVisibility(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateAppVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$4(SwitchMaterial switchMaterial, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        switchMaterial.setChecked(false);
        this.themeViewModel.setAutoThemeEnabled(false);
    }

    private void updateAppVisibility(boolean z6) {
        if (!z6) {
            this.binding.f4772b.setVisibility(8);
            this.binding.f4774d.setVisibility(8);
            return;
        }
        this.binding.f4772b.setVisibility(0);
        this.binding.f4774d.setVisibility(8);
        List list = (List) this.themeViewModel.getAllAppsLiveData().d();
        if (list == null || list.isEmpty()) {
            this.binding.f4772b.setVisibility(8);
            this.binding.f4774d.setVisibility(0);
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentAutoThemeBinding inflate = FragmentAutoThemeBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.f4771a;
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
        this.themeViewModel = (ThemeViewModel) new androidx.appcompat.app.g(requireActivity()).C(ThemeViewModel.class);
        RecyclerView recyclerView = this.binding.f4772b;
        getContext();
        final int i7 = 1;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        AppAdapter appAdapter = new AppAdapter(new i(this));
        this.appAdapter = appAdapter;
        recyclerView.setAdapter(appAdapter);
        final SwitchMaterial switchMaterial = this.binding.f4773c;
        switchMaterial.setOnCheckedChangeListener(new z2.a(4, this));
        this.themeViewModel.getAllAppsLiveData().e(getViewLifecycleOwner(), new i(this));
        final int i8 = 0;
        this.themeViewModel.getAutoThemeEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoThemeFragment f5836b;

            {
                this.f5836b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i8;
                AutoThemeFragment autoThemeFragment = this.f5836b;
                SwitchMaterial switchMaterial2 = switchMaterial;
                Boolean bool = (Boolean) obj;
                switch (i9) {
                    case 0:
                        autoThemeFragment.lambda$onViewCreated$3(switchMaterial2, bool);
                        break;
                    default:
                        autoThemeFragment.lambda$onViewCreated$4(switchMaterial2, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getAutoBrightnessEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoThemeFragment f5836b;

            {
                this.f5836b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i7;
                AutoThemeFragment autoThemeFragment = this.f5836b;
                SwitchMaterial switchMaterial2 = switchMaterial;
                Boolean bool = (Boolean) obj;
                switch (i9) {
                    case 0:
                        autoThemeFragment.lambda$onViewCreated$3(switchMaterial2, bool);
                        break;
                    default:
                        autoThemeFragment.lambda$onViewCreated$4(switchMaterial2, bool);
                        break;
                }
            }
        });
    }
}
