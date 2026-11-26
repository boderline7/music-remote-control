package com.navi.tool.ui.main;

import androidx.fragment.app.d0;
import androidx.fragment.app.z;
import androidx.viewpager2.adapter.g;
import com.navi.tool.ui.cast.ScreenCastingFragment;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import com.navi.tool.ui.theme.Auto3DFragment;
import com.navi.tool.ui.theme.AutoThemeFragment;
import com.navi.tool.ui.theme.TTSFragment;
import com.navi.tool.ui.voice.VoiceNavigationFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SectionsPagerAdapterFiltered extends g {
    private final int[] indices;

    public SectionsPagerAdapterFiltered(d0 d0Var, int[] iArr) {
        super(d0Var);
        this.indices = iArr;
    }

    @Override // androidx.viewpager2.adapter.g
    public z createFragment(int i7) {
        int i8 = this.indices[i7];
        return i8 != 0 ? i8 != 9 ? i8 != 2 ? i8 != 3 ? i8 != 4 ? i8 != 5 ? new ScreenCastingFragment() : new VoiceNavigationFragment() : new j4.g() : new TTSFragment() : new Auto3DFragment() : new DesktopAssistantFragment() : new AutoThemeFragment();
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.indices.length;
    }

    public int getMappedIndex(int i7) {
        return this.indices[i7];
    }
}
