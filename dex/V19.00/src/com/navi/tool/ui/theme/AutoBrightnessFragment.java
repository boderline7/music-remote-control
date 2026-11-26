package com.navi.tool.ui.theme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.z;
import androidx.lifecycle.h0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.databinding.FragmentAutoBrightnessBinding;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AutoBrightnessFragment extends z {
    private AppAdapter appAdapter;
    private FragmentAutoBrightnessBinding binding;
    private ThemeViewModel themeViewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(AppInfo appInfo) {
        this.themeViewModel.setBrightnessAppEnabled(appInfo.getPackageName(), !appInfo.isEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            this.themeViewModel.setAutoThemeEnabled(false);
        }
        this.themeViewModel.setAutoBrightnessEnabled(z6);
        updateBrightnessThresholdVisibility(z6);
        updateAppVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(List list) {
        this.appAdapter.submitList(list);
        Boolean bool = (Boolean) this.themeViewModel.getAutoBrightnessEnabled().d();
        if (bool != null) {
            updateAppVisibility(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(SwitchMaterial switchMaterial, Boolean bool) {
        switchMaterial.setChecked(bool.booleanValue());
        updateBrightnessThresholdVisibility(bool.booleanValue());
        updateAppVisibility(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$4(SwitchMaterial switchMaterial, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        switchMaterial.setChecked(false);
        this.themeViewModel.setAutoBrightnessEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onViewCreated$5(SeekBar seekBar, TextView textView, Integer num) {
        seekBar.setProgress(num.intValue());
        textView.setText(num + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$6(Integer num) {
        this.binding.f4769g.setText(num + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$7() {
        this.themeViewModel.updateCurrentBrightness();
    }

    private void updateAppVisibility(boolean z6) {
        if (!z6) {
            this.binding.f4765c.setVisibility(8);
            this.binding.f4770h.setVisibility(8);
            return;
        }
        this.binding.f4765c.setVisibility(0);
        this.binding.f4770h.setVisibility(8);
        List list = (List) this.themeViewModel.getBrightnessAppsLiveData().d();
        if (list == null || list.isEmpty()) {
            this.binding.f4765c.setVisibility(8);
            this.binding.f4770h.setVisibility(0);
        }
    }

    private void updateBrightnessThresholdVisibility(boolean z6) {
        this.binding.f4764b.setVisibility(z6 ? 0 : 8);
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentAutoBrightnessBinding inflate = FragmentAutoBrightnessBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.f4763a;
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
            this.themeViewModel.updateCurrentBrightness();
        }
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.themeViewModel = (ThemeViewModel) new androidx.appcompat.app.g(requireActivity()).C(ThemeViewModel.class);
        RecyclerView recyclerView = this.binding.f4765c;
        getContext();
        final int i7 = 1;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        final int i8 = 0;
        AppAdapter appAdapter = new AppAdapter(new f(this, i8));
        this.appAdapter = appAdapter;
        recyclerView.setAdapter(appAdapter);
        final SwitchMaterial switchMaterial = this.binding.f4767e;
        switchMaterial.setOnCheckedChangeListener(new z2.a(3, this));
        FragmentAutoBrightnessBinding fragmentAutoBrightnessBinding = this.binding;
        SeekBar seekBar = fragmentAutoBrightnessBinding.f4766d;
        int intValue = ((Integer) this.themeViewModel.getBrightnessThreshold().d()).intValue();
        seekBar.setProgress(intValue);
        final TextView textView = fragmentAutoBrightnessBinding.f4768f;
        textView.setText(intValue + "%");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.AutoBrightnessFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i9, boolean z6) {
                textView.setText(i9 + "%");
                if (z6) {
                    AutoBrightnessFragment.this.themeViewModel.setBrightnessThreshold(i9);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                AutoBrightnessFragment.this.themeViewModel.setBrightnessThreshold(seekBar2.getProgress());
            }
        });
        this.themeViewModel.getBrightnessAppsLiveData().e(getViewLifecycleOwner(), new f(this, i7));
        this.themeViewModel.getAutoBrightnessEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoBrightnessFragment f5830b;

            {
                this.f5830b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i8;
                AutoBrightnessFragment autoBrightnessFragment = this.f5830b;
                SwitchMaterial switchMaterial2 = switchMaterial;
                Boolean bool = (Boolean) obj;
                switch (i9) {
                    case 0:
                        autoBrightnessFragment.lambda$onViewCreated$3(switchMaterial2, bool);
                        break;
                    default:
                        autoBrightnessFragment.lambda$onViewCreated$4(switchMaterial2, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getAutoThemeEnabled().e(getViewLifecycleOwner(), new h0(this) { // from class: com.navi.tool.ui.theme.g

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AutoBrightnessFragment f5830b;

            {
                this.f5830b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i7;
                AutoBrightnessFragment autoBrightnessFragment = this.f5830b;
                SwitchMaterial switchMaterial2 = switchMaterial;
                Boolean bool = (Boolean) obj;
                switch (i9) {
                    case 0:
                        autoBrightnessFragment.lambda$onViewCreated$3(switchMaterial2, bool);
                        break;
                    default:
                        autoBrightnessFragment.lambda$onViewCreated$4(switchMaterial2, bool);
                        break;
                }
            }
        });
        this.themeViewModel.getBrightnessThreshold().e(getViewLifecycleOwner(), new h(textView, seekBar));
        this.themeViewModel.getCurrentBrightness().e(getViewLifecycleOwner(), new f(this, 2));
        if (getView() != null) {
            getView().post(new androidx.activity.e(18, this));
        }
    }
}
