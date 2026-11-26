package com.google.android.material.textfield;

import com.google.android.material.internal.CheckableImageButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends m {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4336e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(l lVar, int i7) {
        super(lVar);
        this.f4336e = i7;
    }

    @Override // com.google.android.material.textfield.m
    public final void r() {
        switch (this.f4336e) {
            case 0:
                l lVar = this.f4381b;
                lVar.f4372o = null;
                CheckableImageButton checkableImageButton = lVar.f4364g;
                checkableImageButton.setOnLongClickListener(null);
                l6.f.y0(checkableImageButton, null);
                break;
        }
    }
}
