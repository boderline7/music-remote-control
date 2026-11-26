package com.navi.tool.ui.main;

import android.content.Context;
import androidx.fragment.app.d0;
import androidx.fragment.app.z;
import androidx.viewpager2.adapter.g;
import com.navi.tool.ui.cast.ScreenCastingFragment;
import com.navi.tool.ui.extra.AdditionalFeaturesFragment;
import com.navi.tool.ui.extra.CarLinkFragment;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import com.navi.tool.ui.extra.MiscSettingsFragment;
import com.navi.tool.ui.log.LogLinkFragment;
import com.navi.tool.ui.scene.SceneModeFragment;
import com.navi.tool.ui.theme.Auto3DFragment;
import com.navi.tool.ui.theme.AutoThemeFragment;
import com.navi.tool.ui.theme.TTSFragment;
import com.navi.tool.ui.voice.VoiceNavigationFragment;
import com.navi.tool.utils.SharedPreferencesUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SectionsPagerAdapter extends g {
    private final Context appContext;
    private volatile int licenseMask;

    public SectionsPagerAdapter(d0 d0Var, int i7) {
        super(d0Var);
        this.appContext = d0Var.getApplicationContext();
        this.licenseMask = i7;
    }

    private static int bitForPosition(int i7) {
        switch (i7) {
            case 3:
                return 2;
            case 4:
                return 4;
            case 5:
                return 8;
            case 6:
                return 32;
            case 7:
                return 64;
            case 8:
                return 0;
            case 9:
                return 256;
            case 10:
                return 512;
            case 11:
                return 1024;
            case 12:
                return 2048;
            default:
                return -1;
        }
    }

    @Override // androidx.viewpager2.adapter.g
    public z createFragment(int i7) {
        int bitForPosition = bitForPosition(i7);
        return (bitForPosition < 0 || (bitForPosition != 0 ? (bitForPosition & this.licenseMask) != 0 : (this.licenseMask & SharedPreferencesUtils.CAP_FEATURES) != 0)) ? i7 == 0 ? new AutoThemeFragment() : i7 == 1 ? new Auto3DFragment() : i7 == 2 ? new TTSFragment() : i7 == 3 ? new j4.g() : i7 == 4 ? new VoiceNavigationFragment() : i7 == 5 ? new ScreenCastingFragment() : i7 == 6 ? new LogLinkFragment() : i7 == 7 ? new AdditionalFeaturesFragment() : i7 == 8 ? new MiscSettingsFragment() : i7 == 9 ? new DesktopAssistantFragment() : i7 == 10 ? new CarLinkFragment() : i7 == 11 ? new SceneModeFragment() : i7 == 12 ? new StoreServiceFragment() : new AutoThemeFragment() : new z();
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return 13;
    }

    public void setLicenseMask(int i7) {
        this.licenseMask = i7;
    }
}
