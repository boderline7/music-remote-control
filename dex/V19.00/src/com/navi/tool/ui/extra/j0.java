package com.navi.tool.ui.extra;

import android.animation.ValueAnimator;
import android.view.KeyEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j0 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5239a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f5240b;

    public /* synthetic */ j0(KeyEvent.Callback callback, int i7) {
        this.f5239a = i7;
        this.f5240b = callback;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f5239a;
        KeyEvent.Callback callback = this.f5240b;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity) callback).lambda$setWallpaperWithAnimation$94(valueAnimator);
                break;
            default:
                ((GlowProgressView) callback).lambda$setProgress$0(valueAnimator);
                break;
        }
    }
}
