package com.navi.tool.service;

import android.graphics.Bitmap;
import com.navi.tool.service.CarSoundManager;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements CarSoundManager.TriConsumer, DesktopAssistantMusicUiHelper.BitmapCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4858a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4859b;

    public /* synthetic */ e(int i7, Object obj) {
        this.f4858a = i7;
        this.f4859b = obj;
    }

    @Override // com.navi.tool.service.CarSoundManager.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        int i7 = this.f4858a;
        Object obj4 = this.f4859b;
        switch (i7) {
            case 0:
                ((CarSoundManager.AnonymousClass6) obj4).lambda$onChanged$0((String) obj, (Boolean) obj2, (Boolean) obj3);
                break;
            default:
                ((CarSoundManager.AnonymousClass7) obj4).lambda$onChanged$0((String) obj, (String) obj2, (String) obj3);
                break;
        }
    }

    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.BitmapCallback
    public final void onLoaded(Bitmap bitmap) {
        ((MusicIslandService) this.f4859b).lambda$onAlbumArtUpdateEvent$10(bitmap);
    }
}
