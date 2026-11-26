package l4;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.h;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f7425a;

    public d(e eVar) {
        this.f7425a = eVar;
    }

    @Override // android.support.v4.media.session.h
    public final void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat != null) {
            this.f7425a.h(mediaMetadataCompat);
        }
    }

    @Override // android.support.v4.media.session.h
    public final void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat != null) {
            this.f7425a.i(playbackStateCompat);
        }
    }
}
