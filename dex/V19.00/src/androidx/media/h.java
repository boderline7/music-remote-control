package androidx.media;

import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class h extends g {

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserServiceCompat f2256f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        super(mediaBrowserServiceCompat);
        this.f2256f = mediaBrowserServiceCompat;
    }

    public final void a() {
        final MediaBrowserServiceCompat mediaBrowserServiceCompat = this.f2256f;
        MediaBrowserServiceCompat$MediaBrowserServiceImplApi23$MediaBrowserServiceApi23 mediaBrowserServiceCompat$MediaBrowserServiceImplApi23$MediaBrowserServiceApi23 = new MediaBrowserServiceCompat$MediaBrowserServiceImplApi23$MediaBrowserServiceApi23(mediaBrowserServiceCompat) { // from class: androidx.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi26$MediaBrowserServiceApi26
            @Override // android.service.media.MediaBrowserService
            public final void onLoadChildren(String str, MediaBrowserService.Result result, Bundle bundle) {
                u.a(bundle);
                h hVar = h.this;
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = hVar.f2256f;
                e eVar = mediaBrowserServiceCompat2.f2228c;
                mediaBrowserServiceCompat2.b();
                hVar.f2256f.getClass();
            }
        };
        this.f2252b = mediaBrowserServiceCompat$MediaBrowserServiceImplApi23$MediaBrowserServiceApi23;
        mediaBrowserServiceCompat$MediaBrowserServiceImplApi23$MediaBrowserServiceApi23.onCreate();
    }
}
