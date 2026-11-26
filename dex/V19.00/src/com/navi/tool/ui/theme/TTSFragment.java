package com.navi.tool.ui.theme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.MainActivity;
import com.navi.tool.databinding.FragmentTtsBinding;
import com.navi.tool.model.AppInfo;
import com.navi.tool.protocol.AmapBroadcastReceiver;
import com.navi.tool.protocol.AutoProtocolManager;
import com.navi.tool.protocol.DestinationWeatherBroadcastReceiver;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import g4.u;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TTSFragment extends z {
    private TtsAppAdapter adapter;
    private AmapBroadcastReceiver amapBroadcastReceiver;
    private BroadcastReceiver appStateReceiver;
    private AutoProtocolManager autoProtocolManager;
    private FragmentTtsBinding binding;
    private DestinationWeatherBroadcastReceiver destinationWeatherBroadcastReceiver;
    private ThemeViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setTtsEnabled(requireContext(), z6);
        updateTrafficLightSpeedLimitVisibility();
        if (z6) {
            registerTtsReceiver();
            AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
            if (autoProtocolManager != null) {
                autoProtocolManager.initialize(requireContext());
            }
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
        } else {
            unregisterTtsReceiver();
            if (!this.binding.f4823o.isChecked() && !this.binding.f4821m.isChecked() && !this.binding.f4820l.isChecked() && !this.binding.f4824p.isChecked()) {
                AutoProtocolManager autoProtocolManager2 = this.autoProtocolManager;
                if (autoProtocolManager2 != null) {
                    autoProtocolManager2.unbindAllServices(requireContext());
                }
                this.adapter.updateApps(null);
                this.binding.f4814f.setVisibility(8);
                this.binding.f4826r.setVisibility(8);
            }
        }
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateTtsTestButtonVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setTtsRoadNameEnabled(requireContext(), z6);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateTtsTestButtonVisibility();
        }
        if (z6) {
            registerTtsReceiver();
            AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
            if (autoProtocolManager != null) {
                autoProtocolManager.initialize(requireContext());
            }
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
            return;
        }
        if (this.binding.f4822n.isChecked() || this.binding.f4821m.isChecked()) {
            return;
        }
        AutoProtocolManager autoProtocolManager2 = this.autoProtocolManager;
        if (autoProtocolManager2 != null) {
            autoProtocolManager2.unbindAllServices(requireContext());
        }
        this.adapter.updateApps(null);
        this.binding.f4814f.setVisibility(8);
        this.binding.f4826r.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$2(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setDestinationWeatherEnabled(requireContext(), z6);
        if (z6) {
            this.binding.f4813e.setVisibility(0);
            registerDestinationWeatherReceiver();
            AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
            if (autoProtocolManager != null) {
                autoProtocolManager.initialize(requireContext());
            }
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
        } else {
            this.binding.f4813e.setVisibility(8);
            unregisterDestinationWeatherReceiver();
            if (!this.binding.f4822n.isChecked() && !this.binding.f4823o.isChecked()) {
                AutoProtocolManager autoProtocolManager2 = this.autoProtocolManager;
                if (autoProtocolManager2 != null) {
                    autoProtocolManager2.unbindAllServices(requireContext());
                }
                this.adapter.updateApps(null);
                this.binding.f4814f.setVisibility(8);
                this.binding.f4826r.setVisibility(8);
            }
        }
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateTtsTestButtonVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$3(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setCruiseTrafficLightVoiceEnabled(requireContext(), z6);
        updateTrafficLightSpeedLimitVisibility();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateTtsTestButtonVisibility();
        }
        if (z6) {
            registerTtsReceiver();
            AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
            if (autoProtocolManager != null) {
                autoProtocolManager.initialize(requireContext());
            }
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
            return;
        }
        if (this.binding.f4822n.isChecked() || this.binding.f4823o.isChecked() || this.binding.f4821m.isChecked()) {
            return;
        }
        AutoProtocolManager autoProtocolManager2 = this.autoProtocolManager;
        if (autoProtocolManager2 != null) {
            autoProtocolManager2.unbindAllServices(requireContext());
        }
        this.adapter.updateApps(null);
        this.binding.f4814f.setVisibility(8);
        this.binding.f4826r.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$4(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setZoneSpeedVoiceEnabled(requireContext(), z6);
        updateZoneSpeedSettingsVisibility();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).updateTtsTestButtonVisibility();
        }
        if (z6) {
            registerTtsReceiver();
            AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
            if (autoProtocolManager != null) {
                autoProtocolManager.initialize(requireContext());
            }
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
            return;
        }
        if (this.binding.f4822n.isChecked() || this.binding.f4823o.isChecked() || this.binding.f4821m.isChecked() || this.binding.f4820l.isChecked()) {
            return;
        }
        AutoProtocolManager autoProtocolManager2 = this.autoProtocolManager;
        if (autoProtocolManager2 != null) {
            autoProtocolManager2.unbindAllServices(requireContext());
        }
        this.adapter.updateApps(null);
        this.binding.f4814f.setVisibility(8);
        this.binding.f4826r.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupRecyclerView$5(AppInfo appInfo, boolean z6) {
        SharedPreferencesUtils.setTtsAppEnabled(requireContext(), appInfo.getPackageName(), z6);
        if (!z6) {
            ToastHelper.show(requireContext(), getString(u.tts_disabled_for_app, appInfo.getAppName()));
        } else {
            this.autoProtocolManager.setPackageName(appInfo.getPackageName());
            ToastHelper.show(requireContext(), getString(u.tts_enabled_for_app, appInfo.getAppName()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAutoNaviApps() {
        List<AppInfo> autoNaviApps = AppDetectionUtils.getAutoNaviApps(requireContext());
        this.adapter.updateApps(autoNaviApps);
        if (autoNaviApps == null || autoNaviApps.isEmpty()) {
            this.binding.f4814f.setVisibility(8);
            this.binding.f4826r.setVisibility(0);
        } else {
            this.binding.f4814f.setVisibility(0);
            this.binding.f4826r.setVisibility(8);
        }
    }

    private void observeViewModel() {
    }

    private void registerDestinationWeatherReceiver() {
        if (this.destinationWeatherBroadcastReceiver == null) {
            DestinationWeatherBroadcastReceiver destinationWeatherBroadcastReceiver = DestinationWeatherBroadcastReceiver.getInstance();
            this.destinationWeatherBroadcastReceiver = destinationWeatherBroadcastReceiver;
            destinationWeatherBroadcastReceiver.initBroadcast((MainActivity) getActivity());
        }
    }

    private void registerTtsReceiver() {
        if (this.amapBroadcastReceiver == null) {
            AmapBroadcastReceiver amapBroadcastReceiver = AmapBroadcastReceiver.getInstance();
            this.amapBroadcastReceiver = amapBroadcastReceiver;
            amapBroadcastReceiver.initBroadcast((MainActivity) getActivity());
        }
    }

    private void setupAppStateReceiver() {
    }

    private void setupRecyclerView() {
        this.adapter = new TtsAppAdapter(requireContext(), new e0.f(10, this));
        RecyclerView recyclerView = this.binding.f4814f;
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        this.binding.f4814f.setAdapter(this.adapter);
    }

    private void unregisterDestinationWeatherReceiver() {
        this.destinationWeatherBroadcastReceiver = null;
    }

    private void unregisterTtsReceiver() {
        this.amapBroadcastReceiver = null;
    }

    private void updateTrafficLightSpeedLimitVisibility() {
        FragmentTtsBinding fragmentTtsBinding = this.binding;
        if (fragmentTtsBinding == null) {
            return;
        }
        this.binding.f4811c.setVisibility(fragmentTtsBinding.f4822n.isChecked() || this.binding.f4820l.isChecked() ? 0 : 8);
    }

    private void updateZoneSpeedSettingsVisibility() {
        FragmentTtsBinding fragmentTtsBinding = this.binding;
        if (fragmentTtsBinding == null) {
            return;
        }
        fragmentTtsBinding.f4812d.setVisibility(fragmentTtsBinding.f4824p.isChecked() ? 0 : 8);
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentTtsBinding inflate = FragmentTtsBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.f4809a;
    }

    @Override // androidx.fragment.app.z
    public void onDestroy() {
        super.onDestroy();
        AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
        if (autoProtocolManager != null) {
            autoProtocolManager.unbindAllServices(requireContext());
        }
    }

    @Override // androidx.fragment.app.z
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override // androidx.fragment.app.z
    public void onPause() {
        super.onPause();
        if (this.appStateReceiver != null) {
            requireContext().unregisterReceiver(this.appStateReceiver);
            this.appStateReceiver = null;
        }
        unregisterTtsReceiver();
        unregisterDestinationWeatherReceiver();
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        if (this.appStateReceiver == null) {
            this.appStateReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.theme.TTSFragment.6
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (!TTSFragment.this.isAdded() || TTSFragment.this.getContext() == null) {
                        return;
                    }
                    if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) || "android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                        TTSFragment.this.loadAutoNaviApps();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            requireContext().registerReceiver(this.appStateReceiver, intentFilter);
        }
        if (SharedPreferencesUtils.isTtsEnabled(requireContext()) || SharedPreferencesUtils.isTtsRoadNameEnabled(requireContext()) || SharedPreferencesUtils.isCruiseTrafficLightVoiceEnabled(requireContext()) || SharedPreferencesUtils.isZoneSpeedVoiceEnabled(requireContext())) {
            registerTtsReceiver();
        }
        if (SharedPreferencesUtils.isDestinationWeatherEnabled(requireContext())) {
            registerDestinationWeatherReceiver();
        }
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.viewModel = (ThemeViewModel) new androidx.appcompat.app.g(this).C(ThemeViewModel.class);
        this.autoProtocolManager = AutoProtocolManager.getInstance();
        setupRecyclerView();
        setupAppStateReceiver();
        observeViewModel();
        final int i7 = 0;
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & SharedPreferencesUtils.CAP_FEATURES) == 3950) {
            this.binding.f4810b.setVisibility(0);
        } else {
            this.binding.f4810b.setVisibility(8);
        }
        this.binding.f4822n.setChecked(SharedPreferencesUtils.isTtsEnabled(requireContext()));
        this.binding.f4823o.setChecked(SharedPreferencesUtils.isTtsRoadNameEnabled(requireContext()));
        boolean isDestinationWeatherEnabled = SharedPreferencesUtils.isDestinationWeatherEnabled(requireContext());
        this.binding.f4821m.setChecked(isDestinationWeatherEnabled);
        int destinationWeatherThreshold = SharedPreferencesUtils.getDestinationWeatherThreshold(requireContext());
        this.binding.f4815g.setProgress(destinationWeatherThreshold - 20);
        this.binding.f4825q.setText(getString(u.current_value, Integer.valueOf(destinationWeatherThreshold)));
        if (isDestinationWeatherEnabled) {
            this.binding.f4813e.setVisibility(0);
        } else {
            this.binding.f4813e.setVisibility(8);
        }
        this.binding.f4820l.setChecked(SharedPreferencesUtils.isCruiseTrafficLightVoiceEnabled(requireContext()));
        this.binding.f4824p.setChecked(SharedPreferencesUtils.isZoneSpeedVoiceEnabled(requireContext()));
        int zoneSpeedVoiceThreshold = SharedPreferencesUtils.getZoneSpeedVoiceThreshold(requireContext());
        this.binding.f4819k.setProgress(zoneSpeedVoiceThreshold - 1);
        this.binding.f4830v.setText("当前值：" + zoneSpeedVoiceThreshold + "%");
        int zoneSpeedVoiceIntervalMinutes = SharedPreferencesUtils.getZoneSpeedVoiceIntervalMinutes(requireContext());
        this.binding.f4818j.setProgress(zoneSpeedVoiceIntervalMinutes + (-1));
        this.binding.f4829u.setText("当前值：" + zoneSpeedVoiceIntervalMinutes + "分钟");
        int zoneSpeedVoiceMaxCount = SharedPreferencesUtils.getZoneSpeedVoiceMaxCount(requireContext());
        this.binding.f4817i.setProgress(zoneSpeedVoiceMaxCount + (-1));
        a.a.v("当前值：", zoneSpeedVoiceMaxCount, "次", this.binding.f4828t);
        updateZoneSpeedSettingsVisibility();
        int trafficLightSpeedLimit = SharedPreferencesUtils.getTrafficLightSpeedLimit(requireContext());
        this.binding.f4816h.setProgress(trafficLightSpeedLimit - 10);
        this.binding.f4827s.setText(trafficLightSpeedLimit + " km/h");
        this.binding.f4816h.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.TTSFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i8, boolean z6) {
                int i9 = i8 + 10;
                TTSFragment.this.binding.f4827s.setText(i9 + " km/h");
                if (z6) {
                    SharedPreferencesUtils.setTrafficLightSpeedLimit(TTSFragment.this.requireContext(), i9);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.binding.f4819k.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.TTSFragment.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i8, boolean z6) {
                int i9 = i8 + 1;
                a.a.v("当前值：", i9, "%", TTSFragment.this.binding.f4830v);
                if (z6) {
                    SharedPreferencesUtils.setZoneSpeedVoiceThreshold(TTSFragment.this.requireContext(), i9);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.binding.f4818j.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.TTSFragment.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i8, boolean z6) {
                int i9 = i8 + 1;
                a.a.v("当前值：", i9, "分钟", TTSFragment.this.binding.f4829u);
                if (z6) {
                    SharedPreferencesUtils.setZoneSpeedVoiceIntervalMinutes(TTSFragment.this.requireContext(), i9);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.binding.f4817i.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.TTSFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i8, boolean z6) {
                int i9 = i8 + 1;
                a.a.v("当前值：", i9, "次", TTSFragment.this.binding.f4828t);
                if (z6) {
                    SharedPreferencesUtils.setZoneSpeedVoiceMaxCount(TTSFragment.this.requireContext(), i9);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        updateTrafficLightSpeedLimitVisibility();
        this.binding.f4822n.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.l

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ TTSFragment f5841b;

            {
                this.f5841b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i8 = i7;
                TTSFragment tTSFragment = this.f5841b;
                switch (i8) {
                    case 0:
                        tTSFragment.lambda$onViewCreated$0(compoundButton, z6);
                        break;
                    case 1:
                        tTSFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 2:
                        tTSFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 3:
                        tTSFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    default:
                        tTSFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                }
            }
        });
        final int i8 = 1;
        this.binding.f4823o.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.l

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ TTSFragment f5841b;

            {
                this.f5841b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i82 = i8;
                TTSFragment tTSFragment = this.f5841b;
                switch (i82) {
                    case 0:
                        tTSFragment.lambda$onViewCreated$0(compoundButton, z6);
                        break;
                    case 1:
                        tTSFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 2:
                        tTSFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 3:
                        tTSFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    default:
                        tTSFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                }
            }
        });
        final int i9 = 2;
        this.binding.f4821m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.l

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ TTSFragment f5841b;

            {
                this.f5841b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i82 = i9;
                TTSFragment tTSFragment = this.f5841b;
                switch (i82) {
                    case 0:
                        tTSFragment.lambda$onViewCreated$0(compoundButton, z6);
                        break;
                    case 1:
                        tTSFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 2:
                        tTSFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 3:
                        tTSFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    default:
                        tTSFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                }
            }
        });
        this.binding.f4815g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.theme.TTSFragment.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i10, boolean z6) {
                int i11 = i10 + 20;
                TTSFragment.this.binding.f4825q.setText(TTSFragment.this.getString(u.current_value, Integer.valueOf(i11)));
                if (z6) {
                    SharedPreferencesUtils.setDestinationWeatherThreshold(TTSFragment.this.requireContext(), i11);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        final int i10 = 3;
        this.binding.f4820l.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.l

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ TTSFragment f5841b;

            {
                this.f5841b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i82 = i10;
                TTSFragment tTSFragment = this.f5841b;
                switch (i82) {
                    case 0:
                        tTSFragment.lambda$onViewCreated$0(compoundButton, z6);
                        break;
                    case 1:
                        tTSFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 2:
                        tTSFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 3:
                        tTSFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    default:
                        tTSFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                }
            }
        });
        final int i11 = 4;
        this.binding.f4824p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.theme.l

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ TTSFragment f5841b;

            {
                this.f5841b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i82 = i11;
                TTSFragment tTSFragment = this.f5841b;
                switch (i82) {
                    case 0:
                        tTSFragment.lambda$onViewCreated$0(compoundButton, z6);
                        break;
                    case 1:
                        tTSFragment.lambda$onViewCreated$1(compoundButton, z6);
                        break;
                    case 2:
                        tTSFragment.lambda$onViewCreated$2(compoundButton, z6);
                        break;
                    case 3:
                        tTSFragment.lambda$onViewCreated$3(compoundButton, z6);
                        break;
                    default:
                        tTSFragment.lambda$onViewCreated$4(compoundButton, z6);
                        break;
                }
            }
        });
        if (SharedPreferencesUtils.isTtsEnabled(requireContext()) || SharedPreferencesUtils.isTtsRoadNameEnabled(requireContext()) || SharedPreferencesUtils.isDestinationWeatherEnabled(requireContext()) || SharedPreferencesUtils.isCruiseTrafficLightVoiceEnabled(requireContext()) || SharedPreferencesUtils.isZoneSpeedVoiceEnabled(requireContext())) {
            loadAutoNaviApps();
            this.binding.f4814f.setVisibility(0);
        } else {
            this.binding.f4814f.setVisibility(8);
            this.binding.f4826r.setVisibility(8);
        }
    }
}
