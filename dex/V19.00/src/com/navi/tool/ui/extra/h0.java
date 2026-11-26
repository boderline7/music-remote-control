package com.navi.tool.ui.extra;

import android.media.MediaPlayer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h0 implements MediaPlayer.OnCompletionListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5194a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5195b;

    public /* synthetic */ h0(androidx.appcompat.app.r rVar, int i7) {
        this.f5194a = i7;
        this.f5195b = rVar;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        int i7 = this.f5194a;
        androidx.appcompat.app.r rVar = this.f5195b;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity) rVar).lambda$startVideoWallpaper$89(mediaPlayer);
                break;
            default:
                ((FileVideoPreviewActivity) rVar).lambda$preparePlayer$6(mediaPlayer);
                break;
        }
    }
}
